package project.ecommerce.web.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.ecommerce.dao.UserDao;
import project.ecommerce.model.User;

@WebServlet(name = "/UserInsert", urlPatterns = "/users/insert")
public class UserInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void init() {
		userDao = new UserDao();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			insertUsers(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String cpf = request.getParameter("cpf");
		String city = request.getParameter("city");

		userDao.insertUser(new User(name, cpf, city));
	}

}
