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
    String listBar[] ;
    String url[] = new WapUrl().getShopManagementFamily();
    private WebDriver driver = FoxDriver.getFoxDriver();

    public void getStore() throws InterruptedException {

//      此处实现的是触发link1(触发一级目录)
        new Preservation().buttonLinkText(listBar[0]);

//        点击店铺管理下面的子目录实现店铺信息、店铺公告、店铺设置、字体设置的数据设置。
        try {
            getManagement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //    负责下的子目录切换。
    private void getManagement() throws InterruptedException, SQLException {
        for (int i = 2; i <listBar.length; i++) {
            switch (i) {
                case 0:
                    driver.findElement(By.linkText(listBar[i])).click();
                    new StoreInformation().getInformation(url[i]);
                    break;
                case 1:
                    driver.findElement(By.linkText(listBar[i])).click();
                    new ShopNotices().getAnnouncement(url[i]);
                    break;
                case 2:
                    driver.findElement(By.linkText(listBar[i])).click();
                    new StoreSettings().getSetting(url[i]);
                    break;
                default:
                    System.out.println("要点击的不存在");
            }
            sleep(5000);
        }
    }

}
