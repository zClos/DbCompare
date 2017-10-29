package sfp;

import java.sql.SQLException;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import sfp.h2.H2;
import sfp.mariadb.MariaDB;
import sfp.mysql.InnoDB;
import sfp.mysql.MyISAM;
import sfp.oracle.Oracle;
import sfp.postgresql.PostgreSql;

public class MyBenchmark {

	/* Check root value */
	public static String ROOT = "root";
	/* Check password */
	public static String PASSWORD = "root";
	public static String DROP_QUERY = "DROP TABLE IF EXISTS SERVICE_PROVIDER";
	public static String CREATE_QUERY = "CREATE TABLE SERVICE_PROVIDER(ID BIGINT PRIMARY KEY, NAME VARCHAR(30), CITY VARCHAR(30), STREET VARCHAR(30), BUILDING VARCHAR(4), BLOCK VARCHAR(3), PHONE VARCHAR(13), EMAIL VARCHAR(30), SITE VARCHAR(30))";
	/* Vary to change table rows value */
	public static int ROW_COUNT = 100000;
	
	public static String insertQuery(int i) {
		return "INSERT INTO SERVICE_PROVIDER(ID, NAME, CITY, STREET, BUILDING, BLOCK, "
				+ "PHONE, EMAIL, SITE) VALUES("	+ i + ", 'NAME" + i + "', 'CITY" + i + "', "
				+ "'STREET" + i + "', 'B', 'BL', 'PHONE" + i + "', 'EMAIL" + i + "', "
				+ "'SITE" + i + "')";
	}
	
	public static void main(String[] args) throws RunnerException, ClassNotFoundException, SQLException {
				
		Options opt = new OptionsBuilder()
				/* Remove unnecessary databases */
                .include(H2.class.getSimpleName())
                .include(MyISAM.class.getSimpleName())
                .include(InnoDB.class.getSimpleName())
                .include(MariaDB.class.getSimpleName())
                .include(PostgreSql.class.getSimpleName())
				.include(Oracle.class.getSimpleName())
                /* Oracle needs more than 10 warmupItarations */
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
	}
}
