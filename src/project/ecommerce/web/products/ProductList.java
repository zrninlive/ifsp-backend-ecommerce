package project.ecommerce.web.products;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import project.ecommerce.dao.ProductDao;
import project.ecommerce.model.Product;

@WebServlet(name = "ProductList", urlPatterns = "/products")
public class ProductList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	public void init() {
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		try {
			// list all products
			List<Product> products = new ArrayList<>();

			if (request.getParameter("category_id") == null || request.getParameter("category_id") == "") {
				products = productDao.selectAllProducts();

			} else {
				int id = Integer.parseInt(request.getParameter("category_id"));

				products = (List<Product>) productDao.selectProduct(id);

				if (products == null) {
					System.out.println("Nenhum Produto encontrado");
				}

			}
			String productsJson = new Gson().toJson(products);

			response.setHeader("Content-Type", "application/json; charset=UTF-8");
			response.getWriter().print(productsJson);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
