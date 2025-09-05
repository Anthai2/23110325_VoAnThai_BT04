package vn.iostar;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.UserModel;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            // Chưa đăng nhập -> quay về trang login (forward để có thể nhận "alert" nếu có)
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        UserModel u = (UserModel) session.getAttribute("account");
        req.setAttribute("username", u.getUsername());

        // Điều hướng theo role
        if (u.getRoleid() == 2) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else if (u.getRoleid() == 3) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
