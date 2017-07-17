package wap.business.example.ligrco.Exhibition.Article;

import LnsmData.CommodityIntroduction;
import LnsmInitialize.FoxDriver;
import LnsmUitl.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 商品编辑页面数据的判断。。
 * Created by ${XiaoHuiHui} on 2017/5/16 on 17:32.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ProductEditor {

    private WebDriver driver = FoxDriver.getWebDrivaer();

    private CommodityIntroduction comm;

    private String names;

    private String prices;

    private String contents;

    private final String tr = "Y";

    private final String fa = "N";

    public ProductEditor(CommodityIntroduction comm) {
        this.comm = comm;
    }

    //编辑页面的数据判断以及操作
    public void setProductEditor() throws InterruptedException {

        System.out.println(comm.toString());
        boolean firstCid = comm.getFirstCid().equals(fa) ? false : true;//主类
        if (firstCid) {
            System.out.println("主类需要改" + comm.getFirstCid());
            //商品主类目的设定
          new LnsmSelect().PropertyValue(driver, "first_cid", comm.getFirstCid());
        } else {
            System.out.println("主类不需要改" + comm.getFirstCid());
        }

        boolean SecondCid = comm.getSecondCid().equals(fa) ? false : true;//子类
        if (SecondCid) {
            System.out.println("子类需要改" + comm.getSecondCid());
            //商品子类目的设定
            new LnsmSelect().PropertyValue(driver, "second_cid", comm.getSecondCid());
        } else {
            System.out.println("子类不需要改" + comm.getSecondCid());
        }

        boolean Checkbox = getBoolean(comm.getCheckbox());//分组
        if (Checkbox) {
            System.out.println("商品分组需要改" + Checkbox);
            String[] checkbox = comm.getCheckbox();
            for (String checkout : checkbox) {
                int i = Integer.parseInt(checkout.trim());
                if (i != 1) {
                    List<WebElement> elements = driver.findElements(By.cssSelector("span[class=lbl]"));
                    int size = elements.size();
                    if (i <= size) {
                        elements.get(i - 1).click();
                        LnsmSystemOut.getStringOut("商品分组进来了。" + i);
                    } else {
                        LnsmSystemOut.getStringOut("所需点击的分组位置大于了当前所需的。");
                    }
                } else {
                    LnsmSystemOut.getStringOut("全部分组默认勾选。不需要进行点击");
                }
            }
        } else {
            System.out.println("商品分组不需要改" + Checkbox);
        }

        /*
        boolean Blank = comm.getBlank().equals(fa) ? false : true;//设置分组按钮
        if (Blank) {
            System.out.println("管理分组按钮需要改" + comm.getBlank());
        } else {
            System.out.println("管理分组按钮不需要改" + comm.getBlank());
        }

        boolean Groupinfo = comm.getGroupinfo().equals(fa) ? false : true;//首页刷新
        if (Groupinfo) {
            System.out.println("首页刷新按钮需要改" + comm.getGroupinfo());
            driver.findElement(By.cssSelector("")).click();//点击刷新按钮
        } else {
            System.out.println("首页刷新按钮不需要改" + comm.getGroupinfo());
        }
*/

        boolean Productname = comm.getProductname().equals(fa) ? false : true;//商品名称
        if (Productname) {
            //类调用。。。
            System.out.println("商品名称改" + comm.getProductname());
            new GoodStatus(driver, comm.getProductname()).setName();
            names = comm.getProductname();
        } else {
            System.out.println("商品名称不需要改" + comm.getProductname());
            //如果不需要修改名称就直接获取原本的数据
            names = driver.findElement(By.cssSelector("input[id=productname][name=name]")).getAttribute("value");
        }

        boolean Price = comm.getPrice().equals(fa) ? false : true;//价格
        if (Price) {
            System.out.println("价格改" + comm.getPrice());
            new GoodStatus(driver, comm.getPrice()).setPrice();
            prices = comm.getPrice();
        } else {
            System.out.println("价格不需要改" + comm.getPrice());
            //如果不需要修改价格就直接获取原本的数据
            prices = driver.findElement(By.cssSelector("input[id=price][name=price]")).getAttribute("value");
        }

        if (getBoolean(comm.getLogo())) {//判断是否需要上传logo
            String[] logoStr = comm.getLogo();//读取文档中需要上传的图片路径名称

            String position = "ul[id=J_pic-box][class=uploadPict]";//定义图片按钮的位置
            GoodStatus goodStatus = new GoodStatus(driver, position, "li");//定义类对象,实例化参数
            int number = goodStatus.pictureStatistics(5);//获取需要上传的图片数量

            //实现上传
            LnsmSystemOut.getStringOut("编辑时的图片需要上传的数量 ", number + "");
            for (int i = 0; i < number && i < logoStr.length; i++) {//上传的图片数量应当小于可上传的空余数量以及文档中给出的数据
                LnsmPreservation.getButtonCssSelector("object[id=SWFUpload_0][class=swfupload]");
                LnsmPicture.getLogo(driver, "SWFUpload_0", logoStr[i]);
            }
        } else {
            LnsmSystemOut.getStringOut("编辑时的图片不需要进行上传");
        }

        boolean Content = comm.getContent().equals(fa) ? false : true;//详情
        if (Content) {
            System.out.println("详情需要改" + comm.getContent());
            //商品编辑的入口
            String load = "iframe[class=ke-edit-iframe]";
            new LnsmFrame().LnsmFrameCss(driver, load, comm.getContent());
            contents = comm.getContent();
        } else {
            System.out.println("详情不需要改" + comm.getContent());
            //商品编辑的入口
            String load = "iframe[class=ke-edit-iframe]";
            contents = new LnsmFrame().LnsmFrameCss(driver, load);
        }

        boolean Preview = comm.getPreview().equals(fa) ? false : true;//预览
        if (Preview) {
            String load = "button.btn.btn-default.preview";
            WebElement element = driver.findElement(By.cssSelector(load));
            element.click();

            //商品详情预览数据对比
            if (!detailsPreview()) {
                LnsmSystemOut.getStringOut("商品预览上面的数据不对");
            }

            LnsmPreservation.getButtonCssSelector("a[class=aui_close]");

        } else {
            System.out.println("预览不需要改" + comm.getPreview());

        }

        //    boolean Preservation = comm.getPreservation().equals(fa) ? false : true;//保存
        boolean Preservation = false;//保存
        if (Preservation) {
            System.out.println("保存需要改" + comm.getPreservation());
            //   LnsmPreservation.getStringOut("button[id=productsave][name=productsave]");
        } else {
            System.out.println("保存不需要改" + comm.getPreservation());
            driver.navigate().back();
            //得到alert\confirm\prompt对话框的对象
            String alert = LnsmPreservation.getAlert(true);//点击系统对话框的确定按钮
            LnsmSystemOut.getStringOut("系统对话框的标题是",alert);
        }

    }


    //判断商品图片数量，价格，商品名称，以及商品详情是否正确
    private boolean detailsPreview() {
        boolean bl = true;
        String picture = textData("span[class=mke_ns2]");

        /*
        Integer.parseInt(text)为商品详情预览显示的商品图片总数量
        oodStatus.pictureQuantity()为商品编辑页面显示的商品图片总数量
         */
        String position = "ul[id=J_pic-box][class=uploadPict]";//定义图片按钮的位置
        GoodStatus goodStatus = new GoodStatus(driver, position, "li");//定义类对象,实例化参数
        if (Integer.parseInt(picture) == goodStatus.pictureQuantity()) {
            LnsmSystemOut.getStringOut("预览上面的商品图片总数量相等");
        } else {
            return false;
        }

//        商品名称比较
        String name = textData("div.detail-goods-cont>p");
        if (name.equals(names)) {
            LnsmSystemOut.getStringOut("预览上面的商品名称正确");
        } else {
            return false;
        }

//        商品价格比较
        String price = textData("span.detail-goods-pirce > strong");
        if (price.equals(prices)) {
            LnsmSystemOut.getStringOut("预览上面的商品价格正确");
        } else {
            return false;
        }

//        商品描述比较
        String content = textData("div.detail-cont>div");
        if (content.equals(contents)) {
            LnsmSystemOut.getStringOut("预览上面的商品描述正确");
        } else {
            return false;
        }
        return bl;
    }

    private String textData(String css) {
        return driver.findElement(By.cssSelector(css)).getText();
    }

    //判断分组以及logo是否需要需改
    private boolean getBoolean(String[] strings) {
        return strings[0].toString().equals(fa) ? false : true;
    }

}
