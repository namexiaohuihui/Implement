package wap.business.example.statistics;

import LnsmInitialize.FoxDriver;
import LnsmUitl.LnsmTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * 數據統計的選擇以及子目錄的切換
 * Created by Administrator on 2016/10/31.
 */
public class Statistics {

    WebDriver driver = FoxDriver.getFoxDriver();

    public void getData(){
        driver.findElement(By.linkText("数据统计")).click();

        getCode("二维码数据");
    }


    private void getCode(String bar) {
        getElsenium(bar);
        driver.findElement(By.linkText(bar)).click();
        assertEquals("二维码数据页面没有打开了","http://seller.52lin.net/stats/qrcode/users",
                driver.getCurrentUrl());
        System.out.println("二维码数据页面打开了");
    }

    private void getElsenium(String listBar){
        if (LnsmTool.getLinkText(listBar)){
            System.out.println(listBar + "元素存在。。");
        }else {
            System.out.println(listBar + "元素不存在。。");
        }
    }
}
