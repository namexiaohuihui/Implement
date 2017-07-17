package wap.business.example.innose.information;

import LnsmInitialize.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static LnsmUitl.LnsmPreservation.getButtonXpath;
import static LnsmUitl.LnsmPreservation.getPreservation;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 店鋪管理-->店铺设置：
 * 狀態、是否預定：單選按鈕
 * 營業時間：對話框的時間設置
 * 以及保存按鈕和保存成功之後的提示
 * 通过数值来判断店铺状态和是否接受预定:
 * 1.为系统调整时间接受预定
 * 2.为系统调整时间不接受预定
 * 3.为强制休息中接受预定
 * 4.为强制休息中不接受预定
 * Created by Administrator on 2016/11/1.
 */
public class StoreSettings {

    //    通过静态类FoxDriver来获取driver对象
    WebDriver driver = FoxDriver.getFoxDriver();


    //    定义一个数值，用于识别店铺营业状态和是否接受预定
    int state = 2;
/*
    定义4个参数，用于设置店铺的营业时间。从开始营业到结束营业的时间
    暂时有不过，在设置时间的时候需要写大一位数。
    例如：想设置4点，此时startTime要设置为5.
 */
    int startTime = 5;
    int startBranch = 56;
    int entTime = 16;
    int entBranth = 25;

    public void getSetting(String url) throws InterruptedException {
//        判断店铺设置网址是否进入正确
        assertEquals("店铺设置进入错误", url, driver.getCurrentUrl());
//      读取营业时间的设置值，然后来判断营业状态和是否接受预定
        getState();
//        通过点击编辑按钮，来设置店铺营业时间
        getEdit();
    }

    /*
    通过点击编辑按钮来触发该方法，该方法主要是对营业时间的设置
    当我们点击编辑时，弹出一个设置界面。我们通过该界面来设置时间段
     */
    private void getEdit() throws InterruptedException {
        if (state <= 2) {
            driver.findElement(By.xpath(".//*[@class = 'referdata']/tbody/tr[3]/td/span[2]")).click();
            getSelect();
        } else {
            System.out.println("不需要编辑营业时间");
            getPreservation("shopsetbtn");
        }
    }

    /*
    当点击编辑营业时间之后，我们需要对下拉框select进行有效的设置.
    对营业的时和分进行设置。让店铺得到更好的营业.
     */
    private void getSelect() throws InterruptedException {
        new Select(driver.findElement(By.id("beginhour"))).selectByIndex(startTime);
        new Select(driver.findElement(By.id("beginminute"))).selectByIndex(startBranch);
        new Select(driver.findElement(By.id("endhour"))).selectByIndex(entTime);
        new Select(driver.findElement(By.id("endminute"))).selectByIndex(entBranth);
        getButtonXpath(".//*[@class= 'aui_buttons']/button[1]");
        sleep(1000);
        getPreservation("shopsetbtn");
    }


    //    读取营业时间的设置值，然后来判断营业状态和是否接受预定
    private void getState() throws InterruptedException {
//        判断设置营业时间的参数
        switch (state) {
            case 1:
                setStatus("business", "whether1");
                break;
            case 2:
                setStatus("business", "whether2");
                break;
            case 3:
                setStatus("rest", "whether1");
                break;
            case 4:
                setStatus("rest", "whether2");
                break;
            default:
                System.out.println("请设置你需要设置店铺的参数....");
                break;
        }
    }


    private void setStatus(String state, String accept) {
//        点击店铺状态单选框
        driver.findElement(By.id(state)).click();
//        点击是否接受预约订单单选框
        driver.findElement(By.id(accept)).click();
       /*判断单选框是否被点击中
        driver.findElement(By.id(state)).isEnabled();
        */
    }
}
