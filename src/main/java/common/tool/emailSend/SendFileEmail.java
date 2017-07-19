package common.tool.emailSend;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * 邮件发送携带文件
 * Created by ${XiaoHuiHui} on 2017/2/28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class SendFileEmail {
    public SendFileEmail() {
        // Recipient's email ID needs to be mentioned.
        String to = "---@qq.com";

        // Sender's email ID needs to be mentioned
        String from = "--@qq.com";

        // Assuming you are sending email from localhost
        // (服务器的host：qq：smtp.qq.com)
        String host = "smtp.qq.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
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
                // 重写验证方法，填写用户名，以及ssl验证码:通过邮箱设置自行获取
                return new PasswordAuthentication(
                        "--", "ssl验证码");
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
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
           // message.setRecipients(Message.RecipientType.CC,
            // new Address[]{new InternetAddress("2222@163.com")});//抄送

           // message.setRecipients(MimeMessage.RecipientType.BCC,
            // new Address[]{new InternetAddress("333@163.com")});//暗送

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "E:\\大哥.xls";
            FileDataSource source =  new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart.setFileName(MimeUtility.encodeText(source.getName(),"utf-8",null));
            // Send the complete message parts
            message.setContent(multipart );

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
