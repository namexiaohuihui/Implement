package content.demo.gongchang;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 17:17.
 * XiaoHiiHui [704866169@qq.com]
 */
public class demo {
    @Test
    public void setStart() throws IOException {
        Animal animal = null;
        animal = Factory.getInstance("Cat");
        if (animal != null) {
            animal.say();
        }
    }
}
