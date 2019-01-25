package business;

import toolskit.parameters.Parameter;
import toolskit.tools.conversion.MutuaMapBean;
import toolskit.tools.excelfile.ReadExcel;
import toolskit.tools.informationException.ErrorException;
import toolskit.tools.EnumProgramBean;

import java.io.IOException;
import java.util.Map;

/**
 * 开始前的数据整理
 * 从计划里面读取所要执行的用例
 * Created by ${XiaoHuiHui} on 2017/8/7 on 18:05.
 * XiaoHiiHui [704866169@qq.com]
 */
public class StartData{
    public static String load = Parameter.load;

    /**
     * 指定读取页面和行数将数据返回并转换成类对象进行处理。
     *
     * @param numSheet 页面
     * @param rowNum   行数
     * @return
     * @throws IOException
     * @throws ErrorException
     */
    /*大傻逼
     * 指定读取页面和行数将数据返回并转换成类对象进行处理。
     * @param numSheet  页面
     * @param rowNum   行数
     */
    public static EnumProgramBean readExcleData(int numSheet, int rowNum) {

        EnumProgramBean epb = readLoad(numSheet, rowNum);
        load = epb.getOne() + epb.getTwo() + epb.getThree();//获取用例路径

        return epb;
    }

    public static EnumProgramBean readExcleLoad(int numSheet, int rowNum) {

        EnumProgramBean epb = readLoad(numSheet, rowNum);

        return epb;
    }

    public static EnumProgramBean readLoad(int numSheet, int rowNum) {

        ReadExcel readExcel = new ReadExcel();
        EnumProgramBean epb = null;

        try {

            Map<String, String> stringStringMap = readExcel.singleReadXlsx(load, numSheet, rowNum);
            epb = (EnumProgramBean) new MutuaMapBean().reflectmapToObject(stringStringMap,
                    new EnumProgramBean().getClass());

        } catch (Exception e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return epb;
    }


    /**
     * 根据指定路径和页面来获取excle表格的数据.
     * @param load   路径
     * @param numSheet 页面薄
     * @param rowNum 行数
     * @return
     */
    public static EnumProgramBean readLoad(String load, int numSheet, int rowNum) {

        ReadExcel readExcel = new ReadExcel(); //读取excle的类对象
        EnumProgramBean epb = null; //定义一个存储读取出数据的类对象

        try {

            //将读取之后的数据转换成map集合
            Map<String, String> stringStringMap = readExcel.singleReadXlsx(load, numSheet, rowNum);
            //map转换成类对象
            epb = (EnumProgramBean) new MutuaMapBean().reflectmapToObject(stringStringMap,
                    new EnumProgramBean().getClass());

        } catch (InterruptedException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        } catch (Exception e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return epb;
    }
}
