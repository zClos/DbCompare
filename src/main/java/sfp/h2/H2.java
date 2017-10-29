package sfp.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class H2 {
	static private String driverName = "org.h2.Driver";
	static private String connectionName = "jdbc:h2:~/test";
	Connection connection;
	Statement statement;
	
	@Setup
	public void prepare() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		connection = DriverManager.getConnection(connectionName, MyBenchmark.ROOT, MyBenchmark.PASSWORD);
		statement = connection.createStatement();
		statement.execute(MyBenchmark.DROP_QUERY);
		statement.execute(MyBenchmark.CREATE_QUERY);
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
    public ResultSet testMethod() throws SQLException, ClassNotFoundException {
		ResultSet resultSet = statement.executeQuery("SELECT * FROM SERVICE_PROVIDER WHERE NAME = 'NAME" + Math.round(Math.random() * MyBenchmark.ROW_COUNT) + "'");
		return resultSet;
	}
}
