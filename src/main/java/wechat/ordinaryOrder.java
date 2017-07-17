package wechat;

import LnsmInitialize.FoxDriver;
import LnsmInitialize.LnsmRegister;
import LnsmUi.ElementInput;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.LnsmSystemOut;
import SingleStep.Generallist;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by 70486 on 2017/7/6 on 21:13.
 */
public class ordinaryOrder {

    private String shopUrl = "http://---";

    private String login = shopUrl + "password?f=/goods/hot-goods";

    LnsmRegister lr = new LnsmRegister();

    private String zhanghao = "----";
    private String mima = "-------";

    WebDriver driver;

    @Test
    public void startLogin() {
        try {
            lr.openBrowser(login);
            driver = FoxDriver.getWebDrivaer();
            loginTo();
            address();
            environmentalScience();
            detailedList();
        } catch (Exception e) {
            e.printStackTrace();
            FoxDriver.shotSelenium();
        }
    }

    //登录
    public void loginTo() throws InterruptedException {
        ElementInput elementInput = new ElementInput();
        elementInput.accordingToId("J_tel", zhanghao);
        elementInput.accordingToId("J_pwd", mima);
        LnsmPreservation.getPreservation("J_login");
        sleep(1000);
    }

    //遮盖、切换、地址
    private void address() {
        //遮盖
        LnsmPreservation.getButtonCssSelector("i.guide-btn");
        //切换
        LnsmPreservation.getButtonCssSelector("a.nav-nearby");
        //进入地址
        LnsmPreservation.getButtonCssSelector("span.flex1.txt-elps");

        //选择地址
        WebElement el = driver.findElement(By.cssSelector("div.m-map-location li:nth-child(2)"));
        LnsmSystemOut.getStringOut(el.getText() + "--");
        LnsmPreservation.getWebElementOb(el);
    }

    //店铺的确认订单
    private void environmentalScience() throws InterruptedException {

        //进入店铺
        WebElement test = driver.findElement(By.partialLinkText("哈哈丶嘟嘟噜店铺"));
        LnsmPreservation.getWebElementOb(test);

        //选择商品
        String str = "span.J_add.shop-goods-add.icon-font.icon-plus-str";
        List<WebElement> goods = driver.findElements(By.cssSelector(str));
        String cur = "span.tab-item.tab-item-cur";
        LnsmPreservation.getButtonCssSelector(cur);
        for (int i = 0; i < 2; i++) {
            LnsmSystemOut.getStringOut(i + "选择");
            WebElement good = goods.get(i);
            LnsmPreservation.getWebElementOb(good);
        }
        LnsmSystemOut.getStringOut("选择完了");
        //去结算:按钮不可点击时是dis。。
        // String dis = "a.J_goBuy.m-cart-by.m-cart-by-dis";
        String by = "a.J_goBuy.m-cart-by";
        LnsmPreservation.getButtonCssSelector(by);

    }

    //确认订单页面的清单
    private void detailedList() {
        Double sunNumber = 0d;
        Double fullCut = 0d;
        Double red = 0d;
        Double number = 0d;
        Double distribution = 0d;
        Double total = 0d;
        Generallist generallist = new Generallist();
        //商品
        List<WebElement> prices = driver.findElements(By.cssSelector("p.order-goods-price span:nth-child(1)"));
        List<WebElement> sums = driver.findElements(By.cssSelector("span.J_goodsSum"));
        List<Map<String, Double>> jihe = new ArrayList<>();

        for (int i = 0; i < prices.size(); i++) {
            String price = prices.get(i).getText();
            double nums = qiege(price, 1);
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
            LnsmSystemOut.getStringOut(i + "个商品的总价" + pric[i]);
            sunNumber = sunNumber + pric[i];
        }
        LnsmSystemOut.getStringOut("商品的总价" + sunNumber);

        try {
            //满减
            String prv = driver.findElement(By.id("J_prv")).getText();
            generallist.setFullCut(qiege(prv, 2, "."));
            fullCut = generallist.getFullCut();
            LnsmSystemOut.getStringOut("满减的金额" + fullCut);
        } catch (Exception e) {
            LnsmSystemOut.getStringOut("满减不存在ya");
        }
        //红包
        String spans = "div.J_result.order-result>a>p span:nth-child(2)";
        String span = driver.findElement(By.cssSelector(spans)).getText();
        generallist.setRed(qiege(span, 2, ">"));
        red = generallist.getRed();
        LnsmSystemOut.getStringOut("红包" + red);

        //数量
        String cn2 = driver.findElement(By.cssSelector("span.c-b2")).getText();
        generallist.setNumber(qiege(cn2, 0, "件"));
        number = generallist.getNumber();
        LnsmSystemOut.getStringOut("数量" + number);

        //配送
        String cb2 = driver.findElement(By.cssSelector("span.J_freight.c-b2")).getText();
        generallist.setDistribution(qiege(cb2, 1, "."));
        distribution = generallist.getDistribution();
        LnsmSystemOut.getStringOut("配送" + distribution);

        //合计
        String cff = driver.findElement(By.cssSelector("span.J_total.c-ff")).getText();
        generallist.setTotal(qiege(cff, 1, "."));
        total = generallist.getTotal();
        LnsmSystemOut.getStringOut("合计" + total);

        //代码计算的价格
        Double prive = sunNumber - fullCut - red;
        if (prive <= 0) {
            LnsmSystemOut.getStringOut("红包大于了总价");
            BigDecimal data1 = new BigDecimal(total);
            BigDecimal data2 = new BigDecimal(distribution);
            int i = data1.compareTo(data2);
            if (i == 0) {
                LnsmSystemOut.getStringOut("实付的价格就是配送费");
            }
        } else {
            prive = prive + distribution;
            LnsmSystemOut.getStringOut("红包小于了总价");
            if (prive == total) {
                LnsmSystemOut.getStringOut("实付的价格就是计算的");
            }
        }
    }


    private double qiege(String prv, int number, String data) {
        prv = prv.replace(" ", "");
        if (data.equals(".")) {
            prv = prv.substring(number, prv.lastIndexOf(data));
        } else if (data.equals("KEY")) {
            prv = prv.substring(number, prv.length());
        } else {
            prv = prv.substring(number, prv.length() - 1);
        }
        double num = Double.parseDouble(prv);
        return num;
    }
    private double qiege(String prv,int number) {
        prv = prv.replace(" ", "");
            prv = prv.substring(number, prv.length());
        double v = Double.parseDouble(prv);
        return v;
    }
}
