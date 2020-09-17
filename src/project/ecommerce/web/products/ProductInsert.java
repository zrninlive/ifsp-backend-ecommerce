package project.ecommerce.web.products;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import project.ecommerce.dao.ProductDao;
import project.ecommerce.model.Product;

@WebServlet(name = "ProductInsert", urlPatterns = "/products/insert")
public class ProductInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	public void init() {
		productDao = new ProductDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			insertProducts(request, response);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	private void insertProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		

		StringBuffer sb = new StringBuffer();
		String linha = null;
		
		BufferedReader reader = request.getReader();
		
		while((linha = reader.readLine()) != null) {
			sb.append(linha);
		}
		
		Product prod = new Product();
		Gson gson = new Gson();
		String json = gson.toJson(prod);  
		
		System.out.println(json);
		try {
			Product jsonObj = new Gson().fromJson(sb.toString(), Product.class);
		}catch(JsonSyntaxException e)  {
			e.printStackTrace();
		}
		
		
		//productDao.insertProduct(produto);
		//productDao.insertProduct(new Product(category_id, title, quantity, description, price, image));
		
		/*
		 * 
		 * 
		
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String title = request.getParameter("title");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		String image = (String) request.getParameter("image");
		System.out.println(title);*/
	}

}
