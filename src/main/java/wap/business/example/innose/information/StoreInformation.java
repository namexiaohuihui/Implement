package wap.business.example.innose.information;


import com.google.gson.Gson;
import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import common.tool.excelfile.ReadExcel;
import common.tool.mysqls.MysqlInquire;
import common.tool.upload.PictureImage;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.StartData;
import wap.business.example.bean.StoreInformationBean;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.innose.Information;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 信息
 * 傻逼正则："[^\\x00-\\xff]|\\d{0,9}\\.\\d{0,9}";
 * Created by Administrator on 2016/11/1.
 */
public class StoreInformation extends Information {

    // 记录店铺执照上传的对象，方便循环执行
    String photo[];//可删除，执照统一上传一张
    String license[];//可删除，实拍统一上传一张
    WebDriver driver = FoxDriver.getWebDrivaer();
    //WebDriver driver = super.driver;

    //地址所在：省/区、市、县/区、详细地址
    String provinceSele = "select[id='province'][name='province']";
    String citySele = "select[id='city'][name='city']";
    String countySele = "select[id='county'][name='county']";
    String detailed = "input[id='address'][name='address']";

    //经纬度
    String longitude = "input[id='lng'][name='lng']";
    String latitude = "input[id='lat'][name='lat']";

    //执照对应图片的数量
    String piczzFile = "ul[id='J_piczz-box'][class='uploadPict']>li";
    //实拍对应图片的数量
    String picFile = "ul[id='J_pic-box'][class='uploadPict']>li";

    //实拍和执照的上传按钮class名
    private String button = "uploadify-button";

    //mysql语句所查询到的内容
    protected StoreInformationBean bean ;

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
        for (int i = 1;i<=row;i++){
            System.out.println("第的内容:" + i);
            //用例执行表的内容
            EnumProgramBean epb = StartData.readLoad(load, 1, i);
            System.out.println(epb.toString());

            switch (epb.getThree()) {

                case "验证":
                    new InformationJudgment(epb).judgmentInformation();
                    break;

                case "修改":
                    new InformationModify(epb);
                    break;

                default:
                    SystemOut.getStringOut("没有这个内容数据" + epb.getThree());
                    break;
            }
        }
/*
        //判断店名是否有内容、有就说明设置过内容然后就调用判断的方法
        // 没有内容就对其进行设置
        WebElement storeName = driver.findElement(By.id("name"));
        String value = storeName.getAttribute("value");
        if (value.length() > 3) {
            System.out.println("该店铺已设置过数据" + value);
           // InformationJudgment iju = new InformationJudgment();
        } else {
            System.out.println("该店铺没有设置过数据" + value);
            InformationModify isu = new InformationModify();
        }
*/
    }

    //返回查询语句的内容。
    private void mysqlInquire(){
        sql = "select * from ph_exclusive.ph_dianpu;";
        bean = new StoreInformationBean();
        //数据库连接及查询
        JSONObject jsonObject = new MysqlInquire().dataMysqlColumnAllRow(sql);
        Gson gson = new Gson();
        bean = gson.fromJson(jsonObject.toString(), (Type) bean.getClass());
    }

    //根据cssSelector来进行元素输入
    public void storeInput(String cssSelector, String content) {
        ElementInput eInput = new ElementInput();
        eInput.accordingToCssSelector(cssSelector, content);
    }

    public void licensePhoto(String load, String id, String address) {

        List<WebElement> el1 = driver.findElements(By.cssSelector(load));
        int i1 = 2 - el1.size();
        System.out.println("上传" + i1);
        for (int i = 0; i < i1; i++) {
            PictureImage.getLogo(driver, id, address);
        }
    }

    /**
     * 父类定义断言给子类用
     * @param message
     * @param expected
     * @param actual
     */
    protected void assertEqualsMessage(String message, Object expected, Object actual ){
        assertEquals(message,expected,actual);
    }


    /**
     * 父类定义输入对象，让子类进行调用
     * @param parameter
     * @param message
     */
    protected void elementInput(WebElement parameter,String message){
        ElementInput eleInput = new ElementInput();

        eleInput.operation(parameter,message);
    }

}