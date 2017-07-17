package wap.business.example.ligrco.Exhibition.Article;

import LnsmData.CommoditiesList;
import LnsmData.CommodityIntroduction;
import LnsmData.CommodityOperation;
import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import LnsmOperation.SupplyOrderOperation;
import LnsmUitl.LnsmExcel;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.LnsmPromptBox;
import LnsmUitl.SystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 商品列表中对商品的操作
 * Created by ${XiaoHuiHui} on 2017/2/4.
 * XiaoHiiHui [704866169@qq.com]
 */
public class GoodOperation {

    private WebDriver driver;

    private String content;//对话框内容

    private boolean button = false;//排序按钮的点击情况。为真就保存，为假就取消

    private CommoditiesList commList;//获取列表的数据有

    private CommodityOperation commOperation;//excle表中要求的操作

    private String load;//操作商品所在的位置

    private int row;

    private final String tr = "Y";

    private final String fa = "N";

    public GoodOperation() {
        this.driver = FoxDriver.getFoxDriver();
    }

    /*
  1.点击商品名称弹出商品详情提示框
  2.通过操作表的数据对商品进行相应的操作
   */
    public GoodOperation(CommoditiesList commList, CommodityOperation commOperation, int row) {

        this.driver = FoxDriver.getFoxDriver();
        this.commList = commList;
        this.commOperation = commOperation;
        this.load = ".//*[@id='sample-table-1']/tbody/tr[" + (this.commList.getRow() + 1) + "]";
        this.row = row;

        SystemOut.getStringOut(this.commList.toString());
        SystemOut.getStringOut(this.commOperation.toString());
        try {
            //temperament();
            getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取商品的操作内容
    private void getStatus() throws Exception {

        //获取状态
        String status = (String) commOperation.getState();
        switch (status) {
            case "待审核":
                setPendingAudit(status);
                break;
            case "不通过":
                setNotPass();
                break;
            case "售卖中":
                setSaleIn();
                break;
            case "已下架":
                setRescinded();
                break;
            case "屏蔽":
                setShield();
                break;
            default:
                System.out.println("小伙子，我们商品没有这个状态，你重新检验一下，你输入的状态为：" + commList.getState());
                break;
        }
    }


    //待审核商品的操作方法。操作项有：-
    private void setPendingAudit(String status) {
        //这个有点装逼
        boolean pdA = commOperation.getOperation().equals("-") ? true : false;
        if (pdA) {
            System.out.println("读取的数据是:" + commOperation.getOperation() + "。。是正确的操作........");
        } else {
            System.out.println("当前状态为:" + status + "。。出现了异常的操作 " + commOperation.getOperation());
        }
    }

    //不通过商品的操作方法。操作项有：编辑和原因
    public void setNotPass() throws Exception {

        LnsmExcel lnsmExcel = new LnsmExcel();

        //操作执行判断
        boolean implement = commOperation.getImplement().equals(fa) ? false : true;
        switch (commOperation.getOperation()) {
            case "编辑"://如果是编辑就点击编辑按钮

                if (implement) {//判断是否需要进行确定按钮的操作
                    setEditTable(1);
                    //需要点击按钮就执行按钮的点击任务..
                } else {
                    SystemOut.getStringOut(commOperation.getState(),
                            commOperation.getOperation(),
                            "的操作" + commOperation.getImplement());
                }


                /*
                setPosition(1);
                driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
                driver.navigate().back();
                */
                break;
            case "原因":
                if (implement) {//判断是否需要进行相应的操作
                    setPosition(2);
                    new LnsmPromptBox(driver).getGoodsReason();
                } else {
                    SystemOut.getStringOut(commOperation.getState(),
                            commOperation.getOperation(),
                            "的操作" + commOperation.getImplement());
                }
                break;
            default:
                System.out.println("商品不通过出现另类操作" + commOperation.getOperation());
                break;
        }
    }


    //售卖中商品的操作方法。操作项有：排序、下架，如果是供货商品还有订货的按钮
    private void setSaleIn() {
        LnsmPromptBox lnsmPromptBox = new LnsmPromptBox(driver);
        boolean oplog = commOperation.getImplement().equals(fa) ? false : true;//判断是否需要进行相应的操作
        switch (commOperation.getOperation()) {
            case "排序":
                //根据位置进行点击
                SortAcquisition(lnsmPromptBox, oplog);//排序的识别
                break;
            case "下架":
                //根据位置进行点击
                setPosition(2);
                //商品上下架的操作
                lnsmPromptBox.setGoodsStatusOnTheShelf(oplog);
                break;
            case "订货":
                OrderOperation();//订货方法的调用
                break;
            default:
                System.out.println("商品售卖中现另类操作" + commOperation.getOperation());
                break;
        }
    }


    //已下架商品的操作方法。操作项有：排序、上架、订货
    private void setRescinded() throws InterruptedException {
        LnsmPromptBox lnsmPromptBox = new LnsmPromptBox(driver);
        boolean oplog = commOperation.getImplement().equals(fa) ? false : true;//判断是否需要进行相应的操作
        switch (commOperation.getOperation()) {
            case "排序":
                SortAcquisition(lnsmPromptBox, oplog);//排序的获取
                break;
            case "上架":

                String[] operation = commList.getOperation();
                String op = operation[operation.length - 1];
                //如果最后一个操作为上架操作说明该商品为普通商品，反之为***商品
                if (op.equals("上架")) {
                    setPosition(3);
                } else {
                    setPosition(2);
                }
                //商品上下架的操作
                lnsmPromptBox.setGoodsStatusOnTheShelf(oplog);
                break;
            case "编辑":
                String[] operation1 = commList.getOperation();
                String op1 = operation1[operation1.length - 1];
                if (op1.equals("上架")) {
                    setEditTable(2);
                    /*
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    driver.navigate().back();
                    */
                } else {
                    SystemOut.getStringOut("这是供货商品没有编辑的功能");
                }

                break;
            case "订货":
                OrderOperation();//订货方法的调用
                break;
            default:
                System.out.println("商品售卖中现另类操作" + commOperation.getOperation());
                break;
        }
    }

    //已屏蔽商品的操作方法。操作项有：原因
    private void setShield() {
        switch (commOperation.getOperation()) {
            case "原因":
                setPosition(1);
                new LnsmPromptBox(driver).getGoodsReason();
                break;
            default:
                System.out.println("商品售卖中现另类操作" + commOperation.getOperation());
                break;
        }
    }

    //排序的获取并调用对话框的内容
    private void SortAcquisition(LnsmPromptBox lnsmPromptBox, boolean oplog) {
        setPosition(1);
        String trim = commList.getName().trim();//商品名称
        String attribute = commOperation.getAttribute();//排序值
        String number = commList.getNumber();//商品ID
        //打开排序设置框时，该方法负责往里面设置属性值。。
        lnsmPromptBox.setGoodsSort(trim, oplog, attribute, number);
    }

    private void OrderOperation() {
        if (setJudge()) {
            if (commOperation.getImplement().equals(fa)) {//按钮是否需要点击
                SystemOut.getStringOut("从操作表中读取的数据为", button + ",因此不需要做点击按钮的操作");
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                //waitForCondition("selenium.browserbot.getUserWindow().$.active == 0;", 30000);
                driver.navigate().back();//浏览器后退
            } else {
                SupplyOrderOperation supply = new SupplyOrderOperation(commList.getNumber());
                supply.setSupplyNumber(commOperation.getAttribute());//输入的内容
                supply.setSupplyButton(tr);//按钮的点击
                //还差密码的输入以及页面的跳转以及最小值的提示
                supply.setSupplyPassWord();
                supply.obtainUrl();//订货成功之后获取网址是否正确

                //订货成功之后返回到相应的页面
                supply.backToPage("商品管理", "商品列表");
            }
        }
    }


    //根据位置进行点击处理
    private void setPosition(int position) {
        load = load + "/td[10]/a[" + position + "]";
        driver.findElement(By.xpath(load)).click();
    }

    //判断是否有订货按钮
    private boolean setJudge() {
        String[] operation = commList.getOperation();
        String op = operation[operation.length - 1];
        boolean bl = false;
        if (op.equals("订货")) {
            setPosition(3);//如果有订货按钮就点击
            bl = true;
        } else {
            SystemOut.getStringOut("该商品没有订货按钮，请重新编辑操作步骤");
        }
        return bl;
    }

    //读取编辑表中的数据，并进行数据封装，然后返回一个对象.
    private void setEditTable(int po) throws InterruptedException {
        try {
            setPosition(po);//页面元素点击部分

            LnsmUrl lnsmUrl = new LnsmUrl();
            //获取编辑页面的前部分，然后拼接商品ID;
            String editUrl = lnsmUrl.getEditCommodity() + commList.getNumber();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);//延迟一下

            assertEquals("编辑商品页面打开出错", driver.getCurrentUrl(), editUrl);//判断页面是否打开

            //商品编辑部分
            new ProductEditor(setProductRead()).setProductEditor();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //读取商品表之后的数据进行类封装。。
    private CommodityIntroduction setProductRead() throws IOException {

        LnsmExcel lnsmExcel = new LnsmExcel();//创建excel读取的对象

        // System.out.println("编辑表中的数据" + singleXlsx + ":" + row);

        List<String> read = lnsmExcel.getSingleReadXlsx(".//src//main//java//商品编辑.xlsx", row);//读取指定行的内容

        //   System.out.println("编辑的数据" + read.toString());

        CommodityIntroduction comm = null;
        //减少循环时，集合长度的获取
        String[] sp1 = read.get(2).split(",");
        String[] sp2 = read.get(7).split(",");

        comm = new CommodityIntroduction(read.get(0), read.get(1), sp1, read.get(3), read.get(4),
                read.get(5), read.get(6), sp2, read.get(8), read.get(9), read.get(10));

        return comm;

    }


    private void temperament() throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.cssSelector("span[class=showcode]"));
        elements.get(0).click();

        String na = "goods/qrcode/" + commList.getNumber();
        //  driver.switchTo().frame(driver.findElement(By.xpath(".//table[@class='aui_dialog']/tbody[1]/tr[2]/td[2]/div[1]/iframe")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.aui_content.aui_state_full>iframe")));

        //  driver.switchTo().frame(driver.findElement(By.xpath(frame)));
        String text = driver.findElement(By.xpath(".//*[@id='showcodwrap']/div[1]/p[1]")).getText();
        if (text.endsWith(commList.getNumber())){
            SystemOut.getStringOut("点击商品名称之后打开的二维码详情对话框","商品编号正确。。。");
        }
        driver.switchTo().defaultContent();
        sleep(1000);
        LnsmPreservation.getButtonCssSelector("a[class=aui_close]");
    }
}

























