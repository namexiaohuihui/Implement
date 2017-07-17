package wap.business.instantiation;

/**
 * 商品分组excel表中的数据进行储存、。
 * Created by ${XiaoHuiHui} on 2017/5/23 on 12:04.
 * XiaoHiiHui [704866169@qq.com]
 */
public class PacketSorting {

    private String operation;//操作

    private String name; //名称

    private String sort;//排序

    private String implement;//执行

    public PacketSorting(String operation, String name, String sort, String implement) {
        this.operation = operation;
        this.name = name;
        this.sort = sort;
        this.implement = implement;
    }

    public PacketSorting() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getImplement() {
        return implement;
    }

    public void setImplement(String implement) {
        this.implement = implement;
    }

    @Override
    public String toString() {
        return "分组操作表格{" +
                "operation='" + operation + '\'' +
                ", name='" + name + '\'' +
                ", sort='" + sort + '\'' +
                ", implement='" + implement + '\'' +
                '}';
    }
}
