package business.example;

import common.tool.caninput.Preservation;

/**
 * 实现退出登录功能
 * Created by Administrator on 2016/9/22.
 */
public class Exitsysos {

    public Exitsysos() {
               Preservation preserva = new Preservation();
               preserva.buttonCssSelector("span.user-info");
               preserva.buttonCssSelector("a.lastone");
    }
}
