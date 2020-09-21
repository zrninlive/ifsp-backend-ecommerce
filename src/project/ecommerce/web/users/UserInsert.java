package project.ecommerce.web.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.UserDao;
import project.ecommerce.model.User;
import java.util.Base64;

@WebServlet(name = "/UserInsert", urlPatterns = "/users/insert")
public class UserInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String encode_password = Base64.getEncoder().encodeToString(password.getBytes());

		userDao.insertUser(new User(name, email, encode_password));
	}

}
