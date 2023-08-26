package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.User;

public class UserOperations {
	private Connection connection;

	public UserOperations(Connection connection) {
		super();
		this.connection = connection;
	}

	public boolean LoginUser(User user) throws Exception {

		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from users where name=? and password=?");
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();

	}
}
