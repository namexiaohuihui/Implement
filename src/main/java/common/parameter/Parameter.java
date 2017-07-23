package common.parameter;

/**
 * 程序运行所需要的参数.
 * Created by Administrator on 2016/12/26.
 */
public class Parameter {
    //家的门号、口令以及于暗号
    private String accountFamily = "23";
    private String passWordFamily = "12";
    private String supplyPassWordFamily = "33";
    private int groupSun;//桌子的数量

    //头头的门号以及口令
    private String accountTop = "--";
    private String passWordTop = "//";

    //开始的门号以及口令
    private String accountStart = "---";
    private String passWordStrat = "//";

    //mysql
    private String[] mysqlData = new String[]{"jdbc:mysql://lo/aa?autoReconnect=true&useSSL=false", "com.mysql.jdbc.Driver", "root", "22"};

    //家的照片
    String photoFamily[] = new String[]{"photo1.exe", "photo2.exe", "photo3.exe", "photo4.exe", "photo5.exe"};
    String licenseFamily[] = new String[]{"license1.exe", "license2.exe"};


    public String getAccountFamily() {
        return accountFamily;
    }

    public String getPassWordFamily() {
        return passWordFamily;
    }

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

    public String[] getMysqlData() {
        return mysqlData;
    }
}
