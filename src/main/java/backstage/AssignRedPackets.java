package backstage;


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
import org.openqa.selenium.support.ui.Select;

//import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by ${XiaoHuiHui} on 2017/5/26 on 16:28.
 * XiaoHiiHui [704866169@qq.com]
 * 红包发放
 */
public class AssignRedPackets {
    private WebDriver driver;
    /**
     * 浏览器对象
     */
    private WapUrl wapUrl;

    // 发放人的ID
    private String USER_ID = "243617";

    // 需要发放红包处于第几行
    private String RedBao = "1";

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

        // 方法的数量
        DingDongRenBao(10);

    }

    public void DingDongRenBao(int number) throws InterruptedException {
        if (number == 0) {
            SystemOut.getStringOut("红包发送完毕");
        } else {
            SystemOut.getStringOut("红包剩余发送量:" + number);
            driver.findElement(By.xpath("//*[@id='datatatle']/tbody/tr[" + RedBao + "]/td[7]/button[2]")).click();
            sleep(1000);
            WebElement element = driver.findElement(By.cssSelector("input[class=form-control][id=userid][name=userids]"));
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            element.click();
            element.clear();
            element.sendKeys(USER_ID);
            sleep(1000);

            WebElement bountCause = driver.findElement(By.id("cause"));
            Select bountSelect = new Select(bountCause);
            bountSelect.selectByVisibleText("其他");
//            sleep(1000);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //   driver.findElement(By.cssSelector("button[id=sendFormBut][type=button]")).click();
            driver.findElement(By.xpath(".//*[@id='sendFormBut']")).click();
//            sleep(1000);

            driver.navigate().refresh();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            DingDongRenBao(number - 1);
        }
    }
}
