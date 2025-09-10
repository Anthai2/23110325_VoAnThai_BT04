package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.entity.Users;

import java.io.IOException;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Users user = (Users) session.getAttribute("account");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        switch (user.getRoleid()) {
            case 3:
                response.sendRedirect(request.getContextPath() + "/admin/home");
                return;
            case 2:
                response.sendRedirect(request.getContextPath() + "/manager/home");
                return;
            case 1:
                response.sendRedirect(request.getContextPath() + "/user/home");
                return;
            default:
                response.sendRedirect(request.getContextPath() + "/login");
                return;
        }
    }
}
