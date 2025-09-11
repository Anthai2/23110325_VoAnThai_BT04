package vn.iostar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import vn.iostar.entity.Category;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet("/admin/category/list")
public class CategoryListController extends HttpServlet {
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> list = categoryService.findAll();
        System.out.println("Category size = " + (list == null ? 0 : list.size()));

        req.setAttribute("listcate", list);
        req.getRequestDispatcher("/views/admin/category/category-list.jsp")
           .forward(req, resp);
    }
}
