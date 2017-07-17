package wap.business.example;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * 实现退出登录功能
 * Created by Administrator on 2016/9/22.
 */
public class exitsysos {

    private WebDriver driver = FoxDriver.getFoxDriver();

    public void getExit() throws InterruptedException {
//        退出
        WebElement element1 = driver.findElement(By.className("user-info"));
        element1.click();
        sleep(1000);
        WebElement element2 = driver.findElement(By.linkText("退出登录"));
        element2.click();
    }
}
