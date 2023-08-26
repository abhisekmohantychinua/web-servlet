package servlets;

import java.io.IOException;
import java.sql.SQLException;

import dao.Connections;
import dao.UserOperations;
import entities.Alert;
import entities.AlertType;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("userName") != null) {
			resp.sendRedirect("home");
		} else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserOperations userOperations = new UserOperations(Connections.getConnection());

		// Get user credentials
		String userName = req.getParameter("name");
		String userPassword = req.getParameter("password");
		User user = new User(userName, userPassword);

		// Authenticating user
		HttpSession session = req.getSession();

		// Generating alerts according to operation
		Alert alert = null;
		try {
			if (userOperations.LoginUser(user)) {
				session.setAttribute("userName", userName);
				resp.sendRedirect("home");
				return;
			} else {
				alert = new Alert("alert-danger", "Invalid user or password.", AlertType.ERROR);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert = new Alert("alert-danger", "Some sql exception occured! Cannot added to Database.", AlertType.ERROR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alert = new Alert("alert-danger", "Some unhandled exception occured.", AlertType.ERROR);
		}

		// Adding alerts to session
		session.setAttribute("alert", alert);

		resp.sendRedirect("login");
	}

}
