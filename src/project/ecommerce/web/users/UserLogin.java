package project.ecommerce.web.users;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.UserDao;

@WebServlet(name = "/UserLogin", urlPatterns = "/users/login")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			String encode_password = Base64.getEncoder().encodeToString(password.getBytes());

			String user = userDao.findUserByEmail(email, encode_password);

			if (user.isEmpty()) {
				response.setStatus(401);
			}

			response.getWriter().print(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
