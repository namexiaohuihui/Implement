package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.caninput.InfoSelect;
import org.openqa.selenium.By;
import wap.business.example.bean.EnumProgramBean;

/**
 * 数据类的设置
 * Created by 70486 on 2017/8/3 on 23:23.
 */
public class InformationModify extends StoreInformation{

    EnumProgramBean epb;


    public InformationModify(EnumProgramBean epb) {
        this.epb = epb;
    }

    protected void modifyInformation(){
        switch (epb.getFour()) {
            case "名字":
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

    public void capitalProvincialIndex(int number){
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryIndex(By.cssSelector(super.provinceSele),number);
    }
    public void capitalProvincialValue(String content,String sql){
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryValue(By.cssSelector(super.citySele),content,sql);
    }
    public void capitalProvincialText(String content){
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryText(By.cssSelector(super.countySele),content);
    }
}
