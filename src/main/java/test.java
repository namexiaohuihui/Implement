import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Created by ${XiaoHuiHui} on 2017/7/17 on 14:36.
 * XiaoHiiHui [704866169@qq.com]
 */
public class test {


    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://192.168.1.101:3306/xiao_ph?autoReconnect=true&useSSL=false";
            String user = "xiaohuihui";
            String pass = "xiaohuihui";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            System.out.println("找不到类");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("语句");
            e.printStackTrace();
        }
        return conn;
    }

    public Connection connectionSql(){
         String name = "com.mysql.jdbc.Driver";
         String url = "jdbc:mysql://192.168.1.101/xiao_ph?autoReconnect=true&useSSL=false";
         String user = "xiaohuihui";
         String password = "xiaohuihui";

        Connection conn = null;
         PreparedStatement pst = null;
        String sql = "select * from xiao_ph.ph_user order by id ;";
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (ClassNotFoundException e) {
            System.out.println("找不到类");
            e.printStackTrace();
        } catch (SQLException e) {
           // System.out.println("语句");
            e.printStackTrace();
        }

        return conn;
    }

    @Test
    public  void execute() {
        String sql = "select * from xiao_ph.ph_user order by id ;";
        Connection conn = connectionSql();
        // new DBHelper(sql);
        System.out.println("运行");
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
        } catch (Exception e) {
        }
    }



}
