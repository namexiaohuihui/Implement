package common.tool.upload;

import common.tool.SystemOut;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * 该类负责图片的处理：
 * 1.上传图片通过第三方来进行
 * 2.判断店铺执照和店铺实拍图片的个数，之后在进行图片的上传
 * Created by Administrator on 2016/12/5.
 */
public class PictureImage {

    /**
     * @param driver WwbDriver对象
     * @param id     需要上传图片的按钮名称
     * @param route  需要上传图片在本项目中的名称
     */
    public static void setLogoId(WebDriver driver, String id, String route) {
        try {
            sleep(1000);
            WebElement swfUpload_0 = driver.findElement(By.id(id));
            swfUpload_0.click();
            sleep(3000);
            try {
                Runtime.getRuntime().exec(route);
                //   Runtime.getRuntime().exec("E:/picture/" + route);
                SystemOut.getStringOut("图片上传的路径", route);
                sleep(6000);
            } catch (IOException e) {
                e.printStackTrace();
                SystemOut.getStringOut("Error to run the exe 图片上传错误" + e.toString());
                sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 目前针对css路径来对图片进行上传功能
     * @param driver WwbDriver对象
     * @param cssSelector 按钮css路径
     * @param route 图片封装exe的路径
     * @param massage 打印执行失败的用例编号
     */
    public static void setLogoCssSelector(WebDriver driver, String cssSelector,
                                          String route, String massage) throws InterruptedException, IOException {

            route = "E:\\drivers\\AutoPicture\\" + route;
            sleep(1000);
        new Preservation().buttonCssSelector(cssSelector);
            sleep(3000);
                Runtime.getRuntime().exec(route);
                //   Runtime.getRuntime().exec("E:/picture/" + route);
                SystemOut.getStringOut("图片上传的路径", route);
                sleep(6000);

    }

}
