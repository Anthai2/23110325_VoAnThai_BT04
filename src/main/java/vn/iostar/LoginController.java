package vn.iostar;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserService;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null) {
            Object msg = session.getAttribute("flash_alert");
            if (msg != null) {
                req.setAttribute("alert", msg);
                session.removeAttribute("flash_alert");
            }
        }

        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        UserModel user;
        try {
            user = service.login(username, password);
        } catch (Exception e) {
            req.setAttribute("alert", "Lỗi hệ thống khi đăng nhập: " + e.getMessage());
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            if ("on".equals(remember)) {
                Cookie c = new Cookie(Constant.COOKIE_REMEMBER, username);
                c.setMaxAge(7 * 24 * 60 * 60);
                c.setHttpOnly(true);
                c.setPath(req.getContextPath().isEmpty() ? "/" : req.getContextPath());
                resp.addCookie(c);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}
