package common.tool.caninput;

import common.FoxDriver;
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
public class Preservation {

    private WebDriver driver = FoxDriver.getWebDrivaer();

    //    点击保存按钮
    public void breservation(String presevation) throws InterruptedException {
        //判断元素是否存在
        boolean id = new Existence().elementId(presevation);
        if (id) {
            driver.findElement(By.id(presevation)).click();
            sleep(1000);
        }
    }

    /**
     * 点击一个按钮通过吃class类名来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonClassName(String presevation) throws InterruptedException {
        //判断元素是否存在
        boolean className = new Existence().elementClassName(presevation);
        if (className) {
            driver.findElement(By.className(presevation)).click();
            sleep(1000);
        }
    }

    /**
     * 点击一个按钮通过此xpath类名来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonXpath(String presevation) throws InterruptedException {
        //判断元素是否存在
        boolean xPath = new Existence().elementXPath(presevation);
        if (xPath) {
            driver.findElement(By.xpath(presevation)).click();
            sleep(1000);
        }
    }


    /**
     * 点击一个按钮通过text来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonLinkText(String presevation) throws InterruptedException {
        //判断元素是否存在
        boolean linkText = new Existence().elementLinkText(presevation);
        if (linkText) {
            driver.findElement(By.linkText(presevation)).click();
            sleep(1000);
        }
    }

    /**
     * 点击一个按钮通过name来定位该对象。并且进行点击
     *
     * @param presevation
     */
    public void buttonName(String presevation) throws InterruptedException {
        //判断元素是否存在
        boolean namw = new Existence().elementName(presevation);
        if (namw) {
            driver.findElement(By.name(presevation)).click();
            sleep(1000);
        }
    }

    /**
     * 点击一个按钮通过css来定位该元素，并进行点击
     *
     * @param presevation
     */
    public void buttonCssSelector(String presevation) throws InterruptedException {
        boolean css = new Existence().elementCssSelector(presevation);
        if (css) {
            driver.findElement(By.cssSelector(presevation)).click();
            sleep(1000);
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
            e.printStackTrace();
        }
    }


    /**
     * 得到alert\confirm\prompt对话框的对象
     *
     * @param bl
     */
    public String alert(boolean bl) {
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
