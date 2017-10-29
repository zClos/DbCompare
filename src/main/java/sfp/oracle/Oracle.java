package sfp.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
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
public class Oracle {
	static private String driverName = "oracle.jdbc.driver.OracleDriver";
	/* Check port-number */
	static private String connectionName = "jdbc:oracle:thin:@localhost:1521:XE";
	Connection connection;
	Statement statement;
	
	@Setup
	public void prepare() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(driverName);
		Locale.setDefault(Locale.ENGLISH);
		connection = DriverManager.getConnection(connectionName, MyBenchmark.ROOT, MyBenchmark.PASSWORD);
		statement = connection.createStatement();
		/* Oracle g11 doesn't support 'drop if exists' syntax  */
		statement.execute("BEGIN EXECUTE IMMEDIATE 'DROP TABLE SERVICE_PROVIDER'; EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF; END;");
		statement.execute("CREATE TABLE SERVICE_PROVIDER(ID NUMBER PRIMARY KEY, NAME VARCHAR2(30), CITY VARCHAR2(30), STREET VARCHAR2(30), BUILDING VARCHAR2(4), BLOCK VARCHAR2(3), PHONE VARCHAR2(13), EMAIL VARCHAR2(30), SITE VARCHAR2(30))");
		for (int i = 0; i < MyBenchmark.ROW_COUNT; i++) {
			statement.execute(MyBenchmark.insertQuery(i));
		}
	}
	
	@TearDown
	public void disconnect() throws SQLException {
		statement.execute("DROP TABLE SERVICE_PROVIDER");
		connection.close();
	}
	
	@Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public ResultSet testMethod() throws SQLException, ClassNotFoundException {
		ResultSet resultSet = statement.executeQuery("SELECT * FROM SERVICE_PROVIDER WHERE NAME = 'NAME" + Math.round(Math.random() * MyBenchmark.ROW_COUNT) + "'");
		return resultSet;
    }
}
