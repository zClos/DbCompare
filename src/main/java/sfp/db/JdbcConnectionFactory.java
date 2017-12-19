package sfp.db;

import sfp.db.properties.DataBaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnectionFactory {

    public static Connection getConnection(DataBaseProperties dataBaseProperties) throws Exception {
        Class.forName(dataBaseProperties.getDriverName());
        return DriverManager.getConnection(
                dataBaseProperties.getURL(),
                dataBaseProperties.getLogin(),
                dataBaseProperties.getPassword()
        );
    }
}
