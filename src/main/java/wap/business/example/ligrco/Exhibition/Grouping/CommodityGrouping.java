package wap.business.example.ligrco.Exhibition.Grouping;

import LnsmData.PacketContent;
import LnsmData.PacketSorting;
import LnsmInitialize.FoxDriver;
import LnsmOperation.CommodityOperation.Comments;
import wap.business.example.ligrco.Exhibition.Grouping.modify.modify;
import LnsmOperation.conversion.dateConversionTime;
import LnsmUitl.LnsmExcel;
import LnsmUitl.LnsmList;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.SystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商品管理-->商品分组：
 * 分组的数量
 * 修改按钮的点击、以及删除之后的提示语
 * 修改按钮的点击:
 * 1.分组名的修改
 * 2.分组排序的修改
 * 分组信息的核对:
 * 1.分组名称
 * 2.销售中的商品数量
 * 3.排序
 * 4.status_time
 * 添加分组的按钮
 * Created by XiaoHuiHui on 2016/12/27.
 */
public class CommodityGrouping extends Comments {
    private WebDriver driver = FoxDriver.getWebDrivaer();


    //商品分组的名字
    private String name;

    //商品分组排序值
    private int sort;

    private LnsmExcel lnsmExcel;

    //判断是否需要执行操作
    private final String tr = "Y";
    private final String fa = "N";

    //存储商品操作表中的数据
    private PacketSorting packetSorting;

    //名字执行的认真
    private boolean nJ;

    //排序执行的认真
    private boolean sJ;

