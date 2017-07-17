package wap.business.example.interactive;

import LnsmInitialize.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * 訂單目錄的選擇以及子目錄的切換
 * Created by Administrator on 2016/10/27.
 */
public class Interactivve {
    WebDriver driver = FoxDriver.getFoxDriver();

    /*获取左边菜单
     List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='sidebar']/div[1]/div[1]/div/ul"));
        for (WebElement kk : elements){
        System.out.println( kk.getText());
        }

        String kk = driver.findElement(By.className("nav-wrap")).getText();
        List<WebElement> arrow = driver.findElements(By.className("hsub"));
        int i = 1;
        for (WebElement op : arrow){
            System.out.println("第" + i + "个数是:" + op.getText());
            ++i;
        }
     */
   String [] str = new String[]{"普通订单","水票订单","门店订单","自提订单","退款/货订单"};
   String [] list = new String[]{"http://seller.52lin.net/orders",
            "http://seller.52lin.net/orders/watiki","http://seller.52lin.net/orders/store",
            "http://seller.52lin.net/orders/fetch","http://seller.52lin.net/repair"};

    public void getOrder(){
        driver.findElement(By.linkText("订单管理")).click();

//        普通订单
        getOrdinary(str[0],list[0]);
//        水票订单
        getWater(str[1],list[1]);
//        门店订单
        getStore(str[2],list[2]);
//        自提订单
        getSince(str[3],list[3]);
//        退款/货订单"
        getReturn(str[4],list[4]);
    }

    private void getOrdinary(String string,String listString){
        driver.findElement(By.linkText(string)).click();
        assertEquals(string+"页面没有被打开",listString,driver.getCurrentUrl());
        System.out.println(string + "页面打开了");
    }
    private void getWater(String string,String listString){
        driver.findElement(By.linkText(string)).click();
        assertEquals(string+"页面没有被打开",listString,driver.getCurrentUrl());
        System.out.println(string + "页面打开了");
    }
    private void getStore(String string,String listString){
        driver.findElement(By.linkText(string)).click();
        assertEquals(string+"页面没有被打开",listString,driver.getCurrentUrl());
        System.out.println(string + "页面打开了");
    }
    private void getSince(String string,String listString){
        driver.findElement(By.linkText(string)).click();
        assertEquals(string+"页面没有被打开",listString,driver.getCurrentUrl());
        System.out.println(string + "页面打开了");
    }
    private void getReturn(String string,String listString){
        driver.findElement(By.linkText(string)).click();
        assertEquals(string+"页面没有被打开",listString,driver.getCurrentUrl());
        System.out.println(string + "页面打开了");
    }
}
