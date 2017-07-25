package content.demo;

import org.apache.commons.io.input.TeeInputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by 70486 on 2017/6/28 on 21:59.
 */

public class testDemo3 {
    private final String INPUT = " Hello i am a string i was written";
    @Test
    public void setStart() throws IOException {

        TeeInputStream tee = null;
        ByteArrayInputStream bar = new ByteArrayInputStream(INPUT.getBytes("US-ASCII"));
        ByteArrayOutputStream bor = new ByteArrayOutputStream();

        tee = new TeeInputStream(bar, bor, true);
        tee.read(new byte[INPUT.length()]);

        System.out.println("内容:" + bor.toString());
        tee.close();
    }
}

