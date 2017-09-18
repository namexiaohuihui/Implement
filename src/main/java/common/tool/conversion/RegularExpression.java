package common.tool.conversion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${XiaoHuiHui} on 2017/9/18 on 15:12.
 * XiaoHiiHui [704866169@qq.com]
 */
public class RegularExpression {


    /**
     * 内置正则只需要传入字符串
     * @param strData
     * @return
     */
    public static String regularExpression(String strData) {

        //正则表达
        String regular = "[^\\x00-\\xff]|\\d{0,9}\\.\\d{0,9}";

        //正则执行
        Pattern p = Pattern.compile(regular);

        //转换
        Matcher matcher = p.matcher(strData);

        //定义一个字符串来存储新的内容
        String str = "";

        //循环
        while (matcher.find()) {
            str = str + matcher.group();
        }
        return str;
    }

    /**
     * 正则和字符串都要自己传入
     * @param strData
     * @param regular
     * @return
     */
    public static String regularExpression(String strData,String regular) {

        //正则执行
        Pattern p = Pattern.compile(regular);

        //转换
        Matcher matcher = p.matcher(strData);

        //定义一个字符串来存储新的内容
        String str = "";

        //循环
        while (matcher.find()) {
            str = str + matcher.group();
        }
        return str;
    }
}
