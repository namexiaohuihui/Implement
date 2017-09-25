package wap.business.example.innose;

import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementExistence;
import common.tool.caninput.Preservation;
import common.tool.conversion.CharacterString;
import org.openqa.selenium.WebDriver;
import wap.business.StartData;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.innose.information.ShopNotices;
import wap.business.example.innose.information.StoreInformation;
import wap.business.example.innose.information.StoreSettings;

import static java.lang.Thread.sleep;

/**
 * 展示管理目录切换
 * Created by Administrator on 2016/9/22.
 */
public class Information {

    //子菜单
    public String[] listBar;
    //浏览器对象
    public WebDriver driver = FoxDriver.getWebDrivaer();
    //按钮点击对象
    public Preservation preservation;
    public EnumProgramBean epb;
    //路径
    private String load;

    public Information(String load) {
        this.load = load;
        epb = StartData.readLoad(load, 2, 1);
        String describe = epb.getFive();
        ElementExistence ex = new ElementExistence();
        boolean b = ex.accordingToLinkText(describe);
        if (b) {
            preservation = new Preservation();
            //      此处实现的是触发link1(触发一级目录)
            preservation.buttonLinkText(describe);
            captureMenu();
            management();
        } else {
            SystemOut.getStringOut(describe + "该菜单没有找到。。");
        }
    }

    public Information() {
    }

    private void captureMenu() {
        String six = epb.getSix();
        listBar = new CharacterString().stringsToString(six, "");
    }

    //    负责下的子目录切换。
    private void management() {
        String sLoad = epb.getOne() + epb.getTwo() + epb.getThree();
        for (int i = 0; i <listBar.length; i++) {
            try {
                switch (i) {
                    case 0:
                        preservation.buttonLinkText(listBar[i]);
                        new StoreInformation(sLoad).informationStore();
                        break;
                    case 1:
                        preservation.buttonLinkText(listBar[i]);
                        new ShopNotices(sLoad).getAnnouncement();
                        break;
                    case 2:
                        preservation.buttonLinkText(listBar[i]);
                        new StoreSettings(sLoad).getSetting();
                        break;
                    default:
                        System.out.println("要点击的不存在");
                }
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
