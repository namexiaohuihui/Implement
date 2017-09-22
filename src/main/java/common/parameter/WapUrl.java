package common.parameter;

import common.tool.SystemOut;
import org.openqa.selenium.WebDriver;

/**
 * 连接入口
 * Created by ${XiaoHuiHui} on 2017/1/16.
 * XiaoHiiHui [704866169@qq.com]
 */
public class WapUrl {

    //大王前缀
    public static String urlFamily = "http://******/";
    //老板前缀
    public static String urlTop = "http://----/";
    //上帝前缀
    public static String urlStrat = "http://++++";

    public static WapUrl sWapUrl;

    //打开的网址
    public static String webHttpFamily = urlFamily + "user/login/";


    //找回密码页面
    public static String passwordRetrievalFamily = urlFamily + "user/findpwd";

    //注册页面
    public static String registerFamily = urlFamily + "user/register";

    //浏览器登陆
    public static String homeStrat = urlStrat + "password?f=/goods/hot-goods";


    public static WapUrl getsWapUrl() {
        if (sWapUrl == null) {
            sWapUrl = new WapUrl();
           // SystemOut.getStringOut("调用时提示：网址对象为空");
        }
        return sWapUrl;
    }


}
