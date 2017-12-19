package sfp.db.properties.oracle;

import sfp.db.properties.DataBaseProperties;

public class Oracle implements DataBaseProperties {

    private final String driverName = "oracle.jdbc.driver.OracleDriver";
    private final String connectionName = "jdbc:oracle:thin:@localhost:1521:db11g";
    private final String login = "system";
    private final String password = "root";

    @Override
    public String getDriverName() {
        return driverName;
    }

    @Override
    public String getURL() {
        return connectionName;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
