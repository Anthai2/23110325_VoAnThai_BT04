package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.Users;

import java.io.IOException;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("account") != null) {
            Users user = (Users) session.getAttribute("account");
            request.setAttribute("username", user.getUsername());
            if (user.getRoleid() == 1) {
                response.sendRedirect(request.getContextPath() + "/user/home");
            } else if (user.getRoleid() == 2) {
                response.sendRedirect(request.getContextPath() + "/manager/home");
            } else if (user.getRoleid() == 3) {
                response.sendRedirect(request.getContextPath() + "/admin/home");
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}