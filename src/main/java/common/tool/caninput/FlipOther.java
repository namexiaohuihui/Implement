package common.tool.caninput;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/17.
 */
public class FlipOther {

    /**
     * 利用正则表达式，将字符串里面的其他字符以及数字切割分开。。
     * 获取指定位置的内容，读取倒数第二个集合的数据，并判断其长度
     */
    public int getOther(WebDriver driver, String load) {
        String data = driver.findElement(By.xpath(load)).getText();
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+|\\d+");
        List list = new ArrayList();
        Matcher m = p.matcher(data);
        while (m.find()) {
            list.add(m.group());
        }

        int i = Integer.parseInt(list.get(list.size() - 2).toString());
        // 取余为零

        if (i % 10 == 0) {
            System.out.print("读取的长度为" + i + "页");
            i = i / 10;
            System.out.println("翻頁應當有" + i + "页");
            return i;
        } else {
            System.out.print("读取的长度为" + i + "页");
            i = (i / 10 + 1);
            System.out.println("翻頁應當有" + i + "页");
            return i;
        }

    }

}
