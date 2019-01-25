package app;


import toolskit.tools.SystemOut;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static java.lang.Thread.sleep;

/**
 * Created by ${XiaoHuiHui} on 2017/7/17 on 14:46.
 * XiaoHiiHui [704866169@qq.com]
 */
public class app {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    //before test Annotation makes a java function to run every time before a TestNG test case
    @BeforeTest
    protected void createAppiumDriver() throws MalformedURLException, InterruptedException {
        driver = StartSystem.start(driver, wait);
    }

    @Test
    public void setUp() throws MalformedURLException {
        //  driver =  StartSystem.start();
        System.out.println("开始");
        try {
            sleep(9000);
            login();
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //After test Annotation makes a java function to run every time after a TestNG test case
    /*
    @AfterTest
    public void afterTest(){
        System.out.println("结束");
        //quit the driver
        driver.quit();
    }
    */


    private void login() {
        try {
//            driver.switchTo().alert();
//            driver.findElementByName("始终允许");
            driver.findElementById("com.lianni.delivery.develop:id/edt_account").click();
            driver.findElementById("com.lianni.delivery.develop:id/edt_account").clear();
            driver.findElementById("com.lianni.delivery.develop:id/edt_account").sendKeys("11111");
            driver.hideKeyboard();
        }catch (Exception e){
            SystemOut.getStringOut(e.toString());
        }
    }

}
