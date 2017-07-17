package common;

import LnsmUitl.SystemOut;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * 获取火狐对象
 * Created by Administrator on 2016/9/22.
 
 
 //                       _oo0oo_
 //                      o8888888o
 //                      88" . "88
 //                      (| -_- |)
 //                      0\  =  /0
 //                    ___/`---'\___
 //                  .' \\|     |// '.
 //                 / \\|||  :  |||// \
 //                / _||||| -:- |||||- \
 //               |   | \\\  -  /// |   |
 //               | \_|  ''\---/''  |_/ |
 //               \  .-\__  '-'  ___/-. /
 //             ___'. .'  /--.--\  `. .'___
 //          ."" '<  `.___\_<|>_/___.' >' "".
 //         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 //         \  \ `_.   \_ __\ /__ _/   .-` /  /
 //     =====`-.____`.___ \_____/___.-`___.-'=====
 //                       `=---='
 //
 //
 //     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 //
 //               佛祖保佑         永无BUG
 
 
 
 */
public class FoxDriver {

    private static WebDriver driver;

    public static WebDriver getWebDrivaer(){
        if (driver==null){
            SystemOut.getStringOut("浏览器对象为空");
        }
       return driver;
    }

    public static  void shotSelenium(){
        System.exit(-1);
    }

    /**获取对象：火狐
     * 如果不是默认路径安装，需要先将firefox.exe配置到系统变量中去，如下：
     System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
     WebDriver driver = new FirefoxDriver();
     * @return
     */
    public static WebDriver getFoxDriver() {
        if (driver==null){
            SystemOut.getStringOut("浏览器对象为空");
            System.setProperty("webdriver.firefox.bin", "E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    /**获取对象：IE
     * 如果ie不是默认路径安装需要配置bin路径到系统属性中去
     Tip:加载IEDriverServer的时候，通常会因为兼容模式的设置问题，而无法启动，尝试在创建IEDriver对象的时候 加入合适的参数设置：
     DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
     ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
     WebDriver driver =
     new InternetExplorerDriver(ieCapabilities);
     * @return
     */
    public static WebDriver getIEDriver() {
        if (driver == null) {
            System.setProperty("webdriver.ie.driver",
                    "src\\main\\java\\WebDriver\\IEDriverServer.exe");
            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            driver= new InternetExplorerDriver(ieCapabilities);
        }
        return driver;
    }

    //   获取对象：谷歌
    public static WebDriver getChromeDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver",
                    "src\\main\\java\\WebDriver\\chromedriver58-60.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }


    public static LnsmRegister getOpenBrowser(){
        return  new LnsmRegister();
    }
}
