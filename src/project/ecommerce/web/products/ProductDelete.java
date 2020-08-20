package project.ecommerce.web.products;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.ProductDao;

@WebServlet(name = "ProductDelete", urlPatterns = "/products/delete")
public class ProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	public void init() {
		productDao = new ProductDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			deleteProduct(request, response);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDao.deleteProduct(id);
		System.out.println("Produto deletado com sucesso");
	}
}
