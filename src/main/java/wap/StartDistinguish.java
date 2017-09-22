package wap;

import common.tool.SystemOut;
import common.tool.conversion.CharacterString;
import common.tool.informationException.ErrorException;
import wap.business.StartData;
import wap.business.example.ShopScene;
import wap.business.example.Signin;
import wap.business.example.bean.EnumProgramBean;

import java.io.IOException;

/**
 * 用来区分所要执行的用例
 * Created by ${XiaoHuiHui} on 2017/9/20 on 17:17.
 * XiaoHiiHui [704866169@qq.com]
 */
public class StartDistinguish {

    public static void startDistinguish() {
        CharacterString cString = new CharacterString();
        //根据路径切割字符，得到想要的数据
        String sString = cString.stringsToString(StartData.load);
        switch (sString) {
            case "商家信息管理场景":
                new ShopScene().getManagementScene();
                break;
            default:
                SystemOut.getStringOut("没有找到相应的场景?、、、、、、、");
                break;
        }
    }
}
