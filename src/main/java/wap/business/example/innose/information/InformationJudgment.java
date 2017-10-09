package wap.business.example.innose.information;

import common.tool.SystemOut;
import wap.business.example.bean.EnumProgramBean;

import java.sql.SQLException;

/**
 * 设置过店铺信息的类判断...
 * Created by 70486 on 2017/8/3 on 23:24.
 */
public class InformationJudgment {

    EnumProgramBean epb;

    public InformationJudgment(EnumProgramBean epb) {
        this.epb = epb;
    }

    protected void judgmentInformation(){
        switch (epb.getFour()) {
            case "名字":
                break;

            case "中心":
                break;

            case "品类":
                break;

            case "LOGO":
                break;

            case "执照":
                break;

            case "实拍":
                break;

            case "联系":
                break;

            case "地址":
                break;

            case "位置":
                break;

            case "经纬度":
                break;

            case "介绍":
                break;

            default:
                SystemOut.getStringOut("没有这个内容数据" + epb.getFour());
                break;
        }
    }
}
