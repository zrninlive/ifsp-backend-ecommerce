package project.ecommerce.web.customers;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.CustomerDao;

@WebServlet(name = "/CustomerLogin", urlPatterns = "/customers/login")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDao customerDao;

	public void init() {
		customerDao = new CustomerDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			System.out.println(password);
			String encode_password = Base64.getEncoder().encodeToString(password.getBytes());

			String customer = customerDao.findCustomerByEmail(email, encode_password);

			if (customer.isEmpty()) {
				response.setStatus(401);
			}

			response.getWriter().print(customer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
