package common.tool.caninput;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by 70486 on 2017/7/20 on 0:40.
 */
public class ConfirmByEle {

    private WebDriver driver = FoxDriver.getWebDrivaer();

    By by = null;

    public By accordingToId(String id) {
        boolean bl = new ElementExistence().elementId(id);
        if (bl) {
            by = By.id(id);
        }
        return by;
    }

    public By accordingToName(String name) throws InterruptedException {
        boolean bl = new ElementExistence().elementName(name);
        if (bl) {
            by = By.name(name);
        }
        return by;
    }

    public By accordingToCss(String css) throws InterruptedException {

        boolean bl = new ElementExistence().elementCssSelector(css);
        if (bl) {
            by = By.cssSelector(css);
        }
        return by;
    }

    public By accordingToXpath(String xpath) throws InterruptedException {

        boolean bl = new ElementExistence().elementXPath(xpath);
        if (bl) {
            by = By.xpath(xpath);
        }
        return by;
    }

    //    获取元素对象并点击输入内容
    public By accordingToLinkText(String linkText) throws InterruptedException {

        boolean bl = new ElementExistence().elementLinkText(linkText);
        if (bl) {
            by = By.linkText(linkText);
        }
        return by;
    }

}
