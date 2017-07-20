package wap;

import common.FoxDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ${XiaoHuiHui} on 2017/7/17 on 14:46.
 * XiaoHiiHui [704866169@qq.com]
 */
public class wap {
    @Before
    public void beforeClass() {
        System.out.println("用例前执行打印本句！");
        System.out.println("每条Test用例是互不相干的");
        System.out.println("用例开始执行…………");
    }

    @After
    public void afterClass(){
        System.out.println("用例结束后运行");
    }

    @Test
    public void actions() {
        //引用火狐浏览器驱动
        WebDriver driver = FoxDriver.getChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //打开禅道界面
        driver.get("http://www.zentao.net/user-login.html");
        //以下元素使用css格式 -cssSelector
        //输入账号
        driver.findElement(By.cssSelector("#account")).sendKeys("baidutest01x");
        //输入密码
        driver.findElement(By.cssSelector("#password")).sendKeys("pswd111");
        //点击登录
        driver.findElement(By.id("submit")).click();

        //抓取成功登录后的用户名信息
        String text = driver.findElement(By.cssSelector("#siteNav > a:nth-child(4)")).getText();
        //断言-校验是否登录成功
        Assert.assertEquals(text,"懵");

        try {
            //页面等待
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //关闭浏览器进程及驱动
        driver.close();

    }
}
