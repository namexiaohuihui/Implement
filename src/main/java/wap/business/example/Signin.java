package wap.business.example;

import common.FoxDriver;
import common.parameter.Parameter;
import common.parameter.QueryStatement;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import common.tool.caninput.ElementObtain;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import common.tool.conversion.MutuaMapBean;
import common.tool.excelfile.ReadExcel;
import common.tool.mysqls.MysqlInquire;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.example.bean.EnumProgramBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 实现登录
 * Created by Administrator on 2016/9/26.
 * http://seller.52lin.net/goods/comment?page=1
 */
public class Signin {

    private WebDriver driver = FoxDriver.getFoxDriver();//浏览器对象
    private String load ;//路径
    private String phone = "phone";//账号
    private String password = "password";//密码
    private String loginwater = "loginwater";//登录
    private String divErrormsg = "div.errormsg";//错误提示栏

    private EnumProgramBean epb ;//用例参数
    private String reason ;//记录失败或者成功的原因

    private boolean bLean = false;//用于判断程序是否执行成功
    public Signin(String load) {
        this.load = load;
    }
    public Signin() {

    }

    public void landSingin() {

        String[] strings = new String[0];//获取用例上的数据
        try {
            strings = stringConversion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ElementInput ele = new ElementInput();

        //        获取账号输入框并输入内容
        ele.accordingToId(phone,strings[0]);

        //        获取密码输入框并输入内容
        ele.accordingToId(password, strings[1]);

        //        点击登录
        try {
            new Preservation().buttonClassName(loginwater);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //登录失败的提示语句。。提示语句长度小于3的时候说明登录成功
        try {
            webElementError(driver.findElement(By.cssSelector(divErrormsg)));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //    打印输出登录失败的原因
    private void webElementError(WebElement element) throws SQLException, InterruptedException {
        if (element.getText().length()>3){
            SystemOut.getStringOut("登录失败，原因是:"+element.getText());
            FoxDriver.shotSelenium();
        }else{
            statusVerification();
        }
    }

    private void statusVerification() throws InterruptedException, SQLException {
        ElementObtain elementObtain = new ElementObtain();
        //找到账户id和手机
        String id = elementObtain.accordingToCss("dl.shopFigure dd:nth-child(3)", null);
        String phone = elementObtain.accordingToCss("dl.shopFigure dd:nth-child(2)", null);

        //数据切割所在类
        CharacterString characterString = new CharacterString();
        //通过内部方法，将数据进行有效的切割.只读取出数字
        int v = characterString.digitalExtract(id);

        //通过id来判断是否登录成功
        idIdentify(v,phone);
    }

    private void idIdentify(int v,String phone) throws SQLException {

        //调用sql执行语句
        String sql =  epb.getFour();

        //拼接查询语句
        sql = sql + v;
        SystemOut.getStringOut("sql执行语句" + sql);

        //创建数据库对象
        MysqlInquire inquire = new MysqlInquire();
        Map<String, String> iMap = inquire.dataMysqlColumnRow(sql, 1);

        assert phone.equals(iMap.get("one")):"Phone number recognition is wrong";

    }

    public String[] stringConversion() throws Exception {
        ReadExcel readExcel = new ReadExcel();
        Map<String, String> stringStringMap = readExcel.singleReadXlsx(load, 1, 1);
        epb = (EnumProgramBean)new MutuaMapBean().reflectmapToObject(stringStringMap, new EnumProgramBean().getClass());
        CharacterString cs = new CharacterString();
        return cs.stringsToString(epb.getThree().toString(), "=");
    }
}
