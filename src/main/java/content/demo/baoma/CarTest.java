package content.demo.baoma;

import common.tool.SystemOut;
import org.junit.Test;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 17:39.
 * XiaoHiiHui [704866169@qq.com]
 */

public class CarTest {
    @Test
    public void driver() {

        CarFactory ic = AgentFactor.getIns().getCarFactor("BMW");
        Car mini = ic.build("BMW");
        if (mini != null) {
            mini.go();
        } else {
            SystemOut.getStringOut("null指针异常");
        }
    }
}
