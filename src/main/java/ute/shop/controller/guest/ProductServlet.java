package ute.shop.controller.guest;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.config.JPAConfig;
import ute.shop.dao.guest.implement.CategoryDAO;
import ute.shop.dao.guest.implement.ProductDAO;
import ute.shop.entity.Product;
import ute.shop.services.guest.implement.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home/productsByCategory"})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService;

   
    public ProductServlet() {
        EntityManager em = JPAConfig.getEntityManager();
        productService = new ProductService(new ProductDAO(em), new CategoryDAO(em));
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
        if (categoryId != null) {
            List<Product> products = productService.getProductsByCategory(Integer.parseInt(categoryId));
            request.setAttribute("products", products);
			/*
			 * request.getRequestDispatcher("/views/guest/productList.jsp").forward(request,
			 * response);
			 */            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/guest/productList.jsp");
            dispatcher.forward(request, response);
        } else {
            request.getRequestDispatcher("/Eshopper/404.html").forward(request, response);

        }
	}
}
