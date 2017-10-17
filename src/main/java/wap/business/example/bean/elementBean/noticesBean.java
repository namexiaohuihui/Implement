package wap.business.example.bean.elementBean;

/**
 * Created by 70486 on 2017/10/17 on 21:19.
 */
public class noticesBean {

    //公告的输入
    public static String content = ".input-sm.colwidth4";

    //有效开始时间
    public static String startTime = "input[id='start_time'][name='start_time']";

    //有效结束时间
    public static String endTime = "input[id='end_time'][name='end_time']";

    //按钮
    public static String noticesave = ".btn.btn-primary.noticesave";

    //提示信息
    public static String successMessage = ".successMessage";


    //    保存之后的提示
    public static String promptError = "开始时间必须小于结束时间";
    public static String promptCorrect = "保存成功，您的公告将在店铺主页显示";

}
