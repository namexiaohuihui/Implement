package common.tool.caninput;

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
 * Created by Administrator on 2016/12/5.
 */
public class InfoSelect {

    //选择商品的状态
    public InfoSelect() {

    }

    //    设置省份、城市、县城
    public InfoSelect(WebDriver driver, String province, String city, String county) throws InterruptedException {
        System.out.println("自提设置城市进入了。。。。");
        sleep(1000);
        new Select(driver.findElement(By.name("province"))).selectByValue(province);
        new Select(driver.findElement(By.name("city"))).selectByValue(city);
        new Select(driver.findElement(By.name("county"))).selectByValue(county);
        sleep(1000);
    }

    //选择商品的状态
    public InfoSelect(WebDriver driver, String name, String province) throws InterruptedException {
        System.out.println("自提设置城市进入了。。。。");
        sleep(1000);
        new Select(driver.findElement(By.name(name))).selectByValue(province);
        sleep(1000);
    }


    /***
     * 商家管理--店铺信息--设置店铺的所在城市
     * @param driver   WebDriver对象
     * @param number   省份所代表的id
     * @param value    value值所代表的城市
     * @param option   区的名字
     * @throws InterruptedException
     */
    public void getAddress(WebDriver driver, int number, String value, String option) throws InterruptedException {
//        读取省份
        Select province = new Select(driver.findElement(By.id("province")));
        System.out.println("省份选择" + new Date().toString());
//        判断省份对象是否存在
        if (province != null) {
//            设置省份
            province.selectByIndex(0);
            province.selectByIndex(number);
//            读取城市
            Select city = new Select(driver.findElement(By.id("city")));
//            判断城市对象是否存在
            System.out.println("城市选择" + new Date().toString());
            if (city != null) {
//              设置城市
                //      city.selectByValue("0");
                city.selectByValue(value);
//               读取区/县
                Select county = new Select(driver.findElement(By.id("county")));
//                判断区/县是否存在
                if (county != null) {
                    county.selectByVisibleText(option);
                } else {
                    System.out.println("option对象为空");
                }
            } else {
                System.out.println("city对象为空");
            }
        } else {
            System.out.println("province对象为空");
        }
    }

    public void singleCategory(WebDriver driver, String name, String first_cid) throws InterruptedException {
        new Select(driver.findElement(By.name(name))).selectByValue(first_cid);
        sleep(500);
    }


    //    设置商品类目
    public void getCategory(WebDriver driver, String first_cid, String second_cid) throws InterruptedException {
        System.out.println("商品类目设置进入了。。。。");
        new Select(driver.findElement(By.name("first_cid"))).selectByValue(first_cid);
        sleep(500);
        new Select(driver.findElement(By.name("second_cid"))).selectByValue(second_cid);
    }

    /**
     * 根据select下内容的所在位置来进行选择
     *
     * @param driver     对象
     * @param first_cid  第一个select对象
     * @param second_cid 第二个select对象
     * @throws InterruptedException
     */
    public void getCategoryIndex(WebDriver driver, int first_cid, int second_cid) {
        System.out.println("商品类目设置进入了。。。。");
        new Select(driver.findElement(By.name("first_cid"))).selectByIndex(first_cid);
        new Select(driver.findElement(By.name("second_cid"))).selectByIndex(second_cid);
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        movesWindow(driver);
    }

    /**
     * 根据名字进行选择
     *
     * @param driver     对象
     * @param first_cid  第一个select对象
     * @param second_cid 第二个select对象
     * @throws InterruptedException
     */
    public void getCategoryVisibleText(WebDriver driver, String first_cid, String second_cid) {
        System.out.println("商品类目设置进入了。。。。");
        new Select(driver.findElement(By.name("first_cid"))).selectByVisibleText(first_cid);
        new Select(driver.findElement(By.name("second_cid"))).selectByVisibleText(second_cid);
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        movesWindow(driver);
    }

    /**
     * 根据VALUE进行选择
     *
     * @param driver     对象
     * @param first_cid  第一个select对象
     * @param second_cid 第二个select对象
     * @throws InterruptedException
     */
    public void getCategoryValue(WebDriver driver, String first_cid, String second_cid) {
        System.out.println("商品类目设置进入了。。。。");
        new Select(driver.findElement(By.name("first_cid"))).selectByValue(first_cid);
        new Select(driver.findElement(By.name("second_cid"))).selectByValue(second_cid);
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
    public List<String> PropertyValue(WebDriver driver, String statusSelect) {
        Select select = new Select(driver.findElement(By.cssSelector(statusSelect)));
        List<WebElement> options = select.getOptions();
        List<String> list = new ArrayList<>();
        for (WebElement op : options) {
            list.add(op.getText());
            LnsmSystemOut.getStringOut(op.getText());
        }
        return list;
    }

    /**
     * 根据值进行设置
     *
     * @param driver
     * @param statusSelect
     * @param st
     * @throws InterruptedException
     */
    public void PropertyValue(WebDriver driver, String statusSelect, String st) {
        Select select = new Select(driver.findElement(By.id(statusSelect)));
        List<WebElement> options = select.getOptions();
        for (WebElement op : options) {
            if (op.getText().trim().equals(st)) {
                select.selectByVisibleText(st);
                //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
                movesWindow(driver);
                break;
            }
        }
    }

    /**
     * 点击筛选之后界面能回到顶部
     *
     * @param driver       对象
     * @param statusSelect 路径
     * @param status       内容
     */
    public void getGoodsStatus(WebDriver driver, String statusSelect, String status) {

        //筛选对象对应的下拉数据
        new Select(driver.findElement(
                By.cssSelector(statusSelect))).selectByVisibleText(status);
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        movesWindow(driver);
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
