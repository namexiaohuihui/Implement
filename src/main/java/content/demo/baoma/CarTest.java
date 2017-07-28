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
        String name = "BMW";
        //AgentFactor作为代理商，需要通过getins来创建对象
        //然后根据客户需要的品牌name来进购车子
        //进购车子之后返回该车子的型号
        CarFactory ic = AgentFactor.getIns().getCarFactor(name);
        //通过车子的型号然后去找工厂进行生产
        Car mini = ic.build(name);
        //生产完成之后在判断是否符合要求，符合就执行车子的动作
        if (mini != null) {
            mini.go();
        } else {
            SystemOut.getStringOut("null指针异常");
        }
    }
}
