package common.tool.caninput;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

/**
 * 获取表格长度以及表格中按钮的位置
 * Created by Administrator on 2016/12/18.
 */
public class readList {

    private WebDriver driver;

    public readList(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 通过路径来计算子tr的子td的路径
     *
     * @param driver
     * @param route
     * @return
     */
    public List getCellRoute(String route) {
        System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.xpath(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName("tr"));
        List list = new ArrayList();
//        循环读取那个自提点的整体数据
        int j = 1;
        System.out.println("进入循环" + new Date());
        for (int i = 0; i < tr.size(); i++) {
            //        获取表中某列的数据,并保存到sb当中
            list.add(getCellData(tr.get(i), "/tr[" + (++j) + "]"));
        }
        return list;
    }

    /**
     * 通过路径来读取子元素的路径
     *
     * @param element
     * @param route
     * @return
     */
    private String getCellData(WebElement element, String route) {
//      列里面可能有"<th>"、"<td>"两种标签，所以分开处理。
        System.out.println("th开始" + new Date());
        /*先隐藏该段代码：因为网页都是td的，没有th，如果th不存在时运行了改程序需要5s，比较耗时
        if (element.findElements(By.tagName("th")).size()>0){
            return  getRoute(element.findElements(By.tagName("th")),route);
        }
        */
        if (element.findElements(By.tagName("td")).size() > 0) {
            System.out.println("th结束" + new Date());
            return getRoute(element.findElements(By.tagName("td")), route);
        }
        return null;
    }

    /**
     * 通过路径来返回子元素的路径
     *
     * @param cells
     * @param route
     * @return
     */
    private String getRoute(List<WebElement> cells, String route) {
        System.out.println("td开始" + new Date());
        for (int i = 0; i < cells.size(); i++) {
            if (i == 3) {
                route = route + "/td[" + (++i) + "]";
            }
        }
        System.out.println("td结束" + new Date());
        return route;
    }

    /**
     * 通过指定路径来获取该路径下的全部列表数据
     *
     * @param route 路径
     * @param one   第一个标签
     * @param two   第二个标签
     * @return
     */
    public List<List> getCellContent(String route, String one, String two) {
    //    System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.xpath(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(one));
        List<List> list = new ArrayList();
//        循环读取那个自提点的整体数据
        LnsmSystemOut.getStringOut("进入循环", "tr的数量" + tr.size());
        for (int i = 0; i < tr.size(); i++) {
            //        获取表中某列的数据,并保存到sb当中
            list.add(getData(tr.get(i), two));
        }
     //   System.out.println("获取结束" + new Date());
        return list;
    }

    /**
     * 通过指定路径来获取该路径下的全部列表数据
     *
     * @param route 路径
     * @param one   第一个标签
     * @param two   第二个标签
     * @return
     */
    public List<List> getCellContentcss(String route, String one, String two) {
        System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.cssSelector(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(one));
        List<List> list = new ArrayList();
//        循环读取那个自提点的整体数据
        LnsmSystemOut.getStringOut("进入循环", "tr的数量" + tr.size());
        for (int i = 0; i < tr.size(); i++) {
            //        获取表中某列的数据,并保存到sb当中
            list.add(getData(tr.get(i), two));
        }
        System.out.println("获取结束" + new Date());
        return list;
    }

    /**
     * 指定路径获取指定行数的数据
     * @param route  指定路径
     * @param one   指定元素对象
     * @param two   指定元素对象
     * @param row   指定位置
     * @return
     */
    public List getColumnContent(String route, String one, String two, int row) {
        //System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.xpath(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(one));
//        循环读取那个自提点的整体数据
        return getData(tr.get(row), two);
    }

    /**
     * 通过指定路径和指定元素名称来获取数据
     * @param route
     * @param one
     * @return  返回指定路径的数据,如果没有找到就返回Null
     */
    public List getColumnContent(String route, String one) {
        //System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.xpath(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
        if (element.findElements(By.tagName(one)).size() > 0){
            //        找到表中全部为tr的元素,循环读取那个自提点的整体数据
            List<WebElement> tr = element.findElements(By.tagName(one));
            return  getRoute(tr);
        }else {
            return null;
        }
    }

    /**
     * 通过路径来读取子元素的内容
     *
     * @param element
     * @return
     */
    private List getData(WebElement element, String two) {
//      列里面可能有"<th>"、"<td>"两种标签，所以分开处理。
        if (element.findElements(By.tagName(two)).size() > 0) {
            return getRoute(element.findElements(By.tagName(two)));
        }
        return null;
    }


    /**
     * 通过路径来返回子元素的內容
     *
     * @param cells
     * @return
     */
    private List getRoute(List<WebElement> cells) {
        List list = new ArrayList();
//        获取表中某列的数据,并保存到sb当中
        for (int i = 0; i < cells.size(); i++) {
//        获取表中某列的数据,并保存到sb当中
            list.add(cells.get(i).getText());
        }
        return list;
    }

    /**
     * 通过指定的路径和tagname名来打印相应行的内容
     *
     * @param route
     * @param name
     * @return
     */
    public List getSingle(String route, String name) {
        By by = By.xpath(route);//对象路径创建一个实体
        WebElement element = driver.findElement(by);//通过实体创建一个element
        List<WebElement> tagName = element.findElements(By.tagName(name));//找出这个element里面的子目录
        List list = new ArrayList();//创建集合来装子目录的内容
        int size = tagName.size();
        for (int i = 0; i < size; i++) {//判断这个子目录里面的内容长度来遍历
            if (i == (size - 1)) {//判断是否等于最后一个
                WebElement element1 = tagName.get(i);
                List<WebElement> a = element1.findElements(By.tagName("a"));
                //待审核的商品，操作列是没有操作展示的
                if (a.size() > 1) {
                    for (int j = 0; j < a.size(); j++) {
                        list.add(a.get(j).getText());
                    }
                    System.out.print("单列的长度" + a.size() + "\t");
                    System.out.println();
                } else {
                    list.add(tagName.get(i).getText());
                }
            } else {
                list.add(tagName.get(i).getText());
            }
        }
        return list;
    }

    //    返回列表的长度
    public int getCellSize(String route, String name, String two) {
        //   System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.xpath(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(name));
        int number = 0;
        for (int i = 0; i < tr.size(); i++) {
            if (tr.get(i).findElements(By.tagName(two)).size() > 3) {
                ++number;
            }
        }
        //   System.out.println("获取的长度为" +number);
        return number;
    }

    //    返回列表的长度,不需要考虑第二个元素
    public int getCellSize(String route, String name) {
        System.out.println("获取开始" + new Date());
//        获取表对象
        By by = By.xpath(route);
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(name));
        System.out.println("获取的长度为" + tr.size());
        return tr.size();
    }


    /**
     * @param driver
     * @param flip    最后一个数的内容
     * @param element
     * @return
     */
    public int getFlip(String load) throws InterruptedException {
        List<List> cellContent = getCellContent(load, "li", "a");
        String s = cellContent.get(cellContent.size() - 1).get(0).toString();
        if ("下一页".equals(s)) {
            driver.findElement(By.xpath(load + "/li[" + (cellContent.size() - 1) + "]/a")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return getFlip(load);
        } else {
            return Integer.parseInt(s);
        }
    }

}
