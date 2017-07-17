package content.demo;

import org.junit.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Created by 70486 on 2017/6/28 on 21:59.
 */

public class testDemo3 {
    @BeforeClass
    public void beforeClass() {
        System.out.println("this is before class");
    }

    @Test
    public void TestNgLearn() {
        System.out.println("this is TestNG test case3");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("this is after class");
    }
}

