package wap.business.example.interactive;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * 目录:
 * 字体、门垫、退还
 * Created by Administrator on 2016/10/27.
 */
public class Interactivve {
    WebDriver driver = FoxDriver.getFoxDriver();
   String [] str ;
   String [] list ;

    public void getOrder(){
        driver.findElement(By.linkText("订单管理")).click();

//        订单
        getOrdinary(str[0],list[0]);
//        订单
        getWater(str[1],list[1]);
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
}
