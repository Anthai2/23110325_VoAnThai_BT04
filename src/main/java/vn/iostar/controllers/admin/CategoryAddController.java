package vn.iostar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import vn.iostar.entity.Category;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet("/admin/category/add")
@MultipartConfig
public class CategoryAddController extends HttpServlet {
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/category/category-form.jsp")
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("cate_name");
        String description = req.getParameter("description");

        Users current = (Users) req.getSession().getAttribute("user");
        if (current == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Category c = new Category();
        c.setCate_name(name);
        c.setDescription(description);
        c.setUser(current);

        categoryService.insert(c); // DAO.create(..)

        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}
