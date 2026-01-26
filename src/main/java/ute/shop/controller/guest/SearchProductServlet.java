package ute.shop.controller.guest;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.config.JPAConfig;
import ute.shop.dao.guest.implement.CategoryDAO;
import ute.shop.dao.guest.implement.ProductDAO;
import ute.shop.entity.Category;
import ute.shop.entity.Product;
import ute.shop.services.guest.implement.ProductService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/home/searchProduct" })
public class SearchProductServlet extends HttpServlet {
    private static final int PRODUCTS_PER_PAGE = 10; // Số sản phẩm mỗi trang
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        EntityManager em = JPAConfig.getEntityManager();
        productService = new ProductService(new ProductDAO(em), new CategoryDAO(em));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("keywords");
        String categoryIdParam = request.getParameter("category");
        String minPriceParam = request.getParameter("minPrice");
        String maxPriceParam = request.getParameter("maxPrice");
        String pageParam = request.getParameter("page");

        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        Integer categoryId = (categoryIdParam != null && !categoryIdParam.isEmpty())
                ? Integer.parseInt(categoryIdParam)
                : null;
        Double minPrice = (minPriceParam != null && !minPriceParam.isEmpty())
                ? Double.parseDouble(minPriceParam)
                : null;
        Double maxPrice = (maxPriceParam != null && !maxPriceParam.isEmpty())
                ? Double.parseDouble(maxPriceParam)
                : null;

        // Gọi dịch vụ tìm kiếm
        List<Product> products = productService.searchProducts(keywords, categoryId, minPrice, maxPrice, page, PRODUCTS_PER_PAGE);
        int totalProducts = productService.countSearchResults(keywords, categoryId, minPrice, maxPrice);

        int totalPages = (int) Math.ceil((double) totalProducts / PRODUCTS_PER_PAGE);

        // Truyền dữ liệu danh mục vào JSP
        List<Category> categories = productService.getAllCategories();

        // Gửi dữ liệu tới JSP
        request.setAttribute("products", products);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keywords", keywords);
        request.setAttribute("categories", categories); // Thêm danh sách danh mục

        request.getRequestDispatcher("/views/guest/searchProduct.jsp").forward(request, response);
    }
}
