package common.tool.caninput;

import LnsmData.CommoditiesList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * 弹出对话框之后的操作
 * Created by ${XiaoHuiHui} on 2017/4/20 on 21:51.
 * XiaoHiiHui [704866169@qq.com]
 */
public class PromptBox {
    private WebDriver driver;


    public PromptBox(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 商品查看原因提示框
     */
    public void getGoodsReason() {
        WebElement element = driver.findElement(By.xpath("//div[@class='aui_title']"));
        String text = element.getText();
        if (text.equals("查看原因")) {
            System.out.println(text + ":提示框打开");
            driver.findElement(By.cssSelector("a[class=aui_close]")).click();
            driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        } else {
            System.out.println("查看原因提示框打开出错");
        }
    }

    /**
     * 打开排序设置框时，该方法负责往里面设置属性值。。
     *
     * @param trim   商品的名称
     * @param oplog  是否执行
     * @param att    排序值
     * @param number 商品id
     */
    public void setGoodsSort(String trim, boolean oplog, String att, String number) {
        try {
            //提示框标题
            String aui_title = driver.findElement(By.cssSelector("div[class=aui_title]")).getText();
            LnsmSystemOut.getStringOut("弹框标题", aui_title);

            //获取排序提示框中的商品名称
            String editsort = driver.findElement(By.xpath(".//*[@id='editsort']/table/tbody/tr[1]/td/span")).getText();

            //获取列表中读取到的商品名称
            if (editsort.equals(trim)) {
                LnsmSystemOut.getStringOut("排序框中商品的名字读取正确");

                //输入框对象
                WebElement element = driver.findElement(By.cssSelector("input[id =sort][name=sort]"));
                LnsmSystemOut.getStringOut("排序框表中读取的数据到底是什么" + oplog);
                if (oplog) {//如果需要执行就输入内容

                    String[] split = att.split("\\.");
                    LnsmSystemOut.getStringOut(split[0]);
                    element.sendKeys(split[0]);//输入排序值

                    sleep(2000);

                    //点排序提示框中的确定
                    driver.findElement(By.xpath(".//*[@id='editsort']/div[2]/button[1]")).click();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                    //获取表单中的所有数据，通过id进行比较。如果相等然后在比较排序值是否相等，相等说明通过了
                    formRecapture(number,split[0]);

                } else {
                    //点击取消
                    LnsmPreservation.getButtonCssSelector("button[class=sortNone][type=button]");
                    LnsmSystemOut.getStringOut("用例显示不需要进行输入操作");
                }
            } else {
                LnsmSystemOut.getStringOut("排序框中商品的名字读取正确错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //商品上下架的操作
    public void setGoodsStatusOnTheShelf(boolean oplog) {
        //提示框标题
        String aui_title = driver.findElement(By.cssSelector("div[class=aui_title]")).getText();
        LnsmSystemOut.getStringOut("上架·下架弹窗提示框标题", aui_title);

        //提示框内容
        String aui_content = driver.findElement(By.cssSelector("div[class=aui_content]")).getText();
        LnsmSystemOut.getStringOut("上架·下架弹窗提示框内容", aui_content);

        //  List<WebElement> elements =   driver.findElements(By.cssSelector("button[type=button]"));
        //   List<WebElement> elements = driver.findElements(By.cssSelector("div > button[type=button]"));
        List<WebElement> elements = driver.findElements(By.cssSelector("div.aui_buttons>button"));

        //筛选出的结果一共有6个，但实际打印的数据只有2个。需要进行数据处理..
        for (WebElement we : elements) {
            //判断是否点确定
            if (oplog) {
                if ("确定".equals(we.getText())) {
                    LnsmSystemOut.getStringOut("大王要我们下架的操作", we.getText());
                    we.click();
                    try {
                        //上下架操作之后提示框有3s等待时间
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }

            } else {
                //判断是否点取消
                if ("取消".equals(we.getText())) {
                    LnsmSystemOut.getStringOut("大王要我们下架的操作", we.getText());
                    we.click();
                    break;
                }
            }
        }
    }

    //获取表单中的所有数据，通过id进行比较。如果相等然后在比较排序值是否相等，相等说明通过了
    private void formRecapture(String number,String split){
        //通过id进行筛选
        new LnsmSelect().getGoodsStatus(driver, "select[id=by][name=by]", "商品编号");
        driver.findElement(By.cssSelector("input[id=val][type=search]")).sendKeys(number);

        //点击搜索按钮
        driver.findElement(By.xpath(".//div[@class='col-xs-12']/form/button")).click();

        String colist = driver.findElement(By.xpath("//*[@id='sample-table-1']/tbody/tr[1]/td[7]")).getText();

        if (colist.equals(split)) {
            LnsmSystemOut.getStringOut("设置排序之后的排序值相等");
        } else {
            LnsmSystemOut.getStringOut("设置排序之后的排序值不相等");
        }

        //选择之后恢复界面初始样
         driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        new LnsmSelect().getGoodsStatus(driver, "select[id=by][name=by]", "商品名称");
        driver.findElement(By.cssSelector("input[id=val][type=search]")).clear();
    }






}
