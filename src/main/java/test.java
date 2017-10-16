import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.conversion.MutualJsonBean;
import common.tool.informationException.ErrorException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.example.bean.GoodsBean;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by ${XiaoHuiHui} on 2017/7/17 on 14:36.
 * XiaoHiiHui [704866169@qq.com]
 */
public class test<T> {

    public Connection connectionSql() {
        String name = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://3231/---?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (ClassNotFoundException e) {
            System.out.println("找不到类");
            e.printStackTrace();
        } catch (SQLException e) {
            // System.out.println("语句");
            e.printStackTrace();
        }

        return conn;
    }


    public void execute() {
        System.out.println("运行");
        try {
            String sql = "SELECT goods_id,seller_id,shop_id,`status`,price,sale_total,sort from " +
                    "lnsm_shop_goods WHERE shop_id = 10175 and `status` =10;";

            //创立链接
            Connection conn = connectionSql();
            //执行sql语句
            PreparedStatement stmt = conn.prepareStatement(sql);
            //得到结果集
            ResultSet resultSet = stmt.executeQuery();
            //bean对象
            GoodsBean goodsBean = new GoodsBean();
            //结果集和bean对象传入，把没条数据存入list中
            List<GoodsBean> list = new MutualJsonBean().resultSetToJson(resultSet, (T) goodsBean);
            for (int i = 0; i < list.size(); i++) {
                SystemOut.getStringOut(list.get(i).toString());
            }
        } catch (Exception e) {
        }
    }

    public void extest() throws ErrorException, IOException {
        String k1 = "seller/shop/logo/2017/10/59ddc678855f1.jpg";
        String k2 = "http://img.t-lianni.com/seller/shop/logo/2017/10/59ddc678855f1.jpg";



        SystemOut.getStringOut(k2.indexOf(k1) + ":");
        SystemOut.getStringOut(k2.lastIndexOf(k1) + ":");
    }

    @Test
    public void readExcle() {

        String ss = "qwe";
        int g = ss.indexOf(",");
        SystemOut.getStringOut(g+ "");

        WebDriver driver = FoxDriver.getChromeDriver();
        //        是浏览器的大小
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //        设置测试的网页
        driver.get("C:\\Users\\Administrator\\Desktop\\连你·生活商家管理中心-信息 _ lianni.com.html");

        String phoneLoad = "#contact";
        WebElement element = driver.findElement(By.cssSelector(phoneLoad));
       /* ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", element);*/
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 900)");
        SystemOut.getStringOut(element.getText());

        element.click();
        element.clear();
        element.sendKeys("111");
    }

}
