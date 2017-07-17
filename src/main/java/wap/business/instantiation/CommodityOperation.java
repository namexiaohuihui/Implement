package wap.business.instantiation;

/**
 * 读取商品操作表中的数据进行保存
 * Created by ${XiaoHuiHui} on 2017/4/7.
 * XiaoHiiHui [704866169@qq.com]
 */
public class CommodityOperation {

    private String state;//状态

    private String operation;//操作

    private String implement;//执行

    private String attribute;//执行之后的属性值



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getImplement() {
        return implement;
    }

    public void setImplement(String implement) {
        this.implement = implement;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }


    @Override
    public String toString() {
        return "商品操作类{" +
                "state='" + state + '\'' +
                ", operation='" + operation + '\'' +
                ", implement='" + implement + '\'' +
                ", attribute='" + attribute + '\'' +
                '}';
    }
}
