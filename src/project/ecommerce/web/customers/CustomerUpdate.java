package project.ecommerce.web.customers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.CustomerDao;


@WebServlet(name = "/CustomerUpdate", urlPatterns = "/customers/update")
public class CustomerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CustomerDao customerDao;

	public void init() {
		customerDao = new CustomerDao();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			updateCustomer(request, response);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

	}
	private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		
		}

}
