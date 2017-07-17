package common.tool.emailSend;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by ${XiaoHuiHui} on 2017/2/28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class MailAuthenticator extends Authenticator {

    //邮箱账号
    private String username;

    //邮箱密码
    private String password;

    public MailAuthenticator(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
