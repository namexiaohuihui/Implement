package wap.business.example.cooperation.boundary;

import LnsmElement.LnsmParameter;
import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import LnsmList.CommodityManage;
import LnsmUitl.LnsmOther;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.LnsmSystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 供货系统中订单页面的操作
 * Created by ${XiaoHuiHui} on 2017/5/8 on 17:44.
 * XiaoHiiHui [704866169@qq.com]
 */
public class Interface {

    private WebDriver driver;

    private String number;//订货商品的数量

    private String url = new LnsmUrl().getSupplyUrl();//订货商品的下单页面

    private boolean button = false;//通过输入内容来判断是否需要点击提交按钮，双层保护相对安全。

    public Interface() {
        driver = FoxDriver.getWebDrivaer();
    }

    public Interface(String id) {
        url = url + id;
        driver = FoxDriver.getWebDrivaer();
    }

    //订货的数量。按钮的点击。密码的输入。页面的跳转
    public void setSupplyNumber(String number) {
        WebElement value = driver.findElement(By.cssSelector("td>input[id=text_box]"));
        setBoolean(number);//判断需要输入的数量是否符合要求
        //判断是否要输入
        if (button) {
            //如果输入的内容小于默认最小值
            value.clear();
            String[] split = number.split("\\.");
            value.sendKeys(split[0]);
        } else {
            LnsmSystemOut.getStringOut("从操作表中读取的数据为", number + ",因此不需要做输入的操作");
        }
    }

    //订货按钮的点击
    public void setSupplyButton(String but) {
        if (button) {
            if (but.equals("N")) {
                LnsmSystemOut.getStringOut("从操作表中读取的数据为", but + ",因此不需要做点击按钮的操作");
            } else {
                driver.findElement(By.cssSelector("input[id=ordersave][type=submit]")).click();
            /*    //通过表格里面的数据和最小值的比较
                WebElement value = driver.findElement(By.cssSelector("td>input[id=text_box]"));
                String minNumber = value.getAttribute("value");
                if (setStringToInt(number) < setStringToInt(minNumber)) {
                    LnsmSystemOut.getStringOut("最小订货量应为", minNumber + ",请重新输入");
                }*/
                //通过点击按钮之后，来识别最小值的弹窗是否出现
                By by = By.cssSelector("div[class=aui_content]");
                String text = driver.findElement(by).getText();
                int length = text.length();
                if (length > 5) {
                    LnsmSystemOut.getStringOut("订货出现最小值的提示", text);
                    button = false;
                }
            }
        } else {
            LnsmSystemOut.getStringOut("表中输入的不是数量而已坑所以不用进行点击处理");
        }
    }

    //订货密码输入框..输入密码之后点击支付，如果提示框还存在就说明密码或者金额不足此时就点击取消
    public void setSupplyPassWord() {
        if (button) {
            //输入密码
            WebElement element = driver.findElement(By.cssSelector("input[id=password][name=password]"));//密码对象
            element.sendKeys(new LnsmParameter().getSupplyPassWord());//输入密码

            //点击支付
            LnsmPreservation.getButtonCssSelector("button[class^=aui_state][type=button]");

            try {
                sleep(3000);//延迟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断密码输入框是否存在。存在就点击取消按钮
            By by = By.cssSelector("td.class.aui_main>div.aui_content]");//提示语对象
            boolean b = new LnsmOther().doesWebElementExist(driver, by);
            if (b) {
                String text = driver.findElement(by).getText();
                LnsmSystemOut.getStringOut("订货输入密码之后提示框的内容", text);
                LnsmPreservation.getButtonCssSelector("button[class=payNone][type=button]");
            }
        }
    }

    public void obtainUrl() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//延迟

        String currentUrl = driver.getCurrentUrl();
        String suppluOrder = new LnsmUrl().getSuppluOrder();

        LnsmSystemOut.getStringOut("订单页面获取的url", currentUrl);
        LnsmSystemOut.getStringOut("订单页面的url", suppluOrder);

        assertEquals("订单页面", currentUrl, suppluOrder);
    }

    //将字符装换成整形.
    private int setStringToInt(String number) {
        return Integer.parseInt(number);
    }

    //判断是否要输入
    private void setBoolean(String number) {
        if (number.equals("—")) {
            button = false;
        } else {
            button = true;
        }
    }

    //订货成功之后返回到相应的页面
    public void backToPage(String ont,String two) {

        try {
            new CommodityManage(ont,two);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
