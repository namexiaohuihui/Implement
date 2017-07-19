package common.tool.emailSend;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * 普通大发送
 * Created by ${XiaoHuiHui} on 2017/2/28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class SendMail {
    public static void main(String[] args) throws Exception {
        /*-------------------配置数据--------------*/
        // 创建邮件的发送过程中用到的主机和端口号的属性文件
        Properties pro = new Properties();
        String host = "smtp.qq.com";
        // 设置邮件发送方的主机地址如果是163邮箱，则为smtp.163.com
        // 如果是其他的邮箱可以参照
        // http://wenku.baidu.com/link?url=Cf-1ggeW3e7Rm9KWfz47UL7vvkRpPxAKBlYoTSGpnK4hxpJDiQ0A4lRoPDncMlcMIvUpEn6PD0aObgm5zJaM7AOGkRdccSx6HDH2fSWkxIq这个文档
        pro.put("mail.transport.protocol", "smtp");
        pro.put("mail.smtp.auth", "true");
        //qq是smtp.qq.com
        pro.put("mail.smtp.host",host);
        //ssl邮箱端口
        pro.put("mail.smtp.socketFactory.port", 465);//465
        //开启ssl
        pro.put("mail.smtp.starttls.enable","true");

        /*---------------发送者的账号密码----------------*/
        // 创建邮件验证信息，即发送邮件的用户名和密码
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 重写验证方法，填写用户名，以及ssl验证码:通过邮箱设置自行获取
                return new PasswordAuthentication(
                        "--", "ssl验证码");
            }
        };

        /*--------------信息的数据-----------------*/
        // 根据邮件会话 构建一个邮件的session
        Session sendMailSession = Session
                .getDefaultInstance(pro, authenticator);
        // 创建一个邮件消息
        Message message = new MimeMessage(sendMailSession);
        // 创建邮件发送者地址
        Address sourceAddress = new InternetAddress("***@qq.com");
        // 将原地址设置到消息的信息中
        message.setFrom(sourceAddress);
        // 创建邮件的接收者地址
        Address destAddress = new InternetAddress("---@163.com");
        // 将接收者的地址设置到消息的信息中
        message.setRecipient(Message.RecipientType.TO, destAddress);
        // 设置邮件的主题
        message.setSubject("Merry Christmas!");
        // 设置邮件的发送内容
        message.setText("你好，圣诞节快乐！");
        Transport.send(message);
    }

}