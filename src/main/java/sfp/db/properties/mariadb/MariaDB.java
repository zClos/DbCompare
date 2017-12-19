package sfp.db.properties.mariadb;

import sfp.db.properties.DataBaseProperties;

public class MariaDB implements DataBaseProperties {

	private final String DRIVER_NAME = "org.mariadb.jdbc.Driver";
	private final String URL = "jdbc:mariadb://localhost:4444/test";
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
