package common.tool.caninput;

import common.tool.SystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 通过元素标签来获取元素的内容
 * Created by 70486 on 2017/7/19 on 23:20.
 */
public class ElementObtain extends ElementExistence {

    private WebDriver driver = super.driver;


    //    获取元素对象的标签属性值
    public String accordingToId(String id, String content) {
        boolean bl = super.accordingToId(id);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.id(id));
            content = operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToName(String name, String content) {
        boolean bl = super.accordingToName(name);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.name(name));
            content = operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToCss(String css, String content) {

        boolean bl = super.accordingToCssSelector(css);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.cssSelector(css));
            content = operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToXpath(String xpath, String content) {

        boolean bl = super.accordingToXpath(xpath);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.xpath(xpath));
            content = operation(cfmpassword, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToLinkText(String linkText, String content) {

        boolean bl = super.accordingToLinkText(linkText);
        if (bl) {
            WebElement cfmpassword = driver.findElement(By.linkText(linkText));
            content = operation(cfmpassword, content);
        }

        return content;
    }

    //  通过content来判断获取值的类型
    public String operation(WebElement cfmpassword, String content) {

        if (content == null || content.equals("")) {
            content = cfmpassword.getText();
        } else {
            content = cfmpassword.getAttribute(content);
        }

        return content;
    }

    public String operation(String iframeLoad, String bodyLoad) {

        //找到ifram对象
        WebElement ele = driver.findElement(By.cssSelector(iframeLoad));

        //进入新的布局
        driver.switchTo().frame(ele);

        //布局里面的元素操作
        WebElement element = driver.findElement(By.cssSelector(bodyLoad));

        String textInfo = element.getText();

        //退出iframe布局
        driver.switchTo().defaultContent();

        return textInfo;
    }
}
