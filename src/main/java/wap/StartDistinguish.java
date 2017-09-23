package wap;

import common.tool.SystemOut;
import common.tool.conversion.CharacterString;
import common.tool.informationException.ErrorException;
import wap.business.example.ShopScene;
import wap.business.example.bean.EnumProgramBean;

/**
 * 用来区分所要执行的用例
 * Created by ${XiaoHuiHui} on 2017/9/20 on 17:17.
 * XiaoHiiHui [704866169@qq.com]
 */
public class StartDistinguish {

    public static void startDistinguish(EnumProgramBean epb) {

        try {
            switch (epb.getFour()) {

                case "Business":
                    //设置菜单的内容
                    new CharacterString().stringsToString(epb.getSeven(), "");
                    new ShopScene().getManagementScene();
                    break;

                default:
                    SystemOut.getStringOut("没有找到相应的场景?、、、、、、、");
                    break;

            }
        } catch (Exception e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
    }


}
