package project.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.ecommerce.utils.JDBCUtils;
import project.ecommerce.model.Category;

public class CategoryDao {

	private static final String INSERT_CATEGORIES_SQL = "INSERT INTO categories"
			+ "(name) VALUES" + "(?);";

	private static final String SELECT_CATEGORY_BY_ID = "SELECT id, name from categories WHERE id = ? ;";

	private static final String SELECT_ALL_CATEGORIES = "SELECT * from categories";

	private static final String DELETE_CATEGORY = "DELETE from categories where id = ?;";

	private static final String UPDATE_CATEGORY = "UPDATE categories set name=? WHERE id=? ;";

	public void insertCategory(Category category) throws SQLException {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORIES_SQL)) {
			ps.setString(1, category.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Category selectCategory(int id) {

		Category category = null;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String name = result.getString("name");

				category = new Category(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	public List<Category> selectAllCategories() {

		List<Category> categories = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
			ResultSet result = ps.executeQuery();

			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				categories.add(new Category(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public boolean deleteCategory(int id) throws SQLException {
		boolean categoryDeleted = false;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORY)) {
			ps.setInt(1, id);
			categoryDeleted = ps.executeUpdate() > 0;
		}
		return categoryDeleted;
	}

	public boolean updateCategory(Category category) throws SQLException {
		boolean categoryUpdated = false;

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_CATEGORY)) {
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());

			categoryUpdated = ps.executeUpdate() > 0;
		}

		return categoryUpdated;
	}

}
