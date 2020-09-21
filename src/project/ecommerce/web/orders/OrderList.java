package project.ecommerce.web.orders;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json; charset=UTF-8");

		try {
			String cpf = request.getParameter("cpf");
			String customer = customerDao.findCustomer(cpf);

			if (customer != "") {
				String orders = orderDao.findOrdersByCpf(cpf);

				response.getWriter().print(orders);

			} else {
				response.setStatus(400);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
