package wap.business.example.homeAddress;

import common.parameter.Parameter;
import common.tool.SystemOut;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.ElementObtain;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import common.tool.mysqls.MysqlInquire;
import wap.business.example.ShopScene;
import wap.business.example.bean.EnumProgramBean;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * 家门口址的驗證
 * Created by Administrator on 2016/10/31.
 */
public class ManagementHomepage extends ShopScene {

    private String load;
    private EnumProgramBean epb;

    public ManagementHomepage(EnumProgramBean epb) {
        this.load = epb.getOne() + epb.getTwo() + epb.getThree();
        stringConversion();
    }

    private void statusVerification() {
        ElementObtain elementObtain = new ElementObtain();
        //找到账户id和手机
        String sPhone = elementObtain.accordingToCss("dl.shopFigure dd:nth-child(2)", null);

        //利用断言来判断登陆账号跟显示的账号是否一致
        assert sPhone.equals(Parameter.accountTop) : "The store ID information does not match";

        //读取id值然后
        String rId = elementObtain.accordingToCss("dl.shopFigure dd:nth-child(3)", null);

        String pId = elementObtain.accordingToCss("dl.shophome>dt", null);

        //数据切割所在类
        CharacterString characterString = new CharacterString();
        //通过内部方法，将数据进行有效的切割.只读取出数字
        int seId = characterString.digitalExtract(rId);
        String shId = characterString.digitalExtractToString(pId);

        //通过id来判断首页是否进入成功
        //idIdentify(seId, shId);
    }


    private void idIdentify(int seId, String shId) {

        //拼接查询语句
        String sql = epb.getSix() + seId;
        SystemOut.getStringOut("sql执行语句" + sql);

        //创建数据库对象
        MysqlInquire inquire = new MysqlInquire();
        Map<String, String> iMap = inquire.dataMysqlColumnRow(sql, 1);
        SystemOut.getStringOut(iMap.get("one") + "年轻人的最后一次");
        assertEquals("The store ID information does not match",shId,iMap.get("one"));

        SystemOut.getStringOut(epb.getZero() + "用例执行成功");
    }

    //点击菜单
    private void stringConversion() {
        String loadName = new CharacterString().stringsToString(load);
        ElementExistence existence = new ElementExistence();
        boolean b = existence.accordingToLinkText(loadName);
        if (b) {
            //按钮点击对象
            Preservation preservation = new Preservation();
            preservation.buttonLinkText(loadName);

            statusVerification();
        } else {
            SystemOut.getStringOut(loadName + "菜单不存在，用例错了吧。。");
        }
    }

}
