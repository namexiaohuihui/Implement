package common.tool.conversion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串的操作
 * Created by ${XiaoHuiHui} on 2017/7/17 on 16:26.
 * XiaoHiiHui [704866169@qq.com]
 */
public class CharacterString {
    //使用Integer的parseInt方法
    public int stringToInt(String number) {
        return Integer.parseInt(number);
    }

    //调用Long类型的parseLong方法
    public Long stringToLong(String number) {
        return Long.parseLong(number);
    }

    //调用Long类型的parseLong方法
    public Double stringToDouble(String number) {
        return Double.parseDouble(number);
    }

    /**
     * 获取用例中赋值的内容，根据等号来进行
     *
     * @param str  需要切割的数据
     * @param conn  如果为空时就只根据逗号进行切割
     * @return
     */
    public String[] stringsToString(String str, String conn) {
        String[] strings = str.split(",");
        for (int i = 0; i < strings.length; i++) {
            //strings[i] = strings[i].substring(strings[i].indexOf(conn)+1,strings[i].length());
            if (conn == null || conn.equals("")) {
                break;
            } else {
                strings[i] = strings[i].split(conn)[1];
            }
        }
        return strings;
    }
    /**
     * 提取数字
     *
     * @param phoneString
     * @return
     */
    public int digitalExtract(String phoneString) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(phoneString);
        String all = matcher.replaceAll("");
        return stringToInt(all);
    }

    public String digitalExtractToString(String phoneString) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(phoneString);
        String all = matcher.replaceAll("");
        return all;
    }

    /**
     * 提取价格
     *
     * @param phoneString
     * @return
     */
    public Double priceExtraction(String phoneString) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(phoneString);
        String all = matcher.replaceAll("");
        return stringToDouble(all);
    }

    /**
     * 提取汉字
     *
     * @param phoneString
     * @return
     */
    public String charactersExtraction(String phoneString) {
        Pattern pattern = Pattern.compile("[\\d]");
        Matcher matcher = pattern.matcher(phoneString);
        return matcher.replaceAll("").trim();
    }


    /**
     * 通过内容来找到路径
     * @param load
     * @return
     */
    public  String stringsToString(String load){

        int i = load.lastIndexOf("\\");
        int i1 = load.lastIndexOf(".");
        String sLoad = load.substring(load.lastIndexOf("\\") + 1,load.lastIndexOf("."));

        return sLoad;
    }

}
