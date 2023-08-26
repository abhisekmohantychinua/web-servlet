package servlets;

import java.io.IOException;
import java.sql.SQLException;

import dao.Connections;
import dao.ProductOperations;
import entities.Alert;
import entities.AlertType;
import entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("userName") != null) {
			req.getRequestDispatcher("add_product.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductOperations productOperations = new ProductOperations(Connections.getConnection());
		Product product = new Product();

		// Get all fields
		product.setId(Integer.parseInt(req.getParameter("id")));
		product.setName(req.getParameter("name"));
		product.setPrice(Integer.parseInt(req.getParameter("price")));
		product.setQuantity(Integer.parseInt(req.getParameter("quantity")));

		// Generating alerts according to operation
		Alert alert = null;
		try {
			// Adding Product to DB
			productOperations.addProduct(product);
			alert = new Alert("alert-success", "Added to database successfully.", AlertType.SUCCESS);

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
		HttpSession session = req.getSession();
		session.setAttribute("alert", alert);

		resp.sendRedirect("add-product");
	}
}
