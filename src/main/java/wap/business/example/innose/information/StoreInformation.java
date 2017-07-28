package wap.business.example.innose.information;


import common.FoxDriver;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.InfoFrame;
import common.tool.caninput.InfoSelect;
import common.tool.caninput.Preservation;
import common.tool.upload.PictureImage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * 信息
 * Created by Administrator on 2016/11/1.
 */
public class StoreInformation {

    //    记录店铺执照上传的对象，方便循环执行
    String photo[] ;
    String license[];
    private WebDriver driver = FoxDriver.getFoxDriver();
    //    实拍和执照的上传按钮class名
    private String button = "uploadify-button ";
    //    店铺介绍的位置
    private int weizhi = 17;

    public void getInformation(String url) throws InterruptedException, SQLException {
//        通過網址進行驗證店鋪信息頁面是否打開。
        assertEquals("店鋪信息頁面沒打開", url, driver.getCurrentUrl());
        /*
        if ((MysqlInquire.getDataLength(mysql, 3)).equals("")) {
            System.out.println("该店铺没有设置过数据");
            getInformation();//名称、省份、地址、经纬设置
            setInformation();//其他设置
        } else {
            System.out.println("该店铺已设置过数据");
            weizhi = 18;
            setInformation();//其他设置
        }
        */
//        店铺信息局部数据调整
        //setLocal();
        setInformation();
    }

