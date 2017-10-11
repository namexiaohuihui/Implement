package wap.business.example;

import common.FoxDriver;
import common.parameter.Parameter;
import common.tool.SystemOut;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.ElementInput;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;

import java.util.Date;

/**
 * 实现登录
 * Created by Administrator on 2016/9/26.
 * http://seller.52lin.net/goods/comment?page=1
 */
public class Signin extends ShopScene {

    private WebDriver driver = FoxDriver.getWebDrivaer();//浏览器对象
    private String load;//路径
    private String phone = "phone";//账号
    private String password = "password";//密码
    private String loginwater = "loginwater";//登录
    private String divErrormsg = "div.errormsg";//错误提示栏

    private EnumProgramBean epb;//用例参数
    private String reason;//记录失败或者成功的原因

    //读取工作薄的行数
    private int rowNum = 4;

    //记录工作薄中的总行数
    private int rowAllNum = 0;

    private boolean bLean = false;//用于判断程序是否执行成功

    public Signin(EnumProgramBean epb) {
        //获取用例路径，该路径存储的是用例路径以及公共数据
        this.load = epb.getOne() + epb.getTwo() + epb.getThree();
        rowAllNum = new ReadExcel().singleXlsx(load, 2);
    }

    public Signin() {

    }

    public void landSingin() {
        try {
            //实例化参数
            stringConversion();

            ElementInput ele = new ElementInput();

            //        获取账号输入框并输入内容
            ele.accordingToId(phone, Parameter.accountTop);

            //        获取密码输入框并输入内容
            ele.accordingToId(password, Parameter.passWordTop);

            //        点击登录
            new Preservation().buttonClassName(loginwater);

            //点击之后会卡，所以要先睡三秒
            //sleep(3000);

            //登录失败的提示语句。。提示语句长度小于3的时候说明登录成功
            webElementError();

        } catch (Exception e) {
            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);
        }
    }

    //    打印输出登录失败的原因
    private void webElementError() {
        //调用判断的类
        ElementExistence ele = new ElementExistence();
        SystemOut.getStringOut("登陆失败提示获取时间" + new Date());
        //判断该元素是否存在，存在返回真
        Boolean existence = ele.accordingToCssSelector(divErrormsg);

        SystemOut.getStringOut("登陆失败提示完成时间" + new Date());
        if (existence) {
            //获取数据并读取失败的原因
            WebElement element = driver.findElement(By.cssSelector(divErrormsg));
            if (element.getText() != null && epb.getFive() != null && epb.getFive().equals(element.getText())) {
                SystemOut.getStringOut(rowNum + "次登录失败，提示语句为:" + epb.getFive());
            } else {
                SystemOut.getStringOut(rowNum + "次登录失败，提示错误为:" + epb.getFive());
            }
            //比较记录的次数，最大失败次数为用例最大时则退出浏览器
            if (rowNum == rowAllNum) {
                FoxDriver.shotSelenium();
            } else {
                rowNum++;
                landSingin();
            }
        } else {
            //读取首页的内容并打印数据提示登录成功
            SystemOut.getStringOut(rowNum + "次登录成功");
            Parameter.LOGIN_STATUS = true;
        }

    }


    private void stringConversion() {

        //调用公共类来做处理。。。
        epb = StartData.readLoad(load, 2, rowNum);
        //SystemOut.getStringOut("表格中读取的数据" + epb.toString());

        String[] strings = new CharacterString().stringsToString(epb.getFour(), ":");
        Parameter.accountTop = strings[0];
        Parameter.passWordTop = strings[1];

    }
}
