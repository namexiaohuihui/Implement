package wap.business.example.innose;

import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import LnsmOperation.StoreOperation.SelfSetting;
import LnsmOperation.StoreOperation.ShopNotices;
import LnsmOperation.StoreOperation.StoreInformation;
import LnsmOperation.StoreOperation.StoreSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLException;

import static java.lang.Thread.sleep;

/**
 * 实现商家店铺管理目录切换
 * Created by Administrator on 2016/9/22.
 */
public class Information {
    String listBar[] = {"店铺信息", "店铺公告", "店铺设置", "自提设置"};
    String url[] = new LnsmUrl().getShopManagementUrl();
    private WebDriver driver = FoxDriver.getFoxDriver();

    public void getStore() throws InterruptedException {

//      此处实现的是触发link1(触发一级目录)
        driver.findElement(By.linkText("店铺管理")).click();

//        点击店铺管理下面的子目录实现店铺信息、店铺公告、店铺设置、字体设置的数据设置。
        try {
            getManagement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //    负责店铺管理下的子目录切换。
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
                case 3:
                    driver.findElement(By.linkText(listBar[i])).click();
                    new SelfSetting().getSince(url[i]);
                    break;
                default:
                    System.out.println("要点击的不存在");
            }
            sleep(5000);
        }
    }


    //    判断元素是否存在之後进行点击，在將判斷是否存在的數據進行返回
    private Boolean getExistence(final String string) {
        Boolean information = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                d.findElement(By.linkText(string)).click();
                //此处是判断是否存在id为dropdown1的元素，存在返回true
                return d.findElement(By.linkText(string)).isDisplayed();
            }
        });
        return information;
    }


}
