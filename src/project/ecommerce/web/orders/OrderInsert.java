package project.ecommerce.web.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;

import project.ecommerce.dao.CustomerDao;
import project.ecommerce.dao.OrderDao;
import project.ecommerce.dao.ProductDao;
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

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		Order order = new Order();

		String customer_cpf = request.getParameter("customer_cpf");
		
		System.out.println(customer_cpf);
		
		int total_products = Integer.parseInt(request.getParameter("total_products"));
		List<Product> orderProducts = new ArrayList<>();
		Double total = 0.0;

		try {
			String customer_json = customerDao.findCustomer(customer_cpf);

			Customer customer = gson.fromJson(customer_json, Customer.class);

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

			List<Document> docProducts = new ArrayList<>();

			for (Product element : orderProducts) {
				productDao.updateProductStock(element.getId(), element.getQuantity());

				Document docProduct = new Document();
				docProduct.append("id", element.getId());
				docProduct.append("title", element.getTitle());
				docProduct.append("quantity", element.getQuantity());
				docProduct.append("price", element.getPrice());
				docProduct.append("image", element.getImage());
				docProduct.append("total", (element.getQuantity() * element.getPrice()));

				docProducts.add(docProduct);
			}

			Document docCustomer = new Document();
			docCustomer.append("name", customer.getName());
			docCustomer.append("cpf", customer.getCpf());
			docCustomer.append("email", customer.getEmail());
			docCustomer.append("phone", customer.getPhone());

			Document docCustomerAddress = new Document();
			docCustomerAddress.append("zipcode", customer.getZipcode());
			docCustomerAddress.append("street", customer.getStreet());
			docCustomerAddress.append("number", customer.getNumber());
			docCustomerAddress.append("city", customer.getCity());
			docCustomerAddress.append("state", customer.getState());

			docCustomer.append("address", docCustomerAddress);

			orderDao.insertOrder(docProducts, docCustomer, total);

			String orderJson = gson.toJson(order);
			response.getWriter().print(orderJson);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			response.setStatus(400);
			response.getWriter().print(new Gson().toJson(e.getMessage()));
		}
	}

}
