package content.demo.baoma;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 17:44.
 * XiaoHiiHui [704866169@qq.com]
 */
//代理商
public class AgentFactor {
    private static AgentFactor ins;

    public static AgentFactor getIns() {
        if (ins == null) {
            ins = new AgentFactor();
        }
        return ins;
    }

    //取得制定品牌 的汽车
    public CarFactory getCarFactor(String Name) {
        if (Name.equals("BMW")) {
            return new BMWFactory();
        }
        return null;
    }
}
