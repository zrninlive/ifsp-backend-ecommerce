package project.ecommerce.web.orders;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

import project.ecommerce.dao.CustomerDao;
import project.ecommerce.dao.OrderDao;


@WebServlet(name="/OrderList", urlPatterns="/orders")
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private OrderDao orderDao;
	private CustomerDao customerDao;
	
	public void init() {
		orderDao = new OrderDao();
		customerDao = new CustomerDao();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			listOrders(request, response);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String cpf = request.getParameter("cpf");		
		String customer = customerDao.findCustomer(cpf);
		
		if(customer != "") {
			String orders = orderDao.findOrdersByCpf(cpf);
			
				response.setContentType("application/json");
				response.getWriter().print(orders);
			
		}else {
			response.setStatus(404);
		}
	}

}
