package wap.business.example.innose;

import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import org.openqa.selenium.WebDriver;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;

import static java.lang.Thread.sleep;

/**
 * 展示管理目录切换
 * Created by Administrator on 2016/9/22.
 */
public class Information {

    //子菜单
    public String[] listBar;

    //按钮点击对象
    public Preservation preservation;

    protected WebDriver driver;
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
        for (int i = 1; i <= rowNum; i++) {
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

        //浏览器对象
        driver = FoxDriver.getWebDrivaer();

        //按钮点击对象
        preservation = new Preservation();

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
                //preservation.buttonLinkText(listBar[i]);
                //new StoreInformation(bean).informationStore();
            }

            //判断子菜单然后进行点击并进行数据操作
            else if (bean.getFour().equals(listBar[1]) && bean.getFour() != null && !listBar[1].equals("")) {
                System.out.println(listBar[1] + "点击了1" + bean.getFour());
                preservation.buttonLinkText(listBar[1]);
                //new ShopNotices(bean).getAnnouncement();
            }

            //判断子菜单然后进行点击并进行数据操作
            else if (bean.getFour().equals(listBar[2]) && bean.getFour() != null && !listBar[2].equals("")) {
                System.out.println(listBar[2] + "点击了2" + bean.getFour());
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
}
