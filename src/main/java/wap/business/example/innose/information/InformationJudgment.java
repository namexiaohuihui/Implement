package wap.business.example.innose.information;

import common.tool.BeanToMapUtil;
import common.tool.mysqls.MysqlInquire;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by 70486 on 2017/8/3 on 23:24.
 */
public class InformationJudgment {

    private void judgmentInformation() {
        MysqlInquire mi = new MysqlInquire();
        String sql = "";
        Map<Integer, List> integerListMap = mi.dataMysqlAll(sql);
        try {
           // BeanToMapUtil.mapToObject2(integerListMap,Class.forName("11"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
