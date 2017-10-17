package wap.business.example.innose.information;


import com.google.gson.Gson;
import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import common.tool.caninput.Preservation;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import common.tool.mysqls.MysqlInquire;
import common.tool.upload.PictureImage;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.bean.StoreInformationBean;
import wap.business.example.innose.Information;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 店铺信息的操作判断页面。
 * 如果是验证数据就进入InformationJudgment
 * 修改数据就进入InformationModify
 * 否者就提示问题。
 * 傻逼正则："[^\\x00-\\xff]|\\d{0,9}\\.\\d{0,9}";
 * Created by Administrator on 2016/11/1.
 */
public class StoreInformation extends Information {

    WebDriver driver = FoxDriver.getWebDrivaer();

    //路径
    private String load;

    //sql
    private String sql;

    public StoreInformation(EnumProgramBean epb) {
        this.load = epb.getOne() + epb.getTwo() + epb.getThree();
        this.sql = epb.getSeven();
    }

    public StoreInformation() {
    }

    public void informationStore() {

        ReadExcel readExcel = new ReadExcel();
        int row = readExcel.singleXlsx(load, 1);
        mysqlInquire();
        Preservation preservation = new Preservation();
        for (int i = 12; i <= row; i++) {
            //用例执行表的内容
            EnumProgramBean epb = StartData.readLoad(load, 1, i);
            System.out.println("第的内容:" + i + "------------" + epb.toString());
            switch (epb.getThree()) {

                case "验证":
                    new InformationJudgment(epb).judgmentInformation();
                    StoreMovesWindow(0);
                    break;

                case "修改":
                    new InformationModify(epb).modifyInformation();
                    StoreMovesWindow(0);
                    preservation.buttonCssSelector(".btn.btn-primary");
                    break;

                default:
                    SystemOut.getStringOut("没有这个内容数据" + epb.getThree());
                    break;
            }
        }
    }


    protected StoreInformationBean getMysqlInquire(){
        return StoreStatic.bean;
    }


    public void licensePhoto(String load, String id, String address) {

        List<WebElement> el1 = driver.findElements(By.cssSelector(load));
        int i1 = 2 - el1.size();
        System.out.println("上传" + i1);
        for (int i = 0; i < i1; i++) {
            PictureImage.setLogoId(driver, id, address);
        }
    }

    protected void bedGoToPicture(String cssSelector, String route, String massage) throws IOException, InterruptedException {
        PictureImage.setLogoCssSelector(driver, cssSelector, route, massage);
    }


    /**
     * 目前真针对于内部输入框的输入..
     *
     * @param iframe  iframe对象的css
     * @param body    body对象的css
     * @param message 内容的输入
     */
    protected void iframeInput(String iframe, String body, String message) {

        ElementInput eleInput = new ElementInput();
        eleInput.operationIframe(iframe, body, message);

    }


    /**
     * @param clazz     类名
     * @param method    方法名
     * @param cause     错误信息
     * @param parameter 用例编号
     */
    protected void caseOutInformation(String clazz, String method, Throwable cause, String parameter) {
        //输出发生错误的地方
        new ErrorException(clazz, method, cause);

        //输出用例信息
        SystemOut.caseEditFail(parameter);
    }

    //打印数据而已
    protected void caseOutInformation(String parameter) {
        //输出用例信息
        SystemOut.caseEditSuccess(parameter);
    }





    //内部类用于保存局部信息
    public static class StoreStatic {
        //mysql语句所查询到的内容
        public static StoreInformationBean bean;

        public StoreInformationBean getBean() {
            return bean;
        }

        public void setBean(StoreInformationBean bean) {
            this.bean = bean;
        }
    }

    //返回查询语句的内容。
    protected void mysqlInquire() {
        //sql = "select * from ph_exclusive.ph_dianpu;";
        StoreInformation.StoreStatic.bean = new StoreInformationBean();
        //数据库连接及查询
        JSONObject jsonObject = new MysqlInquire().dataMysqlColumnAllRow(sql);
        Gson gson = new Gson();
        StoreInformation.StoreStatic.bean = gson.fromJson(jsonObject.toString(),
                (Type) StoreInformation.StoreStatic.bean.getClass());
        //SystemOut.getStringOut("查询到的数据内容" + StoreStatic.bean);
    }
}