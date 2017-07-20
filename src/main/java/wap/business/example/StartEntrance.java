package wap.business.example;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import common.FoxDriver;
import common.parameter.WapUrl;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


/**
 * 程序开始界面
 * By.cssSelector("button[type=button]")写法
 */
public class StartEntrance {
    private WebDriver driver;
    private String webHttp ;

    @Before
    public void setUp() throws Exception {
        webHttp = new WapUrl().getUrlFamily();
        FoxDriver.openBrowser(webHttp);
    }


    @Test
    public void testXiao() throws Exception {
        Signin signin = new Signin();
        signin.getRegister();
    }

/*
    //  关闭浏览器
   @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        System.out.println("浏览器已关闭");
    }
*/

}