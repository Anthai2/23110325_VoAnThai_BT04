package vn.iostar.controllers.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Category;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/category/list", "/category/view", "/category/create", "/category/store", "/category/edit", "/category/update", "/category/delete"})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        String address = uri.substring(ctx.length());
        Users user = (Users) request.getSession().getAttribute("account");

        switch (address) {
            case "/category/list": {
                if (user.getRoleid() == 2) {
                    request.setAttribute("item", categoryService.findByUserId(user.getId()));
                } else {
                    request.setAttribute("item", categoryService.findAll());
                }
                request.getRequestDispatcher("/views/category/category-list.jsp").forward(request, response);
                return;
            }
            case "/category/view": {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("item", categoryService.findById(id));
                request.getRequestDispatcher("/views/category/category-view.jsp").forward(request, response);
                return;
            }
            case "/category/create": {
                request.getRequestDispatcher("/views/category/category-form.jsp").forward(request, response);
                return;
            }
            case "/category/edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("item", categoryService.findById(id));
                request.getRequestDispatcher("/views/category/category-form.jsp").forward(request, response);
                return;
            }
            case "/category/delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                categoryService.delete(id);
                redirectByRole(response, ctx, user.getRoleid());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();
        String address = uri.substring(ctx.length());
        Users user = (Users) request.getSession().getAttribute("account");

        switch (address) {
            case "/category/store": {
                Category c = new Category();
                c.setCate_name(request.getParameter("cate_name"));
                c.setDescription(request.getParameter("description"));
                c.setUser(user);
                c.setCreated_at(LocalDateTime.now());
                categoryService.insert(c);
                redirectByRole(response, ctx, user.getRoleid());
                return;
            }
            case "/category/update": {
                int id = Integer.parseInt(request.getParameter("id"));
                Category c = categoryService.findById(id);
                c.setCate_name(request.getParameter("cate_name"));
                c.setDescription(request.getParameter("description"));
                c.setUpdated_at(LocalDateTime.now());
                categoryService.update(c);
                redirectByRole(response, ctx, user.getRoleid());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    private void redirectByRole(HttpServletResponse response, String ctx, int role) throws IOException {
        if (role == 1) response.sendRedirect(ctx + "/user/home");
        else if (role == 2) response.sendRedirect(ctx + "/manager/home");
        else response.sendRedirect(ctx + "/admin/home");
    }
}