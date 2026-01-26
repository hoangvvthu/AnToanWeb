package ute.shop.controller.admin;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.entity.Category;
import ute.shop.entity.Product;
import ute.shop.entity.Store;
import ute.shop.services.ICategoryService;
import ute.shop.services.IProductService;
import ute.shop.services.IStoreService;
import ute.shop.services.implement.CategoryServiceImpl;
import ute.shop.services.implement.ProductServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;

@WebServlet(urlPatterns = { "/admin/productDetails", "/admin/updateProduct", "/admin/addProduct",
		"/admin/deleteProduct" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		IProductService proser = new ProductServiceImpl();

		if (url.contains("Details")) {
			int productId = Integer.parseInt(req.getParameter("id"));
			Product product = proser.findById(productId);
			req.setAttribute("product", product);
			req.getRequestDispatcher("/views/admin/product.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			String id = req.getParameter("id");
			try {

				proser.deleteProduct(Integer.parseInt(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
		} else if (url.contains("add"))
		{
			ICategoryService catser = new CategoryServiceImpl();
			List<Category> cateList = catser.findAll();
			req.setAttribute("cateList", cateList);
			req.getRequestDispatcher("/views/admin/product-add.jsp").forward(req, resp);
		}
		else
			resp.sendRedirect(req.getContextPath() + "/admin/manager");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("update")) {
			// Nhận dữ liệu từ form
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String slug = req.getParameter("slug");
			String description = req.getParameter("description");
			double price = Double.parseDouble(req.getParameter("price"));
			double promotionalPrice = Double.parseDouble(req.getParameter("promotionalPrice"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
			boolean isSelling = Boolean.parseBoolean(req.getParameter("isSelling"));
			int rating = Integer.parseInt(req.getParameter("rating"));

			// Gọi service để cập nhật sản phẩm
			IProductService productService = new ProductServiceImpl();
			Product product = productService.findProductById(id);
			if (product != null) {
				product.setName(name);
				product.setSlug(slug);
				product.setDescription(description);
				product.setPrice(price);
				product.setPromotionalPrice(promotionalPrice);
				product.setQuantity(quantity);
				product.setIsActive(isActive);
				product.setIsSelling(isSelling);
				product.setRating(rating);
				

				productService.updateProduct(product);
			}
			// Điều hướng quay lại trang quản lý
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
		}

		else if (url.contains("add")) {
			//int id = Integer.parseInt(req.getParameter("_id"));
			String name = req.getParameter("name");
			String slug = req.getParameter("slug");
			String description = req.getParameter("description");
			double price = Double.parseDouble(req.getParameter("price"));
			double promotionalPrice = Double.parseDouble(req.getParameter("promotionalPrice"));
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
			boolean isSelling = Boolean.parseBoolean(req.getParameter("isSelling"));
			int rating = Integer.parseInt(req.getParameter("rating"));
			Date date = new Date(System.currentTimeMillis());
			int cate_id = Integer.parseInt(req.getParameter("category_id"));
			int sto_id = Integer.parseInt(req.getParameter("store_id"));


			// Gọi service để thêm sản phẩm
			IProductService productService = new ProductServiceImpl();
			Product newProduct = new Product();
			//newProduct.set_id(id);
			newProduct.setName(name);
			newProduct.setSlug(slug);
			newProduct.setDescription(description);
			newProduct.setPrice(price);
			newProduct.setPromotionalPrice(promotionalPrice);
			newProduct.setQuantity(quantity);
			newProduct.setSold(0);
			newProduct.setIsActive(isActive);
			newProduct.setIsSelling(isSelling);
			newProduct.setRating(rating);
			//newProduct.setCreatedAt(date);
			newProduct.setListImages(null);
			newProduct.setStyleValueIds(null);
			
			ICategoryService catser = new CategoryServiceImpl();
			Category cate = catser.findById(cate_id);
			newProduct.setCategory(cate);
			
			IStoreService stoser = new StoreServiceImpl();
			Store store = stoser.findById(sto_id);
			newProduct.setStore(store);
			
			
			productService.addProduct(newProduct);

			// Điều hướng về trang quản lý sản phẩm
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
		} else
			resp.sendRedirect(req.getContextPath() + "/admin/manager");
	}
}