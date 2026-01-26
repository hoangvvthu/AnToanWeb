package ute.shop.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Product;
import ute.shop.entity.Product_;
import ute.shop.entity.Store;
import ute.shop.entity.User;
import ute.shop.services.IProductService;
import ute.shop.services.IStoreService;
import ute.shop.services.IUserService;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;
import ute.shop.services.implement.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/manager"})
public class ManagerController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Data for datatable
		IUserService uservice = new UserServiceImpl();
		IProductService pservice = new ProductServiceImpl();
		IStoreService sservice = new StoreServiceImpl();
		
		List<User> userList = uservice.findAll();
		List<Product> proList = pservice.findAll();
		List<Store> stoList = sservice.findAll();
		req.setAttribute("userList", userList);
		req.setAttribute("proList", proList);
		req.setAttribute("stoList", stoList);
		req.getRequestDispatcher("/views/admin/manager.jsp").forward(req, resp);
	}
}
