package common.tool.caninput;

import common.FoxDriver;
import common.tool.Interface.InheritInput;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            WebElement cfmpassword = driver.findElement(By.id(id));
            operation(cfmpassword, content);
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
            WebElement cfmpassword = driver.findElement(By.name(name));
            operation(cfmpassword, content);
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
            WebElement cfmpassword = driver.findElement(By.cssSelector(css));
            operation(cfmpassword, content);
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
            WebElement cfmpassword = driver.findElement(By.xpath(xpath));
            operation(cfmpassword, content);
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
            WebElement cfmpassword = driver.findElement(By.linkText(linkText));
            operation(cfmpassword, content);
        }
    }

    /**
     * 直接通过对象然后输入内容
     *
     * @param cfmpassword 元素对象
     * @param content     输入的内容
     */
    public void operation(WebElement cfmpassword, String content) {
        //        点击元素
        cfmpassword.click();
//        清空输入框里面的内容
        cfmpassword.clear();
//        输入元素
        cfmpassword.sendKeys(content);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
