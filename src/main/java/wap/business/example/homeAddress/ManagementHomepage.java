package wap.business.example.homeAddress;

import common.FoxDriver;
import common.parameter.Parameter;
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

    public ManagementHomepage() throws InterruptedException {
       WebDriver driver = FoxDriver.getFoxDriver();
        sleep(1000);
        WebElement tr = driver.findElement(By.cssSelector("li.hsub.active> a > span"));
        String one = new Parameter().getOneLevel()[0];

        assertEquals("家门口验证失败","---",tr.getText());
    }

}
