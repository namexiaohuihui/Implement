package common.tool.emailSend;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮箱？待定
 * Created by ${XiaoHuiHui} on 2017/2/28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class SimpleMailSender {
    private final transient Properties props = System.getProperties();
    private transient MailAuthenticator authenticator;
    private transient Session session;

    public SimpleMailSender(final String smtpHostName, final String username,
                            final String password){
        try {
            init(username, password, smtpHostName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public SimpleMailSender(final String username, final String password) throws Exception {
        //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
        final String smtpHostName = "smtp." + username.split("@")[1];
        init(username, password, smtpHostName);

    }
    private void init(String username, String password, String smtpHostName) throws Exception {
        // 初始化props
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        //qq是smtp.qq.com
        props.put("mail.smtp.host", smtpHostName);
        //ssl邮箱端口
        props.put("mail.smtp.socketFactory.port", 465);//465
        //开启ssl
        props.put("mail.smtp.starttls.enable","true");
        // 验证
        authenticator = new MailAuthenticator(username, password);
        // 创建session
        session = Session.getInstance(props, authenticator);
        session.setDebug(true);
//        Transport transport = session.getTransport();
//        try {
//            transport.connect("smtp.qq.com", 25, "530486639@qq.com", "llg9004_d");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
    }
    public void send(String recipient, String subject, Object content)
            throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        System.out.println();
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }
//    public void send(String recipient, SimpleMail mail)
//            throws AddressException, MessagingException {
//        send(recipient, mail.getSubject(), mail.getContent());
//        }

    /**
     * 1.重点现在客户端掉http端口25，腾讯好像不让掉了，必须https调用
     * 2.腾讯使用这种方式连接的话，需要重新申请独立密码，不是qq邮箱的密码，还需开启pop/smtp功能
     * 3.用腾讯的邮局发邮件好像有限制，发送过多，好像直接给你连接断开了
     * 4.如果需要做发邮件的功能的话，最好自己搭建邮局
     * @param args
     * @throws AddressException
     * @throws MessagingException
     */
    public static void main(String[] args) throws Exception {
        //用户名，以及ssl验证码:通过邮箱设置自行获取
        SimpleMailSender sms= new SimpleMailSender("--@qq.com",
                "ssl验证码");
        //发送过多的话会断开连接
//        for(int i=0;i<100;i++){
        sms.send("--@qq.com", "hello", "hello");
//            System.out.println("#######:"+i);
//        }
    }

}
