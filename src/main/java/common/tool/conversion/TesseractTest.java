package common.tool.conversion;

import common.FoxDriver;
import common.parameter.WapUrl;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.TimeUnit;

import static common.tool.excelfile.ReadFile.readTextFile;

/**
 * 通过指定元素进行截图，并保存下来，然后在进行转换
 * Created by ${XiaoHuiHui} on 2017/3/6.
 * XiaoHiiHui [704866169@qq.com]
 */
public class TesseractTest {

    public static void main(String[] args) throws IOException,
            InterruptedException {

        WebDriver driver = FoxDriver.getFoxDriver();
        WapUrl wapUrl = new WapUrl();
        driver.get(wapUrl.getWebHttp());
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.id("loginBtn"));

        // 获取元素并转为图片
        screenShotForElement(driver, element, "E:\\test.png");

        driver.quit();

        // 使用Tesseract得到字符串
        Runtime rt = Runtime.getRuntime();
        rt.exec("cmd.exe /C  tesseract.exe E:\\test.png  E:\\test -1 ");

        Thread.sleep(1000);
        // 读取文本数据
        readTextFile("E:\\test.txt");
    }

    /**
     * 屏幕截图元素的方法
     *
     * @param driver
     * @param element
     * @param path
     * @throws InterruptedException
     */
    public static void screenShotForElement(WebDriver driver,
                                            WebElement element, String path) throws InterruptedException {
        try {
            // 截图----全屏
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage img = ImageIO.read(scrFile);

            //得到元素的坐标
            Point p = element.getLocation();

            //获取元素的宽和高
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();

            //创建一个矩形，使用该元素的大小
            Rectangle rect = new Rectangle(width, height);

            //存为png格式的图片
            BufferedImage dest = img.getSubimage(p.getX(), p.getY(),
                    rect.width, rect.height);
            ImageIO.write(dest, "png", scrFile);
            Thread.sleep(1000);

            // 生成图片
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
