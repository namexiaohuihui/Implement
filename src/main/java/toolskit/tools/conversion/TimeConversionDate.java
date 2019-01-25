package toolskit.tools.conversion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 将时间戳转换成日期类的时间
 * Created by ${XiaoHuiHui} on 2017/6/16 on 15:59.
 * XiaoHiiHui [704866169@qq.com]
 */
public class TimeConversionDate {

    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format  转换成日期的格式
     * @return
     */
    public static String timeStampDate(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if (format == null || format.isEmpty()) {//默认格式
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    //返回当前时间
    public  static String  stringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static void testDate(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        System.out.println(hehe);

        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);//获取年份
        int month=ca.get(Calendar.MONTH);//获取月份
        int day=ca.get(Calendar.DATE);//获取日
        int hour=ca.get(Calendar.HOUR);//小时
        int minute=ca.get(Calendar.MINUTE);//分
        int second=ca.get(Calendar.SECOND);//秒
        int WeekOfYear = ca.get(Calendar.DAY_OF_WEEK); //星期
        System.out.println(year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String time = format.format(calendar.getTime());
        System.out.println(time);
    }
}
