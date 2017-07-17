package wap.business.example.ligrco.Exhibition.Article;

import LnsmInitialize.FoxDriver;
import LnsmUitl.LnsmList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * 商品里面的各个数据的操作
 * Created by ${XiaoHuiHui} on 2017/3/21.
 * XiaoHiiHui [704866169@qq.com]
 */
public class GoodStatus {

    private WebDriver driver;
    private String position;//位置
    private String written;//
    private LnsmList lnsmList;

    /**
     * 商品列表表格xpath路径
     */
    private String tablePath = ".//*[@id='sample-table-1']/tbody";

    public GoodStatus() {
    }

    public GoodStatus(WebDriver driver, String position, String written) {
        this.driver = driver;
        this.position = position;
        this.written = written;
    }

    public GoodStatus(WebDriver driver, String written) {
        this.driver = driver;
        this.written = written;
    }

    public void goodOp() {
        new Select(driver.findElement(By.cssSelector(position))).selectByVisibleText(written);
        //        窗口移动。选择类目之后窗口会往下移，此时分组元素在窗口之外。所以要滑动窗口。
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        driver.findElement(By.xpath(".//div[@class='col-xs-12']/form/button")).click();

        List<List> cellContent = lnsmList.getCellContent(tablePath, "tr", "td");
    }

    /**
     * 判断该对象是否为空
     * 如果不为空就开
     *
     * @return
     */
    private LnsmList getLnsmLiseData() {
        if (lnsmList == null) {
            lnsmList = new LnsmList(driver);
        }
        return lnsmList;
    }

    //    商品名称的输入，如果商品名称过长时应提示用户
    public void setName() {
        //先判断需要设置的名字是否符合条件
        if (written.length() >= 5 && written.length() <= 20) {
            WebElement productname = driver.findElement(By.cssSelector("input[id=productname][name=name]"));
            //    WebElement productname = driver.findElement(By.id("productname"));
            //判断输入框中的数据是否输入过
            String product = productname.getAttribute("value");
            if (product.length() >= 5) {
                productname.clear();
                productname.sendKeys(written);
            } else {
                productname.sendKeys(written);
            }
        } else {
            System.out.println("商品名称的长度为5-20请重新输入。。。");
        }
    }


    //商品价格的输入，如果价格有误就进行提示
    public void setPrice() {

        //判断价格是否符合条件.数据进行切割。。
        String[] split = written.split("\\.");
        System.out.println("商品价格的长度" + split.length);
        if (split.length > 2) {//有小数点的要判断小数点前后是否符合

            setPriceLength(true, split);

        } else {//没有小数点的要判断整数是否符合

            setPriceLength(false, split);

        }
    }

    //判断价格输入是否符合，根据长度进行判断
    private void setPriceLength(boolean bl, String[] split) {
        if (bl) {
            if (split[0].length() > 5 || split[1].length() > 2) {

                System.out.println("请输入正确的金额格式" + written);

            } else {
                //小数点前面的长度小于5，小数点后面的长度小于3
                setPriceData();
            }
        } else {
            if (split[0].length() <= 5) {
                setPriceData();
            } else {
                System.out.println("请输入正确的金额格式" + written);
            }
        }
    }

    private void setPriceData() {

        //读取价格输入框中的数据。如果为空就直接输入，如果有数据就清空后输入
        WebElement priceNumber = driver.findElement(By.cssSelector("input[id=price][name=price]"));

        if (priceNumber.getAttribute("value").equals("")) {

            setPriceInput(true, priceNumber);//输入框中没有数据

        } else {
            setPriceInput(false, priceNumber);//输入框中有数据
        }

    }

    //价格输入框的输入
    private void setPriceInput(boolean bl, WebElement priceNumber) {

        if (bl) {
            priceNumber.sendKeys(written);
        } else {
            priceNumber.clear();
            priceNumber.sendKeys(written);
        }
    }

    //商品图片数量可上传的个数
    public int commodityPicturesNumberBelow() {
        return 5 - commodityPicturesNumber();
    }

    //商品图片数量的判断
    private int commodityPicturesNumber() {
        driver = FoxDriver.getWebDrivaer();
        WebElement pictures = driver.findElement(By.cssSelector("ul[id=J_pic-box][class=uploadPict]"));
        List<WebElement> li = pictures.findElements(By.tagName("li"));
        int size = li.size();
        return size;
    }

    //店铺执照可上传的数量
    public int shopLicenseBelow() {
        return 2 - shopLicense();
    }

    //店铺执照数量的判断
    private int shopLicense() {
        WebElement pictures = driver.findElement(By.cssSelector("ul[id=J_piczz-box][class=uploadPict]"));
        List<WebElement> li = pictures.findElements(By.tagName("li"));
        int size = li.size();
        return size;
    }


    //店铺实拍可上传的数量
    private int shopRealShotBelow() {
        return 5 - shopRealShot();
    }

    //店铺实拍数量的判断
    private int shopRealShot() {
        WebElement pictures = driver.findElement(By.cssSelector("ul[id=J_pic-box][class=uploadPict]"));
        List<WebElement> li = pictures.findElements(By.tagName("li"));
        int size = li.size();
        return size;
    }

    //根据位置统计当前图片的数量
    public int pictureStatistics(int number) {
        return number - pictureQuantity();
    }

    //根据位置统计当前可上传的图片数量
    public int pictureQuantity() {
        WebElement pictures = driver.findElement(By.cssSelector(position));
        List<WebElement> li = pictures.findElements(By.tagName(written));
        int size = li.size();
        return size;
    }

}
