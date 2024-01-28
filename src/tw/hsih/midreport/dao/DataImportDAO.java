package tw.hsih.midreport.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataImportDAO {

	private static final String SQL_URL = "jdbc:sqlserver://localhost:1433;database=MidReport;user=watcher;password=P@ssw0rd;encrypt=false;";//不使用 SSL/TLS 加密

	public void insertDataFromCsv(String csvFilePath) {
		try (Connection connection = DriverManager.getConnection(SQL_URL)) {
			insertData(connection, csvFilePath);
			System.out.println("Data imported successfully!");
		} catch (SQLException | IOException e) {
			System.out.println("Error importing data: " + e.getMessage());
		}
	}

	public void insertData(Connection connection, String csvFilePath) throws SQLException, IOException {
		// SQL
		String insertSql = "INSERT INTO Member (CompanyName, Address, Phone) VALUES ( ?, ?, ?)";

		try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
				PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

			// 第一行不顯示
			reader.readLine();

			String line;
			while ((line = reader.readLine()) != null) {
				// 按照逗號分開
				String[] data = line.split(",");

				// 參數
				// preparedStatement.setInt(1, Integer.parseInt(data[0].trim())); // ID
				preparedStatement.setString(1, data[1].trim()); // CompanyName 公司名稱
				preparedStatement.setString(2, data[2].trim()); // Address 住址
				preparedStatement.setString(3, data[3].trim()); // Phone 電話

				// UPDATE
				preparedStatement.executeUpdate();
			}
		}
	}
}
