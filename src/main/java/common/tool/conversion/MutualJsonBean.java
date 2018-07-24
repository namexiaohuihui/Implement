package common.tool.conversion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${XiaoHuiHui} on 2017/8/7 on 11:55.
 * XiaoHiiHui [704866169@qq.com]
 */
public class MutualJsonBean<T> {

    public Object jsonToBean(String resJson, Object oj) {
        //一般情况下，我们从服务器返回来的数据是json数据，所以我们需要把它转换成bean对应的实体类
        //这里有一点要注意的是，我们进行一般的转换，需要json和bean的属性数量和属性类型和名称都要对应完全一致
        //否则可能会报错
        Gson gson = new Gson();
        Object gb = gson.fromJson(resJson, Object.class);//将json数据转换成user类实体
        return gb;
    }

    public String beanTojson() {
        Gson gson = new Gson();
        String strUser1 = gson.toJson("将bean数据转换成json数据");//将bean数据转换成json数据
        return strUser1;
    }

    public Object limitJsonToBean(String resJson) {
        //这里还有另外一种情况，我们注意到在name里面有一个@Expose修饰词，而age里面没有
        //因为这里考虑到这种情况：我们并不需要将所有的数据转换成json活着bean，只需要部分转换就够了
        //我们就需要用到这个属性，以及下面的代码
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();// 不转换没有 @Expose 注解的字段
        Gson gs = gsonBuilder.create();
        Object gb = gs.fromJson(resJson, Object.class);
        return gb;
    }

    /**
     * 获取的数据全部转换成json，没有树状
     *
     * @param rs
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public String resultSetToJson(ResultSet rs) throws SQLException, JSONException {
        // json数组
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.put(jsonObj);
        }
        return array.toString();
    }

    /**
     * 传入结果集合类对象，将读取后的对象存入list中
     *
     * @param rs
     * @param t
     * @return
     * @throws SQLException
     * @throws JSONException
     */
    public List<T> resultSetToJson(ResultSet rs, T t) throws SQLException, JSONException {
        List<T> list = new ArrayList<>();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            Gson gson = new Gson();
            t = gson.fromJson(jsonObj.toString(), (Type) t.getClass());
            list.add(t);
        }
        return list;
    }
}
