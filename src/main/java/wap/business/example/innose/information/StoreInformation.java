package wap.business.example.innose.information;


import common.FoxDriver;
import common.tool.caninput.*;
import common.tool.mysqls.MysqlInquire;
import common.tool.upload.PictureImage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import wap.business.example.innose.Information;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * 信息
 * Created by Administrator on 2016/11/1.
 */
public class StoreInformation extends Information {

    //    记录店铺执照上传的对象，方便循环执行
    String photo[];
    String license[];
    WebDriver driver = super.driver;

    //    实拍和执照的上传按钮class名
    private String button = "uploadify-button ";

    public void informationStore() throws InterruptedException, SQLException {
//        通過網址進行驗證店鋪信息頁面是否打開。
        assertEquals("頁面沒打開:" + super.mainHome, super.url[0], driver.getCurrentUrl());

        //判断店名是否有内容、有就说明设置过内容然后就调用判断的方法
        // 没有内容就对其进行设置
        WebElement storeName = driver.findElement(By.id("name"));
        if ( storeName.getAttribute("value").length()>3){
            System.out.println("该店铺已设置过数据");
            InformationJudgment iju = new InformationJudgment();
            setLocal();//调试
        }else{
            System.out.println("该店铺没有设置过数据");
        }
    }

    private void setLocal() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//*[@class='referBtn']/input"));
        jse.executeScript("arguments[0].click();", element);
    }

    //根据cssSelector来进行元素输入
    public void storeName(String cssSelector, String content){
        ElementInput eInput = new ElementInput();
        eInput.accordingToCssSelector(cssSelector,content);
    }

}