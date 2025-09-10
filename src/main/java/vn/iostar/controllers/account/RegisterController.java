package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.Constant;

import java.io.IOException;
import java.io.Serializable;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect(request.getContextPath());
            return;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = request.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    response.sendRedirect(request.getContextPath());
                    return;
                }
            }
        }
        request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String alertMsg = "";
        IUserService userService = new UserServiceImpl();
        if (userService.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại";
            request.setAttribute("alertMsg", alertMsg);
            request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
            return;
        }
        if (userService.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại";
            request.setAttribute("alertMsg", alertMsg);
            request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
            return;
        }
        if (userService.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại";
            request.setAttribute("alertMsg", alertMsg);
            request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
            return;
        }
        boolean isSuccess = userService.register(email, password, username, fullname, phone);
        if (isSuccess) {
            alertMsg = "Đăng ký thành công";
            request.setAttribute("alert", alertMsg);
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            alertMsg = "System error!";
            request.setAttribute("alert", alertMsg);
            request.getRequestDispatcher(Constant.REGISTER).forward(request, response);
        }
    }
}