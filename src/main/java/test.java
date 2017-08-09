import common.tool.SystemOut;
import common.tool.conversion.MutuaMapBean;
import common.tool.conversion.MutualJsonBean;
import common.tool.excelfile.ReadExcel;
import common.tool.informationException.ErrorException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.bean.GoodsBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by ${XiaoHuiHui} on 2017/7/17 on 14:36.
 * XiaoHiiHui [704866169@qq.com]
 */
public class test<T> {

    public Connection connectionSql() {
        String name = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.204/lnlife_1?autoReconnect=true&useSSL=false";
        String user = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (ClassNotFoundException e) {
            System.out.println("找不到类");
            e.printStackTrace();
        } catch (SQLException e) {
            // System.out.println("语句");
            e.printStackTrace();
        }

        return conn;
    }


    public void execute() {
        System.out.println("运行");
        try {
            String sql = "SELECT goods_id,seller_id,shop_id,`status`,price,sale_total,sort from " +
                    "lnsm_shop_goods WHERE shop_id = 10175 and `status` =10;";

            //创立链接
            Connection conn = connectionSql();
            //执行sql语句
            PreparedStatement stmt = conn.prepareStatement(sql);
            //得到结果集
            ResultSet resultSet = stmt.executeQuery();
            //bean对象
            GoodsBean goodsBean = new GoodsBean();
            //结果集和bean对象传入，把没条数据存入list中
            List<GoodsBean> list = new MutualJsonBean().resultSetToJson(resultSet, (T) goodsBean);
            for (int i = 0; i < list.size(); i++) {
                SystemOut.getStringOut(list.get(i).toString());
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void extest() throws ErrorException, IOException {
        String FILE_NAME = "E:/MyFirstExcel.xlsx";
        String FILE_NAME2 = "E:/muban.xls";
        File file = new File(FILE_NAME);
        InputStream is =  new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(is);
        String sheetName = workbook.getSheetName(workbook.getNumberOfSheets()-1);
        SystemOut.getStringOut(sheetName);
    }

    public void readExcle() throws Exception {
        //  String load = "E:\\drivers\\CasePlan\\CasrScene\\BusinessInformation\\商家信息管理场景.xlsx";
        String load = "C:\\Users\\70486\\Desktop\\商家信息管理场景.xlsx";
        ReadExcel readExcel = new ReadExcel();
        Map<String, String> stringStringMap = readExcel.singleReadXlsx(load, 1, 1);
        try {
            SystemOut.getStringOut(stringStringMap);
            EnumProgramBean o = (EnumProgramBean) new MutuaMapBean().reflectmapToObject(stringStringMap, new EnumProgramBean().getClass());
            SystemOut.getStringOut(o.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
