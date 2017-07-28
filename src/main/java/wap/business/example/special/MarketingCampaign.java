package wap.business.example.special;

import common.FoxDriver;
import common.tool.caninput.ElementExistence;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * 優惠活動的切換
 * Created by Administrator on 2016/10/31.
 */
public class MarketingCampaign {

    WebDriver driver = FoxDriver.getFoxDriver();

    String [] listBar ;
    String [] url ;
    public void getCampaign(){

        driver.findElement(By.linkText("营销活动")).click();
//        优惠活动
        getDiscount(listBar[0],url[0]);
//        水票
       getWater(listBar[1],url[1]);
    }

    private void getDiscount(String listBar,String url){
        getElsenium(listBar);
        driver.findElement(By.linkText(listBar)).click();
        assertEquals("优惠活动页面没有打开",url,driver.getCurrentUrl());
        System.out.println("优惠活动页面已经打开了");
    }

    private void getWater(String listBar,String url){
        getElsenium(listBar);
        driver.findElement(By.linkText(listBar)).click();
        assertEquals("水票页面没有打开",url,driver.getCurrentUrl());
        System.out.println("水票页面已经打开了");
    }

    private void getElsenium(String listBar){
        if (new ElementExistence().elementLinkText(listBar)){
            System.out.println(listBar + "元素存在。。");
        }else {
            System.out.println(listBar + "元素不存在。。");
        }
    }
}
