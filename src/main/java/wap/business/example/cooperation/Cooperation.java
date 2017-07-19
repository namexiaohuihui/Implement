package wap.business.example.cooperation;


import common.FoxDriver;
import common.parameter.WapUrl;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 连你供货的选择
 * Created by Administrator on 2017/1/18.
 */
public class Cooperation {

    private WebDriver driver = FoxDriver.getFoxDriver();

    private String supplySystem[] = new WapUrl().getSupplySystemFamily();

    private String directory[] ;

    public Cooperation() throws InterruptedException {
        driver.findElement(By.linkText("连你供货")).click();
        ;
        getSubDirectory();
    }

    public void getSubDirectory() throws InterruptedException {
        for (int i = 0; i < supplySystem.length; i++) {
            switch (i) {
                case 0:
                    new Preservation().buttonLinkText(directory[i]);
                    break;
                case 1:
                    new Preservation().buttonLinkText(directory[i]);
                    break;
                default:
                    System.out.println("供货商品子目录判断出错....");
            }
        }
    }
}
