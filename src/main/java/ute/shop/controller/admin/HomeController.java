package ute.shop.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Order;
import ute.shop.entity.Product;
import ute.shop.services.IOrderService;
import ute.shop.services.IProductService;
import ute.shop.services.ITransactionService;
import ute.shop.services.IUserService;
import ute.shop.services.implement.OrderServiceImpl;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.TransactionServiceImpl;
import ute.shop.services.implement.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/home"})
public class HomeController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ITransactionService transer = new TransactionServiceImpl();
		IOrderService orser = new OrderServiceImpl();
		IProductService proser = new ProductServiceImpl();
		IUserService uservice = new UserServiceImpl();
		double totalSale = transer.caculatedTotalSale();
		req.setAttribute("totalSale", totalSale);
		long totalOrder = orser.countTotalOrders();
		req.setAttribute("totalOrder", totalOrder);
		List<Product> proList = proser.findTopSelling();
		req.setAttribute("proList", proList);
        List<Object[]> topUsers = uservice.findTopUser();
        req.setAttribute("topUsers", topUsers);
    	List<Order> last7order = orser.findLatestOrders();
		req.setAttribute("last7order", last7order);
       
        
		
		
        
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
	}
}
