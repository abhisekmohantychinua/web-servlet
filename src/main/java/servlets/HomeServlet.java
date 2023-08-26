package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Connections;
import dao.ProductOperations;
import entities.Alert;
import entities.AlertType;
import entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("userName") != null) {
			ProductOperations productOperations = new ProductOperations(Connections.getConnection());
			List<Product> products = null;

			// Generating alerts according to operation
			Alert alert = null;
			try {
				products = productOperations.getAllProduct();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				alert = new Alert("alert-danger", "Some sql exception occured! Cannot added to Database.",
						AlertType.ERROR);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				alert = new Alert("alert-danger", "Some unhandled exception occured.", AlertType.ERROR);
			}

			req.setAttribute("products", products);

			// Adding alerts to session
			session.setAttribute("alert", alert);

			req.getRequestDispatcher("home.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}

	}
}
