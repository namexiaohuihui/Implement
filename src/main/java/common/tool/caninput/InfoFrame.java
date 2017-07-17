package common.tool.caninput;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static LnsmUitl.LnsmFile.getIntroductionFile;
import static java.lang.Thread.sleep;

/**
 * Created by XiaoHuiHui on 2016/12/28.
 */
public class InfoFrame {

    /**
     * 进入frame的方法
     * @param driver  driver对象
     * @param frame   frame名字
     * @param name    记事本的名字
     * @throws InterruptedException
     */
    public InfoFrame(WebDriver driver, String frame, String data) throws InterruptedException {
        sleep(1000);
//        进入店铺介绍frame框内
        driver.switchTo().frame(driver.findElement(By.xpath(frame)));
//        找到店铺介绍输入框
        WebElement keContent = driver.findElement(By.className("ke-content"));
//        点击输入框
        keContent.click();
//        ctrl+a全选输入框里面的内容
        keContent.sendKeys(Keys.CONTROL, "a");
//        delect一键删除全选的内容
        keContent.sendKeys(Keys.BACK_SPACE);
//        通过IO流读取文本框的内容，然后输入到店铺介绍的输入框里面
        keContent.sendKeys(getIntroductionFile(data));
//        退出frame
        driver.switchTo().defaultContent();
    }

    public void LnsmFrameCss(WebDriver driver,String frame,String data) throws InterruptedException {
        sleep(1000);
//        进入店铺介绍frame框内
        driver.switchTo().frame(driver.findElement(By.cssSelector(frame)));
//        找到店铺介绍输入框
        WebElement keContent = driver.findElement(By.className("ke-content"));
//        点击输入框
        keContent.click();
//        ctrl+a全选输入框里面的内容
        keContent.sendKeys(Keys.CONTROL, "a");
//        delect一键删除全选的内容
        keContent.sendKeys(Keys.BACK_SPACE);
//        通过IO流读取文本框的内容，然后输入到店铺介绍的输入框里面
       // keContent.sendKeys(getIntroductionFile(data));
        keContent.sendKeys(data);
//        退出frame
        driver.switchTo().defaultContent();
    }
    public String LnsmFrameCss(WebDriver driver,String frame) throws InterruptedException {
        sleep(1000);
//        进入店铺介绍frame框内
        driver.switchTo().frame(driver.findElement(By.cssSelector(frame)));
//        找到店铺介绍输入框
        WebElement keContent = driver.findElement(By.className("ke-content"));
//        点击输入框
        keContent.click();
//        获取输入框的内容
        String text = keContent.getText();
//        退出frame
        driver.switchTo().defaultContent();
        return  text;
    }

    public InfoFrame() {
    }

    public void LnsmFrame(WebDriver driver, String frame, String data) throws InterruptedException {
        sleep(1000);
//        进入店铺介绍frame框内
        driver.switchTo().frame(driver.findElement(By.xpath(frame)));
//        找到店铺介绍输入框
        WebElement keContent = driver.findElement(By.className("ke-content"));
//        点击输入框
        keContent.click();
//        ctrl+a全选输入框里面的内容
        keContent.sendKeys(Keys.CONTROL, "a");
//        delect一键删除全选的内容
        keContent.sendKeys(Keys.BACK_SPACE);
//        通过IO流读取文本框的内容，然后输入到店铺介绍的输入框里面
        keContent.sendKeys(data);
//        退出frame
        driver.switchTo().defaultContent();
    }



}
