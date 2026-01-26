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

@WebServlet("/home/userDetail")
public class UserDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        EntityManager em = JPAConfig.getEntityManager();
        userService = new UserService(new UserDAO(em));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdParam = request.getParameter("id");
        if (userIdParam == null || userIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing.");
            return;
        }

        int userId = Integer.parseInt(userIdParam);
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/views/guest/userDetail.jsp").forward(request, response);
    }
}
