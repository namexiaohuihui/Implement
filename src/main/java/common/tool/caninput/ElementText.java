package common.tool.caninput;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * Created by 70486 on 2017/7/19 on 23:20.
 */
public class ElementText {

    private WebDriver driver = FoxDriver.getWebDrivaer();


    //    获取元素对象并点击输入内容
    public String accordingToId(String id, String content) throws InterruptedException {
        boolean bl = new Existence().elementId(id);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.id(id));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象并点击输入内容
    public String accordingToName(String name, String content) throws InterruptedException {
        boolean bl = new Existence().elementName(name);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.name(name));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象并点击输入内容
    public String accordingToCss(String css, String content) throws InterruptedException {

        boolean bl = new Existence().elementCssSelector(css);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.cssSelector(css));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象并点击输入内容
    public String accordingToXpath(String xpath, String content) throws InterruptedException {

        boolean bl = new Existence().elementXPath(xpath);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.xpath(xpath));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象并点击输入内容
    public String accordingToLinkText(String linkText, String content) throws InterruptedException {

        boolean bl = new Existence().elementLinkText(linkText);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.linkText(linkText));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //  直接通过对象然后输入内容
    private String operation(WebElement cfmpassword, String content) throws InterruptedException {

        if (content == null || content.equals("")) {
            content = cfmpassword.getText();
        } else {
            content = cfmpassword.getAttribute(content);
        }

        return content;
    }
}
