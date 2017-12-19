package sfp.db.properties.postgresql;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import sfp.db.properties.DataBaseProperties;

@State(Scope.Thread)
public class PostgreSql implements DataBaseProperties {

    private final String DRIVER_NAME = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
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
