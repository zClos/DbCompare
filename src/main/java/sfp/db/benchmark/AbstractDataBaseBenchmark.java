package sfp.db.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import sfp.db.JdbcConnectionFactory;
import sfp.db.properties.DataBaseProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 30, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public abstract class AbstractDataBaseBenchmark {

    private Connection connection;
    protected Statement statement;

    /**
     * Override this query if your data base doesn't support this query
     */
    protected String dropQuery = "DROP TABLE IF EXISTS SERVICE_PROVIDER";

    /**
     * Override this query if your data base doesn't support this query
     */
    protected String createQuery =
            "CREATE TABLE SERVICE_PROVIDER(" +
                    "ID BIGINT PRIMARY KEY," +
                    " NAME VARCHAR(30)," +
                    " CITY VARCHAR(30)," +
                    " STREET VARCHAR(30)," +
                    " BUILDING VARCHAR(4)," +
                    " BLOCK VARCHAR(3)," +
                    " PHONE VARCHAR(13)," +
                    " EMAIL VARCHAR(30)," +
                    " SITE VARCHAR(30))";

    private String selectQuery = "SELECT * FROM SERVICE_PROVIDER WHERE NAME = 'NAME";

    private String insertQuery(int i) {
        return "INSERT INTO SERVICE_PROVIDER(ID, NAME, CITY, STREET, BUILDING, BLOCK, "
                + "PHONE, EMAIL, SITE) VALUES(" + i + ", 'NAME" + i + "', 'CITY" + i + "', "
                + "'STREET" + i + "', 'B', 'BL', 'PHONE" + i + "', 'EMAIL" + i + "', "
                + "'SITE" + i + "')";
    }

    /**
     * Sets default size of the test table
     */
    private final int defaultTableRows = 100_000;
    private int rowIndex = 1;


    /**
     * Implementation of this method must contain overriding queries of drop and create table
     * After all changes you must call prepare(DataBaseProperties dataBaseProperties) method of super class.
     * Put your implementation of DataBaseProperties as parameter
     */
    @Setup
    public abstract void prepare() throws Exception;


    protected Statement prepare(DataBaseProperties dataBaseProperties) throws Exception {
        connection = JdbcConnectionFactory.getConnection(dataBaseProperties);
        statement = connection.createStatement();
        statement.execute(dropQuery);
        statement.execute(createQuery);
        for (rowIndex = 1; rowIndex < defaultTableRows; rowIndex++) {
            statement.execute(insertQuery(rowIndex));
        }
        return statement;
    }


    @Benchmark
    public boolean insertQueryTest() throws SQLException {
        return statement.execute(insertQuery(rowIndex++));
    }

    @Benchmark
    public boolean insertQueryWithConsumeCpuTest() throws SQLException {
        Blackhole.consumeCPU(1000);
        return statement.execute(insertQuery(rowIndex++));
    }

    /**
     * Override this to customize your own benchmark of select query
     */
    @Benchmark
    public ResultSet randomSelect() throws SQLException {
        return statement.executeQuery(selectQuery + (int) (Math.random() * defaultTableRows) + '\'');
    }

    @Benchmark
    @Threads(30)
    public ResultSet randomSelectMultithreading() throws SQLException {
        return statement.executeQuery(selectQuery + (int) (Math.random() * defaultTableRows) + '\'');
    }

    @TearDown
    public void closeConnections() throws SQLException {
        statement.execute(dropQuery);
        connection.close();
    }
}
