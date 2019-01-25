package toolskit.tools.enumTool;

/**
 * 枚举使用类、
 * Created by ${XiaoHuiHui} on 2017/8/7 on 17:27.
 * XiaoHiiHui [704866169@qq.com]
 */
public class EmployEnum {

    public static String employChineseToEnglish(int number) {
        String enumName = "";
        //遍历所有的枚举
        for (ChineseToEnglish cte : ChineseToEnglish.values()) {
            if (cte.getIndex() == number) {
                enumName = cte.getName();
                break;
            }
        }
        return enumName;
    }
}
