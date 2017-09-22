package common.parameter;

/**
 * 程序运行所需要的参数.
 * Created by Administrator on 2016/12/26.
 */
public class Parameter {

    public static String supplyPassWordFamily = "33";
    public static int groupSun;//分组的数量

    //文档存放位置
    public static  String load = "E:\\drivers\\CasePlan\\测试计划.xlsx";

    //大佬的入口
    public static String accountTop = "--";
    public static String passWordTop = "//";

    //老板的入口
    public static String accountStart = "---";
    public static String passWordStrat = "//";

    public static Parameter parameter ;

    //家的照片
    public static String photoFamily[] = new String[]{"photo1.exe", "photo2.exe", "photo3.exe", "photo4.exe", "photo5.exe"};
    public static String licenseFamily[] = new String[]{"license1.exe", "license2.exe"};

    public static Parameter getParameter(){
        if (parameter==null){
            parameter = new Parameter();
        }
        return parameter;
    }
}
