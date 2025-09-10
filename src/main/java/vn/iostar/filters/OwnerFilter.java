package vn.iostar.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Category;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebFilter(filterName = "OwnerFilter", urlPatterns = {
        "/category/edit", "/category/update", "/category/delete"
})
public class OwnerFilter implements Filter {
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse p = (HttpServletResponse) res;

        Users acc = (Users) r.getSession().getAttribute("account");
        if (acc == null) {
            p.sendRedirect(r.getContextPath() + "/login");
            return;
        }

        if (acc.getRoleid() == 3) {
            chain.doFilter(req, res);
            return;
        }

        String sid = r.getParameter("id");
        if (sid == null) {
            p.sendError(400, "Missing id");
            return;
        }

        int id = Integer.parseInt(sid);
        Category c = categoryService.findById(id);
        if (c == null) {
            p.sendError(404);
            return;
        }

        if (c.getUser() == null || c.getUser().getId() != acc.getId()) {
            p.sendError(403);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}