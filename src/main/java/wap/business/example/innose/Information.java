package wap.business.example.innose;

import common.FoxDriver;
import common.parameter.WapUrl;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import wap.business.example.innose.information.ShopNotices;
import wap.business.example.innose.information.StoreInformation;
import wap.business.example.innose.information.StoreSettings;

import java.sql.SQLException;

import static java.lang.Thread.sleep;

/**
 * 展示管理目录切换
 * Created by Administrator on 2016/9/22.
 */
public class Information {
    //主菜单:
    public String mainHome = "家庭管理";
    //子菜单
    public String listBar[] ;
    //子菜单链接
    public String url[] = new WapUrl().getShopManagementFamily();
    //浏览器对象
    public WebDriver driver = FoxDriver.getFoxDriver();
    //按钮点击对象
    public Preservation preservation = new Preservation();

    public void store() throws InterruptedException {

//      此处实现的是触发link1(触发一级目录)

        try {
            management();
            preservation.buttonLinkText(mainHome);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    //    负责下的子目录切换。
    private void management() throws InterruptedException, SQLException {
        for (int i = 0; i <listBar.length; i++) {
            switch (i) {
                case 0:
                    preservation.buttonLinkText(listBar[i]);
                    new StoreInformation().informationStore();
                    break;
                case 1:
                    preservation.buttonLinkText(listBar[i]);
                    new ShopNotices().getAnnouncement(url[i]);
                    break;
                case 2:
                    preservation.buttonLinkText(listBar[i]);
                    new StoreSettings().getSetting(url[i]);
                    break;
                default:
                    System.out.println("要点击的不存在");
            }
            sleep(5000);
        }
    }

}
