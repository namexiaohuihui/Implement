package common.tool.caninput;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

/**
 * 判断元素是否存在
 * Created by Administrator on 2016/11/11.
 */
public class Existence {

    private WebDriver driver = FoxDriver.getFoxDriver();

    //    根据传入的ID来判断该元是否存在
    public boolean elementId(String id) {

        try {
            driver.findElement(By.id(id));
            System.out.println(id + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(id + "：该元素不存在");
            return false;
        }
    }

    //    根据传入的tagname来判断该元是否存在
    public boolean elementTagName(String tagName) {

        try {
            driver.findElement(By.tagName(tagName));
            System.out.println(tagName + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(tagName + "：该元素不存在");
            return false;
        }
    }

    //    根据传入的linkText来判断该元是否存在
    public boolean elementLinkText(String linkText) {

        try {
            driver.findElement(By.linkText(linkText));
            System.out.println(linkText + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(linkText + "：该元素不存在");
            return false;
        }
    }

    //    根据传入的name来判断该元是否存在
    public boolean elementName(String name) {

        try {
            driver.findElement(By.name(name));
            System.out.println(name + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(name + "：该元素不存在");
            return false;
        }
    }

    //    根据传入的classname来判断该元是否存在
    public boolean elementClassName(String className) {

        try {
            driver.findElement(By.className(className));
            System.out.println(className + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(className + "：该元素不存在");
            return false;
        }
    }

    //    根据传入的classname来判断该元是否存在
    public boolean elementCssSelector(String css) {

        try {
            driver.findElement(By.cssSelector(css));
            System.out.println(css + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(css + "：该元素不存在");
            return false;
        }
    }

    //    根据传入的xpath来判断该元是否存在
    public boolean elementXPath(String xpath) {

        try {
            driver.findElement(By.xpath(xpath));
            System.out.println(xpath + "：该元素存在");
            return true;
        } catch (Exception x) {
            System.out.println(xpath + "：该元素不存在");
            return false;
        }
    }

    /**
     * 每500毫秒扫描一次页面，检查元素是否存在，存在的话返回true，
     * 不存在继续等到，直到等到时间超过2秒报错
     * driver.findElement(elementLocator).isDisplayed():isDisplayed检测是否出现
     * @param elementLocator
     * @return
     */
    public boolean waitForElement(final By elementLocator) {

        try {
            WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(driver, 2, 500).ignoring(
                    StaleElementReferenceException.class).withMessage("元素在30秒内没有出现!");
            return driverWait.until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {

                    try {
                        if (driver.findElement(elementLocator).isDisplayed()) {
                            return false;
                        }
                    } catch (IndexOutOfBoundsException e) {
                    } catch (NoSuchElementException e) {
                    }

                    return true;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *  判断元素是否存在之後进行点击，在將判斷是否存在的數據進行返回.
     *  页面最长等待时间为10s
     * @param string
     * @return
     */
    private Boolean existence(final String string) {
        Boolean information = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                d.findElement(By.linkText(string)).click();
                //此处是判断是否存在id为dropdown1的元素，存在返回true
                return d.findElement(By.linkText(string)).isDisplayed();
            }
        });
        return information;
    }

    //判断一个元素是否出现
    public boolean doesWebElementExist(WebDriver driver, By selector) {
        try {
            driver.findElement(selector);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
