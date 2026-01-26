package ute.shop.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Commission;
import ute.shop.entity.Store;
import ute.shop.entity.StoreLevel;
import ute.shop.entity.User;
import ute.shop.services.IStoreService;
import ute.shop.services.IUserService;
import ute.shop.services.implement.StoreServiceImpl;
import ute.shop.services.implement.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/storeDetails", "/admin/updateStore", "/admin/addStore", "/admin/deleteStore" })
public class StoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		IStoreService proser = new StoreServiceImpl();

		if (url.contains("Details")) {
			int storeId = Integer.parseInt(req.getParameter("id"));
			Store store = proser.findById(storeId);
			List<Commission> comList = proser.findAllComission();
			req.setAttribute("comList", comList);
			req.setAttribute("store", store);

			req.getRequestDispatcher("/views/admin/store.jsp").forward(req, resp);

		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {
				// proser.deleteStore(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
		} else if (url.contains("add")) {
			IStoreService stoser = new StoreServiceImpl();
			List<Commission> comList = stoser.findAllComission();
			req.setAttribute("comList", comList);
			req.getRequestDispatcher("/views/admin/store-add.jsp").forward(req, resp);
		} else
			resp.sendRedirect(req.getContextPath() + "/admin/manager");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("update")) {
			// Nhận dữ liệu từ form
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String bio = req.getParameter("bio");
			String slug = req.getParameter("slug");
			int point = Integer.parseInt(req.getParameter("point"));
			double commissionSold = Double.parseDouble(req.getParameter("commissionSold"));
			double rating = Double.parseDouble(req.getParameter("rating"));
			boolean isOpen = Boolean.parseBoolean(req.getParameter("isOpen"));

			// Gọi service để cập nhật sản phẩm
			IStoreService storeService = new StoreServiceImpl();
			Store store = storeService.findById(id);
			if (store != null) {
				store.setName(name);
				store.setBio(bio);
				store.setSlug(slug);
				store.setPoint(point);
				store.setCommissionSold(commissionSold);
				store.setRating(rating);
				store.setIsOpen(isOpen);
				storeService.updateStore(store);
			}
			// Điều hướng quay lại trang quản lý
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
		}

		else if (url.contains("add")) {
			String name = req.getParameter("name");
			String bio = req.getParameter("bio");
			String slug = req.getParameter("slug");
			int point = Integer.parseInt(req.getParameter("point"));
			double commissionSold = Double.parseDouble(req.getParameter("commissionSold"));
			double rating = Double.parseDouble(req.getParameter("rating"));
			boolean isOpen = Boolean.parseBoolean(req.getParameter("isOpen"));
			int user_id = Integer.parseInt(req.getParameter("owner_id"));
			int com_id = Integer.parseInt(req.getParameter("commission"));

			// Gọi service để thêm sản phẩm
			Store newStore = new Store();
			// newStore.set_id(id);
			newStore.setName(name);
			newStore.setBio(bio);
			newStore.setSlug(slug);
			newStore.setIsActive(true);
			newStore.setIsOpen(isOpen);
			newStore.setAvatar("avatar.jpg");
	        newStore.setCover("cover.jpg");
	        newStore.setFeaturedImages(List.of("feature1.jpg", "feature2.jpg"));
			newStore.setPoint(point);
			newStore.setCommissionSold(commissionSold);
			newStore.setRating(rating);
	        newStore.setE_wallet(1000.9);
	        
	        StoreLevel storeLevel = new StoreLevel();
	        storeLevel.set_id(12); // ID đã tồn tại
	        newStore.setStoreLevel(storeLevel);

			
			IUserService uservice = new UserServiceImpl();
			User user = uservice.findById(user_id);
			newStore.setOwner(user);

			IStoreService stoser = new StoreServiceImpl();
			Commission com = stoser.findComById(com_id);
			newStore.setCommission(com);

			stoser.addStore(newStore);

			// Điều hướng về trang quản lý sản phẩm
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
		} else
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
	}
}
