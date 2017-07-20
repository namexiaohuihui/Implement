package wap.business.example;

import common.FoxDriver;
import common.parameter.Parameter;
import common.parameter.WapUrl;
import common.tool.caninput.ElementInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 实现注册
 * Created by Administrator on 2016/9/28.
 */
public class Possessor {

    private WebDriver driver = FoxDriver.getFoxDriver();
    private String passWord ;
    private String name ;
    private WapUrl wapurl = new WapUrl();
    private Parameter parameter = new Parameter();
    private ElementInput elementInput = new ElementInput();
    public void getUrl() throws InterruptedException {

        name = parameter.getAccountFamily();
        passWord = parameter.getPassWordFamily();

        //        在忘记密码界面点击找回密码
        driver.findElement(By.linkText("找回密码")).click();
        sleep(1000);
//        在找回密码界面设立检查点，如果为真就点击进入注册页面
        String url = wapurl.getPasswordRetrievalFamily();
        assertEquals("找回密码页面",driver.getCurrentUrl(),url);
        driver.findElement(By.xpath("//*[@title = '注册']")).click();
        System.out.println("忘记密码页面检查通过");

//        在注册页面设立检查点，如果为真就开始在输入框输入内容
        String registrteredUrl =wapurl.getRegisterFamily();
        assertEquals("找回密码页面",driver.getCurrentUrl(),registrteredUrl);
        System.out.println("注册页面检查通过");

        //    获取账号输入框
        elementInput.accordingToName("phone",name);
        //    获取短信验证码输入框
        elementInput.accordingToName("vercode","111111");
        //    获取登录密码输入框
        elementInput.accordingToName("password",passWord);
        //    获取确定密码输入框
        elementInput.accordingToName("cfmpassword", passWord + "1");
        //    获取单选框对象
        getRadio();
        //    获取注册按钮对象
        getButton();
    }
//    获取单选框对象
    private void getRadio() {
        WebElement checkbox = driver.findElement(By.name("checkbox"));
        if (checkbox.isDisplayed()){
            System.out.println("记住协议已经点击了");
        }else{
            System.out.println("记住协议还没点击，现在进行点击。。");
            checkbox.click();
        }
        checkbox.click();
    }
//    获取注册按钮对象
    private void getButton() throws InterruptedException {
        WebElement enroll_shop = driver.findElement(By.id("enroll_shop"));
        enroll_shop.click();
        sleep(2000);
        WebElement aui_content = driver.findElement(By.className("aui_content"));
        if (aui_content.isDisplayed()){
            System.out.println(name+ aui_content.getText());
        }
        else if (driver.findElement(By.className("cfmpassword")).isDisplayed()){
            System.out.println("你两次输入的密码不一致，请重新输入");
            elementInput.accordingToName("cfmpassword", passWord);
            System.out.println("正在重新输入密码");
            getButton();
        }
        else{
        System.out.println("注册成功" + "账号为:" + name + "密码为：" + passWord);
        }

    }

}
