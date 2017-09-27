package wap.business.example.innose.information;

import common.FoxDriver;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.Preservation;
import common.tool.excelfile.ReadFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.innose.Information;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 介绍
 * 点击年份元素对象之后快速选择年份需求暂时放弃。
 * Created by Administrator on 2016/11/1.
 */
public class ShopNotices extends Information {

    //    设置截止时间，根据格式进行设置
    String time = "2018-09-08 21:56:32";
    //    保存之后的提示
    String promptError = "开始时间必须小于结束时间";
    String promptCorrect = "保存成功，您的公告将在店铺主页显示";

    //    获取driver对象
    WebDriver driver = FoxDriver.getFoxDriver();

    private String load;

    public ShopNotices(EnumProgramBean epb) {
        this.load = epb.getOne() + epb.getTwo() + epb.getThree();
        ;
    }

    public ShopNotices() {
    }

    public void getAnnouncement() {

        //    获取系统的当前时间，用于设置店铺公告的起止时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //    设置店铺公告
        grtNotice();

        //    设置公告的有效日期  getEffectiveDate(driver);(日历选择器0
        //    传入起始时间的对象以及需要设置的起始时间
        getStartStopTime("start_time", format.format(new Date()));
        //    传入截止时间的对象以及需要设置的截止时间
        getStartStopTime("end_time", time);

        //    点击保存按钮
        new Preservation().breservation("noticesave");

        //    判断点击保存之后，提示信息是否出现，如果出现了提示信息是什么
        getData("successMessage");
    }


    /**
     * 设置有效时间的起止日期，通过sendkeys属性直接赋值，需要传入对象和时间久可以.
     *
     * @param name 对象元素的ID
     * @param data 需要设置的时间
     *             通过ID来找到我们需要设置的元素对象，然后对其直接赋值
     */
    private void getStartStopTime(String name, String data) {
        WebElement element = driver.findElement(By.id(name));
        element.clear();
        element.sendKeys(data);
    }

    /**
     * 点击保存之后提示信息是否出现
     * @param string 传入提示信息的class属性
     * 通过工具类判断该提示信息是否出现。
     *如果出现了先读取提示信息的内容
     * 在判断该内容是保存成功的提示还是保存失败的提示
     */
    private void getData(String string) {
//        判断是否存在
        if (new ElementExistence().accordingToCssSelector(string)) {
//            读取提示信息的内容
            String successMessage = driver.findElement(By.className(string)).getText();
//            判断提示信息的内容是否为保存成功
            if (successMessage.equals(promptCorrect)) {
                System.out.println("保存成功" + successMessage);
            } else {
                System.out.println("保存失败" + successMessage);
            }
        } else {
            System.out.println("元素不存在");
        }
    }

    //    根据html定义的元id来找到的输入框，并且利用IO原理断区本项目中txt文件的数据，
    //    对公告进行设置
    private void grtNotice() {
//        根据html定义的元素id找到店铺公告的输入框
//        According to the HTML definition of the element ID to find the store announcement input box
        WebElement text = driver.findElement(By.id("content"));
//        对该输入框里面的内容进行清除
        text.click();
        text.clear();
//       通过IO流读取项目里面txt文件里面的数据进行设置
        text.sendKeys(new ReadFile().noticesFile("ShopNotices"));
    }


}
