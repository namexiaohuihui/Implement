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
        System.out.println("666");
        //relative path to apk file
        final File classpathRoot = new File(System.getProperty("user.dir"));
        final File appDir = new File(classpathRoot, "E:\\drivers");
        final File app = new File(appDir, "weixin6510android1080.apk");

        //setting up desired capability
        DesiredCapabilities caps = new DesiredCapabilities();
        //指定测试机的ID,通过adb命令`adb devices`获取
        caps.setCapability("browserName", "72836533");
        //指定测试平台
        caps.setCapability("platform", "ANDROID");
        caps.setCapability("platformVersion", "5.1.1");
        caps.setCapability("deviceName", "ANDROID");
        caps.setCapability("app", app.getAbsolutePath());

        //将上面获取到的包名和Activity名设置为值
        caps.setCapability("appPackage", "com.tencent.mm");
        caps.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
        //A new session could not be created的解决方法(A new session could not be created:无法创建新会话)
        caps.setCapability("appWaitActivity","com.tencent.mm.ui.LauncherUI");
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        caps.setCapability("sessionOverride", true);

        //初始化驱动对象
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        //初始化显式等待对象
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void setUp(){
        System.out.println("开始");
    }
    //After Test Annotation makes a java function to run every time after a TestNG test case
    @AfterTest
    public void afterTest(){
        System.out.println("结束");
        //quit the driver
        driver.quit();
    }
}
