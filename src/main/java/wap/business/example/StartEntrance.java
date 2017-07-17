package wap.business.example;

import LnsmElement.LnsmUrl;
import LnsmList.CommodityManage;
import LnsmUi.LnsmAccount;
import LnsmUitl.LnsmSystemOut;
import common.FoxDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * 程序开始界面
 * By.cssSelector("button[type=button]")写法
 */
public class StartEntrance {
    private WebDriver driver;
    private String webHttp ;

    @Before
    public void setUp() throws Exception {
        webHttp = new LnsmUrl().getWebHttp();
        openBrowser(webHttp);
    }


    @Test
    public void testXiao() throws Exception {

//      实现登录
        new LnsmAccount().getRegister();
      //  setDangDuTest();
//       实现注册
        //  new possessor().getUrl();

//        管理首页
        //  new ManagementHomepage().getHomepage();

//        店铺管理
 //       new Information().getStore();

//        商品管理
        new CommodityManage().getStore();
//        订单管理
        //   new Interactivve().getOrder();
//        营销活动
        //  new MarketingCampaign().getCampaign();
//        数据统计
        //  new Statistics().getData();
//        账户信息
        //   new AccountInformation().getInformation();
    }
/*
    //  关闭浏览器
   @After
    public void tearDown() throws InterruptedException {
        driver.quit();
        System.out.println("浏览器已关闭");
    }
*/


    private void setDangDuTest(){
        driver.get("http://seller.52lin.net/sporders/order/10325");
        String text = driver.findElement(By.cssSelector("td>input[id=text_box]")).getAttribute("value");
        LnsmSystemOut.getStringOut("最小订货量",text);
    }

    public void openBrowser(String webHttp){
        //        创建浏览器对象
        driver = FoxDriver.getFoxDriver();
//        是浏览器的大小
        driver.manage().window().maximize();
        //        设置测试的网页
        driver.get(webHttp);
//        设置网页超时的时间
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String title = driver.getTitle();
        System.out.println("打开页面的标题" + title);
        if (title.equals("页面载入出错")) {
            driver.get(webHttp);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } else if (title.equals("连接超时")) {
            driver.get(webHttp);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }
}