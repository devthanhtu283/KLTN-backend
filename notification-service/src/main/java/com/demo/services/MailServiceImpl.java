package com.demo.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;

    @Override
    public boolean send(String from, String to, String subject, String content) {
        try {

            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            sender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void sendNewJobEmail(String toEmail, String jobTitle, String companyName, String jobLink) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🎯 Tin tuyển dụng mới từ công ty bạn theo dõi!");

            String content = """
                    <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                        <h2 style="color: #2c3e50;">📢 %s vừa đăng tin tuyển dụng mới!</h2>
                        <p>Chào bạn,</p>
                        <p>Công ty <strong>%s</strong> mà bạn đang theo dõi vừa đăng một công việc mới:</p>
                        <p style="font-size: 18px; color: #2980b9;"><strong>%s</strong></p>
                        <p>Bạn có thể xem chi tiết và ứng tuyển ngay tại link dưới đây:</p>
                        <p><a href="%s" style="color: #27ae60;">👉 Xem chi tiết công việc</a></p>
                        <hr>
                        <p style="font-size: 12px; color: #7f8c8d;">Bạn nhận được email này vì đã follow công ty %s.</p>
                        <p style="font-size: 12px;">Nếu bạn không muốn nhận email nữa, vui lòng bỏ theo dõi công ty.</p>
                    </body>
                    </html>
                    """.formatted(companyName, companyName, jobTitle, jobLink, companyName);

            helper.setText(content, true);
            System.out.println(content);
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
