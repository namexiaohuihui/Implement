package common.tool.mysqls;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MysqlInquire {
    static DBHelper db1 = null;
    static ResultSet ret = null;

    /**
     * 通过sql返回查询的内容
     * 返回的内容很多
     * @param sql
     * @return
     */
    public List<List> dataMysqlAll(String sql) {
        db1 = new DBHelper(sql);//创建DBHelper对象
        int length = 0;
        List<List> ll = new ArrayList<>();
        List<String> list = null;
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            ResultSetMetaData data = ret.getMetaData();

            while (ret.next()) {//循环读取每一行的数据
                list = new ArrayList<>();
                //每行中有多少列，将每列的数据存入
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    list.add(ret.getString(i));
                }
                ll.add(list);
            }
//           获取ResultSet数据的长度。先将游标移动到最后面，之后最后长度。在将游标还原。
            ret.last(); // 游标移到最后, 获得rs长度
            length = ret.getRow();
            // ret.first(); // 还原游标到rs开头
//            最后不要忘记关闭了。
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ll;
    }

    /**
     * 查询结果中，获取指定行数的内容
     *
     * @param sql
     * @param i
     * @return
     * @throws SQLException
     */
    public String dataMysqlRow(String sql, int i) throws SQLException {
        db1 = new DBHelper(sql);//创建DBHelper对象
        String column = "";
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {//循环读取每一行的数据
                column = ret.getString(i);
                System.out.println("该表的字段的内容为：" + ret.getString(i));
            }//显示数据
//            最后不要忘记关闭了。
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return column;
    }


    /**
     * 返回该查询语句查询之后的内容长度。。
     *
     * @param sql
     * @return
     */
    public int dataLength(String sql) {
        db1 = new DBHelper(sql);//创建DBHelper对象
        int length = 0;
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
//           获取ResultSet数据的长度。先将游标移动到最后面，之后最后长度。在将游标还原。
            ret.last(); // 游标移到最后, 获得rs长度
            length = ret.getRow();
            // ret.first(); // 还原游标到rs开头
            System.out.println("该表的长度为：" + length);
//            最后不要忘记关闭了。
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return length;
    }
}