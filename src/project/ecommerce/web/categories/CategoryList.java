package project.ecommerce.web.categories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import project.ecommerce.dao.CategoryDao;
import project.ecommerce.model.Category;

@WebServlet(name = "CategoryList", urlPatterns = "/categories")
public class CategoryList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDao categoryDao;

	public void init() {
		categoryDao = new CategoryDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			listCategory(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void listCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Category> categories = new ArrayList<>();
		categories = categoryDao.selectAllCategories();

		String categoriesJson = new Gson().toJson(categories);

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json; charset=UTF-8");	
		response.getWriter().print(categoriesJson);

	}

}
