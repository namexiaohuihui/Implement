package common.tool.mysqls;

import common.tool.SystemOut;
import common.tool.enumTool.EmployEnum;
import common.tool.informationException.ErrorException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 数据库的操作类：---用法
 * 1.获取数据库链接Connection
 * 2.准备执行语句PreparedStatement
 * 3.执行语句，得到结果集ResultSet
 * Created by Administrator on 2016/11/3.
 */

public class MysqlInquire {
    DBHelper db1 = null;
    ResultSet ret = null;

    /**
     * 通过sql返回查询的内容
     * 返回的内容很多
     *
     * @param sql
     * @return
     */
    public Map<Integer, List> dataMysqlAll(String sql) {
        dbhelperCreate(sql);
        List<String> list = null;
        Map<Integer, List> aMap = new HashMap<>();
        try {
            ResultSetMetaData data = ret.getMetaData();
            while (ret.next()) {//循环读取每一行的数据
                list = new ArrayList<>();
                //每行中有多少列，将每列的数据存入
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    list.add(ret.getString(i));//获取该行中指定列的内容
                }
                aMap.put(ret.getRow(), list);
            }
            dbhelperClose();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return aMap;
    }

    /**
     * 读取每行中指定列的内容
     *
     * @param sql
     * @param i   默认读取第一行的内容
     * @return
     * @throws SQLException
     */
    public Map<String, String> dataMysqlColumnRow(String sql, int i) {
        dbhelperCreate(sql);
        Map<String, String> aMap = new HashMap<>();
        try {
            while (ret.next()) {//循环读取每一行的数据
                //将当前行数以及读取该行中指定列的内容保存到map中。
                aMap.put(EmployEnum.employChineseToEnglish(ret.getRow()), ret.getString(i));
                sleep(1000);
            }//显示数据
            dbhelperClose();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        } catch (InterruptedException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return aMap;
    }


    /**
     * 读取一行中全部列的内容
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public JSONObject dataMysqlColumnAllRow(String sql) {
        ResultSetMetaData rsmd = null;
        try {
            sleep(1000);
            dbhelperCreate(sql);
            sleep(1000);
            rsmd = ret.getMetaData();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultSetTojsonObject(rsmd);
    }

    /**
     * 读取全部行中的数据
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public JSONArray dataMysqlColumnAll(String sql) {
        ResultSetMetaData rsmd = null;
        try {
            sleep(1000);
            dbhelperCreate(sql);
            sleep(1000);
            rsmd = ret.getMetaData();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultSetToJSONArray(rsmd);
    }


    //将数据库的内容转换成json
    private JSONArray resultSetToJSONArray(ResultSetMetaData re) {
        JSONArray jsonArray = new JSONArray();
        try {
            int numBer = re.getColumnCount();
            while (ret.next()) {//循环读取每一行的数据

                JSONObject jsonObject = new JSONObject();

                //通过列名和列内容组成键值对的关系，然后存map中
                for (int i = 1; i <= numBer; i++) {
                    jsonObject.put(re.getColumnName(i), ret.getString(i));
                }
                jsonArray.put(jsonObject);
            }
            //关闭连接
            dbhelperClose();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return jsonArray;
    }

    /**
     * 将数据库的内容转换成jsonObject
     * @param re
     * @return
     */
    private JSONObject resultSetTojsonObject(ResultSetMetaData re) {

        JSONObject jsonObject = new JSONObject();

        try {
            //读取列的数量
            int numBer = re.getColumnCount();
            //SystemOut.getStringOut("数据库中列的长度为" + numBer);
            while (ret.next()) {//循环读取每一行的数据

                //通过列名和列内容组成键值对的关系，然后存map中
                //re.getColumnLabel(i) 获取默认标题：为sql中as后面定义的名字
                //re.re.getColumnName(i) 获取标题：为sql中该字段在表的名字
                for (int i = 1; i <= numBer; i++) {

                    String col = re.getColumnLabel(i);
                    String str = ret.getString(i);
                    jsonObject.put(col,str);
                   // SystemOut.getStringOut(i + "行的数据为" + col + "          " + str);
                   // jsonObject.put(re.getColumnName(i), ret.getString(i));
                }
            }
            //关闭连接
            dbhelperClose();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return jsonObject;
    }



    /**
     * 返回该查询语句查询之后的内容长度。。
     *
     * @param sql
     * @return
     */
    public int dataLength(String sql) {
        dbhelperCreate(sql);
        int length = 0;
        try {
//           获取ResultSet数据的长度。先将游标移动到最后面，之后最后长度。在将游标还原。
            ret.last(); // 游标移到最后, 获得rs长度
            length = ret.getRow();
            // ret.first(); // 还原游标到rs开头
            System.out.println("该表的长度为：" + length);
            dbhelperClose();
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
        return length;
    }



    //创建数据库连接
    private void dbhelperCreate(String sql) {
        db1 = new DBHelper();//创建DBHelper对象
        db1.dbhelperMerge(sql);
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
    }

    // 最后不要忘记关闭了。
    private void dbhelperClose() {
        try {
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        }
    }
}