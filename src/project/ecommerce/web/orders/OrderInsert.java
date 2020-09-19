package project.ecommerce.web.orders;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;

import project.ecommerce.dao.CustomerDao;
import project.ecommerce.dao.OrderDao;
import project.ecommerce.dao.ProductDao;
import project.ecommerce.dao.UserDao;
import project.ecommerce.model.Customer;
import project.ecommerce.model.Order;
import project.ecommerce.model.Product;

@WebServlet(name = "/OrderInsert", urlPatterns = "/orders/insert")
public class OrderInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao orderDao;
	private ProductDao productDao;
	private CustomerDao customerDao;

	public void init() {
		orderDao = new OrderDao();
		productDao = new ProductDao();
		customerDao = new CustomerDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setHeader("Content-Type", "application/json; charset=UTF-8");
		Gson gson = new Gson();
		Order order = new Order();

		String customer_cpf = request.getParameter("customer[cpf]");

		int total_products = Integer.parseInt(request.getParameter("total_products"));
		List<Product> orderProducts = new ArrayList<>();
		Double total = 0.0;

		try {
			String customer_json = customerDao.findCustomer(customer_cpf);

			Customer customer = gson.fromJson(customer_json, Customer.class);

			System.out.println(customer);

			for (int i = 0; i < total_products; i++) {
				int product_id = Integer.parseInt(request.getParameter("products[" + i + "][id]"));
				int order_quantity = Integer.parseInt(request.getParameter("products[" + i + "][quantity]"));

				Product order_product = productDao.selectProductById(product_id);

				if (order_product.getId() == 0) {
					throw new Exception("PRODUCT ID " + product_id + " NOT FOUND");
				}

				if (order_product.getQuantity() < order_quantity) {
					throw new Exception("NO STOCK FOR " + order_product.getTitle());
				}

				order_product.setQuantity(order_quantity);

				total += order_product.getPrice() * order_quantity;

				orderProducts.add(order_product);

			}

			for (Product element : orderProducts) {
				productDao.updateProductStock(element.getId(), element.getQuantity());
			}

			order = new Order(orderProducts, customer, total);

			String orderJson = gson.toJson(order);
			response.getWriter().print(orderJson);

		} catch (Exception e) {
			response.setStatus(400);
			response.getWriter().print(new Gson().toJson(e.getMessage()));
			e.printStackTrace();
		}
	}

}
