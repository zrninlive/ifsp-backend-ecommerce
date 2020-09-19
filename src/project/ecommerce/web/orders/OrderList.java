package project.ecommerce.web.orders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import project.ecommerce.dao.CustomerDao;
import project.ecommerce.dao.OrderDao;

@WebServlet(name = "/OrderList", urlPatterns = "/orders")
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao orderDao;
	private CustomerDao customerDao;

	public void init() {
		orderDao = new OrderDao();
		customerDao = new CustomerDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		try {
			String cpf = request.getParameter("cpf");
			String customer = customerDao.findCustomer(cpf);

			if (customer != "") {
				String orders = orderDao.findOrdersByCpf(cpf);

				response.setHeader("Content-Type", "application/json; charset=UTF-8");
				response.getWriter().print(orders);

			} else {
				response.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
