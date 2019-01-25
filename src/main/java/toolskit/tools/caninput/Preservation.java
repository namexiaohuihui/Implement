package toolskit.tools.caninput;

import toolskit.FoxDriver;
import toolskit.tools.informationException.ErrorException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;


/**
 * 该类为事件点击类：
 * 负责点击某个元素对象
 * Created by Administrator on 2016/11/15.
 */
public class Preservation extends ElementExistence {

    private WebDriver driver = FoxDriver.getWebDrivaer();

    //    点击保存按钮
    public void breservation(String id) {
        //判断元素是否存在
        try {
            boolean bl = super.accordingToId(id);
            if (bl) {
                driver.findElement(By.id(id)).click();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击一个按钮通过吃class类名来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonClassName(String className) {
        //判断元素是否存在
        try {
            boolean bl = super.accordingToCssName(className);
            if (bl) {
                driver.findElement(By.className(className)).click();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击一个按钮通过此xpath类名来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonXpath(String xPath) {
        //判断元素是否存在
        try {
            boolean bl = super.accordingToXpath(xPath);
            if (bl) {
                driver.findElement(By.xpath(xPath)).click();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 点击一个按钮通过text来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonLinkText(String linkText) {
        //判断元素是否存在
        try {
            boolean bl = super.accordingToLinkText(linkText);
            if (bl) {
                driver.findElement(By.linkText(linkText)).click();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击一个按钮通过name来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonName(String Name) throws InterruptedException {
        //判断元素是否存在
        try {
            boolean bl = super.accordingToName(Name);
            if (bl) {
                driver.findElement(By.name(Name)).click();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击一个按钮通过css来定位该元素，并进行点击
     *
     * @param presevation
     */
    public void buttonCssSelector(String cssSelector) {
        try {
            boolean bl = super.accordingToCssSelector(cssSelector);
            if (bl) {
                driver.findElement(By.cssSelector(cssSelector)).click();
                sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击一个按钮通过元素来进行点击
     *
     * @param presevation
     */
    public void webElementOb(WebElement webElement) {
        try {
            webElement.click();
            sleep(1000);
        } catch (InterruptedException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
    }


    /**
     * 得到alert\confirm\prompt对话框的对象
     *
     * @param bl
     */
    public String alertSystem(boolean bl) {
        Alert alert = driver.switchTo().alert();
            /*
             getText()    得到它的文本值
             accept()      相当于点击它的"确认"
             dismiss()     相当于点击"取消"或者叉掉对话框
             sendKeys() 输入值，这个alert\confirm没有对话框就不能用了，不然会报错。
             */
        //点击确认返回
        String text = alert.getText();
        if (bl) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return alert.getText();
    }

}
