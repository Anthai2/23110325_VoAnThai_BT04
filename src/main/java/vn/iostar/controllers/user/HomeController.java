package vn.iostar.controllers.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/user/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("item", categoryService.findAll());
        req.getRequestDispatcher("/views/category/category-list.jsp").forward(req, resp);
    }
}