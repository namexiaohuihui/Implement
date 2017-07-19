package wap.business.instantiation;

import common.tool.caninput.ReadList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

/**
 * 读取商品分组列表的数据，进行保存
 * Created by 70486 on 2017/5/10 on 22:32.
 */
public class PacketContent {

    //分组的名字
    private String name;

    //分组下的商品数量
    private String number;

    //分组的排序
    private String sort;

    //分组的修改时间
    private String date;

    //分组的按钮
    private String[] operation;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String[] getOperation() {
        return operation;
    }

    public void setOperation(String[] operation) {
        this.operation = operation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PacketContent(String name, String number, String sort, String date, String[] operation) {
        this.name = name;
        this.number = number;
        this.sort = sort;
        this.date = date;
        this.operation = operation;
    }

    public PacketContent() {
    }

    /**
     * 读取pc端列表中指定的数据，然后保存在集合对象中.
     *
     * @return
     * @throws IOException
     */
    public PacketContent setPacketNumber(WebDriver driver,int size) throws IOException {
        ReadList readList = new ReadList(driver);
        By by = By.xpath(".//tbody[@id='grouping']/tr["+size+"]");
        List<String> cellContent = readList.columnContent(by, "td");

        //将数据转换成类对象进行储存
        PacketContent pc = null;
        for (int i = 0; i < cellContent.size(); i++) {
            String[] im = cellContent.get(4).toString().split(" ");
            pc = new PacketContent(cellContent.get(0).toString(), cellContent.get(1).toString(),
                    cellContent.get(2).toString(), cellContent.get(3).toString(), im);

        }
        return pc;
    }

    @Override
    public String toString() {
        return "商品分组的数据{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sort='" + sort + '\'' +
                ", date='" + date + '\'' +
                ", operation='" + operation+ '\'' +
                '}';
    }
}
