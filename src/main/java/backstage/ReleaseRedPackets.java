package backstage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReleaseRedPackets {
    @Before
    public void openBrowser() {
        System.out.print("1");
    }

    @After
    public void afterClass() {
        System.out.println("程序运行完毕");
    }

    @Test
    public void redEnvelope() throws Exception {
        System.out.print("没有");
    }
}
