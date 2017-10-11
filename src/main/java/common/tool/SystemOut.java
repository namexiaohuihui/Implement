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
     * 用例成功,并且编辑成功
     *
     * @param massage   用例编号
     * @param parameter 用例输入的内容
     */
    public static void caseSuccess(String massage, String parameter) {
        System.out.println(massage + "用例执行成功，编辑内容为：" + parameter);
    }

    /**
     * 编辑判断成功
     *
     * @param massage 执行编号
     */
    public static void caseEditSuccess(String massage) {
        System.out.println(massage + "用例中的元素对象不需要编辑,程序判断成功。。");
    }

    /**
     * 用例执行成功
     *
     * @param massage 执行编号
     */
    public static void caseSuccess(String massage) {
        System.out.println(massage + "用例执行成功");
    }


    /**
     * 用例执行失败，并且打印出输入信息
     *
     * @param massage
     * @param parameter
     */
    public static void caseFail(String massage) {
        System.out.println(massage + "用例执行失败。。。。");
    }

    /**
     * 不需要进行编辑时发生失败
     *
     * @param massage   用例编号
     * @param parameter 用例输入的内容
     */
    public static void caseEditFail(String massage) {
        System.out.println(massage + "用例中的元素对象不需要编辑，程序判断失败。");
    }

    public static void caseFail(String massage, String parameter) {
        System.out.println(massage + "用例执行失败，编辑内容为：" + parameter);
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
