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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Users acc = (Users) req.getSession().getAttribute("account");
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();

        if (acc == null) {
            resp.sendRedirect(ctx + "/login");
            return; // QUAN TRỌNG
        }

        int role = acc.getRoleid(); // 1=user, 2=manager, 3=admin
        boolean ok =
                (uri.startsWith(ctx + "/user/")    && role == 1) ||
                (uri.startsWith(ctx + "/manager/") && role == 2) ||
                (uri.startsWith(ctx + "/admin/")   && role == 3);

        if (!ok) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}
