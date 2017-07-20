package content.demo;

import common.FoxDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by 70486 on 2017/6/28 on 21:59.
 */

public class testDemo3 {

    @Test
    public void setStart(){
        System.setProperty("webdriver.chrome.driver", "E:\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
    }
}

