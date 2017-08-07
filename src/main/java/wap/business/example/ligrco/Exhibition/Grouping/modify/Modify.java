package wap.business.example.ligrco.Exhibition.Grouping.modify;

import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
import common.tool.caninput.Preservation;
import common.tool.mysqls.MysqlInquire;
import common.tool.mysqls.StatementOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wap.business.instantiation.PacketSorting;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/** 分组修改类
 * Created by ${XiaoHuiHui} on 2017/6/13 on 14:26.
 * XiaoHiiHui [704866169@qq.com]
 */
public class Modify {

    //列表数据保存的对象
    PacketSorting packetSorting;

    //名字和排序执行的认证
    boolean nJ;
    boolean sJ;

    //判断是否需要执行操作
    final String tr = "Y";
    final String fa = "N";

    //用于记录
   protected int size ;

    WebDriver driver = FoxDriver.getWebDrivaer();


    public Modify(PacketSorting packetSorting){
        this.packetSorting = packetSorting;
    }

    public Modify() {

    }


    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 商品分组有三个按钮，其中修改和删除为显示，保存按钮为隐形的。。。
     * @throws InterruptedException
     * @throws IOException
     */
    public  void editNameSort() throws InterruptedException, IOException {
        ModifyNickname mn = new ModifyNickname();
        ModifySort ms = new ModifySort();
        switch (packetSorting.getImplement()) {
            case tr:
                typeJudgment(mn,ms);//名称或者排序的输入
                //保存之后数据对比.
                break;
            case fa:
                getoutPrompt("保存成功");
                SystemOut.getStringOut("商品分组点击分组之后直接点击保存不需要修改其他", packetSorting.getImplement());
                break;
            default:
                SystemOut.getStringOut("商品分组执行出现第三方", packetSorting.getImplement());
                break;
        }
    }

    /**
     * 判断是否需要进行保存，如果需要就判断是否需要修改名字或者排序
     * 如果不需要就直接点击保存
     * @throws InterruptedException
     * @throws IOException
     */
    void typeJudgment(ModifyNickname mn, ModifySort ms) throws InterruptedException, IOException {
        mn.executionEditName(packetSorting.getName());

        ms.executionEditSort(packetSorting.getSort());

        getoutPrompt("保存成功");//保存按钮的提示框

        refresh();//保存成功之后，刷新页面
    }



    /**
     * 根据位置点击修改按钮
     *
     * @param posi
     * @throws InterruptedException
     */
    void modifyEdit(int posis) throws InterruptedException {
        //positions表示第几行数据，posi表示第几个按钮
        String load = ".//tbody[@id='grouping']/" +
                "tr[2]/td[5]/span[" + posis + "]";
        new Preservation().buttonXpath(load);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    /**
     * 实现名字或者排序的输入
     *
     * @param ont  位置。根据这个内置来找到元素对象
     * @param edit 内容。将这个内容输入到元素里面
     */
    void editNameSorting(String lo, String edit) throws InterruptedException {
        ElementInput el = new ElementInput();
        el.accordingToCssSelector(lo,edit);
    }


    /**
     * 点击保存或者删除之后，自置的对话框是否出现
     *
     * @param prompt
     */
    private void getoutPrompt(String prompt) {
        try {
            modifyEdit(2);
            driver.findElement(By.cssSelector("div.aui_content>div"));
            SystemOut.getStringOut("保存之后的提示框名字出现了");
        } catch (Exception e) {
            SystemOut.getStringOut("保存之后的名字没有出现");
            e.printStackTrace();
        }
    }

    protected void refresh(){
        //手动刷新页面，重新对分组进行排序：因为保存之后不能自动刷新页面
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }



    protected void dataMysql(String content, String structure, String condition, String sorting) throws InterruptedException {
        MysqlInquire inquire = new MysqlInquire();

        StatementOperation statementOperation = new StatementOperation();
        String lnsm_group = statementOperation.statementInquire(content, structure, condition, sorting);
        Map<Integer, List> integerListMap = inquire.dataMysqlAll(lnsm_group);
    }

}
