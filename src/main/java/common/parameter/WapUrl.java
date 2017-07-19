package common.parameter;

/**
 * 连接入口
 * Created by ${XiaoHuiHui} on 2017/1/16.
 * XiaoHiiHui [704866169@qq.com]
 */
public class WapUrl {

    //前缀
    private String urlFamily = "http://****/";
    private String urlTop = "http://----/";
    private String urlStrat = "http://++++";

    //家
    private String homePageFamily = urlFamily + "http://****";

    //打开的网址
    private String webHttpFamily = urlFamily + "user/login/";

    //信息子目录的网址
    private String commodityManageFamily[] = {urlFamily + "setting/shop/edit", urlFamily + "setting/shop/notice"
            , urlFamily + "setting/shop/set", urlFamily + "setting/fetch"};

    //展示子目录的网址
    private String shopManagementFamily[] = {urlFamily + "goods/new", urlFamily + "goods",
            urlFamily + "goods/group"};

    //交互子目录的网址
    private String supplySystemFamily[] = {urlFamily + "supply/goods/goods",
            urlFamily + "sporders/orders"};

    private String codeFamily = urlFamily + "stats/qrcode/users";
    //商品订单页面
    private String supplyFamily = urlFamily + "sporders/order/";

    //商品订单页面
    private String suppluFamily = urlFamily + "sporders/orders";

    //找回密码页面
    private String passwordRetrievalFamily = urlFamily + "user/findpwd";

    //注册页面
    private String registerFamily = urlFamily + "user/register";

    //入口
    private String homeStrat = urlStrat + "password?f=/goods/hot-goods";

    //大王的链接
    private String hopTop = urlTop + "login";

    public WapUrl() {
    }

    public String getUrlFamily() {
        return urlFamily;
    }

    public String getUrlTop() {
        return urlTop;
    }

    public String getUrlStrat() {
        return urlStrat;
    }

    public String getHomePageFamily() {
        return homePageFamily;
    }

    public String getWebHttpFamily() {
        return webHttpFamily;
    }

    public String[] getCommodityManageFamily() {
        return commodityManageFamily;
    }

    public String[] getShopManagementFamily() {
        return shopManagementFamily;
    }

    public String[] getSupplySystemFamily() {
        return supplySystemFamily;
    }

    public String getCodeFamily() {
        return codeFamily;
    }

    public String getSupplyFamily() {
        return supplyFamily;
    }

    public String getSuppluFamily() {
        return suppluFamily;
    }

    public String getPasswordRetrievalFamily() {
        return passwordRetrievalFamily;
    }

    public String getRegisterFamily() {
        return registerFamily;
    }

    public String getHomeStrat() {
        return homeStrat;
    }

    public String getHopTop() {
        return hopTop;
    }
}
