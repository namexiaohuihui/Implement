package wap.business.example.innose.information;

import LnsmInitialize.FoxDriver;
import LnsmUi.MysqlInquire;
import LnsmUitl.LnsmList;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.LnsmSince;
import LnsmUitl.LnsmTool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Date;

import static LnsmUitl.LnsmPreservation.getButtonClassName;
import static LnsmUitl.LnsmPreservation.getButtonXpath;
import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 店鋪管理--->自提设置：
 * 自提功能的開關、自提點的設置
 * 以及對自提點的操作
 * 別忘記了操作之後的提示
 * Created by Administrator on 2016/11/1.
 * 设置思路:
 * 1.先判断自提按钮是否打开。打开了才进行设置
 * 2.添加自提点，并设置自提点
 * 3.对自提点进行操作
 */
public class SelfSetting {

    //    获取webdriver对象
    private WebDriver driver = FoxDriver.getWebDrivaer();
    //    打开自提点的提示语
    private String open = "确定要开启上门自提功能吗？";
    //    关闭自提点的提示语
    private String close = "关闭后，已生成的上门自提订单不受影响（请记得为未完成的自提订单准备好商品）";
    //    通过第三方变量来读取当前自提按钮的状态
    private boolean whether;
    //    通过第三方变量来设置字体按钮的状态:1：为打开，2：为关闭
    private int status = 2;
    //    设置自提点的名称
    private String name = "测试一号";
    //    设置自提点的电话号码
    private String tel = "18778036666";
    //    设置自提点的省份
    private String province = "440000";
    //    设置自提点的城市
    private String city = "440200";
    //    设置自提点的县城
    private String county = "440222";
    //    设置自提点的详细地址
    private String address = "路边街27号";
    //    设置表的路径
    private String route = ".//*[@id='sample-table-1']/tbody";
    //    设置4个参数，选择自提点来对屏蔽以及编辑的操作:
    private int object1 = 1; //对象1
    private int shield = 2; //屏蔽的位置
    private int object2 = 2;//对象2
    private int edit = 1;//编辑的位置

    public void getSince(String url) throws InterruptedException {
//        设置断点，判断当前网址是否为服务器设置的网址
        assertEquals("自提设置页面的判断", url, driver.getCurrentUrl());

//       判断个人是否有自提点
        getData();
    }

    /**
     * 通过数据库查询该店铺是否拥有自提点。
     * 如果有就开始打开自提点、添加自提点、编辑自提点的操作
     * 如果没有就先添加自提点。打开自提点、然后在编辑
     * 只是顺序不一样而已
     *
     * @throws InterruptedException
     */
    private void getData() throws InterruptedException {
//        创建查询语句
        String sql = "select * from lnsm_fetch_addr where seller_id = 10479;";
//        判断该语句返回的内容是否为0
        if (new MysqlInquire().getDataLength(sql) > 0) {
//        根据用户对自提点的需求来对设置当前店铺的自提
            setSince();

//        通过点击‘添加自提点’按钮来设置新的自提点。
            SetAddSince();
//          通过编辑的指令来对自提点进行编辑
            editAndShield();
        } else {
            System.out.println("该店铺没有自提点...");
//        通过点击‘添加自提点’按钮来设置新的自提点。
            SetAddSince();
//        根据用户对自提点的需求来对设置当前店铺的自提
            setSince();
//          通过编辑的指令来对自提点进行编辑
            editAndShield();
        }
    }

