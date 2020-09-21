package project.ecommerce.web.products;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.ProductDao;
import project.ecommerce.model.Product;

@WebServlet(name = "ProductInsert", urlPatterns = "/products/insert")
public class ProductInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	public void init() {
		productDao = new ProductDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			int category_id = Integer.parseInt(request.getParameter("category_id"));
			String title = request.getParameter("title");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String description = request.getParameter("description");
			boolean highlight = Boolean.parseBoolean(request.getParameter("highlight"));
			Double price = Double.parseDouble(request.getParameter("price"));
			String image = (String) request.getParameter("image");

			productDao.insertProduct(new Product(category_id, title, quantity, description, highlight, price, image));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
