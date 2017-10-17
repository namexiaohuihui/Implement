package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.conversion.TimeConversionDate;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.bean.elementBean.noticesBean;

import java.util.Date;

/**
 * 验证和修改部分
 * Created by 70486 on 2017/10/17 on 22:07.
 */
public class noticesModifyJudge extends ShopNotices{

    //修改
    protected void modifyNotices(EnumProgramBean caseBean){
        switch (caseBean.getFour()) {
            case "公告":

                //读取数据库和界面的内容进行判断
                String contentMysql = NoticesStatic.bean.getContent();
                String contentLoad = noticesBean.content;

                //需要打印的日志
                String contentMessage = caseBean.getZero() + "进行验证";

                //输入的信息
                String contentParameter = new Date() + "+" + caseBean.getZero();

                super.elementInput(contentMysql,contentLoad, contentParameter,contentMessage);
                break;
            case "开始":

                String startTimeMysql = NoticesStatic.bean.getStartTime();
                String startTimeLoad = noticesBean.startTime;

                //需要打印的日志
                String startTimeMessage = caseBean.getZero() + "进行验证";

                //输入的信息;将当前时间戳进行装换
                String startTimeParameter = TimeConversionDate.stringToday();

                super.elementInput(startTimeMysql,startTimeLoad, startTimeParameter,startTimeMessage);
                break;
            case "结束":

                String endTime = NoticesStatic.bean.getEndTime();
                String endTimeLoad = noticesBean.startTime;
                //需要打印的日志
                String endTimeMessage = caseBean.getZero() + "进行验证";

                //输入的信息;将当前时间戳进行装换
                String endTimeParameter = TimeConversionDate.stringToday();

                super.elementInput(endTime,endTimeLoad, caseBean.getFive(), caseBean.getZero());
                break;

            default:

                String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
                String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                SystemOut.getStringOut(caseBean.getFour(),clazz,method);
                break;
        }
    }

    //验证
    protected void judgeNotices(EnumProgramBean caseBean){

        switch (caseBean.getFour()) {
            case "公告":

                //读取数据库和界面的内容进行判断
                String contentMysql = NoticesStatic.bean.getContent();
                String contentLoad = noticesBean.content;

                //需要打印的日志
                String contentMessage = caseBean.getZero() + "进行验证";
                judgmentParameterAttribute(contentMysql, contentLoad, "value", contentMessage);

                break;

            case "开始":

                String startTimeMysql = NoticesStatic.bean.getStartTime();
                String startTimeLoad = noticesBean.startTime;
                //需要打印的日志
                String startTimeMessage = caseBean.getZero() + "进行验证";
                judgmentParameterAttribute(startTimeMysql, startTimeLoad, "value", startTimeMessage);

                break;

            case "结束":

                String endTime = NoticesStatic.bean.getEndTime();
                String endTimeLoad = noticesBean.startTime;
                //需要打印的日志
                String endTimeMessage = caseBean.getZero() + "进行验证";
                judgmentParameterAttribute(endTime, endTimeLoad, "value", endTimeMessage);

                break;

            default:
                String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
                String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                SystemOut.getStringOut(caseBean.getFour(),clazz,method);

                break;

        }
    }

}
