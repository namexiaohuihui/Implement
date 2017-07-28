package wap.business.example.security;

import common.FoxDriver;
import common.tool.caninput.ElementExistence;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * 賬戶信息菜單里的子目錄切換
 * Created by Administrator on 2016/10/31.
 */
public class SecurityDefence {
    WebDriver driver = FoxDriver.getFoxDriver();
    String []listBar ;
    String [] url ;
    public void getInformation(){
        driver.findElement(By.linkText("账户信息")).click();
//      我的余额
        getDetail(listBar[0],url[0]);
//       账户安全
        getAccount(listBar[1],url[1]);
    }

//    我的余额
    private void getDetail(String bar,String url){
        getElsenium(bar);
        driver.findElement(By.linkText(bar)).click();
        assertEquals("我的余额页面没有打开",url,driver.getCurrentUrl());
        System.out.println("我的余额页面打开了");
    }

//    账户安全
    private void getAccount(String bar,String url){
        getElsenium(bar);
        driver.findElement(By.linkText(bar)).click();
        assertEquals("账户安全页面没有打开",url,driver.getCurrentUrl());
        System.out.println("账户安全页面打开了");
    }
    private void getElsenium(String listBar){
        if (new ElementExistence().elementLinkText(listBar)){
            System.out.println(listBar + "元素存在。。");
        }else {
            System.out.println(listBar + "元素不存在。。");
        }
    }
}
