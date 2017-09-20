package wechat;


import common.FoxDriver;
import common.parameter.Parameter;
import common.parameter.WapUrl;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.example.StartEntrance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 确认页面的数据对比
 * Created by 70486 on 2017/7/6 on 21:13.
 */
public class ConfirmOrder {

    //对象的创建
    Parameter parameter = new Parameter();
    Preservation preservation = new Preservation();
    CharacterString chaStr = new CharacterString();
    //浏览器对象
    WebDriver driver;

    @BeforeClass
    public void readyStart() {
        //网址对象的创建
        WapUrl wapurl = new WapUrl();

        //统一网址打开的模板
        driver =FoxDriver.openBrowser(null);
    }

    @Test
    public void startLogin() {
        try {
            //登录
            loginTo();
            //地址
            address();
            //下单
            environmentalScience();
            //确认订单页面的数据对比
            detailedList();
        } catch (Exception e) {
            e.printStackTrace();
            FoxDriver.shotSelenium();
        }
    }

    //登录
    public void loginTo() throws InterruptedException {
        //元素输入对象的创建
        ElementInput elementInput = new ElementInput();

        //参数
        String zhanghao = parameter.getAccountStart();
        String mima = parameter.getPassWordStrat();

        //输入
        elementInput.accordingToId("J_tel", zhanghao);
        elementInput.accordingToId("J_pwd", mima);

        //点击
        preservation.buttonCssSelector("J_login");
    }

    //遮盖、切换、地址
    private void address() throws InterruptedException {
        //遮盖
        preservation.buttonCssSelector("i.guide-btn");
        //切换
        preservation.buttonCssSelector("a.nav-nearby");
        //进入地址
        preservation.buttonCssSelector("span.flex1.txt-elps");

        //选择地址
        WebElement el = driver.findElement(By.cssSelector("div.m-map-location li:nth-child(2)"));
        SystemOut.getStringOut(el.getText() + "--");
        //点击地址
        preservation.webElementOb(el);
    }

    //家的确认
    private void environmentalScience() throws InterruptedException {

        //进入家
        WebElement test = driver.findElement(By.partialLinkText("哈哈丶嘟嘟噜店铺"));
        preservation.webElementOb(test);

        //选择东西
        String str = "span.J_add.shop-goods-add.icon-font.icon-plus-str";
        List<WebElement> goods = driver.findElements(By.cssSelector(str));
        String cur = "span.tab-item.tab-item-cur";
        preservation.buttonCssSelector(cur);
        for (int i = 0; i < 2; i++) {
            SystemOut.getStringOut(i + "选择");
            WebElement good = goods.get(i);
            preservation.webElementOb(good);
        }
        SystemOut.getStringOut("选择完了");
        //去结算:按钮不可点击时是dis。。
        // String dis = "a.J_goBuy.m-cart-by.m-cart-by-dis";
        String by = "a.J_goBuy.m-cart-by";
        preservation.buttonCssSelector(by);

    }

    //确认页面的清单
    private void detailedList() {
        Double sunNumber = 0d;
        Double fullCut = 0d;
        Double red = 0d;
        Double number = 0d;
        Double distribution = 0d;
        Double total = 0d;
        Generallist generallist = new Generallist();
        //东西
        List<WebElement> prices = driver.findElements(By.cssSelector("p.order-goods-price span:nth-child(1)"));
        List<WebElement> sums = driver.findElements(By.cssSelector("span.J_goodsSum"));
        List<Map<String, Double>> jihe = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++) {
            String price = prices.get(i).getText();
            double nums = chaStr.digitalExtract(price);
            String sum = sums.get(i).getText().split("x ")[1];
            double sumnumber = Double.parseDouble(sum);
            Map<String, Double> map = new HashMap<>();
            map.put("price", nums);
            map.put("sum", sumnumber);
            jihe.add(map);
        }
        generallist.setGoods(jihe);
        List<Map<String, Double>> goods = generallist.getGoods();
        Double[] pric = new Double[goods.size()];
        for (int i = 0; i < goods.size(); i++) {
            Map<String, Double> stringMap = jihe.get(i);
            double price = stringMap.get("price");
            double sum = stringMap.get("sum");
            pric[i] = price * sum;
            SystemOut.getStringOut(i + "个商品的总价" + pric[i]);
            sunNumber = sunNumber + pric[i];
        }
        SystemOut.getStringOut("商品的总价" + sunNumber);

        try {
            //满减
            String prv = driver.findElement(By.id("J_prv")).getText();
            generallist.setFullCut(chaStr.priceExtraction(prv));
            fullCut = generallist.getFullCut();
            SystemOut.getStringOut("满减的金额" + fullCut);
        } catch (Exception e) {
            SystemOut.getStringOut("满减不存在ya");
        }
        //红包
        String spans = "div.J_result.order-result>a>p span:nth-child(2)";
        String span = driver.findElement(By.cssSelector(spans)).getText();
        generallist.setRed(chaStr.priceExtraction(span));
        red = generallist.getRed();
        SystemOut.getStringOut("红包" + red);

        //数量
        String cn2 = driver.findElement(By.cssSelector("span.c-b2")).getText();
        generallist.setNumber(chaStr.priceExtraction(cn2));
        number = generallist.getNumber();
        SystemOut.getStringOut("数量" + number);

        //配送
        String cb2 = driver.findElement(By.cssSelector("span.J_freight.c-b2")).getText();
        generallist.setDistribution(chaStr.priceExtraction(cb2));
        distribution = generallist.getDistribution();
        SystemOut.getStringOut("配送" + distribution);

        //合计
        String cff = driver.findElement(By.cssSelector("span.J_total.c-ff")).getText();
        generallist.setTotal(chaStr.priceExtraction(cff));
        total = generallist.getTotal();
        SystemOut.getStringOut("合计" + total);

        //代码计算的价格
        Double prive = sunNumber - fullCut - red;
        if (prive <= 0) {
            SystemOut.getStringOut("红包大于了总价");
            BigDecimal data1 = new BigDecimal(total);
            BigDecimal data2 = new BigDecimal(distribution);
            int i = data1.compareTo(data2);
            if (i == 0) {
                SystemOut.getStringOut("实付的价格就是配送费");
            }
        } else {
            prive = prive + distribution;
            SystemOut.getStringOut("红包小于了总价");
            if (prive == total) {
                SystemOut.getStringOut("实付的价格就是计算的");
            }
        }
    }

}
