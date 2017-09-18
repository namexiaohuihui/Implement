package wap.business.example.homeAddress;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import common.FoxDriver;
import common.parameter.Parameter;
import common.tool.caninput.ElementObtain;
import common.tool.conversion.RegularExpression;
import common.tool.mysqls.MysqlInquire;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wap.business.example.bean.UserBean;

import java.io.StringReader;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

/**
 * 家门口址的驗證
 * Created by Administrator on 2016/10/31.
 */
public class ManagementHomepage {

    String sql ;
    public ManagementHomepage() throws InterruptedException, SQLException {
        WebDriver driver = FoxDriver.getWebDrivaer();
        sleep(1000);
        //获取界面上一级菜单的名字
        WebElement tr = driver.findElement(By.cssSelector("li.hsub.active> a > span"));
        //获取程序上一级菜单名字
        String one = new Parameter().getOneLevel()[0];

        assertEquals("家门口验证失败", "---", tr.getText());

        //数据库类创建
        MysqlInquire inquire = new MysqlInquire();

        //数据库连接及查询
        JSONArray jsonArray = inquire.dataMysqlColumnAllRow(sql);

        //gson数据定义
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(jsonArray.toString()));
        reader.setLenient(true);

        //将json数据转换成bean
       List<HomepageParameter> pageHome = gson.fromJson(jsonArray.toString(),
               new TypeToken<List<HomepageParameter>>(){}.getType());

       //bean对象提取
        HomepageParameter page = pageHome.get(0);

        //获取页面数据
        ElementObtain obtain = new ElementObtain();
        String grade = obtain.accordingToCss("div[class=evaluate]>p[1]", "");
        String deliveryMmin = obtain.accordingToCss("div[class=evaluate]>p[2]", "");

        //数据进行正则操作
        grade = RegularExpression.regularExpression(grade,"\\d{0,9}\\.\\d{0,9}");
        deliveryMmin = RegularExpression.regularExpression(deliveryMmin,"\\d{0,9}\\.\\d{0,9}");

        if (page.getGrade() == grade){
            System.out.println("首页的评分正确");
        }else {
            System.out.println("首页的评分失败");
        }

        if (page.getDeliveryMin() == deliveryMmin){
            System.out.println("首页的时间对上了");
        }else {
            System.out.println("首页的时间没有对上");
        }
    }


    public class HomepageParameter{
        String grade ;
        String delivery_min ;

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getDeliveryMin() {
            return delivery_min;
        }

        public void setDeliveryMin(String delivery_min) {
            this.delivery_min = delivery_min;
        }

        @Override
        public String toString() {
            return "HomepageParameter{" +
                    "grade='" + grade + '\'' +
                    ", delivery_min='" + delivery_min + '\'' +
                    '}';
        }
    }
}
