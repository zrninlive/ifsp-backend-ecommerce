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

@WebServlet(name = "/CategoryUpdate", urlPatterns = "/categories/update")
public class CategoryUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDao categoryDao;

	public void init() {
		categoryDao = new CategoryDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			updateCategory(request, response);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		categoryDao.updateCategory(new Category(id, name));
	}

}
