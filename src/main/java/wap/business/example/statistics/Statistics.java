package wap.business.example.statistics;

import common.FoxDriver;
import common.parameter.WapUrl;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

/**
 * 數據統計的選擇以及子目錄的切換
 * Created by Administrator on 2016/10/31.
 */
public class Statistics {

    WebDriver driver = FoxDriver.getFoxDriver();

    public void getData() throws InterruptedException {
        new Preservation().buttonLinkText("数据统计");
        getCode("二维码数据");
    }


    private void getCode(String bar) {
        getElsenium(bar);
        driver.findElement(By.linkText(bar)).click();
        assertEquals("二维码数据页面没有打开了",new WapUrl().getCodeFamily(),
                driver.getCurrentUrl());
        System.out.println("二维码数据页面打开了");
    }

    private void getElsenium(String listBar){
        if (new ElementExistence().elementLinkText(listBar)){
            System.out.println(listBar + "元素存在。。");
        }else {
            System.out.println(listBar + "元素不存在。。");
        }
    }
}
