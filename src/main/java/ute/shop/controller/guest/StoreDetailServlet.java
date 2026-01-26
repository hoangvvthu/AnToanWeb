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
import ute.shop.services.guest.implement.StoreService;

import java.io.IOException;

@WebServlet(urlPatterns = { "/home/storeDetail" })
public class StoreDetailServlet extends HttpServlet {
    private StoreService storeService;

    @Override
    public void init() throws ServletException {
        EntityManager em = JPAConfig.getEntityManager();
        storeService = new StoreService(new StoreDAO(em));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int storeId = Integer.parseInt(request.getParameter("id"));

        Store store = storeService.getStoreDetails(storeId);

        request.setAttribute("store", store);
        request.getRequestDispatcher("/views/guest/storeDetail.jsp").forward(request, response);
    }
}
