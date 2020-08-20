package project.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.ecommerce.model.Product;
import project.ecommerce.utils.JDBCUtils;

public class ProductDao {

	// define sql statments
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" 
	+ "(category_id, title, quantity, description, price, image) VALUES"
			+ "(?, ?, ?, ?, ? , ?);";

	private static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT * from products WHERE category_id = ?;";

	private static final String SELECT_ALL_PRODUCTS = "Select * from products;";

	private static final String DELETE_PRODUCT = "DELETE from products where id = ? ;";

	private static final String UPDATE_PRODUCT = 
			"UPDATE products set title=?, quantity=?, description=?, price=?, image=? WHERE id=?;" ;
	
	

	public List<Product> selectAllProducts() {
		List<Product> products = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCTS)) {
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				int category_id = result.getInt("category_id");
				String title = result.getString("title");
				int quantity = result.getInt("quantity");
				String description = result.getString("description");
				double price = result.getDouble("price");
				String image = result.getString("image");
				products.add(new Product(id, category_id, title, quantity, description, price, image));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public List<Product> selectProduct(int category_id) {
		List<Product> products = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_BY_CATEGORY)) {
			ps.setInt(1, category_id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String title = result.getString("title");
				int quantity = result.getInt("quantity");
				String description = result.getString("description");
				double price = result.getDouble("price");
				String image = result.getString("image");
				products.add(new Product(id,category_id, title,quantity,description,price,image));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public void insertProduct(Product product) throws SQLException {

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
			ps.setInt(1, product.getCategory_id());
			ps.setString(2, product.getTitle());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getDescription());
			ps.setDouble(5, product.getPrice());
			ps.setString(6, product.getImage());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateProduct(Product product) throws SQLException {
		boolean productUpdated = false;
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT)) {
			ps.setString(1, product.getTitle());
			ps.setInt(2, product.getQuantity());
			ps.setString(3, product.getDescription());
			ps.setDouble(4, product.getPrice());
			ps.setString(5, product.getImage());
			ps.setInt(6, product.getId());			
			
			productUpdated = ps.executeUpdate() > 0;
		}
		return productUpdated;
	}

	public boolean deleteProduct(int id) throws SQLException {
		boolean productDeleted = false;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT)) {
			ps.setInt(1, id);
			productDeleted = ps.executeUpdate() > 0;
		}
		return productDeleted;
	}
}
