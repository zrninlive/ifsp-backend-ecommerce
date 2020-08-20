package project.ecommerce.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/db_ecommerce_sql?useTimezone=true&serverTimezone=UTC";
	private static final String jdbcUsername = "root";
	private static final String jdbcPassword = "";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return connection;
	}
}
