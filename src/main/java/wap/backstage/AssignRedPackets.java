package wap.backstage;


import common.FoxDriver;
import common.parameter.Parameter;
import common.parameter.WapUrl;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * 头头红包类
 * Created by ${XiaoHuiHui} on 2017/5/26 on 16:28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class AssignRedPackets {
    private WebDriver driver;
    private WapUrl wapUrl;
    private String USER_ID = "243617";

    @Before
    public void openBrowser() {
        wapUrl = new WapUrl();
        driver = FoxDriver.openBrowser();
    }

    @After
    public void afterClass() {
        System.out.println("程序运行完毕");
        driver.quit();
    }

    @Test
    public void redEnvelope() throws Exception {

        //调用方法，实行元素的输入
        ElementInput elementInput = new ElementInput();
        //账号密码输入
        elementInput.accordingToCssSelector("input[name=username][class=form-control]",
                Parameter.accountTop);
        elementInput.accordingToCssSelector("input[name=password][class=form-control]",
                Parameter.passWordTop);
        // 登陆按钮
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.btn-flat")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.linkText("推广管理")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.linkText("红包管理")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.linkText("红包设置")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        for (int i = 1; i <= 10; i++) {
            SystemOut.getStringOut("红包发放:" + i);
            driver.findElement(By.xpath("//*[@id='datatatle']/tbody/tr[1]/td[7]/button[2]")).click();
            sleep(2000);
            WebElement element = driver.findElement(By.cssSelector("input[class=form-control][id=userid][name=userids]"));
            element.click();
            element.clear();
            element.sendKeys(USER_ID);
            sleep(1000);

            //   driver.findElement(By.cssSelector("button[id=sendFormBut][type=button]")).click();
            driver.findElement(By.xpath(".//*[@id='sendFormBut']")).click();
            sleep(1000);

            driver.navigate().refresh();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        SystemOut.getStringOut("红包发放完毕...");
    }
}
