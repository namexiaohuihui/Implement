package wap.business.example.ligrco.Exhibition.Grouping;

import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by 70486 on 2017/6/13 on 23:22.
 */
public class Operating {
    //判断是否需要执行操作

    public final String tr = "Y";
    public final String fa = "N";

    /**
     * 根据位置点击修改、保存以及删除按钮
     *
     * @param posi
     * @throws InterruptedException
     */
    public void modifyEdit(WebDriver driver, int posis) throws InterruptedException {

        //positions表示第几行数据，posi表示第几个按钮
        String load = ".//tbody[@id='grouping']/" +
                "tr[2]/td[5]/span[" + posis + "]";
        new Preservation().buttonXpath(load);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * 实现名字或者排序的输入
     *
     * @param ont  位置。根据这个内置来找到元素对象
     * @param edit 内容。将这个内容输入到元素里面
     */
    public void editNameSorting(WebDriver driver,String lo, String edit) {
        WebElement element = driver.findElement(By.xpath(lo));
        //全选内容然后删除
        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.DELETE);
        //输入新的参数
        element.sendKeys(edit);
    }
}
