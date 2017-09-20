package wap.business.example;

import common.FoxDriver;
import common.parameter.WapUrl;
import common.tool.SystemOut;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import wap.business.StartData;
import wap.business.example.homeAddress.ManagementHomepage;


/**
 * 程序开始界面
 * By.cssSelector("button[type=button]")写法
 */
public class StartEntrance {
    private WebDriver driver;
    private String webHttp ;
    private WapUrl sWapUrl;


    @Before
    public void setUp() throws Exception {

        //        创建浏览器对象
        driver = FoxDriver.getChromeDriver();
        //        是浏览器的大小
        driver.manage().window().maximize();

        sWapUrl = WapUrl.getsWapUrl();
        StartData.readExcleData();//从计划里面读取用例所在位置
        StartDistinguish.startDistinguish();//判断执行用例的位置
    }


    @Test
    public void testXiao() throws Exception {
        webHttp = WapUrl.urlTop;//定义网址
        FoxDriver.openBrowser(webHttp);//运行
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