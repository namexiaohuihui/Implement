package wap.business.example;

import common.parameter.Parameter;
import common.tool.SystemOut;
import common.tool.conversion.CharacterString;
import common.tool.excelfile.ReadExcel;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.innose.Information;

/**
 * business的入口
 * Created by ${XiaoHuiHui} on 2017/9/21 on 16:15.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ShopScene {

    private String sLoad;
    private int numSheet = 1;
    private int rowNum = 1;

    //二级菜单存储地
    private String[] listBar;

    public ShopScene(EnumProgramBean epb) {
        //设置菜单的内容
        listBar = new CharacterString().stringsToString(epb.getSeven(), "");
    }

    public ShopScene() {
    }

    public void getManagementScene() {
        //将场景的路径保存下来
        sLoad = StartData.load;
        ReadExcel readExcel = new ReadExcel();
        rowNum = readExcel.singleXlsx(sLoad, numSheet);
        SystemOut.getStringOut("daboss的行数" + rowNum);
        for (int i = 1; i < rowNum; i++) {
            //从用例里面读取执行文件所在位置
            EnumProgramBean epb = StartData.readExcleLoad(numSheet, i);
            startDistinguish(epb);
        }
    }

    public void startDistinguish(EnumProgramBean epb) {

        //登录:元素等于某个值，并且不能为空或者等于空格
        if (epb.getFour() != null & !epb.getFour().equals("") & epb.getFour().equals(listBar[0])) {
            SystemOut.getStringOut(epb.getFour() + "进来了。" + listBar[0]);//打印数据
            Signin signin = new Signin(epb);//创建对象
            signin.landSingin();
            SystemOut.getStringOut(epb.getFour() + "跑完。");
        }

        //判断是否登录了
        else if (Parameter.LOGIN_STATUS) {
            //登陆成功之后来时菜单的游走
            if (epb.getFour() != null & !epb.getFour().equals("") & epb.getFour().equals(listBar[1])) {
                //ManagementHomepage managementHomepage = new ManagementHomepage(epb);
                SystemOut.getStringOut(epb.getFour() + "跑完。");
            }

            //登陆成功之后来时菜单的游走
            else if (epb.getFour() != null & !epb.getFour().equals("") & epb.getFour().equals(listBar[2])) {
                Information information = new Information(epb);
                SystemOut.getStringOut(epb.getFour() + "跑完。");
            }

            //游走中没有找到相应的菜单
            else {
                SystemOut.getStringOut(epb.getFour() + "------没有相应的用例?、、、、、、、");
            }

        }

        //没有登陆成功的输出
        else {
            SystemOut.getStringOut("账号未登录不能进行菜单的操作。。");
        }
    }
}
