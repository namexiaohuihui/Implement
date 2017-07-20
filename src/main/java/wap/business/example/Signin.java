package wap.business.example;

import common.FoxDriver;
import common.parameter.Parameter;
import common.parameter.QueryStatement;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import common.tool.caninput.ElementText;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import common.tool.mysqls.MysqlInquire;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * 实现登录
 * Created by Administrator on 2016/9/26.
 * http://seller.52lin.net/goods/comment?page=1
 */
public class Signin {

    private WebDriver driver = FoxDriver.getFoxDriver();

    public void getRegister() throws InterruptedException {

        Parameter parameter = new Parameter();

        ElementInput ele = new ElementInput();

        //        获取账号输入框并输入内容
        ele.accordingToId("phone", parameter.getAccountFamily());

        //        获取密码输入框并输入内容
        ele.accordingToId("password", parameter.getPassWordFamily());

        //        点击登录
        new Preservation().buttonClassName("loginwater");

        statusVerification();
    }


    private void statusVerification() throws InterruptedException {
        ElementText elementText = new ElementText();
        //找到id和手机
        String id = elementText.accordingToCss("dl.shopFigure dd:nth-child(3)", null);
        String phone = elementText.accordingToCss("dl.shopFigure dd:nth-child(2)", null);

        //数据切割
        CharacterString characterString = new CharacterString();
        int v = (int) characterString.cuttingCharacter(id, ":", ")");
        QueryStatement qs = new QueryStatement();
        String userId = qs.getUserId();
        userId = userId + v;
        SystemOut.getStringOut("打印数据" + userId);
        MysqlInquire inquire = new MysqlInquire();//创建数据库对象
        List<List> dataMysql = inquire.getDataMysql(userId);

        for (int i = 0; i < dataMysql.size(); i++) {
            List list = dataMysql.get(i);
            for (int j = 0 ;j<list.size();j++){
                SystemOut.getStringOut(list.get(j).toString());
            }
        }
    }

    //    输出登录失败的原因
    private void getWebElement(WebElement element) {
        String str = element.getText();
        switch (str) {
            case "请输入11位正确的手机号":
                //    System.out.println("你的手机号输入有误，请查证后重新输入");
                SystemOut.getStringOut(str);
                break;
            case "请输入登录密码":
                //   System.out.println("请输入密码在点击登录按钮");
                SystemOut.getStringOut(str);
                break;
            case "用户名或密码错误":
                //   System.out.println("用户名或密码错误，请查证后重新输入");
                SystemOut.getStringOut(str);
                break;
        }
        FoxDriver.shotSelenium();
    }
}
