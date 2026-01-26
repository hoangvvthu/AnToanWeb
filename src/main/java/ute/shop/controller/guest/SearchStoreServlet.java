package ute.shop.controller.guest;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.config.JPAConfig;
import ute.shop.dao.guest.implement.StoreDAO;
import ute.shop.entity.Store;
//import ute.shop.utils.Page;
import ute.shop.services.guest.implement.StoreService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home/searchStore" })
public class SearchStoreServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreService storeService;

    @Override
    public void init() throws ServletException {
        EntityManager em = JPAConfig.getEntityManager();
        storeService = new StoreService(new StoreDAO(em));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("keywords");
        String pageParam = request.getParameter("page");

        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int pageSize = 10; // Số cửa hàng mỗi trang

        List<Store> stores = storeService.searchStores(keywords, page, pageSize);
        long totalStores = storeService.countSearchResults(keywords);
        int totalPages = (int) Math.ceil((double) totalStores / pageSize);

        request.setAttribute("stores", stores);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("keywords", keywords);

        request.getRequestDispatcher("/views/guest/searchStore.jsp").forward(request, response);
    }
}
