package common.parameter;

/**
 * Created by ${XiaoHuiHui} on 2017/1/16.
 * XiaoHiiHui [704866169@qq.com]
 */
public class WapUrl {
    
    //前缀
    private String shopUrl = "http://****/";
    private String bossUrl = "http://----/";

    //打开的网址
    private String webHttp = shopUrl + "user/login/";

    //店铺管理子目录的网址
    private String commodityManageUrl[] = {shopUrl + "setting/shop/edit", shopUrl + "setting/shop/notice"
            , shopUrl + "setting/shop/set", shopUrl + "setting/fetch"};

    //商品管理子目录的网址
    private String shopManagementUrl[] = {shopUrl + "goods/new", shopUrl + "goods",
            shopUrl + "goods/group", shopUrl + "goods/comment"};

    //供货商品子目录的网址
    private String supplySystem[] = {shopUrl + "supply/goods/goods",
            shopUrl + "sporders/orders"};

    //商品编辑
    private String editCommodity = shopUrl + "goods/edit/";

    //店铺主页
    private String homePage = "http://****";

    //商品订单页面
    private String supplyUrl = shopUrl + "sporders/order/";

    //商品订单页面
    private String suppluOrder = shopUrl + "sporders/orders";

    //找回密码页面
    private String  passwordRetrieval = shopUrl + "user/findpwd";

    //注册页面
    private String register = shopUrl + "user/register";


    //大王的链接
    private String bossshopUrlLog = bossUrl + "login";


    public String getWebHttp() {
        return webHttp;
    }

    public String[] getCommodityManageurl() {
        return commodityManageUrl;
    }

    public String[] getShopManagementUrl() {
        return shopManagementUrl;
    }

    public String[] getSupplySystem() {
        return supplySystem;
    }

    public String getEditCommodity() {
        return editCommodity;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getSupplyUrl() {
        return supplyUrl;
    }

    public String getSuppluOrder() {
        return suppluOrder;
    }

    public String getBossshopUrlLog() {
        return bossshopUrlLog;
    }

    public String getPasswordRetrieval() {
        return passwordRetrieval;
    }

    public String getRegister() {
        return register;
    }
}
