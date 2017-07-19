package wap.business.example.ligrco;

import common.parameter.WapUrl;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wap.business.example.ligrco.Exhibition.Article.GoodsList;
import wap.business.example.ligrco.Exhibition.Evaluation.CommentsReceive;
import wap.business.example.ligrco.Exhibition.Grouping.CommodityGrouping;

import java.util.concurrent.TimeUnit;

import static common.FoxDriver.*;

/**
 * 實現商品管理的選擇以及子目錄的切換
 * Created by Administrator on 2016/10/14.
 */
public class ExhibitionShow {
    private WebDriver driver = getFoxDriver();

    private String url[] = new WapUrl().getShopManagementFamily();

    private String storeName[];

    private String menu;


    Preservation preservation = new Preservation();

    public void getStore() throws Exception {
        preservation.buttonLinkText(menu);
        getVerification();
    }

    public ExhibitionShow(String ont, String two) throws Exception {

        preservation.buttonLinkText(ont);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        preservation.buttonLinkText(two);

    }

    public ExhibitionShow() throws Exception {
    }

    private void getVerification() throws Exception {
        for (int i = 2; i < 3; i++) {
            switch (i) {
                case 0://商品列表
                    driver.findElement(By.linkText(storeName[i])).click();
                    new GoodsList(url[i]);
                    break;

                case 1://商品分组
                    driver.findElement(By.linkText(storeName[i])).click();
                    new CommodityGrouping(url[i]);
                    break;

                case 2://收到评论
                    driver.findElement(By.linkText(storeName[i])).click();
                    new CommentsReceive(url[i]);
                    break;
            }
        }
    }


}
