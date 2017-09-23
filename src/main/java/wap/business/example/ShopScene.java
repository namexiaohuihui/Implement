package wap.business.example;

import common.parameter.Parameter;
import common.tool.SystemOut;
import common.tool.excelfile.ReadExcel;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.homeAddress.ManagementHomepage;

/**
 * Created by ${XiaoHuiHui} on 2017/9/21 on 16:15.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ShopScene {

    private String sLoad;
    private int numSheet = 1;
    private int rowNum = 1;


    public void getManagementScene() {
            //将场景的路径保存下来
            sLoad = StartData.load;
            ReadExcel readExcel = new ReadExcel();
            rowNum = readExcel.singleXlsx(sLoad, numSheet);

            for (int i = 1; i < rowNum; i++) {
                //从用例里面读取执行文件所在位置
                EnumProgramBean epb = StartData.readExcleLoad(numSheet, i);
                startDistinguish(epb);
            }
    }

    public void startDistinguish(EnumProgramBean epb) {

        String load = epb.getOne() + epb.getTwo() + epb.getThree();//获取用例路径
        String sString = epb.getFour();//切割路径得到自己想要的东西

        //登录
        if (sString.equals(Parameter.Scene_Menu.get(0))) {
            SystemOut.getStringOut(Parameter.Scene_Menu.get(0) + "进来了。");
            Signin signin = new Signin(load);
            signin.landSingin();

            //判断是否登录了
        } else if (Parameter.LOGIN_STATUS) {
            //第一个菜单执行
            if (sString.equals(Parameter.Scene_Menu.get(1))) {
                SystemOut.getStringOut(Parameter.Scene_Menu.get(1) + "进来了。");
                ManagementHomepage managementHomepage = new ManagementHomepage(load);
            }
            //第二个菜单执行
            else if (sString.equals(Parameter.Scene_Menu.get(2))) {
                SystemOut.getStringOut(Parameter.Scene_Menu.get(2) + "进来了。");
            } else {
                SystemOut.getStringOut(epb.getFour() + "------没有相应的用例?、、、、、、、");
            }
        }
        //找不到想要的菜单
        else {
            SystemOut.getStringOut("账号未登录不能进行菜单的操作。。");
        }
    }
}
