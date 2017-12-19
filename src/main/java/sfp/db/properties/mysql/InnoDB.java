package sfp.db.properties.mysql;

import sfp.db.properties.DataBaseProperties;

public class InnoDB implements DataBaseProperties {

    private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/test";
    private final String LOGIN = "root";
    private final String PASSWORD = "12345";

    @Override
    public String getDriverName() {
        return DRIVER_NAME;
    }

    @Override
    public String getURL() {
        return URL;
    }

    @Override
    public String getLogin() {
        return LOGIN;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

}
