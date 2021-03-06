package toolskit.tools.caninput;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
            WebElement element = driver.findElement(By.id(id));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            content = operation(element, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToName(String name, String content) {
        boolean bl = super.accordingToName(name);
        if (bl) {
            WebElement element = driver.findElement(By.name(name));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            content = operation(element, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToCss(String css, String content) {

        boolean bl = super.accordingToCssSelector(css);
        if (bl) {
            WebElement element = driver.findElement(By.cssSelector(css));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            content = operation(element, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToXpath(String xpath, String content) {

        boolean bl = super.accordingToXpath(xpath);
        if (bl) {
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            content = operation(element, content);
        }

        return content;
    }

    //    获取元素对象的标签属性值
    public String accordingToLinkText(String linkText, String content) {

        boolean bl = super.accordingToLinkText(linkText);
        if (bl) {
            WebElement element = driver.findElement(By.linkText(linkText));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            content = operation(element, content);
        }

        return content;
    }

    //  通过content来判断获取元素兑现的值
    public String operation(WebElement element, String content) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                element);
        if (content == null || content.equals("")) {
            content = element.getText();
        } else {
            content = element.getAttribute(content);
        }

        return content;
    }

    public String operation(String iframeLoad, String bodyLoad) {

        //找到ifram对象
        WebElement eleIfram = driver.findElement(By.cssSelector(iframeLoad));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                eleIfram);

        //进入新的布局
        driver.switchTo().frame(eleIfram);

        //布局里面的元素操作
        WebElement eleBody = driver.findElement(By.cssSelector(bodyLoad));

        String textInfo = eleBody.getText();

        //退出iframe布局
        driver.switchTo().defaultContent();

        return textInfo;
    }
}
