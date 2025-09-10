package vn.iostar.controllers.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/manager/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Users acc = (Users) req.getSession().getAttribute("account");
        req.setAttribute("item", categoryService.findByUserId(acc.getId()));
        req.getRequestDispatcher("/views/category/category-list.jsp").forward(req, resp);
    }
}