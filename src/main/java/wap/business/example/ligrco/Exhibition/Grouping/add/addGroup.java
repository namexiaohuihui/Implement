package wap.business.example.ligrco.Exhibition.Grouping.add;

import LnsmData.PacketSorting;
import wap.business.example.ligrco.Exhibition.Grouping.Operating;
import LnsmUitl.LnsmPreservation;

/**
 * 点击添加分组之后的操作
 * Created by 70486 on 2017/6/13 on 23:31.
 */
public class addGroup extends Operating{

    private String route = "div[class=group-form-item][id=J_addgroup]";

    private int size ;

    PacketSorting packetSorting ;

    public addGroup(PacketSorting packetSorting) {
        this.packetSorting = packetSorting;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addGroupClick(){
        LnsmPreservation.getButtonCssSelector(route);
    }


}
