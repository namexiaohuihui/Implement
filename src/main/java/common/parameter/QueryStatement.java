package common.parameter;

/**
 * 执行语句sql
 * Created by ${XiaoHuiHui} on 2017/7/20 on 17:41.
 * XiaoHiiHui [704866169@qq.com]
 */
public class QueryStatement {

    //mysql
    private String[] mysqlData = new String[]{"jdbc:mysql://lo/aa?autoReconnect=true&useSSL=false", "com.mysql.jdbc.Driver", "root", "22"};


    public String[] getMysqlData() {
        return mysqlData;
    }
}
