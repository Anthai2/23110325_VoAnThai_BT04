package vn.iostar.controllers.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/manager/home"})
public class ManagerHomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users acc = (Users) req.getSession().getAttribute("account");
        if (acc == null || acc.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("listcate", cateService.findByUserId(acc.getId())); // CHỈ của manager hiện tại
        req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
    }
}
