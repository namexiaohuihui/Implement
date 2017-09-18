package wap.business;

import common.parameter.Parameter;
import common.tool.SystemOut;
import common.tool.conversion.MutuaMapBean;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import wap.business.example.bean.EnumProgramBean;

import java.io.IOException;
import java.util.Map;

/**
 * 开始前的数据整理
 * 从计划里面读取所要执行的用例
 * Created by ${XiaoHuiHui} on 2017/8/7 on 18:05.
 * XiaoHiiHui [704866169@qq.com]
 */
public class StartData {
    public static String load = new Parameter().getLoad();

    /**
     * 把读取的用例所在位置返回。
     * @return
     * @throws IOException
     */
    public static String readExcleData() throws IOException, ErrorException {
        String loads = "";
        ReadExcel readExcel = new ReadExcel();
        Map<String, String> stringStringMap = readExcel.singleReadXlsx(load, 1, 1);
        try {
            EnumProgramBean epb = (EnumProgramBean) new MutuaMapBean()
                    .reflectmapToObject(stringStringMap,
                    new EnumProgramBean().getClass());
            loads = epb.getOne() + epb.getTwo() + epb.getThree();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loads;
    }
}
