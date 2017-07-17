package wap.business.example;

import LnsmElement.LnsmParameter;
import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.LnsmSystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 实现登录
 * Created by Administrator on 2016/9/26.
 * http://seller.52lin.net/goods/comment?page=1
 */
public class signin {
    private WebDriver driver = FoxDriver.getFoxDriver();

    public void getRegister() throws InterruptedException {
        LnsmParameter lnsmParameter = new LnsmParameter();
        //        获取账号输入框并输入内容
        WebElement phone = driver.findElement(By.id("phone"));
        phone.click();
        phone.clear();
        phone.sendKeys(lnsmParameter.getAccount());

//        获取密码输入框并输入内容
        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.clear();
        password.sendKeys(lnsmParameter.getPassWord());

//        点击登录
        LnsmPreservation.getButtonClassName("loginwater");
        //点击登录之后，等待页面加载数据
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sleep(5);
        LnsmUrl lnsmUrl = new LnsmUrl();
        // assertEquals("登录页面的判断",driver.getCurrentUrl(),lnsmUrl.getLoginUrl());
        if (driver.getCurrentUrl().equals(lnsmUrl.getWebHttp())) {
            getWebElement(driver.findElement(By.className("errormsg")));
        } else {
            System.out.println("登录成功");
        }
    }


    //    输出登录失败的愿意.
    private void getWebElement(WebElement element) {
        String str = element.getText();
        switch (str) {
            case "请输入11位正确的手机号":
                //    System.out.println("你的手机号输入有误，请查证后重新输入");
                LnsmSystemOut.getStringOut(str);
                break;
            case "请输入登录密码":
                //   System.out.println("请输入密码在点击登录按钮");
                LnsmSystemOut.getStringOut(str);
                break;
            case "用户名或密码错误":
                //   System.out.println("用户名或密码错误，请查证后重新输入");
                LnsmSystemOut.getStringOut(str);
                break;
            default:
                //       设置验证判断是否登录成功
                LnsmSystemOut.getStringOut("登录成功", str);
                break;
        }
        FoxDriver.shotSelenium();
    }
}
