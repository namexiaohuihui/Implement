package wap.business.example.ligrco.Exhibition.Grouping.modify;

import LnsmData.PacketSorting;
import LnsmData.StatementOperation;
import LnsmUitl.LnsmSystemOut;

/**
 * 分组名字修改类
 * Created by ${XiaoHuiHui} on 2017/6/13 on 14:22.
 * XiaoHiiHui [704866169@qq.com]
 */
public class modifyNickname extends modify {

    private String name;

    //名字执行的认证
    private boolean nJ;

    public modifyNickname(PacketSorting packetSorting) {
        super(packetSorting);
    }

    public modifyNickname() {
    }

    /**
     * 名字和排序的编辑
     *
     * @throws InterruptedException
     */
    void executionEditName(String nick) throws InterruptedException {
        //编辑名称
        name = nick;
        nJ = nameJudge(name);
    }

    /**
     * 判断名字的格式是否符合要求
     *
     * @param ont
     * @param edit
     */
    boolean nameJudge(String edit) throws InterruptedException {
        if (edit.equals(fa)) {
            LnsmSystemOut.getStringOut("商品名字不需要修改");
            return false;
        } else if (edit.length() < 2 || edit.length() > 10) {
            LnsmSystemOut.getStringOut("商品分组名字进行修改时发现出错", edit);
            return false;
        }
        String lo = "//*[@id='grouping']/tr[2]/td[1]/input";
        editNameSorting(lo, edit);
        return true;
    }


}
