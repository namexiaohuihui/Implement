package toolskit.tools.caninput;

import toolskit.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * frame文本框内容的输入
 * Created by XiaoHuiHui on 2016/12/28.
 */
public class InfoFrame {
    
    private static WebDriver driver = FoxDriver.getWebDrivaer();

    /**
     * 不需要传入记事本
     *
     * @param driver
     * @param by
     * @param data
     * @throws InterruptedException
     */
    public void editFrameInfo(By by, String data) throws InterruptedException {
        sleep(1000);
//        进入店铺介绍frame框内
        driver.switchTo().frame(driver.findElement(by));
//        找到店铺介绍输入框
        WebElement keContent = driver.findElement(By.className("ke-content"));
//        点击输入框
        keContent.click();
//        ctrl+a全选输入框里面的内容
        keContent.sendKeys(Keys.CONTROL, "a");
//        delect一键删除全选的内容
        keContent.sendKeys(Keys.BACK_SPACE);
//        通过IO流读取文本框的内容，然后输入到介绍的输入框里面
        keContent.sendKeys(data);
//        退出frame
        driver.switchTo().defaultContent();

    }


    /**
     * 返回iframe里面的内容
     * @param by
     * @return
     * @throws InterruptedException
     */
    public String contentFrame(By by) throws InterruptedException {
        sleep(1000);
//        进入店铺介绍frame框内
        driver.switchTo().frame(driver.findElement(by));
//        找到店铺介绍输入框
        WebElement keContent = driver.findElement(By.className("ke-content"));
//        点击输入框
        keContent.click();
//        获取输入框的内容
        String text = keContent.getText();
//        退出frame
        driver.switchTo().defaultContent();
        return text;
    }
}
