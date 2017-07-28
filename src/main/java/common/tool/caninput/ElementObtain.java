package common.tool.caninput;

import common.FoxDriver;
import common.tool.Interface.InheritInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * 通过元素标签来获取元素的内容
 * Created by 70486 on 2017/7/19 on 23:20.
 */
public class ElementObtain extends ElementExistence{

    private WebDriver driver =super.driver;


    //    获取元素对象的标签属性值
    public String accordingToId(String id, String content) throws InterruptedException {
        boolean bl = super.accordingToId(id);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.id(id));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToName(String name, String content) throws InterruptedException {
        boolean bl = super.accordingToName(name);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.name(name));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToCss(String css, String content) throws InterruptedException {

        boolean bl = super.accordingToCssSelector(css);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.cssSelector(css));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToXpath(String xpath, String content) throws InterruptedException {

        boolean bl = super.accordingToXpath(xpath);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.xpath(xpath));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToLinkText(String linkText, String content) throws InterruptedException {

        boolean bl = super.accordingToLinkText(linkText);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.linkText(linkText));
            content =  operation(cfmpassword, content);
        }

        return content;
    }

    //  直接通过对象然后输入内容
    public String operation(WebElement cfmpassword, String content) throws InterruptedException {

        if (content == null || content.equals("")) {
            content = cfmpassword.getText();
        } else {
            content = cfmpassword.getAttribute(content);
        }

        return content;
    }
}
