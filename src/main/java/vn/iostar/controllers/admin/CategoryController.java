package vn.iostar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.models.CategoryModel;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/add",
        "/admin/category/insert",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete",
        "/admin/category/search"
})
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ICategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getServletPath(); // chính xác hơn contains()

        switch (path) {
            case "/admin/categories": {
                List<CategoryModel> list = cateService.findAll();
                req.setAttribute("listcate", list);
                req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
                break;
            }
            case "/admin/category/add": {
                req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
                break;
            }
            case "/admin/category/edit": {
                // chấp nhận cả id và categoryid cho linh hoạt
                String idStr = req.getParameter("id");
                if (idStr == null || idStr.isBlank()) idStr = req.getParameter("categoryid");
                int id = Integer.parseInt(idStr);

                CategoryModel category = cateService.findById(id);
                req.setAttribute("cate", category);
                req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
                break;
            }
            case "/admin/category/delete": {
                String idStr = req.getParameter("id");
                if (idStr != null && !idStr.isBlank()) {
                    int id = Integer.parseInt(idStr);
                    cateService.delete(id);
                }
                // Xoá xong quay lại list
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
                break;
            }
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getServletPath();

        if ("/admin/category/insert".equals(path)) {
            String categoryname = req.getParameter("categoryname");
            int status = safeParseInt(req.getParameter("status"), 0);

            // Tạm thời hardcode ảnh; nếu upload thật, thêm @MultipartConfig + đọc Part
            String images = "data:image/webp;base64,.....";

            CategoryModel category = new CategoryModel();
            category.setCategoryname(categoryname);
            category.setImages(images);
            category.setStatus(status);

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");

        } else if ("/admin/category/update".equals(path)) {
            int categoryid = safeParseInt(
                    req.getParameter("categoryid") != null ? req.getParameter("categoryid")
                                                           : req.getParameter("id"), 0);
            String categoryname = req.getParameter("categoryname");
            int status = safeParseInt(req.getParameter("status"), 0);

            String images = "data:image/webp;base64,.....";

            CategoryModel category = new CategoryModel();
            category.setCategoryid(categoryid);
            category.setCategoryname(categoryname);
            category.setImages(images);
            category.setStatus(status);

            cateService.update(category);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private int safeParseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
