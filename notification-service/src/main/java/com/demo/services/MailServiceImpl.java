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

    @Override
    public void sendMatchJobEmail(String toEmail, String jobTitle, String companyName, String jobLink) {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🎯 Có một công việc phù hợp với hồ sơ của bạn!");

            String content = """
                    <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6; background-color: #f9f9f9; padding: 20px;">
                        <div style="max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                            <h2 style="color: #2c3e50;">🚀 Tin vui từ <span style="color:#e67e22;">%s</span>!</h2>
                            <p>Chào bạn,</p>
                            <p>Chúng tôi phát hiện <strong>một công việc mới</strong> từ công ty <strong>%s</strong> <br>
                            rất <strong>phù hợp với hồ sơ (CV)</strong> của bạn.</p>
                            <p style="font-size: 18px; color: #2980b9; margin-top: 20px;"><strong>%s</strong></p>
                            <p style="margin-top: 10px;">Bạn có thể xem thông tin chi tiết và ứng tuyển ngay tại đường dẫn bên dưới:</p>
                            <p><a href="%s" style="background: #27ae60; color: white; padding: 10px 15px; border-radius: 4px; text-decoration: none;">👉 Xem chi tiết công việc</a></p>
                            <hr style="margin-top: 30px;">
                            <p style="font-size: 12px; color: #95a5a6;">Bạn nhận được email này vì hệ thống phát hiện sự phù hợp giữa hồ sơ của bạn và bài đăng tuyển dụng mới.</p>
                            <p style="font-size: 12px; color: #bdc3c7;">Nếu không muốn nhận email tương tự, vui lòng điều chỉnh cài đặt thông báo của bạn.</p>
                        </div>
                    </body>
                    </html>
                    """.formatted(companyName, companyName, jobTitle, jobLink);

            helper.setText(content, true);
            sender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
