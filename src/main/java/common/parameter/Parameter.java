package common.parameter;

/**
 * 程序运行所需要的参数.
 * Created by Administrator on 2016/12/26.
 */
public class Parameter {

    private String supplyPassWordFamily = "33";
    private int groupSun;//分组的数量

    //文档存放位置
    private  String load = "E:\\drivers\\CasePlan\\测试计划.xlsx";

    //大佬的入口
    private String accountTop = "--";
    private String passWordTop = "//";

    //老板的入口
    private String accountStart = "---";
    private String passWordStrat = "//";

    //一级菜单
    String oneLevel[] = {""};

    //家的照片
    String photoFamily[] = new String[]{"photo1.exe", "photo2.exe", "photo3.exe", "photo4.exe", "photo5.exe"};
    String licenseFamily[] = new String[]{"license1.exe", "license2.exe"};


    public String getSupplyPassWordFamily() {
        return supplyPassWordFamily;
    }

    public int getGroupSun() {
        return groupSun;
    }

    public String getAccountTop() {
        return accountTop;
    }

    public String getPassWordTop() {
        return passWordTop;
    }

    public String getAccountStart() {
        return accountStart;
    }

    public String getPassWordStrat() {
        return passWordStrat;
    }

    public String[] getPhotoFamily() {
        return photoFamily;
    }

    public String[] getLicenseFamily() {
        return licenseFamily;
    }

    public String[] getOneLevel() {
        return oneLevel;
    }

    public String getLoad() {
        return load;
    }
}