    private void setLocal() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//*[@class='referBtn']/input"));
        jse.executeScript("arguments[0].click();", element);
    }

    //    名称、省份、地址、经纬设置
    private void getInformation() throws InterruptedException {
        //        设置店铺名称
        getStoreName("name", "哈哈_冬瓜");//         店铺的所在区域
        getAdd(19, "440200", "始兴县");
//        店铺的所在地址
        getStoreName("address", "你大爷的地址...");
//            设置经纬度
        getStoreName("lng", "3.1415926");
        getStoreName("lat", "3.1415926");
    }

    //      进入店铺信息页面，对店铺信息进行数据设置
    private void setInformation() throws InterruptedException {
//        设店铺主营商品：根据select的下标或者进行设置
        getTransformation("水店");
//        设置店铺的配送距离
        getRadius("range", "27");
//        设置店铺的起送价格
        getRadius("init_price", "6");
//        设置店铺的配送费
        getRadius("send_tail", "1");
//        上传LOGO图片
        //    getLogo("SWFUpload_0", "shop_logo.exe");
//        实拍和执照的上传
        getLicensePhoto();
//        设置店铺的配送电话
        getRadius("contact", "--");
//        设置店铺的介绍
//        getIntroduce();
//        保存按钮
        new Preservation().buttonXpath("//*[@class='referBtn']/input");
//        点击保存之后等待5S让页面元素进行加载
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        加载完成之后，判断提示用于是否为修改成功
        assertEquals("保存之后提示语出现没", "修改成功！", driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div")).getText());
    }

    /**
     * 局部数据进行测试：现进行的内容为：营业执照和店铺实拍的个数上传
     * 思路：
     * 1.先读取删除的按钮个数
     * 2.判断两个按钮是否为可点击状态
     * 3.判断执照是否存在第一张，如果存在且可点击说明，执照只有一张，这时只需要上传一张而已。
     * 4.如果第三部满足，说明剩下图片就是实拍的。总数5减去剩下的，之后可计算出上传的数量。
     *
     * @throws InterruptedException
     */
    private void getLicensePhoto() throws InterruptedException {
//        读取执照和实拍删除按钮的个数，来读取已上传的数量
        int total = driver.findElements(By.linkText("x")).size();

//        real表示营业执照class的状态，shot表示店铺实拍classs的状态
        String real = driver.findElement(By.xpath(".//div[@id = 'file_uploadzz-button']")).getAttribute("class");
        String shot = driver.findElement(By.xpath(".//div[@id = 'file_upload-button']")).getAttribute("class");

//        用于记录执照和实拍需要上传的个数
        int licenseNumber = 0;
        int photoNumber = 0;
//        判断执照第一张是否存在
        if (new ElementExistence().elementXPath(".//ul[@id='J_piczz-box']/li")) {
//            如果第一张存在那么判断上传按钮状态是否为可点击状态
            if (real.equals(button)) {
//                如果是可点击状态那么就上传一张图片
                licenseNumber = 1;
                System.out.println("执行者，你好！营业执照现在有" + licenseNumber + "张,需要进行上传了。");
                getLogo("SWFUpload_1", "license1.exe");
            }
//            走else说明为不可点击状态，此时不需要上传图片
            else {
//                表示执照已经拥有两张了
                licenseNumber = 2;
                System.out.println("执行者，你好！营业执照现在有两张不需要进行上传了。");
            }
        }
//        走else说明营业执照第一张不存在，此时需要上传两张图片
        else {
//            说明执照一张都没有
            licenseNumber = 0;
            for (int i = 0; i < 2; i++) {
                getLogo("SWFUpload_1", license[i]);
            }
            System.out.println("执行者，你好！营业执照现在有" + licenseNumber + "张,需要进行上传了。");
        }
//        上传完执照之后就判断实拍需要上传的数量
//        先判断按钮是否可点击如果不可点击就没必要进行判断了
        if (shot.equals(button)) {
            photoNumber = 5 - (total - licenseNumber);
            for (int i = 0; i < photoNumber; i++) {
                getLogo("SWFUpload_2", photo[i]);
            }
            System.out.println("执行者，你好！需要上传" + photoNumber + "张实拍。");
        }
//        不可点击就提示用户
        else {
            System.out.println("执行者，你好！店铺实拍现在有5张,不需要进行上传了。");
        }
    }

    //    判断店铺名称是否存在
    public void getStoreName(String newName, String number) {
        WebElement storeName = driver.findElement(By.id(newName));
//            点击店铺名称输入框
        storeName.click();
//            清除店铺名称输入框里面的内容
        storeName.clear();
//            输入店铺的名称
        storeName.sendKeys(number);
    }

    //    读取店铺省份
    public void getAdd(int position, String value, String options) throws InterruptedException {
//        读取店铺省份的内容，通过value来读取内容
        String data = driver.findElement(By.xpath("//*[@id=\"province\"]")).getAttribute("value");
//      如果长度大于1，所以设置过
        if (data.length() > 1) {
            System.out.println(value + "为现在省份");
        } else {
//            长度小于1说明没有设置过，然后调用方法进行设置
            InfoSelect infoSelect = new InfoSelect();
            By province = By.cssSelector("select[id=province][name=province]");
            By city = By.cssSelector("select[id=city][name=city]");
            By county = By.cssSelector("select[id=county][name=county]");
            infoSelect.categoryIndex(province,position);
            infoSelect.categoryValue(city,value);
            infoSelect.categoryText(county,options);
        }
    }

    //    通过id来获取对象，并且清除里面的内容。然后重新输入
    private void getRadius(String name, String number) {
//        找到对象
        WebElement range = driver.findElement(By.id(name));
        range.clear();
        range.sendKeys(number);
    }


    //    设置店铺LOGO图像
    private void getLogo(String name, String route) throws InterruptedException {
//        统一上传的功能
        PictureImage.getLogo(driver, name, route);
    }

    //        设置店铺介绍
    private void getIntroduce() throws InterruptedException {
        weizhi = 18;
        String load = "//*[@id=\"shopissueform\"]/table/tbody/tr[" + weizhi + "]/td/div/div[2]/iframe";
        new InfoFrame().editInfoFrame(load, "ShopIntroduction");
    }


    //    判断select对象是否存在
    private boolean setSelect(String city) {
//        读取这个对象
        Select select = new Select(driver.findElement(By.id(city)));
//        如果这个兑现不为空就说明存在
        if (select != null) {
//            然后返回为真
            return true;
        } else {
//            否则返回为假并打印输出
            System.out.println(city + "对象为空");
            return false;
        }
    }

    //    根据输入的内容来判断是value还是文字
    private void getTransformation(String string) {
//        获取店铺类型下拉列表
        Select category = new Select(driver.findElement(By.id("category")));
        try {
//            将字符串进行转换，如果是数字则根据value来设置
            Integer.parseInt(string);
            category.selectByValue(string);
        } catch (Exception x) {
//            如果不是数字就根据类型名来设置
            category.selectByVisibleText(string);
        }
    }
}