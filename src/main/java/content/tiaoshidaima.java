package content;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import common.tool.SystemOut;
import common.tool.mysqls.DBHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import wap.business.example.bean.UserBean;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 70486 on 2017/9/13 on 22:58.
 */
public class tiaoshidaima {

    private String sql = "SELECT grade,delivery_min AS 'deliveryMin' FROM lnsm_shop WHERE id = 10175";

    List<UserBean> list;
    @Test
    public void startKasishi() throws SQLException {
        DBHelper dbHelper = new DBHelper();

        String[] mysqlData = new String[]{
                "jdbc:mysql://192.168.10.204/lnlife_4?autoReconnect=true&useSSL=false",
                "com.mysql.jdbc.Driver", "root", "123456"};
        dbHelper.url = mysqlData[0];
        dbHelper.name = mysqlData[1];
        dbHelper.user = mysqlData[2];
        dbHelper.password = mysqlData[3];

        dbHelper.dbhelperStart(sql);

        ResultSet ret = dbHelper.pst.executeQuery();

        JSONArray jsonArray = new JSONArray();

        while(ret.next()){
         //   SystemOut.getStringOut("-----------------------------------------");
            ResultSetMetaData re = ret.getMetaData();
         //   System.out.println("该行中，该列的长度：" + re.getColumnCount());

            JSONObject jsonObject = new JSONObject();

            for (int i = 1; i <= re.getColumnCount(); i++) {
                jsonObject.put(re.getColumnName(i),ret.getString(i));

                SystemOut.getStringOut("指定内容" +ret.getString(i),
                        "指定列名" + re.getColumnName(i)+ ":" + re.getColumnClassName(i));

            }

            jsonArray.put(jsonObject);
        }
        SystemOut.getStringOut(jsonArray.toString());

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(jsonArray.toString()));
        reader.setLenient(true);
        list = gson.fromJson(reader, new TypeToken<List<UserBean>>() {}.getType());

        for (int i = 0 ; i<list.size();i++){
            SystemOut.getStringOut(list.get(i).toString());
        }
    }

    @Test
    public void qiegeshujndgfs() {
        String str = "<span style=\"color:#E56600;\">自</span>提<span style=\"color:#E53333;\">颜</span>色，" +
                "<span style=\"color:#EE33EE;\">这都</span>过<span style=\"color:#FFE500;\">长了</span>？？" +
                "<span style=\"background-color:#FFFFFF;\">、" +
                "</span><span style=\"font-size:16px;background-color:#FFFFFF;color:#FFE500;\">不带这样<strong>" +
                "的还能不能好</strong><span style=\"color:#000000;\">好玩耍了。107.807873 &nbsp;&nbsp;23.179698有提示吗？" +
                "</span></span></span></span></span><br />";

        String ings = "[^\\x00-\\xff]|\\d{0,9}\\.\\d{0,9}";

        Pattern p = Pattern.compile(ings);
        Matcher matcher = p.matcher(str);

        String string  = "";
        while(matcher.find()){
            string =string+matcher.group();
            System.out.print(matcher.group());
        }


    }
}
