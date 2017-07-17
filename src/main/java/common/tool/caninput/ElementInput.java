package common.tool.caninput;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * 元素的输入
 * Created by ${XiaoHuiHui} on 2017/5/26 on 17:09.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ElementInput {

    private WebDriver driver = FoxDriver.getWebDrivaer();


    //    获取元素对象并点击输入内容
    public void accordingToId(String id, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.id(id));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToName(String name, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.name(name));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToCss(String css, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.cssSelector(css));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToXpath(String xpath, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.xpath(xpath));
        operation(cfmpassword, content);
    }

    //    获取元素对象并点击输入内容
    public void accordingToLinkText(String linkText, String content) throws InterruptedException {
        WebElement cfmpassword = driver.findElement(By.linkText(linkText));
        operation(cfmpassword, content);
    }

    //  直接通过对象然后输入内容
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
