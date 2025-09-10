package vn.iostar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Users;

import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/home"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users acc = (Users) req.getSession().getAttribute("account");
        if (acc == null || acc.getRoleid() != 3) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Nếu bạn có trang admin/home.jsp như ảnh chụp -> forward tới đây:
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);

        // Nếu muốn nhảy thẳng tới danh mục:
        // req.getRequestDispatcher("/views/admin/category/category-list.jsp").forward(req, resp);
    }
}
