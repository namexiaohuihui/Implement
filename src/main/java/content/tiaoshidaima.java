package content;

import common.tool.SystemOut;
import common.tool.mysqls.DBHelper;
import org.junit.Test;

import java.sql.*;

/**
 * Created by 70486 on 2017/9/13 on 22:58.
 */
public class tiaoshidaima {


    @Test
    public void startKasishi() throws SQLException {
        DBHelper dbHelper = new DBHelper();

        String[] mysqlData = new String[]{"jdbc:mysql://192.168.154.1/ph_exclusive?autoReconnect=true&useSSL=false", "com.mysql.jdbc.Driver", "xiaohuihui", "xiaohuihui"};
        dbHelper.url = mysqlData[0];
        dbHelper.name = mysqlData[1];
        dbHelper.user = mysqlData[2];
        dbHelper.password = mysqlData[3];

        dbHelper.dbhelperStart("select * from ph_shop; ");

        ResultSet ret = dbHelper.pst.executeQuery();
        while(ret.next()){
            SystemOut.getStringOut("-----------------------------------------");
            ResultSetMetaData re = ret.getMetaData();
            System.out.println("该行中，该列的长度：" + re.getColumnCount());
            for (int i = 1; i <= re.
                    getColumnCount(); i++) {
                SystemOut.getStringOut("指定内容" +ret.getString(i),
                        "指定列名" + re.getColumnName(i));
            }
        }
        SystemOut.getStringOut("**********************************************");
        ret.last();
        int length = ret.getRow();
        ret.first(); // 还原游标到rs开头
        System.out.println("该表的长度为：" + length);

    }
}
