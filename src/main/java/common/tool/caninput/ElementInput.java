package common.tool.caninput;

import LnsmInitialize.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

/**
 * Created by ${XiaoHuiHui} on 2017/5/26 on 17:09.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ElementInput {

    private WebDriver driver;

    public ElementInput(String string, String content) {
        driver = FoxDriver.getWebDrivaer();
    }

    public ElementInput() {
        driver = FoxDriver.getWebDrivaer();
    }

    //    获取元素对象并点击输入内容
    public void accordingToId(String string, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.id(string));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToName(String string, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.name(string));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToCss(String string, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.cssSelector(string));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToXpath(String string, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.xpath(string));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToLinkText(String string, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.linkText(string));

        operation(cfmpassword, content);
    }


    private void operation(WebElement cfmpassword, String content) throws InterruptedException {
 //        点击元素
        cfmpassword.click();
//        清空输入框里面的内容
        cfmpassword.clear();
//        输入元素
        cfmpassword.sendKeys(content);
        sleep(1000);
    }
}
