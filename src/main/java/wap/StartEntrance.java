package wap;

import common.FoxDriver;
import common.parameter.Parameter;
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
    private WapUrl sWapUrl;


    @Before
    public void setUp() throws Exception {
        EnumProgramBean epb = StartData.readExcleData(1,1);//从计划里面读取用例所在位置
        WapUrl.urlTop = epb.getFour();//定义网址内容
        FoxDriver.openBrowser();//运行
    }


    @Test
    public void testXiao() throws Exception {
        StartDistinguish.startDistinguish();//判断执行用例的位置
    }

    private void startParameter(){
        WapUrl sWapUrl = WapUrl.getsWapUrl();//创建网址对象
        Parameter parameter = Parameter.getParameter();//创建网址对象
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