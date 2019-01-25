package toolskit.parameters;

/**
 * 执行语句sql
 * Created by ${XiaoHuiHui} on 2017/7/20 on 17:41.
 * XiaoHiiHui [704866169@qq.com]
 */
public class QueryStatement {



    public String[] getMysqlData() {
        //mysql"jdbc:mysql://192.168.10.204/lnlife_2?autoReconnect=true&useSSL=false&characterEncoding=utf8",
        //"com.mysql.jdbc.Driver", "root", "123456"
       String[] mysqlData = {"jdbc:mysql://127.0.0.1:3306/ph_exclusive?characterEncoding=utf8&autoReconnect=true&useSSL=false",
                "com.mysql.jdbc.Driver", "root", "xiaodingdong"};
       String[] mysqlData2 = {"jdbc:mysql://192.168.10.204:3306/lnlife_2?characterEncoding=utf8&autoReconnect=true&useSSL=false",
                "com.mysql.jdbc.Driver", "root", "123456"};
        return mysqlData;
    }
}
