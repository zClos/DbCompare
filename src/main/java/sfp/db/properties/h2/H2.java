package sfp.db.properties.h2;

import sfp.db.properties.DataBaseProperties;

public class H2 implements DataBaseProperties {

    private final String DRIVER_NAME = "org.h2.Driver";
    private final String URL = "jdbc:h2:~/test";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";

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
