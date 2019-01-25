package toolskit.tools.emailSend;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 普通邮箱的发送
 * Created by ${XiaoHuiHui} on 2017/2/28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class SendEmail {
    private String to = "" ;
    private String from = "" ;
    private String user = "" ;
    private String pass = "" ;

    /**
     *
     * @param to
     * @param from
     * @param user
     * @param pass
     */
    public SendEmail(String to, String from, String user, String pass) {
        this.to = to;
        this.from = from;
        this.user = user;
        this.pass = pass;
    }

    public void SendEmailTo() {

         final String user =this.user ;
         final String pass =this.pass ;

        // Assuming you are sending email from localhost
        // (服务器的host：qq：smtp.qq.com)
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
                        user, pass);
            }
        };
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, authenticator);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(this.from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
