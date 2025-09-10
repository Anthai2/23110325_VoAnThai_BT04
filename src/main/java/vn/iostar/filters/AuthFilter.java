package vn.iostar.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.entity.Users;

import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
    private static final Set<String> PUBLIC = Set.of(
            "/", "/login", "/register", "/verify-otp", "/reset-password", "/waiting",
            "/style/", "/image", "/assets/", "/js/", "/css/"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse p = (HttpServletResponse) res;
        String path = r.getRequestURI().substring(r.getContextPath().length());

        boolean isPublic = PUBLIC.stream().anyMatch(path::startsWith);
        Users acc = (Users) r.getSession().getAttribute("account");
        if (isPublic || "/".equals(path)) {
            chain.doFilter(req, res);
            return;
        }
        if (acc == null) {
            p.sendRedirect(r.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}