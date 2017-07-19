package wap.business.example.homeAddress;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 家门口址的驗證
 * Created by Administrator on 2016/10/31.
 */
public class ManagementHomepage {

    private WebDriver driver = FoxDriver.getFoxDriver();

    public void getHomepage() throws InterruptedException {
        sleep(1000);
        WebElement element = driver.findElement(By.xpath(".//*[@id='breadcrumbs']/ul/li[2]"));
        assertEquals("家门口验证失败","---",element.getText());
        System.out.println("家门口验证成功");
    }
}
