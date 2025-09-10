package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.Users;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.sendRedirect(request.getContextPath() + "login");
            return;
        }
        Users account = (Users) session.getAttribute("account");
        Users fresh = account;
        try {
            Users user = userService.get(account.getUsername());
            if (user != null) {
                fresh = user;
            }
        } catch (Exception e) {
        }
        request.setAttribute("user", fresh);
        request.getRequestDispatcher("/views/account/profile.jsp").forward(request, response);
    }
}