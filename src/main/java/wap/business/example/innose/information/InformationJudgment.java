package wap.business.example.innose.information;

import common.tool.mysqls.MysqlInquire;

import java.sql.SQLException;

/**
 * Created by 70486 on 2017/8/3 on 23:24.
 */
public class InformationJudgment {
   private  void kk(){
       MysqlInquire mi = new MysqlInquire();
       String sql = "";
       try {
           String s = mi.dataMysqlRow(sql, 1);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}
