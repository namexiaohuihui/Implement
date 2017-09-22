package common.tool.mysqls;

import common.parameter.QueryStatement;
import common.tool.SystemOut;
import org.junit.Test;

import java.sql.*;

/**
 * Created by Administrator on 2016/11/3.
 */

public class DBHelper {
    public String url ;
    public String name;
    public String user;
    public String password;

    public Connection conn = null;
    public PreparedStatement pst = null;

    public void dbhelperStart(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dbhelperMerge(String sql){
        startData();
        dbhelperStart(sql);
    }

    public void startData() {
        QueryStatement qs = new QueryStatement();
        String[] start = qs.getMysqlData();
        url = start[0];
        name = start[1];
        user = start[2];
        password = start[3];
        //SystemOut.getStringOut("url:" + url +"     name:"+ name + "    user:"+user + "     password:"+password);
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
