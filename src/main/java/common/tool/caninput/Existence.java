package common.tool.caninput;

import LnsmInitialize.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

/**工具类主要用于判断元素是否存在
 * Created by Administrator on 2016/11/11.
 */
public class Existence {

    private static WebDriver driver = FoxDriver.getFoxDriver();

    //    根据传入的ID来判断该元是否存在
    public  static  boolean getId(String element){

        try {
            driver.findElement(By.id(element));
            System.out.println(element + "：该元素存在");
            return  true;
        }
        catch (Exception x){
            System.out.println(element + "：该元素不存在");
            return  false;
        }
    }

    //    根据传入的tagname来判断该元是否存在
    public  static  boolean getTagName(String element){

        try {
            driver.findElement(By.tagName(element));
            System.out.println(element + "：该元素存在");
            return  true;
        }
        catch (Exception x){
            System.out.println(element + "：该元素不存在");
            return  false;
        }
    }

    //    根据传入的linkText来判断该元是否存在
    public  static  boolean getLinkText(String element){

        try {
            driver.findElement(By.linkText(element));
            return  true;
        }
        catch (Exception x){
            return  false;
        }
    }

    //    根据传入的name来判断该元是否存在
    public  static  boolean getNamw(String element){

        try {
            driver.findElement(By.name(element));
            return  true;
        }
        catch (Exception x){
            return  false;
        }
    }

    //    根据传入的classname来判断该元是否存在
    public  static  boolean getClassName(String element){

        try {
            driver.findElement(By.className(element));
            System.out.println(element + "：该元素存在");
            return  true;
        }
        catch (Exception x){
            System.out.println(element + "：该元素不存在");
            return  false;
        }
    }

    //    根据传入的xpath来判断该元是否存在
    public  static  boolean getXPath(String element){

        try {
            driver.findElement(By.xpath(element));
            System.out.println(element + "：该元素存在");
            return  true;
        }
        catch (Exception x){
            System.out.println(element + "：该元素不存在");
            return  false;
        }
    }

    //每500毫秒扫描一次页面，检查元素是否存在，存在的话返回true，不存在继续等到，直到等到时间超过30秒报错
    public static boolean waitForElement(final By elementLocator) {

        try {
            WebDriverWait driverWait = (WebDriverWait) new WebDriverWait(driver, 30, 500).ignoring(
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
