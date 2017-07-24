package wap.business.example;

import common.FoxDriver;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * 实现退出登录功能
 * Created by Administrator on 2016/9/22.
 */
public class Exitsysos {

    public Exitsysos() {
        try {
               Preservation preserva = new Preservation();
               preserva.buttonCssSelector("span.user-info");
               preserva.buttonCssSelector("a.lastone");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
