package wap.backstage;

import LnsmElement.LnsmParameter;
import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import LnsmInitialize.LnsmRegister;
import LnsmUi.ElementInput;
import LnsmUitl.LnsmSystemOut;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * 后台指派红包类
 * Created by ${XiaoHuiHui} on 2017/5/26 on 16:28.
 * XiaoHiiHui [704866169@qq.com]
 */
public class assignRedPackets {
    private WebDriver driver;
    private LnsmParameter lnsmParameter;
    private LnsmUrl lnsmUrl;

    @Before
    public void openBrowser() {
        lnsmParameter = new LnsmParameter();
        lnsmUrl = new LnsmUrl();
        LnsmRegister openBrowser = FoxDriver.getOpenBrowser();
        openBrowser.openBrowser(lnsmUrl.getBossshopUrlLog());
        driver = FoxDriver.getWebDrivaer();
        LnsmSystemOut.getStringOut("-----");
    }


    @Test
    public void redEnvelope() throws Exception {

        //调用方法，实行元素的输入
        ElementInput elementInput = new ElementInput();
        elementInput.accordingToCss("input[name=username][class=form-control]", lnsmParameter.getBossAccount());
        elementInput.accordingToCss("input[name=password][class=form-control]", lnsmParameter.getBossPassWord());

        driver.findElement(By.cssSelector("button[id=loginBtn][type=submint]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.linkText("红包管理")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.linkText("红包设置")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        for (int i = 1; i <= 10; i++) {
            LnsmSystemOut.getStringOut("红包发放:" + i);
            driver.findElement(By.xpath("//*[@id='datatatle']/tbody/tr[" + i + "]/td[7]/button[2]")).click();
            sleep(2000);
            WebElement element = driver.findElement(By.cssSelector("input[class=form-control][id=userid][name=userids]"));
            element.click();
            element.clear();
            element.sendKeys(10609 + "");
            sleep(1000);

            //   driver.findElement(By.cssSelector("button[id=sendFormBut][type=button]")).click();
            driver.findElement(By.xpath("//*[@id='sendFormBut']")).click();
            sleep(1000);

            driver.navigate().refresh();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }
}
