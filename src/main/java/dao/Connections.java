package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connections {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adv_java_collage", "root",
						"root123");
				System.out.println("Connection created !");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
