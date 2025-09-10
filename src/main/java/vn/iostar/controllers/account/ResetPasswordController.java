package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/account/reset-password.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("otpEmail");
        if (email == null) {
            request.setAttribute("alert", "Phiên làm việc đã hết hạn. Vui lòng thử lại");
            request.getRequestDispatcher("/views/account/reset-password.jsp").forward(request, response);
            return;
        }
        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            request.setAttribute("alert", "Mật khẩu nhập lại không khớp");
            request.getRequestDispatcher("/views/account/reset-password.jsp").forward(request, response);
            return;
        }
        IUserService userService = new UserServiceImpl();
        if (userService.updatePasswordByEmail(email, newPassword)) {
            session.removeAttribute("otp");
            session.removeAttribute("otpEmail");
            session.removeAttribute("otpExpireTime");
            request.setAttribute("alert", "Đặt lại mật khẩu thành công. Vui lòng đăng nhập");
            request.getRequestDispatcher("/views/account/login.jsp").forward(request, response);
        } else {
            request.setAttribute("alert", "Có lỗi xảy ra khi đặt lại mật khẩu");
            request.getRequestDispatcher("/views/account/resetPassword.jsp").forward(request, response);
        }
    }
}