package content.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by 70486 on 2017/6/28 on 21:59.
 */

public class testDemo2 {
    @BeforeClass
    public void setup() {
        System.out.println("测试2BeforeClass");
    }

    @Test
    @Parameters("name")
    public void test(String name) {
        System.out.println("测试2的名字是" + name);
    }

    @AfterClass
    public void teardown() {
        System.out.println("测试2AfterClass");
    }
}

