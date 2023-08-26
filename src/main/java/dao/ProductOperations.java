package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class ProductOperations {
	private Connection connection;

	public ProductOperations(Connection connection) {
		super();
		this.connection = connection;
	}

	public Product addProduct(Product product) throws Exception {

		PreparedStatement preparedStatement = connection.prepareStatement("insert into products values(?,?,?,?);");
		preparedStatement.setInt(1, product.getId());
		preparedStatement.setString(2, product.getName());
		preparedStatement.setInt(3, product.getPrice());
		preparedStatement.setInt(4, product.getQuantity());
		preparedStatement.executeUpdate();
		return product;

	}

	public List<Product> getAllProduct() throws Exception {
		List<Product> products = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from products;");
		while (resultSet.next()) {
			Product product = new Product();
			product.setId(resultSet.getInt("id"));
			product.setName(resultSet.getString("name"));
			product.setPrice(resultSet.getInt("price"));
			product.setQuantity(resultSet.getInt("quantity"));
			products.add(product);
		}

		return products;
	}
}
