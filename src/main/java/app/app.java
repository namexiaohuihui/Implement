package app;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;

import static java.lang.Thread.sleep;

/**
 * Created by ${XiaoHuiHui} on 2017/7/17 on 14:46.
 * XiaoHiiHui [704866169@qq.com]
 */
public class app {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    //before Test Annotation makes a java function to run every time before a TestNG test case
    @BeforeTest
    protected void createAppiumDriver() throws MalformedURLException, InterruptedException {
        StartSystem.start(driver,wait);
    }

    @Test
    public void setUp(){
        System.out.println("开始");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //After Test Annotation makes a java function to run every time after a TestNG test case
    /*
    @AfterTest
    public void afterTest(){
        System.out.println("结束");
        //quit the driver
        driver.quit();
    }
    */
}
