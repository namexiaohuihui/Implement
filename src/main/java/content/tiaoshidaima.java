package content;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import common.tool.SystemOut;
import common.tool.mysqls.DBHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import wap.business.example.bean.DianpuBean;

import javax.swing.text.html.parser.Entity;
import java.io.StringReader;
import java.sql.*;

/**
 * Created by 70486 on 2017/9/13 on 22:58.
 */
public class tiaoshidaima {


    @Test
    public void startKasishi() throws SQLException {
        DBHelper dbHelper = new DBHelper();

        String[] mysqlData = new String[]{
                "jdbc:mysql://192.168.154.1/ph_exclusive?autoReconnect=true&useSSL=false",
                "com.mysql.jdbc.Driver", "xiaohuihui", "xiaohuihui"};
        dbHelper.url = mysqlData[0];
        dbHelper.name = mysqlData[1];
        dbHelper.user = mysqlData[2];
        dbHelper.password = mysqlData[3];

        dbHelper.dbhelperStart("select * from ph_shop ; ");

        ResultSet ret = dbHelper.pst.executeQuery();

        JSONArray jsonArray = new JSONArray();

        while(ret.next()){
         //   SystemOut.getStringOut("-----------------------------------------");
            ResultSetMetaData re = ret.getMetaData();
         //   System.out.println("该行中，该列的长度：" + re.getColumnCount());

            JSONObject jsonObject = new JSONObject();

            for (int i = 1; i <= re.getColumnCount(); i++) {
                jsonObject.put(re.getColumnName(i),ret.getString(i));

              /*  SystemOut.getStringOut("指定内容" +ret.getString(i),
                        "指定列名" + re.getColumnName(i));*/

            }

            jsonArray.put(jsonObject);
        }
        SystemOut.getStringOut(jsonArray.toString());
        String string =  jsonArray.toString().
                replaceAll("\\[","").
                replaceAll("\\]","");

      //  Gson gson = new Gson();
      //  JsonReader reader = new JsonReader(new StringReader(string));
     //   reader.setLenient(true);
      //  DianpuBean<Entity, String> entity = gson.fromJson(reader, new TypeToken<DianpuBean<Entity, String>>() {}.getType());
      //  return entity;
      //  DianpuBean dianpuBean = gson.fromJson(string, DianpuBean.class);
      //  SystemOut.getStringOut(dianpuBean.toString());
    }
}
