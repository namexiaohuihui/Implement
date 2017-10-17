package wap.business.example.innose;

import com.google.gson.Gson;
import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.ElementInput;
import common.tool.caninput.ElementObtain;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import common.tool.mysqls.MysqlInquire;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.bean.StoreInformationBean;
import wap.business.example.innose.information.ShopNotices;
import wap.business.example.innose.information.StoreInformation;

import java.lang.reflect.Type;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 展示管理目录切换
 * Created by Administrator on 2016/9/22.
 */
public class Information {

    //子菜单
    public String[] listBar;

    WebDriver driver = FoxDriver.getWebDrivaer();

    //按钮点击对象
    public Preservation preservation;

    //路径
    private String LOAD_CASE;

    //读取用例的薄位置
    private int numSheet = 1;

    //该薄的用例数据
    private int rowNum;

    //将上一级所保存的数据通过对象传入
    public Information(EnumProgramBean epb) {
        //设置参数
        captureMenu(epb);
        for (int i = 2; i <= 2; i++) {
            //读取管理中的例子
            EnumProgramBean bean = StartData.readLoad(LOAD_CASE, numSheet, i);
            //目录切换
            manageMent(bean);
        }
    }

    public Information() {
    }

    //读取菜单，设置参数。。
    private void captureMenu(EnumProgramBean epb) {
        //设置用例路径
        this.LOAD_CASE = epb.getOne() + epb.getTwo() + epb.getThree();


        //按钮点击对象.
        getPreservation();

        //用例对象
        ReadExcel readExcel = new ReadExcel();

        //读取指定路径中用例的行数
        rowNum = readExcel.singleXlsx(LOAD_CASE, numSheet);

        //设置菜单的内容
        listBar = new CharacterString().stringsToString(epb.getFive(), "");

        stringConversion(epb.getFour());
    }

    //    负责下的子目录切换。
    private void manageMent(EnumProgramBean bean) {
        try {

            //判断子菜单然后进行点击并进行数据操作
            if (bean.getFour().equals(listBar[0]) && bean.getFour() != null && !listBar[0].equals("")) {
                System.out.println(listBar[0] + "点击了0" + bean.getFour());
                preservation.buttonLinkText(listBar[0]);
                //new StoreInformation(bean);
                new StoreInformation(bean).informationStore();
            }

            //判断子菜单然后进行点击并进行数据操作
            else if (bean.getFour().equals(listBar[1]) && bean.getFour() != null && !listBar[1].equals("")) {
                System.out.println(listBar[1] + "点击了1" + bean.getFour());
                preservation.buttonLinkText(listBar[1]);
                new ShopNotices().getAnnouncement(bean);
            }

            //判断子菜单然后进行点击并进行数据操作
            else if (bean.getFour().equals(listBar[2]) && bean.getFour() != null && !listBar[2].equals("")) {
                //System.out.println(listBar[2] + "点击了2" + bean.getFour());
                //preservation.buttonLinkText(listBar[i]);
                //new StoreSettings(bean).getSetting();
            }
            //提示不存在
            else {
                System.out.println(bean.getFour() + "要点击的不存在");
            }
            sleep(5000);
        } catch (Exception e) {
            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);
        }
    }

    //点击菜单。判断菜单是否存在
    private void stringConversion(String describe) {
        ElementExistence ex = new ElementExistence();
        boolean b = ex.accordingToLinkText(describe);
        if (b) {
            preservation = new Preservation();
            //      此处实现的是触发link1(触发一级目录)
            preservation.buttonLinkText(describe);
        } else {
            SystemOut.getStringOut(describe + "菜单不存在，用例错了吧。。");
        }
    }

    protected Preservation getPreservation(){
        preservation = new Preservation();
        return preservation;
    }


    /**
     * 页面移动。默认为0.
     *
     * @param number 向下移动的距离
     */
    protected void StoreMovesWindow(int number) {
        if (number==0){
            try {
                //移动到底部
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                //等待时间
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, "+number+")");
        }
    }

    /**
     * 页面移动，移动到指定的元素位置。该元素位于屏幕中间
     * @param cssLoad  元素路径
     */
    protected void StoreMovesWindow(String  cssLoad) {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = By.cssSelector(cssLoad).findElement(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);",
                element);
    }

    /**
     * 返回某个元素在另一个元素中的位置
     * @param message
     * @param parameter
     * @return
     */
    protected int getLastIndexOf(String message,String parameter){
        int index = message.lastIndexOf(parameter);
        return index;
    }


    /**
     * 父类定义断言给子类用
     *
     * @param message
     * @param expected
     * @param actual
     */
    protected void assertEqualsMessage(String message, Object expected, Object actual) {
        assertEquals(message, expected, actual);
        SystemOut.getStringOut(message,"断言成功。。。");
    }

    /**
     * 目前针对于需要通过Attribute来读取数据，并进行比较的对象
     *
     * @param argument  sql读取的数据
     * @param parameter css的路径
     * @param name      Attribute的读取数据
     * @param message   打印的信息
     */
    protected void judgmentParameterAttribute(String argument, String parameter, String value, String message) {
        StoreMovesWindow(parameter);
        WebElement eleName = driver.findElement(By.cssSelector(parameter));
        String eleText = eleName.getAttribute(value);
        int indexOf = getLastIndexOf(argument, eleText);
        if (indexOf!=-1){
            eleText = eleText.substring(indexOf, argument.length());
            assertEqualsMessage(message, argument, eleText);
        }else {
            SystemOut.getStringOut("图片验证错误。。。。。。。。。。坑呀");
        }
    }

    /**
     * 目前针对可直接获取text的内容，然后直接进行比较的对象
     *w 两个要对比的数据
     * @param argument  数据库中查询到该页面的内容
     * @param parameter css路径对象
     * @param message   打印的数据
     */
    protected void judgmentParameterText(String argument, String parameter, String message) {
        //移动页面
        StoreMovesWindow(parameter);
        //创建对象
        WebElement eleName = driver.findElement(By.cssSelector(parameter));
        //获取对象信息并去除空格
        String eleText = eleName.getText().trim();
        //数据判断
        assertEqualsMessage(message, argument, eleText);
    }

    /**
     * 父类定义输入对象，让子类进行调用
     * 根据cssSelector来进行元素输入
     *
     * @param parameter   数据库中读取到数据信息
     * @param cssSelector 路径
     * @param message     输入的信息
     * @param useCase     用例编号
     */
    public void elementInput(String parameter,String cssSelector, String message, String useCase) {

            //元素内容定义
            WebElement element = By.cssSelector(cssSelector).findElement(driver);
            String operation = new ElementObtain().operation(element, "value");
            assertEqualsMessage(message,parameter,operation);
            //元素输入内容
            ElementInput eleInput = new ElementInput();
            eleInput.operation(element, message);
            SystemOut.getStringOut(useCase + "这是什么??、、");

    }
}
