package wap.business.example;

import common.tool.SystemOut;
import common.tool.conversion.CharacterString;
import common.tool.informationException.ErrorException;
import wap.business.StartData;
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
        String sString = cString.stringsToString(StartData.load);
        try {
            switch (sString) {
                case "商家信息管理场景":
                    EnumProgramBean epb = StartData.readExcleData();//从用例里面读取执行文件所在位置
                    startDistinguish();
                    break;

                case "开始结束":
                    Signin signin = new Signin(StartData.load);
                    signin.landSingin();
                   // ManagementHomepage managementHomepage = new ManagementHomepage();
                    break;
                default:
                    SystemOut.getStringOut("没有找到要执行的用例呢?、、、、、、、");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ErrorException e) {
            e.printStackTrace();
        }
    }
}
