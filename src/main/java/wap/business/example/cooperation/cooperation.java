package wap.business.example.cooperation;

import LnsmElement.LnsmUrl;
import LnsmInitialize.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 连你供货的选择
 * Created by Administrator on 2017/1/18.
 */
public class cooperation {

    private WebDriver driver = FoxDriver.getFoxDriver();

    private String supplySystem[] = new LnsmUrl().getSupplySystem();

    private String directory[] = {"供货商品", "进货订单"};

    public cooperation() {
        driver.findElement(By.linkText("连你供货")).click();
        ;
        getSubDirectory();
    }

    public void getSubDirectory() {
        for (int i = 0; i < supplySystem.length; i++) {
            switch (i) {
                case 0:
                    driver.findElement(By.linkText(directory[i])).click();
                    break;
                case 1:
                    driver.findElement(By.linkText(directory[i])).click();
                    break;
                default:
                    System.out.println("供货商品子目录判断出错....");
            }
        }
    }
}
