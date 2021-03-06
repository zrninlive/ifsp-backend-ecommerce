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
			+ "(category_id, title, quantity, description, highlight, price, image) VALUES" + "(?, ?, ?, ?, ? , ?);";

	private static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT * from products WHERE category_id = ?;";

	private static final String SELECT_PRODUCT_BY_ID = "SELECT * from products WHERE id = ?;";

	private static final String SELECT_ALL_PRODUCTS = "Select * from products;";

	private static final String DELETE_PRODUCT = "DELETE from products where id = ? ;";

	private static final String UPDATE_PRODUCT = "UPDATE products set title=?, quantity=?, description=?, highlight=? ,price=?, image=? WHERE id=?;";

	private static final String UPDATE_PRODUCT_STOCK = "UPDATE products set quantity=? WHERE id=?;";

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
				boolean highlight = result.getBoolean("highlight");
				double price = result.getDouble("price");
				String image = result.getString("image");
				products.add(new Product(id, category_id, title, quantity, description, highlight, price, image));
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
				boolean highlight = result.getBoolean("highlight");
				double price = result.getDouble("price");
				String image = result.getString("image");
				products.add(new Product(id, category_id, title, quantity, description, highlight, price, image));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public Product selectProductById(int id) {

		Product product = new Product();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				product.setId(result.getInt("id"));
				product.setCategory_id(result.getInt("category_id"));
				product.setTitle(result.getString("title"));
				product.setQuantity(result.getInt("quantity"));
				product.setDescription(result.getString("description"));
				product.setHighlight(result.getBoolean("highlight"));
				product.setPrice(result.getDouble("price"));
				product.setImage(result.getString("image"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	public void insertProduct(Product product) throws SQLException {

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
			ps.setInt(1, product.getCategory_id());
			ps.setString(2, product.getTitle());
			ps.setInt(3, product.getQuantity());
			ps.setString(4, product.getDescription());
			ps.setBoolean(5, product.getHighlight());
			ps.setDouble(6, product.getPrice());
			ps.setString(7, product.getImage());
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
			ps.setBoolean(4, product.getHighlight());
			ps.setDouble(5, product.getPrice());
			ps.setString(6, product.getImage());
			ps.setInt(7, product.getId());

			productUpdated = ps.executeUpdate() > 0;
		}
		return productUpdated;
	}

	public boolean updateProductStock(int id, int quantity_order) throws SQLException {
		boolean productUpdated = false;

		Product product = this.selectProductById(id);

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT_STOCK)) {
			ps.setInt(1, (product.getQuantity() - quantity_order));
			ps.setInt(2, id);

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
