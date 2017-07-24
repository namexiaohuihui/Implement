package wap.business.example.ligrco.Exhibition.Article;

import common.FoxDriver;
import common.tool.SystemOut;
import common.tool.caninput.FlipOther;
import common.tool.caninput.InfoSelect;
import common.tool.caninput.ReadList;
import common.tool.excelfile.ReadExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import wap.business.instantiation.CommoditiesList;
import wap.business.instantiation.CommodityIntroduction;
import wap.business.instantiation.CommodityOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * 商品的操作只能通过状态筛选之后进行。。。原因：实力有限。。
 * 商品管理-->商品列表：
 * 商品的筛选：
 * 1.商品状态的筛选
 * 2.商品分组的筛选
 * 3.商品内容的筛选
 * 输入框的输入，以及按钮的点击
 * 商品名的点击没，打开二维码
 * 商品的操作：暂时不懂
 * 修改分组
 * 全选按钮的变化
 * 翻页（上下页，数字翻页）
 * 商品的数量
 * Created by XiaoHuiHui on 2016/12/27.
 * <p>
 * <p>
 * 操作思路：
 * 1.读取商品
 * 2.判断其状态。兑取相应excel表中的数据
 * 3.根据excel表中的数据来进行相应的操作
 */
public class GoodsList {

    /**
     * 从excle表格中读取的每一行数据都是一个对象，这个集合用于存储读取出来的每一个对象。。
     */
    List<CommodityIntroduction> listComm;
    /**
     * 1. 进来读取数据库，查看商品的数量。   之后判断商品的信息是否正确
     * 2. 翻页
     * 3. 筛选
     * 4. 未实现：
     * 翻页长度的核算
     */

    WebDriver driver = FoxDriver.getFoxDriver();
    /**
     * 商品列表表格xpath路径
     */
    private String tablePath = ".//*[@id='sample-table-1']/tbody";
    /**
     * 商品状态、分组、名字等数据的筛选路径
     */
    private String statusSelect = "select[id=status][name=status]";
    private String groupSelect = "select[id=group][name=group]";
    private String bySelect = "select[id=by][name=by]";
    /**
     * 翻页对象的xpath路径
     */
    private String route = ".//*[@class = 'page-content']/div[1]/div[2]/div[2]/div[1]/ul";
    /**
     * 列表数量显示对象的Xpath路径
     */
    private String dataroute = ".//*[@class = 'page-content']/div[1]/div[2]/div[1]/div[1]";
    private ReadList readList;  //列表对象
    private ReadExcel readExcel; //excel对象


    public GoodsList(String url) throws Exception {

        readList = new ReadList(driver);
        readExcel = new ReadExcel();

        int singleXlsx = readExcel.singleXlsx(".//src//main//java//商品编辑.xlsx");//读取商品编辑表中的长度

        int single = 1;//编辑表中的第N行

        //获取读取excel表中读取的数据并进行封装对象.该表存放着商品的操作
        List<CommodityOperation> goodsOplog = setGoodsOplog();


        for (int i = 0; i < 1; i++) {
            sleep(1000);
            SystemOut.getStringOut("-------------------------------------------------开始执行",(i+1) + "");

            CommodityOperation commOperation = goodsOplog.get(i);

            By bystatus = By.cssSelector(statusSelect);
            InfoSelect infoSelect = new InfoSelect();
            infoSelect.categoryText(bystatus,commOperation.getState());

            //点击搜索按钮
            driver.findElement(By.xpath(".//div[@class='col-xs-12']/form/button")).click();
            By bytable = By.cssSelector(statusSelect);
            int row = readList.cellSize(bytable, "tr", "td");

            row = ((int) Math.random()) * row;

            if (single <= singleXlsx) {
                //商品的操作
                CommoditiesList colist = new CommoditiesList().setComodityList(readList, row, tablePath);
                GoodOperation goodOperation = new GoodOperation(colist, commOperation, single);
                single = 1;
            } else {
                single = 1;
                CommoditiesList colist = new CommoditiesList().setComodityList(readList, row, tablePath);
                GoodOperation goodOperation = new GoodOperation(colist, commOperation, single);
            }
        }
    }


    /**
     * 读取商品操作表的数据，并将每一行的数据转换成一个对象进行储存..
     *
     * @return
     * @throws IOException
     */
    private List<CommodityOperation> setGoodsOplog() throws IOException {
        //读取数据
        List<List> wholeReadXlsx = readExcel.wholeReadXlsx(".//src//main//java//商品操作统计表.xlsx");


        //创建存储对象的集合
        List<CommodityOperation> goodsOplogList = new ArrayList<>();
        //编辑读取的数据
        for (int i = 0; i < wholeReadXlsx.size(); i++) {
            //获取每行的数据
            List list = wholeReadXlsx.get(i);
            //创建对象
            CommodityOperation goodsOplog = new CommodityOperation();
            //通过set方法进行存储
            goodsOplog.setState((String) list.get(0));
            goodsOplog.setOperation((String) list.get(1));
            goodsOplog.setImplement((String) list.get(2));
            goodsOplog.setAttribute((String) list.get(3));
            //集合添加数据
            goodsOplogList.add(goodsOplog);
        }
        //返回存储数据的集合
        return goodsOplogList;
    }


    /**
     * 判断数据的长度是否等于翻页的长度
     */
    private void getDataSize() throws InterruptedException {
        int other = new FlipOther().getOther(driver, dataroute);
        /*
        判断翻页的长度：
            1.如果最后一个li为“下一页”，说明还可以继续翻页
            2.如果最后一个为“N”，说明已经到底了。。
         */
        String route = ".//*[@class = 'page-content']/div[1]/div[2]/div[2]/div[1]/ul";
        By by = By.xpath(route);
        int flip = readList.weizhidaima(by);
        if (other == flip) {
            System.out.println("数据长度和翻页的长度一致");
        } else {
            System.out.println("数据长度和翻页的长度不一致");
        }

    }


    private void getLeng() {
        String dataroute = ".//*[@class = 'page-content']/div[1]/div[2]/div[2]/div[1]/ul";
        By by = By.xpath(dataroute);
        List<List> cellContent = readList.cellContent(by, "li", "a");
        List list = cellContent.get(cellContent.size() - 2);
        List list2 = cellContent.get(cellContent.size() - 1);
        int flipSize = Integer.parseInt(list.get(0).toString());
        System.out.println("返回的一级长度" + cellContent.size());
        if ("下一页".equals(list2.get(0))) {
            driver.findElement(By.xpath(dataroute + "/div[2]/div[1]/ul/li[" + (cellContent.size() - 1) + "]/a")).click();
            System.out.println("点了");
        }
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<List> cellContent1 = readList.cellContent(by, "li", "a");
        List list1 = cellContent1.get(cellContent1.size() - 1);

        if ("下一页".equals(list1.get(0).toString())) {
            driver.findElement(By.xpath(dataroute + "/div[2]/div[1]/ul/li[" + (cellContent1.size()) + "]/a")).click();
        } else {
            System.out.println("假的");
        }
    }

}
