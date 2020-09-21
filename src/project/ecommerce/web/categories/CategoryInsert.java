package project.ecommerce.web.categories;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.CategoryDao;
import project.ecommerce.model.Category;

@WebServlet(name = "CategoryInsert", urlPatterns = "/categories/insert")
public class CategoryInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDao categoryDao;

	public void init() {
		categoryDao = new CategoryDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		try {
			String name = request.getParameter("name");
			categoryDao.insertCategory(new Category(name));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
