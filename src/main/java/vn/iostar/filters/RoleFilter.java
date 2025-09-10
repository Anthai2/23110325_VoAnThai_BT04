package vn.iostar.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Users;

import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/manager/*", "/admin/*"})
public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Users user = (Users) req.getSession().getAttribute("account");
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        Users acc = (Users) req.getSession().getAttribute("account");
        if (acc == null) {
            resp.sendRedirect(ctx + "/login");
            return;
        }
        if (!((uri.startsWith(req.getContextPath() + "/user/") && user.getRoleid() == 1) || (uri.startsWith(req.getContextPath() + "/manager/") && user.getRoleid() == 2) || (uri.startsWith(req.getContextPath() + "/admin/") && user.getRoleid() == 3))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}