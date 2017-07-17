package wap.business.example.ligrco.Exhibition.Release;

import LnsmData.CommodityIntroduction;
import LnsmElement.LnsmParameter;
import LnsmInitialize.FoxDriver;
import LnsmOperation.CommodityOperation.Comments;
import LnsmUitl.*;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 商品管理--发布商品：
 * 商品一级目录和二级目录：通过select来定位设置
 * 商品分组：通过标签来读取列表长度，然后点击需要设置的值
 * 点击管理分组时，新打开网页然后设置：
 * 1.跳转网页
 * 2.设置数据
 * 3.设置完成后，关掉页面。回到发布的页面
 * 刷新按钮：
 * 1.留在页面，继续编辑
 * 2.离开页面，重新编辑
 * 商品名称、价格：通过input直接输入
 * 图片通过logo方法上传
 * 详情描述：商品的描述，定义一个记事本然后读取该记事本的内容在上传、
 * 点击预览能看到自己设置的数据
 * 点击发布之后跳转至商品列表，然后读取第一条值是否为刚设置的商品
 * Created by XiaoHuiHui on 2016/12/27.
 */
public class ReleaseCommodity extends Comments {


    //    商品详情输入框的位置
    private String group = "//*[@id=\"shopissueform\"]/table/tbody/tr[2]/td[1]";

    //    浏览器对象
    private WebDriver driver = FoxDriver.getFoxDriver();

    //    参数对象
    private LnsmParameter lnsmParameter;

    //      商品操作实现表
    CommodityIntroduction coin = new CommodityIntroduction();

    //      代码执行
    public ReleaseCommodity(String url) throws InterruptedException {
//        比较
        super(url);
//      获取数据对象
        lnsmParameter = new LnsmParameter();
//        局部测试
        setRelease();
//        全部执行
        // setAll();
    }


    /**
     * 部分调试：刷新按钮的点击
     * 刷新之后检查下拉框的内容以及分组按钮是否被勾选：
     * 下拉框就判断子类目是否有内容
     * 分组按钮就判断是否被勾选
     *
     * @throws InterruptedException
     */
    private void setRelease() throws InterruptedException {
        setAll();
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        Thread.sleep(2000);
        driver.findElement(By.linkText("刷新")).click();
        System.out.println(" 1 " + driver.switchTo().alert().getText());
//        确定离开
        driver.switchTo().alert().accept();
//        setAll();
    }

    private void setAll() throws InterruptedException {
        setCategory();
        setCheckbox();
        setLogoAndContent("");
        //setName("");
       // setPrice("");
    }

    private void setCategory() throws InterruptedException {
        //  LnsmSelect.getCategory(driver, first_cid, second_cid);//商品主类目和子类目的设定
    }

    //    分组按钮的点击。。先是数据库分组的数量和页面分组的数量比较，然后是页面分组数量和用户需要点击的兑现比较
    private void setCheckbox() throws InterruptedException {
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        Thread.sleep(2000);
//        获取当前页面分组的数量
        int lable = new LnsmList(driver).getCellSize(group, "label");
//        读取该店铺的分组数量
//                sleep(1000);
//                //获取当前浏览器大小
//                driver.findElement(By.xpath(group + "/label[" + checkbox + "]/span")).click();
    }

    private void setWindows() throws InterruptedException {
        //        获取当前窗口对象
        String windowHandle = driver.getWindowHandle();
        System.out.println("窗口地址" + driver.getCurrentUrl());
        driver.findElement(By.linkText("管理分组")).click();
        sleep(3000);
//        管理分组的窗口
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> list = new ArrayList(windowHandles);
        for (String string : list) {
            if (string.equals(windowHandle) == false) {
                driver.switchTo().window(string);
                System.out.println("窗口地址2" + driver.getCurrentUrl());
                /*
                未完待续：点击跳转之后的操作
                 */
                driver.close();
            }
        }
        driver.switchTo().window(windowHandle);
        System.out.println("窗口地址3" + driver.getCurrentUrl());
    }


    //上传logo和详情
    private void setLogoAndContent(String logo) throws InterruptedException {
        LnsmPicture.getLogo(driver, "SWFUpload_0", logo);
        String logoName = driver.findElement(By.xpath("//*[@id=\"J_pic-box\"]/li/img")).getAttribute("src");
        String load = "//*[@id=\"shopissueform\"]/table/tbody/tr[9]/td/div/div[2]/iframe";
      //   new LnsmFrame(driver, load, content);
    }
}
