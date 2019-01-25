package toolskit.tools.caninput;

import toolskit.tools.SystemOut;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 获取表格长度以及表格中按钮的位置
 * Created by Administrator on 2016/12/18.
 */
public class ReadList {

    private WebDriver driver;

    public ReadList(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 获取一级菜单tb下的二级菜单td的长度以及内容
     * <p>
     * 例如：
     * 某个菜单下有一级tb元素以及二级td元素
     * if判断出td是否存在，存在的长度为多少
     * 然后再获取td中的Text
     *
     * @param element
     * @return
     */
    private List dataLevel(WebElement element, String two) {
//      列里面可能有"<th>"、"<td>"两种标签，所以分开处理。
        if (element.findElements(By.tagName(two)).size() > 0) {
            return returnRoute(element.findElements(By.tagName(two)));
        }
        return null;
    }


    /**
     * 获取二级菜单td的text
     * <p>
     * 例如：
     * 想获取td的text，只能判断他的长度是否符合。
     * for将其遍历之后找出他的text内容
     *
     * @param cells
     * @return
     */
    private List returnRoute(List<WebElement> cells) {
        List list = new ArrayList();
//        获取表中某列的数据,并保存到sb当中
        for (int i = 0; i < cells.size(); i++) {
//        获取表中某列的数据,并保存到sb当中
            list.add(cells.get(i).getText());
        }
        return list;
    }

    /**
     * 传入by表单对象，以及一级菜单tb和二级菜单td
     * 例如：
     * 在一个表单下tb和td，我们只需要知道表单对象以及一二对象就可以返回该对象中的全部数据
     *
     * @param by
     * @param one
     * @param two
     * @return
     */
    public List<List> cellContent(By by, String one, String two) {
        //    System.out.println("获取开始" + new Date());
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(one));
        List<List> list = new ArrayList();
//        循环读取那个自提点的整体数据
        SystemOut.getStringOut("进入循环", "tr的数量" + tr.size());
        for (int i = 0; i < tr.size(); i++) {
            //        获取表中某列的数据,并保存到sb当中
            list.add(dataLevel(tr.get(i), two));
        }
        //   System.out.println("获取结束" + new Date());
        return list;
    }

    /**
     * 传入by表单对象，和一级菜单tb和二级菜单td,以及我们需要读取的某行
     * 例如：
     * 在一个表单下tb和td，我们只需要知道某行的数据。。那我们就可以直接返回指定行的数据
     *
     * @param by
     * @param one
     * @param two
     * @param row
     * @return
     */
    public List columnContent(By by, String one, String two, int row) {
        //System.out.println("获取开始" + new Date());
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(one));
//        循环读取那个自提点的整体数据
        return dataLevel(tr.get(row), two);
    }

    /**
     * 传入by表单对象，和一级菜单tb
     * 例如：
     * 在一个表单下tb，我们只需要一级菜单的内容。。
     *
     * @param route
     * @param one
     * @return 返回指定路径的数据, 如果没有找到就返回Null
     */
    public List columnContent(By by, String one) {
        //System.out.println("获取开始" + new Date());
//        获取列表对象
        WebElement element = driver.findElement(by);
        if (element.findElements(By.tagName(one)).size() > 0) {
            //        找到表中全部为tr的元素,循环读取那个自提点的整体数据
            List<WebElement> tr = element.findElements(By.tagName(one));
            return returnRoute(tr);
        } else {
            return null;
        }
    }

    /**
     * 暂时没有什么用。。待续
     * 例子：
     * 后续开发
     *
     * @param route
     * @param name
     * @return
     */
    public List single(By by, String name) {
        WebElement element = driver.findElement(by);//通过实体创建一个element
        List<WebElement> tagName = element.findElements(By.tagName(name));//找出这个element里面的子目录
        List list = new ArrayList();//创建集合来装子目录的内容
        int size = tagName.size();
        for (int i = 0; i < size; i++) {//判断这个子目录里面的内容长度来遍历
            if (i == (size - 1)) {//判断是否等于最后一个
                WebElement element1 = tagName.get(i);
                List<WebElement> a = element1.findElements(By.tagName("a"));
                if (a.size() > 1) { //商品操作列中是否有分列
                    for (int j = 0; j < a.size(); j++) {
                        list.add(a.get(j).getText());
                    }
                }
                //没有分列就直接添加
                else {
                    list.add(tagName.get(i).getText());
                }
            }
            //如果是最后一个数据列就直接添加
            else {
                list.add(tagName.get(i).getText());
            }
        }
        return list;
    }

    /**
     * 返回一个表单下的行数。。。设置if>3的目的是。。过滤某些行只是功能显示行并不是我们需要的数据
     * 例子：
     *  想知道一个表单下全部的行，但是有些只是一些表明，他们的二级菜单长度比较小，就不是我们想要的数据
     *  我们可以通过过滤达到一些效果...
     * @param by
     * @param name
     * @param two
     * @return int
     */
    public int cellSize(By by, String one, String two) {
        //   System.out.println("获取开始" + new Date());
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(one));
        int number = 0;
        for (int i = 0; i < tr.size(); i++) {
            if (tr.get(i).findElements(By.tagName(two)).size() > 3) {
                ++number;
            }
        }
        //   System.out.println("获取的长度为" +number);
        return number;
    }

    /**
     * 返回列表的长度,不需要考虑第二个元素
     * 例子：
     *  不管表单下的行用来干嘛的我只要全部长度..
     * @param by
     * @param name
     * @return
     */
    public int cellSize(By by , String name) {
        System.out.println("获取开始" + new Date());
//        获取列表对象
        WebElement element = driver.findElement(by);
//        找到表中全部为tr的元素
        List<WebElement> tr = element.findElements(By.tagName(name));
        System.out.println("获取的长度为" + tr.size());
        return tr.size();
    }


    public Integer weizhidaima(By by) throws InterruptedException {
     /*   List<List> cellContent = cellContent(load, "li", "a");
        String s = cellContent.get(cellContent.size() - 1).get(0).toString();
        if ("下一页".equals(s)) {
            driver.findElement(By.xpath(load + "/li[" + (cellContent.size() - 1) + "]/a")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return numberFlip(load);
        } else {
            return Integer.parseInt(s);
        }*/
        return 1;
    }

}
