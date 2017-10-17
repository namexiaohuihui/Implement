package wap.business.example.innose.information;

import com.google.gson.Gson;
import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.ElementObtain;
import common.tool.caninput.Preservation;
import common.tool.conversion.TimeConversionDate;
import common.tool.excelfile.ReadExcel;
import common.tool.excelfile.ReadFile;
import common.tool.informationException.ErrorException;
import common.tool.mysqls.MysqlInquire;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.bean.ShopNoticesBean;
import wap.business.example.bean.elementBean.noticesBean;
import wap.business.example.innose.Information;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * 记事本读写并写入
 *             text.sendKeys(new ReadFile().noticesFile("E:\\drivers\\BigDataFile\\ShopNotices"));
 * 介绍
 * 点击年份元素对象之后快速选择年份需求暂时放弃。
 * Created by Administrator on 2016/11/1.
 */
public class ShopNotices extends Information {

    //    获取driver对象
    WebDriver driver = FoxDriver.getWebDrivaer();

    private String LOAD_CASE;

    private EnumProgramBean caseBean;

    private String sql;

    //用例的数量
    private int rowNum;

    //读取用例的薄位置
    private int numSheet = 1;

    public ShopNotices() {
    }


    public void getAnnouncement(EnumProgramBean epb) {

        captureMenu(epb);

        noticesModifyJudge notices = new noticesModifyJudge();

        for (int i = 1; i <= rowNum; i++) {
            caseBean = StartData.readLoad(LOAD_CASE, numSheet, i);
            //SystemOut.getStringOut(caseBean.toString());
            switch (caseBean.getThree()) {
                case "验证":
                    notices.judgeNotices(caseBean);
                    break;
                case "修改":
                    notices.modifyNotices(caseBean);
                    //执行完毕之后要点击保存
                     //preservaTion(caseBean.getZero());
                    break;
                default:
                    System.out.println(caseBean.getFour() + "要设置的元素不存在..");
                    break;
            }

        }
    }

    protected void preservaTion(String zero) {
        Preservation preservation = new Preservation();
        //    点击保存按钮
        preservation.buttonCssSelector(noticesBean.noticesave);

        //    判断点击保存之后，提示信息是否出现，如果出现了提示信息是什么
        successMessageJudge(zero);
    }



    /**
     * 点击保存之后提示信息是否出现
     * @param string 传入提示信息的class属性
     * 通过工具类判断该提示信息是否出现。
     *如果出现了先读取提示信息的内容
     * 在判断该内容是保存成功的提示还是保存失败的提示
     */
    protected void successMessageJudge(String zero) {
//        判断是否存在
        if (new ElementExistence().accordingToCssSelector(noticesBean.successMessage)) {

            //读取对象的值
            WebElement ele = By.cssSelector(noticesBean.successMessage).findElement(driver);
            String operation = new ElementObtain().operation(ele, "");

            String message = zero + "比较失败";

            judgmentParameterText(operation,noticesBean.promptCorrect,message);


        } else {
            System.out.println("元素不存在");
        }
    }



    protected void captureMenu(EnumProgramBean epb) {
        try {
            //设置用例路径
            this.LOAD_CASE = epb.getOne() + epb.getTwo() + epb.getThree();

            //sql
            this.sql = epb.getSeven();

            //用例对象
            ReadExcel readExcel = new ReadExcel();

            //读取指定路径中用例的行数
            rowNum = readExcel.singleXlsx(LOAD_CASE, numSheet);

            mysqlInquire();
        }catch (Exception e){
            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);
        }

    }
    //返回查询语句的内容。
    protected void mysqlInquire() {
        //sql = "select * from ph_exclusive.ph_dianpu;";
        NoticesStatic.bean = new ShopNoticesBean();
        //数据库连接及查询
        JSONObject jsonObject = new MysqlInquire().dataMysqlColumnAllRow(sql);
        Gson gson = new Gson();
        NoticesStatic.bean = gson.fromJson(jsonObject.toString(),
                (Type) NoticesStatic.bean.getClass());
        //SystemOut.getStringOut("查询到的数据内容" + NoticesStatic.bean);
    }
    //内部类用于保存局部信息
    public static class NoticesStatic {
        //mysql语句所查询到的内容
        public static ShopNoticesBean bean;

        public ShopNoticesBean getBean() {
            return bean;
        }

        public void setBean(ShopNoticesBean bean) {
            this.bean = bean;
        }
    }

}
