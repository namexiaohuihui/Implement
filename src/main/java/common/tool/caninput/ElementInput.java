package common.tool.caninput;

import org.openqa.selenium.*;

import static java.lang.Thread.sleep;

/**
 * 元素的输入
 * Created by ${XiaoHuiHui} on 2017/5/26 on 17:09.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ElementInput extends ElementExistence {

    private WebDriver driver = super.driver;


    /**
     * 获取元素对象并点击输入内容
     *
     * @param id      元素的id
     * @param content 输入的内容
     */
    public void accordingToId(String id, String content) {
        boolean bl = super.accordingToId(id);
        if (bl) {
            WebElement element = driver.findElement(By.id(id));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            operation(element, content);
        }
    }

    /**
     * 获取元素对象并点击输入内容
     *
     * @param name    元素的name
     * @param content 输入的内容
     */
    public void accordingToName(String name, String content) {
        boolean bl = super.accordingToName(name);
        if (bl) {
            WebElement element = driver.findElement(By.name(name));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            operation(element, content);
        }
    }


    /**
     * 获取元素对象并点击输入内容
     *
     * @param css     元素的cssselector
     * @param content 输入的内容
     */
    public void accordingToCssSelector(String css, String content) {
        boolean bl = super.accordingToCssSelector(css);
        if (bl) {
            WebElement element = driver.findElement(By.cssSelector(css));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            operation(element, content);
        }
    }

    /**
     * 获取元素对象并点击输入内容
     *
     * @param xpath   元素的xpath路径
     * @param content 输入的内容
     */
    public void accordingToXpath(String xpath, String content) {

        boolean bl = super.accordingToXpath(xpath);
        if (bl) {
            WebElement element = driver.findElement(By.xpath(xpath));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            operation(element, content);
        }
    }

    /**
     * 获取元素对象并点击输入内容
     *
     * @param linkText 元素的文字描述
     * @param content  输入的内容
     */
    public void accordingToLinkText(String linkText, String content) {

        boolean bl = super.accordingToLinkText(linkText);
        if (bl) {
            WebElement element = driver.findElement(By.linkText(linkText));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                    element);
            operation(element, content);
        }
    }

    /**
     * 直接通过对象然后输入内容
     *
     * @param element 元素对象
     * @param content     输入的内容
     */
    public void operation(WebElement element, String content) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                element);
        //        点击元素
        element.click();
//        清空输入框里面的内容
        element.clear();
//        输入元素
        element.sendKeys(content);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过iframe来输入内容
     *
     * @param iframe  iframe对象的css
     * @param body    body对象的css
     * @param message 内容的输入
     */
    public void operationIframe(String iframe, String body, String message) {

        //找到ifram对象
        WebElement eleIfram = driver.findElement(By.cssSelector(iframe));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                eleIfram);

        //进入新的布局
        driver.switchTo().frame(eleIfram);

        //布局里面的元素操作
        WebElement eleBody = driver.findElement(By.cssSelector(body));
        eleBody.click();
        eleBody.sendKeys(Keys.CONTROL, "a");
        eleBody.sendKeys(Keys.BACK_SPACE);
        eleBody.sendKeys(message);

        //退出iframe布局
        driver.switchTo().defaultContent();
    }
}
