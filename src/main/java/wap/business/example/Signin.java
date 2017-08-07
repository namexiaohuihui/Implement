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
import common.tool.mysqls.MysqlInquire;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.Map;

/**
 * 实现登录
 * Created by Administrator on 2016/9/26.
 * http://seller.52lin.net/goods/comment?page=1
 */
public class Signin {

    private WebDriver driver = FoxDriver.getFoxDriver();

    public void landSingin() throws InterruptedException, SQLException {

        Parameter parameter = new Parameter();

        ElementInput ele = new ElementInput();

        //        获取账号输入框并输入内容
      //  ele.accordingToId("phone", parameter.getAccountFamily());

        //        获取密码输入框并输入内容
      //  ele.accordingToId("password", parameter.getPassWordFamily());

        //        点击登录
        new Preservation().buttonClassName("loginwater");

        By by =  By.cssSelector("div.errormsg");
        boolean b = new ElementExistence().waitForElement(by);
        if (b){
            webElementError(driver.findElement(by));
        }else {
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
        //通过内部方法，将数据进行有效的切割
        int v = characterString.digitalExtract(id);

        //通过id来判断是否登录成功
        idIdentify(v,phone);
    }

    private void idIdentify(int v,String phone) throws SQLException {

        //调用sql执行语句
        QueryStatement qs = new QueryStatement();
        String userId = qs.getUserId();

        //拼接查询语句
        userId = userId + v;
        SystemOut.getStringOut("sql执行语句" + userId);

        //创建数据库对象
        MysqlInquire inquire = new MysqlInquire();
        Map<Integer, String> integerStringMap = inquire.dataMysqlColumnRow(userId, 1);

        assert phone.equals(""):"Phone number recognition is wrong";
    }


    //    打印输出登录失败的原因
    private void webElementError(WebElement element) {
        String str = element.getText();
        switch (str) {
            case "请输入11位正确的手机号":
                SystemOut.getStringOut(str);
                break;
            case "请输入登录密码":
                SystemOut.getStringOut(str);
                break;
            case "用户名或密码错误":
                SystemOut.getStringOut(str);
                break;
        }
        FoxDriver.shotSelenium();
    }
}
