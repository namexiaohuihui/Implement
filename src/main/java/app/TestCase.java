package app;

import org.junit.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Author: DingDong
 * @Date: 2019/3/8 14:55
 * @USER: Administrator
 * @PACKAGE_NAME: app
 * @PROJECT_NAME: Implement
 * @Description:
 **/
public class TestCase {
    @BeforeClass
    public static void setup() {
        System.out.print("setup\n");
    }

    @Before
    public void clear() {
        System.out.print("clear\n");
    }

    @After
    public void tearqweqwe() {
        System.out.print("tear\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.print("tearDown\n");
    }

    @Test
    public void addition() {
        System.out.print("Addition\n");
    }
    @Test
    public void combination() {
        System.out.print("combination\n");
    }
}
