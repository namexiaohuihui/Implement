package wap.business.example.ligrco;

import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import LnsmOperation.CommodityOperation.Evaluation.CommentsReceive;
import LnsmOperation.CommodityOperation.Grouping.CommodityGrouping;
import LnsmOperation.CommodityOperation.Article.GoodsList;
import LnsmOperation.CommodityOperation.Release.ReleaseCommodity;
import LnsmUitl.LnsmPreservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * 實現商品管理的選擇以及子目錄的切換
 * Created by Administrator on 2016/10/14.
 */
public class Exhibition {
    private WebDriver driver = FoxDriver.getFoxDriver();

    private String url[] = new LnsmUrl().getCommodityManageurl();

    private String storeName[] = {"发布商品", "商品列表", "商品分组", "收到评论"};

    private String menu = "商品管理";

    public void getStore() throws Exception {
        LnsmPreservation.getButtonLinkText(menu);
        getVerification();
    }

    public Exhibition(String ont, String two) throws Exception {

        LnsmPreservation.getButtonLinkText(ont);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        LnsmPreservation.getButtonLinkText(two);

    }

    public Exhibition() throws Exception {
    }

    private void getVerification() throws Exception {
        for (int i = 2; i < 3; i++) {
            switch (i) {
                case 0://发布商品
                    try {
                        driver.findElement(By.linkText(storeName[i])).click();
                        new ReleaseCommodity(url[i]);
                    } catch (Exception e) {
                        break;
                    }
                    break;

                case 1://商品列表
                    driver.findElement(By.linkText(storeName[i])).click();
                    new GoodsList(url[i]);
                    break;

                case 2://商品分组
                    driver.findElement(By.linkText(storeName[i])).click();
                    new CommodityGrouping(url[i]);
                    break;

                case 3://收到评论
                    driver.findElement(By.linkText(storeName[i])).click();
                    new CommentsReceive(url[i]);
                    break;
            }
        }
    }


}
