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

    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            response.sendRedirect(request.getContextPath() + "/waiting");
            return; 
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                    String username = cookie.getValue();
                    Users remembered = userService.findByUsername(username); 
                    if (remembered != null) {
                        session = request.getSession(true);
                        session.setAttribute("account", remembered);
                        response.sendRedirect(request.getContextPath() + "/waiting");
                        return; 
                    }
                }
            }
        }
        request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String username  = request.getParameter("username");
        String password  = request.getParameter("password");
        boolean isRemember = "on".equals(request.getParameter("remember"));

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("alert", "Tài khoản hoặc mật khẩu không được để rỗng");
            request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
            return;
        }

        Users user = userService.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            if (isRemember) {
                saveRememberMe(response, username);
            }

            response.sendRedirect(request.getContextPath() + "/waiting");
            return;
        }

        request.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
        request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60);             
        cookie.setPath("/");                    
        // cookie.setHttpOnly(true);            
        // cookie.setSecure(true);              
        response.addCookie(cookie);
    }
}
