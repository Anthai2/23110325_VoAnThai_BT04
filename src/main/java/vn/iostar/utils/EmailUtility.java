package vn.iostar.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtility {

    public static void sendEmail(
            String host, String port,
            final String userName, final String password,
            String toAddress, String subject, String message
    ) throws MessagingException {

        // Cấu hình SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Xác thực người gửi
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(props, auth);

//     // Tạo session với cấu hình SMTP
//        Session session1 = Session.getInstance(props, auth);
//
//        // Tạo message gắn với session
//        MimeMessage msg = new MimeMessage(session1);
//
//        // Thiết lập người gửi
//        msg.setFrom();
//
//        // Người nhận
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
//
//        // Tiêu đề
//        msg.setSubject(subject);
//
//        // Nội dung
//        msg.setText(message);
//
//        // Gửi mail
//        Transport.send(msg);

    }
}
