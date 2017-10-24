package sfp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import sfp.MyBenchmark;

@State(Scope.Thread)
public class MyISAM {
	static private String driverName = "com.mysql.jdbc.Driver";
	static private String connectionName = "jdbc:mysql://localhost:3306/test";
	Connection connection;
	Statement statement;
	
	@Setup
	public void prepare() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driverName).newInstance();
		connection = DriverManager.getConnection(connectionName, MyBenchmark.ROOT, MyBenchmark.PASSWORD);
		statement = connection.createStatement();
		statement.execute(MyBenchmark.DROP_QUERY);
		statement.execute(MyBenchmark.CREATE_QUERY + " ENGINE = MYISAM");
		for (int i = 0; i < MyBenchmark.ROW_COUNT; i++) {
			statement.execute(MyBenchmark.insertQuery(i));
		}
	}
	
	@TearDown
	public void disconnect() throws SQLException {
		statement.execute(MyBenchmark.DROP_QUERY);
		connection.close();
	}
	
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testMethod() throws SQLException, ClassNotFoundException {
		statement.executeQuery("SELECT * FROM SERVICE_PROVIDER WHERE NAME = 'NAME" + Math.round(Math.random() * MyBenchmark.ROW_COUNT) + "'");
    }
}
