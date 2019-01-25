package toolskit.tools.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 将日期类的时间转换成时间戳
 * Created by ${XiaoHuiHui} on 2017/6/16 on 15:59.
 * XiaoHiiHui [704866169@qq.com]
 */
public class DateConversionTime {


    /**
     * 获取集合当中，时间最大的所在位置
     * @param listTime
     * @return
     */
    public int conversionTime(List<String> listTime) {
        long max = 0;//记录最大的时间
        int number = 0;//记录最大时间出现的位置
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt;
            String date;
            long ts1;
            for (int i = 1; i < listTime.size() - 1; i++) {
                date = listTime.get(i);
                dt = sdf.parse(date);
                ts1 = dt.getTime();
                System.out.println(date + ": " + ts1);
                if (ts1 > max) {
                    max = ts1;
                    number = i;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * 将集合中的日期全部转换成时间戳
     * @param listTime
     * @return
     */
    public List<Long> conversionTimes(List<String> listTime) {
        List<Long> list = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dt;
            String date;
            long ts1;
            for (int i = 1; i < listTime.size() - 1; i++) {
                date = listTime.get(i);
                dt = sdf.parse(date);
                ts1 = dt.getTime();
                System.out.println(date + ": " + ts1);
                list.add(ts1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将单个日期转换成时间戳
     * @param date
     * @return
     */
    public long conversionTime(String date) {
        Date dt = null;
        long ts = 0L;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dt = sdf.parse(date);
            ts = dt.getTime();
            System.out.println(date + ": " + ts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ts;
    }


}
