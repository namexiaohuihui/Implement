package wap.business.example.innose.information;

import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import wap.business.example.innose.Information;

import static common.FoxDriver.getWebDrivaer;
import static java.lang.Thread.sleep;

/**
 * 管理
 * Created by Administrator on 2016/11/1.
 */
public class StoreSettings extends Information {

    //    通过静态类FoxDriver来获取driver对象
    WebDriver driver = getWebDrivaer();
    //点击对象创建
    Preservation preservation = new Preservation();

    //存储用例的地方
    private String load;
    //    定义一个数值，用于识别营业状态和是否接受预定
    int state = 2;
/*
    定义4个参数，用于设置的营业时间。从开始营业到结束营业的时间
    暂时有不过，在设置时间的时候需要写大一位数。
    例如：想设置4点，此时startTime要设置为5.
 */
    int startTime = 5;
    int startBranch = 56;
    int entTime = 16;
    int entBranth = 25;

    public StoreSettings(String load) {
        this.load = load;
    }

    public StoreSettings() {
    }

    public void getSetting() {
//      读取营业时间的设置值，然后来判断营业状态和是否接受预定
        getState();
//        通过点击编辑按钮，来设置营业时间
        getEdit();
    }

    /*
    通过点击编辑按钮来触发该方法，该方法主要是对营业时间的设置
    当我们点击编辑时，弹出一个设置界面。我们通过该界面来设置时间段
     */
    private void getEdit() {
        if (state <= 2) {
            driver.findElement(By.xpath(".//*[@class = 'referdata']/tbody/tr[3]/td/span[2]")).click();
            getSelect();
        } else {
            System.out.println("不需要编辑营业时间");
            preservation.breservation("shopsetbtn");
        }
    }

    /*
    当点击编辑营业时间之后，我们需要对下拉框select进行有效的设置.
    对营业的时和分进行设置。让得到更好的营业.
     */
    private void getSelect() {
        try {
        new Select(driver.findElement(By.id("beginhour"))).selectByIndex(startTime);
        new Select(driver.findElement(By.id("beginminute"))).selectByIndex(startBranch);
        new Select(driver.findElement(By.id("endhour"))).selectByIndex(entTime);
        new Select(driver.findElement(By.id("endminute"))).selectByIndex(entBranth);
        preservation.buttonXpath(".//*[@class= 'aui_buttons']/button[1]");
            sleep(1000);
            preservation.breservation("shopsetbtn");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    读取营业时间的设置值，然后来判断营业状态和是否接受预定
    private void getState() {
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
                System.out.println("请设置你需要设置的参数....");
                break;
        }
    }


    private void setStatus(String state, String accept) {
//        点击状态单选框
        driver.findElement(By.id(state)).click();
//        点击是否接受预约订单单选框
        driver.findElement(By.id(accept)).click();
       /*判断单选框是否被点击中
        driver.findElement(By.id(state)).isEnabled();
        */
    }
}
