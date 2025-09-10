package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.entity.Users;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserServiceImpl;
import vn.iostar.utils.EmailUtility;

import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/account/forgotPassword.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String alertMsg = "";
        IUserService userService = new UserServiceImpl();
        Users user = userService.getUserByEmail(email);
        if (user == null) {
            alertMsg = "Email này không tồn tại trong hệ thống";
            request.setAttribute("alertMsg", alertMsg);
            request.getRequestDispatcher("/views/account/forgotPassword.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            session.setAttribute("otp", otp);
            session.setAttribute("otpEmail", email);
            session.setAttribute("otpExpireTime", System.currentTimeMillis() + 5 * 60 * 1000);
            try {
                String subject = "Mã OTP xác nhận đặt lại mật khẩu";
                String message = "Xin chào " + user.getUsername() + "\n\n" + "Mã OTP của bạn là: " + otp + "\n" + "Mã này sẽ hết hạn sau 5 phút.\n\n" + "Nếu không phải bạn yêu cầu, vui lòng bỏ qua email này.";
                EmailUtility.sendEmail("smtp.gmail.com", "587", "nguyennhatminh0514@gmail.com", "roqa xsgc ktsz vewl", email, subject, message);
                response.sendRedirect(request.getContextPath() + "/views/account/verifyOtp.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                alertMsg = "Không thể gửi email OTP. Vui lòng thử lại";
                request.setAttribute("alertMsg", alertMsg);
                request.getRequestDispatcher("/views/account/forgotPassword.jsp").forward(request, response);
            }
        }
    }
}