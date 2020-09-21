package project.ecommerce.web.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.UserDao;

@WebServlet(name = "/UserList", urlPatterns = "/users")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		try {
			String users = userDao.findUsers();

			response.setHeader("Content-Type", "application/json; charset=UTF-8");
			response.getWriter().print(users);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