    public CommodityGrouping(String url) {
        super(url);
        try {
            PacketListContent();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PacketListContent() throws InterruptedException, IOException {

        //列表数据准备
        //  PacketContent  pack = setPacketNumber();
        List<PacketContent> pc = setPacketList();

        //excle表格数据准备
        List<PacketSorting> ps = setPacketOplog();

        for (int i = 0; i < 1; i++) {
            //获取对象
            packetSorting = ps.get(i);
            int size = i % pc.size();
            PacketContent packetContent = pc.get(size);
            //打印数据
            SystemOut.getStringOut(packetContent.toString(), i + "");

            SystemOut.getStringOut(packetSorting.toString(), i + "");
            //代码执行，list下标是从0开始，列表数据是从1开始的，excle也是从0开始的
              editNameSort(size);
        }
    }


    /**
     * 商品分组有三个按钮，其中修改和删除为显示，保存按钮为隐形的。。。
     *
     * @throws InterruptedException
     * @throws IOException
     */
    private void editNameSort(int size) throws InterruptedException, IOException {
        switch (packetSorting.getOperation()) {
            case "修改":
                modifyEdit(1);
                modify modify = new modify(packetSorting);
                //分组页面的分组操作
                modify.setSize(size + 1);
                modify.editNameSort();
                break;
            case "删除":
                modifyEdit(3);
                executionJudgment();
                break;
            default:
                SystemOut.getStringOut("商品分组操作出现第三方", packetSorting.getImplement());
                break;
        }
    }


    /**
     * 如果选择删除的话，就通过这个方法进行区分数据。
     * 并打印出对话框的内容
     */
    private void executionJudgment() {
        if (packetSorting.getImplement().equals(tr)) {
            SystemOut.getStringOut("商品分组执行点击确定按钮", packetSorting.getImplement());
            getAlert(true);
        } else if (packetSorting.getImplement().equals(fa)) {
            SystemOut.getStringOut("商品分组执行点击取消按钮", packetSorting.getImplement());
            getAlert(false);
        } else {
            SystemOut.getStringOut("商品分组执行出现第三方", packetSorting.getImplement());
        }
    }


    /**
     * 通过冒泡排序来对数据进行排序，找出之前设置的分组所在位置
     *
     * @throws IOException
     */
    private void bubbleSort() throws IOException {
        List<PacketContent> packetContents = setPacketList();

        if (sJ && nJ) {//名字和排序都进行修改之后的查找
            SystemOut.getStringOut("分组编辑时，名字和排序都进行修改了");

        } else if (sJ) {//排序修改之后就比较排序的位置
            SystemOut.getStringOut("分组编辑时，只对对排序进行修改");
            //找出编辑之后，分组的所在位置
            int i = modifyLocation(packetContents);

            SystemOut.getStringOut(i + "新编辑之后的商品位置");
            //根据指定位置来读取数据，判断时间是否正确
            List<String> list = modifyRead(i);
            //根据读取的排序和之前设置的排序进行比较
            judgmentModify(list.get(2), sort + "");

        } else if (nJ) {//名字修改之后就比较时间的位置

        } else {
            SystemOut.getStringOut("分组编辑时，什么也没有进行修改");
        }

    }

    /**
     * 该方法主要负责找出新编辑之后的分组所在位置
     *
     * @return 返回分组的最新位置
     */
    private int modifyLocation(List<PacketContent> packetContents) {
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

    /**
     * 通过指定位置获取到相应的数据
     *
     * @param size 分组的最新位置
     * @return 返回指定位置之后读取的数据
     * @throws IOException
     */
    private List<String> modifyRead(int size) throws IOException {
        //lo:通过冒泡得出新修改的排序所在位置
        String lo = "//*[@id='grouping']/tr[" + size + "]";
        //*[@id="grouping"]/tr[6]
        List<String> td = new LnsmList(driver).getSingle(lo, "td");
        return td;
    }

    /**
     * 拿到数据数据之后执行对比
     *
     * @param data pc列表上读取到的数据
     * @param date excle表格中，设置的数据
     * @throws IOException
     */
    private void judgmentModify(String data, String date) throws IOException {
        if (data.equals(date)) {
            SystemOut.getStringOut(data, "编辑之后数据正确", date);
        } else {
            SystemOut.getStringOut(data, "编辑之后数据错误", date);
        }

    }


    /**
     * 通过编辑时间来最后一步验证是否找对了位置
     *
     * @param size           修改成之后的分组所在位置
     * @param packetContents 全部数据
     * @throws IOException
     */
    private void dataModified(int size, List<PacketContent> packetContents) throws IOException {
        List<String> listTime = new ArrayList<>();
        for (int i = 1; i < packetContents.size(); i++) {
            listTime.add(packetContents.get(i).getDate());
        }
        int i = new dateConversionTime().conversionTime(listTime);
        if (i == size) {
            SystemOut.getStringOut("分组修改之后，时间所在的位置跟排序所在的位置相同");
        } else {
            SystemOut.getStringOut("分组修改之后，时间所在的位置跟排序所在的位置出现了明显的差异");
        }
    }


    /**
     * 根据位置点击修改按钮
     *
     * @param posi
     * @throws InterruptedException
     */
    private void modifyEdit(int posis) throws InterruptedException {

        //positions表示第几行数据，posi表示第几个按钮
        String load = ".//tbody[@id='grouping']/" +
                "tr[2]/td[5]/span[" + posis + "]";
        LnsmPreservation.getButtonXpath(load);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    /**
     * 点击系统对话框上面的按钮.
     *
     * @param bl 为真就点击正确，为假就点击取消
     */
    private void getAlert(boolean bl) {
        String alert = LnsmPreservation.getAlert(bl);//点击系统对话框上的操作
        SystemOut.getStringOut("系统对话框的标题是", alert);
    }


    /**
     * 点击保存或者删除之后，自置的对话框是否出现
     *
     * @param prompt
     */
    private void getoutPrompt(String prompt) {
        try {
            driver.findElement(By.cssSelector("div.aui_content>div"));
            SystemOut.getStringOut("保存之后的提示框名字出现了");
        } catch (Exception e) {
            SystemOut.getStringOut("保存之后的名字没有出现");
            e.printStackTrace();
        }
    }


    /**
     * 获取excle表格的内容并赋值给类对象，然后保存在集合对象中
     *
     * @return
     * @throws IOException
     */
    private List<PacketSorting> setPacketOplog() throws IOException {
        lnsmExcel = new LnsmExcel();
        //读取数据
        List<List> wholeReadXlsx = lnsmExcel.getWholeReadXlsx(".//src//main//java//商品分组排序.xlsx");

        //创建存储对象的集合
        List<PacketSorting> goodsOplogList = new ArrayList<>();
        //编辑读取的数据
        for (int i = 0; i < wholeReadXlsx.size(); i++) {
            //获取每行的数据
            List list = wholeReadXlsx.get(i);
            //创建对象
            PacketSorting paOplog = new PacketSorting();
            //通过set方法进行存储
            paOplog.setOperation((String) list.get(0));
            paOplog.setName((String) list.get(1));
            paOplog.setSort((String) list.get(2));
            paOplog.setImplement((String) list.get(3));
            //集合添加数据
            goodsOplogList.add(paOplog);
        }
        //返回存储数据的集合
        return goodsOplogList;
    }

    /**
     * 读取pc端列表中全部的数据，然后保存在集合对象中
     *
     * @throws IOException
     */
    private List<PacketContent> setPacketList() throws IOException {
        LnsmList lnsmList = new LnsmList(driver);
        List<List> cellContent = lnsmList.getCellContent(
                ".//tbody[@id='grouping']", "tr", "td");

        //将数据转换成类对象进行储存

        List<PacketContent> pack = new ArrayList<>();
        for (int i = 0; i < cellContent.size(); i++) {
            List list = cellContent.get(i);
            String[] im = list.get(4).toString().split(" ");
            PacketContent pc = new PacketContent(list.get(0).toString(), list.get(1).toString(),
                    list.get(2).toString(), list.get(3).toString(), im);
            pack.add(pc);
        }

        return pack;
    }

    /**
     * 读取pc端列表中指定的数据，然后保存在集合对象中.
     *
     * @return
     * @throws IOException
     */
    private PacketContent setPacketNumber() throws IOException {
        LnsmList lnsmList = new LnsmList(driver);
        List<String> cellContent = lnsmList.getColumnContent(
                ".//tbody[@id='grouping']/tr[2]", "td");

        //将数据转换成类对象进行储存
        PacketContent pc = null;
        for (int i = 0; i < cellContent.size(); i++) {
            String[] im = cellContent.get(4).toString().split(" ");
            pc = new PacketContent(cellContent.get(0).toString(), cellContent.get(1).toString(),
                    cellContent.get(2).toString(), cellContent.get(3).toString(), im);

        }
        return pc;
    }

}
