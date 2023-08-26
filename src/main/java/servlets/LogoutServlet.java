package servlets;

import java.io.IOException;

import entities.Alert;
import entities.AlertType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("userName");
		Alert alert = new Alert("alert-success", "Logged out successfully!", AlertType.SUCCESS);
		session.setAttribute("alert", alert);
		resp.sendRedirect("login");
	}

}
