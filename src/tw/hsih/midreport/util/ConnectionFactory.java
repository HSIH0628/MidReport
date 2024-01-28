package tw.hsih.midreport.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {
		final String SQL_URL = "jdbc:sqlserver://localhost:1433;database=MidReport;user=watcher;password=P@ssw0rd;encrypt=false;";

		Connection conn = DriverManager.getConnection(SQL_URL);

		return conn;
	}
}
