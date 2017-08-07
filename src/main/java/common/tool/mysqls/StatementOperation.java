package common.tool.mysqls;


import java.util.List;
import java.util.Map;

/**
 * 查询语句的起始点
 * Created by ${XiaoHuiHui} on 2017/6/16 on 17:01.
 * XiaoHiiHui [704866169@qq.com]
 */
public class StatementOperation {

    StatementOperation so;

    public StatementOperation StatementOperation() {
        if (so == null) {
            so = new StatementOperation();
        }
        return so;
    }


    public String statementInquire(String content, String structure, String condition, String sorting) {
        String sql = null;

        if (condition == null || condition.equals(null)) {
            sql = "select " + content + " from " + structure;
        } else {
            sql = "select " + content + " from " + structure + " where " + condition;
        }

        if (sorting == null || sorting.equals(null)) {
            sql = sql + sorting + ";";
        } else {
            sql = sql + sorting + " ;";
        }
        return sql;
    }

    public Map<Integer, List> dataMysql(String content, String structure, String condition, String sorting) throws InterruptedException {
        MysqlInquire inquire = new MysqlInquire();//创建数据库对象
        String sql = statementInquire(content, structure, condition, sorting);//创建查询语句
        Map<Integer, List> integerListMap = inquire.dataMysqlAll(sql);
        return integerListMap;//返回查询结果
    }
}
