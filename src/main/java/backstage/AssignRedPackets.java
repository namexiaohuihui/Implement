package backstage;

//import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import toolskit.tools.excelfile.ReadExcel;
import toolskit.FoxDriver;
import toolskit.parameters.Parameter;
import toolskit.parameters.WapUrl;
import toolskit.tools.SystemOut;
import toolskit.tools.caninput.ElementInput;

/**
 * Created by ${XiaoHuiHui} on 2017/5/26 on 16:28.
 * XiaoHiiHui [704866169@qq.com]
 * 红包发放
 */
public class AssignRedPackets {
    private WebDriver driver;
    /**
     * 浏览器对象
     */
    private WapUrl wapUrl;

    // 发放人的ID
    private String USER_ID = "243617";

    // 需要发放红包处于第几行
    private String RedBao = "1";

    // 需要发放红包的数量
    int run_number = 1;

    // 默认运行方式的定义
    String runWay = "excel";

    @Before
    public void openBrowser() {
        wapUrl = new WapUrl();
        driver = FoxDriver.openBrowser();
    }

    @After
    public void afterClass() {
        System.out.println("程序运行完毕");
        driver.quit();
    }

    @Test
    public void redEnvelope() throws Exception {
        //调用方法，实行元素的输入
        ElementInput elementInput = new ElementInput();
        //账号密码输入
        elementInput.accordingToCssSelector("input[name=username][class=form-control]",
                Parameter.accountTop);
        elementInput.accordingToCssSelector("input[name=password][class=form-control]",
                Parameter.passWordTop);
        // 登陆按钮
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.btn-flat")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.linkText("推广管理")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.linkText("红包管理")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(By.linkText("红包设置")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        RunMode(elementInput);
    }

    /**
     * 根据运行方式来判断是从excel中读取数据进行运行还是固定发放给同一个用户
     * excel的格式
     * 用户ID    发放原因     详细说明
     * xxxx      xxxx         xxxx
     * xxxx      xxxx         xxxx
     * xxxx      xxxx         xxxx
     *
     * @param elementInput
     */
    public void RunMode(ElementInput elementInput) {
        try {
            switch (runWay) {
                case "define":
                    SystemOut.getStringOut("你好,走的是define");
                    DingDongRenBao(run_number, elementInput);
                    break;
                case "excel":
                    SystemOut.getStringOut("你好,走的是excel");
                    ExcelIssueRed(elementInput);
                    break;
                default:
                    SystemOut.getStringOut("没找到对应的运行方式,程序结束.");
                    break;

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里采用fou的形式进行发放：
     *
     * @param elementInput
     */
    public void ExcelIssueRed(ElementInput elementInput) {
        try {
            ReadExcel obj = new ReadExcel();
            File file = new File("C:\\Users\\DingDonf\\Desktop\\红包发放.xlsx");
            InputStream inputStream = new FileInputStream(file);
            List<Map<String, String>> excelList = obj.readExcel(inputStream, "发放");
            System.out.println("从excel读取数据并开始使用:");
            for (Map<String, String> excelMap : excelList) {
                SystemOut.getStringOut("需要发放的用户:" + excelMap);
                driver.findElement(By.xpath("//*[@id='datatatle']/tbody/tr[" + RedBao + "]/td[7]/button[2]")).click();
                sleep(1000);
                WebElement element = driver.findElement(By.cssSelector("input[class=form-control][id=userid][name=userids]"));
                driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                element.click();
                element.clear();
                element.sendKeys(excelMap.get("用户ID"));
                sleep(1000);

                WebElement bountCause = driver.findElement(By.id("cause"));
                Select bountSelect = new Select(bountCause);
                bountSelect.selectByVisibleText(excelMap.get("发放原因"));
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                //           详细信息输入
                elementInput.accordingToId("remark", excelMap.get("详细说明") + "，是的没错自己发的红包哟..");
                driver.findElement(By.id("remark"));
                //   driver.findElement(By.cssSelector("button[id=sendFormBut][type=button]")).click();
                driver.findElement(By.xpath(".//*[@id='sendFormBut']")).click();

                driver.navigate().refresh();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里采用递归的形式进行发放
     *
     * @param number
     * @param elementInput
     * @throws InterruptedException
     */
    public void DingDongRenBao(int number, ElementInput elementInput) throws InterruptedException {
        if (number == 0) {
            SystemOut.getStringOut("红包发送完毕");
        } else {
            SystemOut.getStringOut("红包剩余发送量:" + number);
            driver.findElement(By.xpath("//*[@id='datatatle']/tbody/tr[" + RedBao + "]/td[7]/button[2]")).click();
            sleep(1000);
            WebElement element = driver.findElement(By.cssSelector("input[class=form-control][id=userid][name=userids]"));
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            element.click();
            element.clear();
            element.sendKeys(USER_ID);
            sleep(1000);

            WebElement bountCause = driver.findElement(By.id("cause"));
            Select bountSelect = new Select(bountCause);
            bountSelect.selectByVisibleText("其他");
//            sleep(1000);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//           详细信息输入
            elementInput.accordingToId("remark", "自动输入的跑到流程然后输入详细的说明");
            driver.findElement(By.id("remark"));
            //   driver.findElement(By.cssSelector("button[id=sendFormBut][type=button]")).click();
            driver.findElement(By.xpath(".//*[@id='sendFormBut']")).click();
//            sleep(1000);

            driver.navigate().refresh();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            DingDongRenBao(number - 1, elementInput);
        }
    }
}
