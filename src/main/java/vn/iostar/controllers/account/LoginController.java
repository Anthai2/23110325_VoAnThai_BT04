package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.entity.Users;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.Constant;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/waiting");
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = request.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    response.sendRedirect(request.getContextPath() + "/waiting");
                    return;
                }
            }
        }
        request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isRemember = false;
        String remember = request.getParameter("remember");
        if ("on".equals(remember)) {
            isRemember = true;
        }
        String alerMsg = "";
        if (username.isEmpty() || password.isEmpty()) {
            alerMsg = "Tài khoản hoặc mật khẩu không được để rỗng";
            request.setAttribute("alert", alerMsg);
            request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
            return;
        }
        UserServiceImpl service = new UserServiceImpl();
        Users user = service.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);
            if (isRemember) {
                saveRememberMe(response, username);
            }
            response.sendRedirect(request.getContextPath() + "/waiting");
        } else {
            alerMsg = "Tài khoản hoặc mật khẩu không đúng";
            request.setAttribute("alert", alerMsg);
            request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
    }
}