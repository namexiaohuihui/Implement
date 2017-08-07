package common.tool.caninput;

import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.mysqls.MysqlInquire;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public void categoryText(By by, String content) {
        WebDriver  driver = FoxDriver.getWebDrivaer();
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
                movesWindow();
                break;
            }
        }
        if (!bl) {
            SystemOut.getStringOut("content=" + content, "没有设置成功，原因是没有找到这个值");
        }
    }

    /**
     * 根据VALUE进行选择
     * 1.通过数据库（my）找到符合要求的value，并存到map中（my）
     * 2.循环判断需要设置的value是否符合要求
     * @param by select对象
     * @param content value值
     * @param sql sql语句
     */
    public void categoryValue(By by, String content,String sql) {
        WebDriver  driver = FoxDriver.getWebDrivaer();
        MysqlInquire my = new MysqlInquire();
        boolean bl = false;
        try {
            Map<String, String> aMap = my.dataMysqlColumnRow(sql, 0);
            int sizeRow = aMap.size();
            for (int i = 1;i<=sizeRow;i++){
                if (aMap.get(i).equals(content)){
                    new Select(driver.findElement(by)).selectByValue(content);
                    //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
                    movesWindow();
                    bl = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!bl){
            SystemOut.getStringOut("content=" + content, "没有设置成功，原因是没有找到这个value");
        }
    }

    /**
     * 根据select下内容的所在位置来进行选择
     *
     * @param driver     对象
     * @param first_cid  第一个select对象
     * @param second_cid 第二个select对象
     * @throws InterruptedException
     */
    public void categoryIndex(By by, int position) {
        WebDriver  driver = FoxDriver.getWebDrivaer();
        Select select = new Select(driver.findElement(by));
        Integer integer = propertyLength(select);
        if (position>integer){
            select.selectByIndex(position);
            //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
            movesWindow();
        }else{
            SystemOut.getStringOut("select下标设置长度大于了现有的长度");
        }
    }

    /**
     * 返回select下的全部数据
     *
     * @param driver       对象
     * @param statusSelect 路径
     * @return
     */
    public List<String> propertyValueContent(By by, String statusSelect) {
        WebDriver  driver = FoxDriver.getWebDrivaer();
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
    public Integer propertyValueSize(By by, String statusSelect) {
        WebDriver  driver = FoxDriver.getWebDrivaer();
        //select对象的确认
        Select select = new Select(driver.findElement(by));
        List<WebElement> options = select.getOptions();
        return propertyLength(select);
    }

    public Integer propertyLength( Select select){
        List<WebElement> options = select.getOptions();
        return options.size();
    }

    /*
    通过select对数据筛选之后，窗口会自动执行下移命令。
    通过js代码将其上移回来
     */
    private void movesWindow() {
        WebDriver  driver = FoxDriver.getWebDrivaer();
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
