package project.ecommerce.web.orders;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.OrderDao;
import project.ecommerce.dao.ProductDao;
import project.ecommerce.dao.UserDao;

@WebServlet(name="/OrderInsert", urlPatterns="/orders/insert")
public class OrderInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UserDao userDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    
    public void init() {
    	userDao = new UserDao();
    	productDao = new ProductDao();
    	orderDao = new OrderDao();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) 
    	throws IOException , ServletException{
    	
    	try {
    		insertOrders(request, response)
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
    private void insertOrders(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException{
    	
    	String produto = request.getParameter("product");
    	String 

    	orderDao.createOrder(new Order());
    }
}
