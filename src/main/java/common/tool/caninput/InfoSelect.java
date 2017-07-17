package common.tool.caninput;

import common.tool.SystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * select下拉框的显示
 * Created by Administrator on 2016/12/5.
 */
public class InfoSelect {

    /**
     * 根据text进行设置,选择之后回到顶部
     *
     * @param driver
     * @param statusSelect
     * @param st
     * @throws InterruptedException
     */
    public void categoryText(WebDriver driver, By by, String content) {
        //select对象的确认
        Select select = new Select(driver.findElement(by));
        List<WebElement> options = select.getOptions();
        boolean bl = false;
        //遍历该对象下的全部text
        for (WebElement op : options) {
            //判断是否相等,目的是防止该对象下没有该元素
            if (op.getText().trim().equals(content)) {
                select.selectByVisibleText(content);
                bl = true;
                //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
                movesWindow(driver);
                break;
            }
        }
        if (!bl) {
            SystemOut.getStringOut("content=" + content, "没有设置成功，原因是没有找到这个值");
        }
    }

    /**
     * 根据VALUE进行选择
     *
     * @param driver     对象
     * @param first_cid  第一个select对象
     * @param second_cid 第二个select对象
     * @throws InterruptedException
     */
    public void categoryValue(WebDriver driver, By by, String content) {
        new Select(driver.findElement(by)).selectByValue(content);
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        movesWindow(driver);
    }

    /**
     * 根据select下内容的所在位置来进行选择
     *
     * @param driver     对象
     * @param first_cid  第一个select对象
     * @param second_cid 第二个select对象
     * @throws InterruptedException
     */
    public void categoryIndex(WebDriver driver, By by, int position) {
        new Select(driver.findElement(by)).selectByIndex(position);
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        movesWindow(driver);
    }

    /**
     * 返回select下的全部数据
     *
     * @param driver       对象
     * @param statusSelect 路径
     * @return
     */
    public List<String> propertyValueContent(WebDriver driver, By by, String statusSelect) {
        //select对象的确认
        Select select = new Select(driver.findElement(by));
        List<WebElement> options = select.getOptions();
        List<String> list = new ArrayList<>();
        //遍历该对象下的全部text
        for (WebElement op : options) {
            list.add(op.getText().trim());
        }
        return list;
    }

    /**
     * 返回select下的内容长度
     *
     * @param driver       对象
     * @param statusSelect 路径
     * @return
     */
    public Integer propertyValueSize(WebDriver driver, By by, String statusSelect) {
        //select对象的确认
        Select select = new Select(driver.findElement(by));
        List<WebElement> options = select.getOptions();
        return options.size();
    }


    /*
    通过select对数据筛选之后，窗口会自动执行下移命令。
    通过js代码将其上移回来
     */
    private void movesWindow(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
