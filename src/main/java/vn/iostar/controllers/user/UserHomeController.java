package vn.iostar.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/user/home"})
public class UserHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users acc = (Users) req.getSession().getAttribute("account");
        if (acc == null || acc.getRoleid() != 1) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("listcate", cateService.findAll());
        req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
    }
}
