package tw.hsih.midreport.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class test {
	public static void main(String[] args) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=Hsih;user=watcher;password=P@ssw0rd;encrypt=true;trustServerCertificate=true";
		Connection conn = DriverManager.getConnection(urlstr);
		System.out.println("Connection Status:" + !conn.isClosed());
		conn.close();
	}

}
