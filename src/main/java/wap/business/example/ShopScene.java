package wap.business.example;

import common.tool.SystemOut;
import common.tool.conversion.CharacterString;
import common.tool.conversion.MutuaMapBean;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.homeAddress.ManagementHomepage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by ${XiaoHuiHui} on 2017/9/21 on 16:15.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ShopScene {

    private String sLoad;
    private int numSheet = 1;
    private int rowNum = 1;


    public void getManagementScene() {
        try {
            //将场景的路径保存下来
            //sLoad = StartData.load;
            sLoad = StartData.load;
            ReadExcel readExcel = new ReadExcel();
            rowNum = readExcel.singleXlsx(sLoad, numSheet);
            for (int i = 1; i < rowNum; i++) {
                //从用例里面读取执行文件所在位置
                EnumProgramBean epb = StartData.readExcleLoad(numSheet, i);
                startDistinguish(epb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ErrorException e) {
            e.printStackTrace();
        }
    }

    public void startDistinguish(EnumProgramBean epb) {
        String load = epb.getOne() + epb.getTwo() + epb.getThree();//获取用例路径
        CharacterString cString = new CharacterString();
        //根据路径切割字符，得到想要的数据
        //SystemOut.getStringOut(load + "傻逼");
        String sString = cString.stringsToString(load);
        switch (sString) {
            case "开始结束":
                SystemOut.getStringOut(sString + "进来了。");
                Signin signin = new Signin(load);
                signin.landSingin();
                break;
            case "管理首页":
                try {
                    SystemOut.getStringOut(sString + "进来了。");
                    ManagementHomepage managementHomepage = new ManagementHomepage(load);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "店铺管理":
                SystemOut.getStringOut(sString + "进来了。");
                break;
            default:
                SystemOut.getStringOut("没有相应的用例?、、、、、、、");
                break;
        }
    }

}
