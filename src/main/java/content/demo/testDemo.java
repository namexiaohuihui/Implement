package content.demo;

/**
 * Created by 70486 on 2017/6/28 on 21:59.
 */
import org.testng.annotations.*;

public class testDemo {
    @BeforeClass
    public void setup()
    {
        System.out.println("begin test");
    }
    @Test
    @Parameters({"name"})
    public void test44(String test1)
    {
        System.out.println("测试1的名字是"+test1);
    }
    @AfterClass
    public void teardown()
    {
        System.out.println("end test");
    }

    @Test(dependsOnMethods = {"test44"})
    public void dependTest()
    {
        System.out.println("我是依赖测试");
    }

    @DataProvider(name = "gong")
    public static Object[][] dataBase(){
        return  new Object[][]{
                {"小明","公"},
                {"消防","雌"}
        };
    }

    @Test(dataProvider = "gong")
    public void test2(String name,String sex) {
        System.out.println(name  + "性别" + sex);
    }

}

