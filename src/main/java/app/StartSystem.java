package app;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ${XiaoHuiHui} on 2017/7/31 on 15:43.
 * XiaoHiiHui [704866169@qq.com]
 */
public class StartSystem {

    public static AndroidDriver start(AndroidDriver driver, WebDriverWait wait) throws MalformedURLException {
        System.out.println("配置开始读取");
        //relative path to apk file
        // final File classpathRoot = new File(System.getProperty("user.dir"));
        //final File appDir = new File(classpathRoot, "E:\\drivers");
//        final File appDir = new File("C:\\Users\\Administrator\\Desktop");
//        final File app = new File(appDir, "app-Test_-release_08101700.apk");

        //setting up desired capability
        DesiredCapabilities caps = new DesiredCapabilities();
        //指定测试机的ID,通过adb命令`adb devices`获取
//        caps.setCapability("browserName", "192.168.10.155:5555");
        caps.setCapability("deviceName", "64535188");
        //指定测试平台
        caps.setCapability("platform", "ANDROID");
        caps.setCapability("platformVersion", "7.1.1");
        caps.setCapability("deviceName", "ANDROID");
//        caps.setCapability("app", app.getAbsolutePath());

        //将上面获取到的包名和Activity名设置为值
        caps.setCapability("appPackage", "com.lianni.delivery");
        caps.setCapability("appActivity", ".StartActivity");
        //A new session could not be created的解决方法(A new session could not be created:无法创建新会话)
        caps.setCapability("appWaitActivity", ".StartActivity");
        //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        caps.setCapability("sessionOverride", true);

        //初始化驱动对象
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

        //初始化显式等待对象
        wait = new WebDriverWait(driver, 10);
        return driver;
    }
}
