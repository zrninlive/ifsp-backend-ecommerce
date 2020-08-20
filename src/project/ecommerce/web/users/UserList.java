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
		try {
			listUsers(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String users = userDao.findUsers();

		response.setContentType("application/json");
		response.getWriter().print(users);

	}

}
