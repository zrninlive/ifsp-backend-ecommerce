package project.ecommerce.web.customers;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.CustomerDao;
import project.ecommerce.model.Customer;

@WebServlet(name = "/CustomerInsert", urlPatterns = "/customers/insert")
public class CustomerInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDao customerDao;

	public void init() {
		customerDao = new CustomerDao();
	}

	protected void doPo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			insertCustomers(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertCustomers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cpf = request.getParameter("cpf");
		String phone = request.getParameter("phone");

		String zipcode = request.getParameter("zipcode");
		String street = request.getParameter("street");
		String number = request.getParameter("number");
		String city = request.getParameter("city");
		String state = request.getParameter("state");

		String encode_password = Base64.getEncoder().encodeToString(password.getBytes());

		customerDao.insertCustomer(
				new Customer(name, email, encode_password, cpf, phone, zipcode, street, number, city, state));
	}
}
