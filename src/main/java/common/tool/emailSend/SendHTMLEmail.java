package common.tool.emailSend;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 文件发送携带html
 * Created by ${XiaoHuiHui} on 2017/2/28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class SendHTMLEmail {
    public SendHTMLEmail() {
        // Recipient's email ID needs to be mentioned.
        String to = "3575541582@qq.com";

        // Sender's email ID needs to be mentioned
        String from = "704866169@qq.com";

        // Assuming you are sending email from localhost(服务器的host：qq：smtp.qq.com)
        String host = "smtp.qq.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        //qq是smtp.qq.com
        properties.put("mail.smtp.host", host);
        //ssl邮箱端口
        properties.put("mail.smtp.socketFactory.port", 465);//465
        //开启ssl
        properties.put("mail.smtp.starttls.enable", "true");

        // 创建邮件验证信息，即发送邮件的用户名和密码
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 重写验证方法，填写用户名，以及ssl验证码
                return new PasswordAuthentication(
                        "704866169", "sdjnfmbeyjribcbf");
            }
        };
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, authenticator);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Send the actual HTML message, as big as you like
            message.setContent("<h1>This is actual message</h1>", "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
