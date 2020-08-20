package project.ecommerce.web.categories;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.CategoryDao;

@WebServlet(name = "/CategoryDelete", urlPatterns = "/categories/delete")
public class CategoryDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDao categoryDao;

	public void init() {
		categoryDao = new CategoryDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			deleteCategory(request, response);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		System.out.println("delete");
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDao.deleteCategory(id);
	}

}
