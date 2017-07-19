package wap.business.instantiation;

import common.tool.caninput.ReadList;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

/**
 * 这是商品列表类，
 * Created by Animation on 2017/4/7.
 */
public class CommoditiesList {

    private String number;//商品ID
    private String name;//商品名称
    private String price;//商品价格
    private String salesVolume;//商品销量
    private String stock;//商品库存
    private String[] grouping;//商品分组
    private String sort;//商品排序
    private String time;//商品创建的时间
    private String state;//商品的状态
    private String[] operation;//商品的操作
    private int row;//根据row的值来获取相应行的商品属性

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(String salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String[] getGrouping() {
        return grouping;
    }

    public void setGrouping(String[] grouping) {
        this.grouping = grouping;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getOperation() {
        return operation;
    }

    public void setOperation(String[] operation) {
        this.operation = operation;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "商品列表列{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", salesVolume='" + salesVolume + '\'' +
                ", stock='" + stock + '\'' +
                ", grouping=" + Arrays.toString(grouping) +
                ", sort='" + sort + '\'' +
                ", time='" + time + '\'' +
                ", state='" + state + '\'' +
                ", operation=" + Arrays.toString(operation) +
                ", row=" + row +
                '}';
    }

    public CommoditiesList setComodityList(ReadList readList, int row, String tablePath) {
        By by = By.xpath(tablePath);
        //根据标签来获取列表的数据
        List columnContent = readList.columnContent(by, "tr", "td", row);
        CommoditiesList comm = new CommoditiesList();
        //讲列表中读取商品的属性转成类对象进行存储
        comm.setNumber((String) columnContent.get(0));
        comm.setName((String) columnContent.get(1));
        comm.setPrice((String) columnContent.get(2));
        comm.setSalesVolume((String) columnContent.get(3));
        comm.setStock((String) columnContent.get(4));
        comm.setGrouping(((String) columnContent.get(5)).split("、"));
        comm.setSort((String) columnContent.get(6));
        comm.setTime((String) columnContent.get(7));
        comm.setState((String) columnContent.get(8));
        comm.setOperation(((String) columnContent.get(9)).split(" "));
        comm.setRow(row);
        return comm;
    }


}
