package vn.iostar.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.entity.Category;
import vn.iostar.entity.Users;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/add",
        "/admin/category/insert",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete"
})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getServletPath();

        switch (path) {
            case "/admin/categories": {
                req.setAttribute("listcate", cateService.findAll());
                req.getRequestDispatcher("/views/admin/category/category-list.jsp").forward(req, resp);
                return;
            }
            case "/admin/category/add": {
                // hiển thị form thêm mới (có thể dùng chung category-form.jsp nếu bạn muốn)
                req.getRequestDispatcher("/views/admin/category/category-add.jsp").forward(req, resp);
                return;
            }
            case "/admin/category/edit": {
                String sid = req.getParameter("id");
                if (sid == null) { resp.sendError(400, "Missing id"); return; }
                int id = Integer.parseInt(sid);

                Category c = cateService.findById(id);
                if (c == null) { resp.sendError(404); return; }

                req.setAttribute("cate", c);
                // dùng lại form chung cho edit
                req.getRequestDispatcher("/views/admin/category/category-form.jsp").forward(req, resp);
                return;
            }
            default:
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getServletPath();

        switch (path) {
            case "/admin/category/insert": {
                String name = req.getParameter("name");
                String desc = req.getParameter("description");

                if (name == null || name.isBlank()) {
                    req.setAttribute("alert", "Tên danh mục không được để trống");
                    req.getRequestDispatcher("/views/admin/category/category-add.jsp").forward(req, resp);
                    return;
                }

                // Lấy owner từ session (nếu bạn có ràng buộc user)
                Users owner = (Users) req.getSession().getAttribute("account");

                Category c = new Category();
                c.setCate_name(name.trim());
                c.setDescription(desc != null ? desc.trim() : null);
                c.setUser(owner);

                boolean ok = cateService.insert(c);
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
                return;
            }

            case "/admin/category/update": {
                String sid  = req.getParameter("id");
                String name = req.getParameter("name");
                String desc = req.getParameter("description");

                if (sid == null) { resp.sendError(400, "Missing id"); return; }
                int id = Integer.parseInt(sid);

                Category c = cateService.findById(id);
                if (c == null) { resp.sendError(404); return; }

                if (name != null && !name.isBlank()) c.setCate_name(name.trim());
                c.setDescription(desc != null ? desc.trim() : c.getDescription());

                boolean ok = cateService.update(c);
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
                return;
            }

            case "/admin/category/delete": {
                String sid = req.getParameter("id");
                if (sid == null) { resp.sendError(400, "Missing id"); return; }
                int id = Integer.parseInt(sid);

                cateService.delete(id);
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
                return;
            }

            default:
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
