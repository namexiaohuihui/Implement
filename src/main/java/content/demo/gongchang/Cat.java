package content.demo.gongchang;

import common.tool.SystemOut;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 16:23.
 * XiaoHiiHui [704866169@qq.com]
 */
public class Cat implements Animal {
    @Override
    public void say() {
        SystemOut.getStringOut("I am big cat, meow");
    }
}
