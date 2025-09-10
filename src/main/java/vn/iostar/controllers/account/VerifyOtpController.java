package vn.iostar.controllers.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/verify-otp"})
public class VerifyOtpController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String inputOtp = request.getParameter("otp");
        Integer otp = (Integer) session.getAttribute("otp");
        Long expireTime = (Long) session.getAttribute("otpExpireTime");
        if (otp == null || expireTime == null) {
            request.setAttribute("alert", "OTP không hợp lệ. Vui lòng gửi yêu cầu lại.");
            request.getRequestDispatcher("/views/account/verifyOtp.jsp").forward(request, response);
            return;
        }
        if (System.currentTimeMillis() > expireTime) {
            request.setAttribute("alert", "OTP đã hết hạn. Vui lòng yêu cầu nhập lại.");
            request.getRequestDispatcher(request.getContextPath() + "/views/account/forgotPassword.jsp").forward(request, response);
            return;
        }
        if (inputOtp.equals(String.valueOf(otp))) {
            response.sendRedirect(request.getContextPath() + "/views/account/resetPassword.jsp");
        } else {
            request.setAttribute("alert", "OTP không đúng. Vui lòng thử lại");
            request.getRequestDispatcher("/views/account/verifyOtp.jsp").forward(request, response);
        }
    }
}