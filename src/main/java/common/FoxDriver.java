package common;

import common.parameter.WapUrl;
import common.tool.SystemOut;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * 获取火狐对象
 * Created by Administrator on 2016/9/22.
 * <p>
 * <p>
 * //                       _oo0oo_
 * //                      o8888888o
 * //                      88" . "88
 * //                      (| -_- |)
 * //                      0\  =  /0
 * //                    ___/`---'\___
 * //                  .' \\|     |// '.
 * //                 / \\|||  :  |||// \
 * //                / _||||| -:- |||||- \
 * //               |   | \\\  -  /// |   |
 * //               | \_|  ''\---/''  |_/ |
 * //               \  .-\__  '-'  ___/-. /
 * //             ___'. .'  /--.--\  `. .'___
 * //          ."" '<  `.___\_<|>_/___.' >' "".
 * //         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //         \  \ `_.   \_ __\ /__ _/   .-` /  /
 * //     =====`-.____`.___ \_____/___.-`___.-'=====
 * //                       `=---='
 * //
 * //
 * //     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * //
 * //               佛祖保佑         永无BUG
 */
public class FoxDriver {

    private static WebDriver driver;

    private static String loadRoute = "E:\\drivers\\Drivers\\";

    private static int recording = 1;//统计网页打开错误次数

    public static WebDriver getWebDrivaer() {
        if (driver == null) {
            SystemOut.getStringOut("调用时提示：浏览器对象为空");
        }
        return driver;
    }

    /**
     * 获取对象：火狐
     * 如果不是默认路径安装，需要先将firefox.exe配置到系统变量中去，如下：
     * System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
     * WebDriver driver = new FirefoxDriver();
     *
     * @return
     */
    public static WebDriver getFoxDriver() {
        if (driver == null) {
            SystemOut.getStringOut("创建时---火狐：浏览器对象为空");
            System.setProperty("webdriver.firefox.bin", "E:\\Program Files\\Mozilla Firefox\\firefox.exe");
           // System.setProperty("webdriver.firefox.driver", loadRoute + "geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    /**
     * 获取对象：IE
     * 如果ie不是默认路径安装需要配置bin路径到系统属性中去
     * Tip:加载IEDriverServer的时候，通常会因为兼容模式的设置问题，而无法启动，尝试在创建IEDriver对象的时候 加入合适的参数设置：
     * DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
     * ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
     * WebDriver driver =
     * new InternetExplorerDriver(ieCapabilities);
     *
     * @return
     */
    public static WebDriver getIEDriver() {
        if (driver == null) {
            SystemOut.getStringOut("创建时---IE：浏览器对象为空");
            System.setProperty("webdriver.ie.driver",
                    loadRoute + "IEDriverServer.exe");
            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            driver = new InternetExplorerDriver(ieCapabilities);
        }
        return driver;
    }

    //   获取对象：谷歌
    public static WebDriver getChromeDriver() {
        if (driver == null) {
            SystemOut.getStringOut("创建时---谷歌：浏览器对象为空");
            System.setProperty("webdriver.chrome.driver", loadRoute + "chromedriver59-61.exe");
            driver = new ChromeDriver();

        }
        return driver;
    }

    public static WebDriver openBrowser() {
        //        创建浏览器对象
        driver = FoxDriver.getChromeDriver();
        //        是浏览器的大小
        driver.manage().window().maximize();
        //        设置测试的网页
        driver.get(WapUrl.urlTop);
        //        设置网页超时的时间
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String title = driver.getTitle();
        System.out.println("打开页面的标题" + title);

        if (title.equals("页面载入出错")) {
            recordingStatistics(WapUrl.urlTop);//重新加载网页
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        } else if (title.equals("连接超时")) {
            recordingStatistics(WapUrl.urlTop);//重新加载网页
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        recording = 1;//网址能打开之后就情况统计次数
        SystemOut.getStringOut("第" + recording, "次成功打开页面");
        return driver;
    }


    public static void recordingStatistics(String webHttp) {
        recording += 1;//每次进来这个方法都加1次
        if (recording == 3) {
            SystemOut.getStringOut("连续打开" + recording, "次都没能打开页面");
            shotSelenium();
        }
        //刷新页面
        // driver.navigate().refresh();
        //重新加载
        driver.get(webHttp);
    }

    public static void shotSelenium() {
        System.exit(-1);
    }
}
