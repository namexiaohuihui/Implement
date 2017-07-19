package wap.business.example.ligrco.Exhibition.Grouping.modify;


import common.tool.SystemOut;
import wap.business.instantiation.PacketContent;

import java.util.ArrayList;
import java.util.List;

/**
 * 分组排序修改类
 * Created by ${XiaoHuiHui} on 2017/6/13 on 14:23.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ModifySort extends Modify {

    private int sort;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * 排序的编辑
     *
     * @throws InterruptedException
     */
    public void executionEditSort(String sort) throws InterruptedException {

        //编辑排序
        sJ = sortingJudge(sort);

    }


    /**
     * 判断排序
     *
     * @param ont
     * @param edit
     */
    private boolean sortingJudge(String edit) throws InterruptedException {
        try {
            sort = Integer.parseInt(edit);
            if (sort > 999) {
                SystemOut.getStringOut("商品分组排序只能输入0-999之间的正整数", edit);
                return false;
            }
        } catch (NumberFormatException e) {
            //   e.printStackTrace();
            SystemOut.getStringOut("商品分组排序进行修改时出现了类型转换异常", edit);
            return false;
        }
        String lo = "//*[@id='grouping']/tr[2]/td[3]/input";
        //数据编辑
        editNameSorting(lo, edit);
        return true;
    }

    /**
     * 该方法主要负责找出新编辑之后的分组所在位置
     *
     * @return 返回分组的最新位置
     */
    int modifyLocation(List<PacketContent> packetContents) {
        //        找出除全部分组之外的分组排序值
        List<Integer> listSort = new ArrayList<>();
        List<String> listname = new ArrayList<>();
        for (int i = 1; i < packetContents.size(); i++) {
            listSort.add(Integer.parseInt(packetContents.get(i).getSort()));
            listname.add(packetContents.get(i).getName());
        }

//        将分组进行排序,因为名字不能进行排序所以使用了冒泡法.
//        因为list最后添加的数据是需要设置的数据，所以从最后一个进行比较
        int size = 0;
        for (int i = 0; i < listSort.size() - 1; i++) {
            if (sort <= listSort.get(i)) {
                size = i;
                break;
            }
            continue;
        }
        return size;
    }
}