    //    自提点的操作
    private void editAndShield() throws InterruptedException {
        if (LnsmTool.getXPath(route)) {
//            获取自提点表格数据
            int cellSize = new LnsmList(driver).getCellSize(route,"tr");
            System.out.println("获取结束" + new Date());
            System.out.println("自提列表的长度" + cellSize);
//            屏蔽自提点的点击
            driver.findElement(By.xpath(route + "/tr[" + object1 + "]/td[4]" + "/a[" + shield + "]")).click();
//            对弹出的对话框进行确定按钮点击
            LnsmPreservation.getButtonClassName("aui_state_highlight");
//            点击确定之后会刷新页面，让程序页面加载2s
            System.out.println("确定点击" + new Date());
//            等待页面加载
            sleep(5000);
            if (object2 <= cellSize) {
                //            点击编辑按钮
                driver.findElement(By.xpath(route + "/tr[" + object2 + "]/td[4]" + "/a[" + edit + "]")).click();
                System.out.println("编辑点击" + new Date());
//            进入iframe表格：编辑自提点的内容
                driver.switchTo().frame(driver.findElement(By.xpath(".//*[@class='aui_main']/div[1]/iframe")));
//           进行设置
                new LnsmSince(driver).SelfInformation(name, tel, province, city, county, address);
//            退出
                driver.switchTo().defaultContent();
//            ‘确定按钮'的点击
                LnsmPreservation.getButtonXpath(".//table[@class='aui_dialog']/tbody/tr[3]/td[1]/div[1]/button[1]");
            } else {
                System.out.println("第二个对象不存在");
            }
        } else {
            System.out.println("该店铺尚未设置过自提点");
        }
        sleep(1000);
    }


    //    点击添加自提点之后弹出自提对话框，判断该提示框是否存在
    private void SetAddSince() throws InterruptedException {
//        ’添加自提点‘按钮点击
        LnsmPreservation.getButtonXpath(".//div[@class='page-content']/div[2]/div[1]/div[1]/div[2]");
//        判断‘自提点页面是否打开’
        if (LnsmTool.getClassName("aui_title")) {
            System.out.println("添加自提点页面打开了、、");
//            输入自提点的内容和点击确定按钮
            new LnsmSince(driver).SinceSomeSettings(name, tel, province, city, county, address);
//            ‘确定按钮'的点击
            LnsmPreservation.getButtonXpath(".//div[@class='aui_buttons']/button[1]");
        } else {
            System.out.println("自提点不存在。请查证");
        }
        sleep(1000);
        System.out.println("添加自提点页面关闭了、、");
    }


    //    设置当前店铺是否需要开启自提点
    private void setSince() throws InterruptedException {
//        设置当前用户的自提前，需要先获取店铺当前的自提状态。
        getDoor();
//        进行需求判断
        switch (status) {
            case 1:
                setDoor(1);
                break;
            case 2:
                setDoor(2);
                break;
            default:
                System.out.println("请输入1或者2来设置自提点,当前你输入的是" + status);
        }
        sleep(1000);
    }

    //    第二次判断自提点
    private void setDoor(int number) throws InterruptedException {
//        如果用户设置关闭自提点，并且当前自提按钮为打开状态
        if (number == 2 && whether) {
            driver.findElement(By.className("lbl")).click();
            getButtonClassName("aui_state_highlight");
        }
//        如果用户设置开启自提点，并且当前自提按钮为关闭状态
        else if (number == 1 && !whether) {
            driver.findElement(By.className("lbl")).click();
            getButtonClassName("aui_state_highlight");
        }
//        如果都不属于而提示用户
        else {
            System.out.println("当前状态是你想要的，不用重复设置。。" + whether);
        }
    }

    /*
    获取自提点对象。并通过点击之后的文字来判断按钮的状态，并返回给当前类
     */
    private void getDoor() throws InterruptedException {
//        点击自提开关
        driver.findElement(By.className("lbl")).click();
//        读取弹出对话框的数据
        getText();
    }

    /*
    获取点击打开自提点之后的文本信息
     */
    private void getText() throws InterruptedException {
//        通过这个对象来获取信息
        String aui_main = driver.findElement(By.className("aui_main")).getText();
//        如果提示语是提示询问你是否需要打开，说明当前为关闭状态
        if (aui_main.equals(open)) {
            whether = false;
        }
//        如果提示语是提示关闭之后的现象，说明当前为打开状态
        else if (aui_main.equals(close)) {
            whether = true;
        }//
        else {
            System.out.println("提示语都是错误的。。");
        }
//        点击取消，关闭对话框
        getButtonXpath(".//div[@class = 'aui_buttons']/button[2]");
    }
}
