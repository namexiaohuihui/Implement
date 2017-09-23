package wap;

import common.FoxDriver;
import common.parameter.WapUrl;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;


/**
 * 程序开始界面
 * By.cssSelector("button[type=button]")写法
 */
public class StartEntrance {
    private WebDriver driver;
    private EnumProgramBean epb;

    @Before
    public void setUp() {
        epb = StartData.readExcleData(1, 1);//从计划里面读取用例所在位置
        WapUrl.urlTop = epb.getFive();//定义网址内容
        FoxDriver.openBrowser();//运行
    }


    @Test
    public void testXiao() {
        StartDistinguish.startDistinguish(epb);//判断执行用例的位置
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