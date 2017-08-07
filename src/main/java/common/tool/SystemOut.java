package common.tool;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Animation on 2017/4/12 on 21:29.
 *
 * @AUTHOR XiaoHuiHui {704866169@qq.com}
 */
public class SystemOut {


    public static void getStringOut(String data, String text) {
        System.out.println(data + ":" + text);
    }

    public static void getStringOut(String status, String data, String text) {
        System.out.println(status + ":" + data + ":" + text);
    }

    public static void getStringOut(String text) {
        System.out.println(text);
    }

    /**
     * @param li
     */
    public static void getStringOut(List<List> li) throws InterruptedException {
        getStringOut("打印list开始" + li.size());
        for (int i = 0; i < li.size(); i++) {
            SystemOut.getStringOut(li.get(i).toString());
        }
    }


    public static void getStringOut(Map<String, String> aMap) throws InterruptedException {
        getStringOut("打印list开始" + aMap.size());
        Iterator<Map.Entry<String, String>> iterator = aMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println("Key = " + next.getKey() + ", Value = " + next.getValue());
        }
    }
}
