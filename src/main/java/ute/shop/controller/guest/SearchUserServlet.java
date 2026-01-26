package ute.shop.controller.guest;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ute.shop.config.JPAConfig;
import ute.shop.dao.guest.implement.UserDAO;
import ute.shop.entity.User;
import ute.shop.services.guest.implement.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/home/searchUser")
public class SearchUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        EntityManager em = JPAConfig.getEntityManager();
        userService = new UserService(new UserDAO(em));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywords = request.getParameter("keywords");
        boolean onlyVerified = "true".equals(request.getParameter("onlyVerified"));
        
        // Correct handling of page parameter with default value
        String pageParam = request.getParameter("page");
        int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
        int pageSize = 10;

        List<User> users = userService.searchUsers(keywords, onlyVerified, page, pageSize);
        int totalUsers = userService.countUsers(keywords, onlyVerified);
        int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
        request.setAttribute("keywords", keywords);
        request.setAttribute("users", users);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("/views/guest/searchUser.jsp").forward(request, response);
    }

}
