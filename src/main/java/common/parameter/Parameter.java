package common.parameter;

/**
 * 程序运行所需要的参数.
 * Created by Administrator on 2016/12/26.
 */
public class Parameter {
    //    登录的账号
    private String account = "111";
    //    登录的密码
    private String passWord = "123456";
    //订货的密码
    private String supplyPassWord = "33";

    //    登录的账号
    private String bossAccount = "panhui";
    //    登录的密码
    private String bossPassWord = "123456";

    //    商品总数的计算
    private int releaseSun;
    //    店铺分组的计算
    private int groupSun;

    public String getAccount() {
        return account;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getSupplyPassWord() {
        return supplyPassWord;
    }

    public String getBossAccount() {
        return bossAccount;
    }

    public String getBossPassWord() {
        return bossPassWord;
    }

    public int getReleaseSun() {
        return releaseSun;
    }

    public int getGroupSun() {
        return groupSun;
    }
}
