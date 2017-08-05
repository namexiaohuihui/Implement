package wap.business.example.innose.information;

import common.tool.caninput.InfoSelect;
import org.openqa.selenium.By;

/**
 * Created by 70486 on 2017/8/3 on 23:23.
 */
public class InformationSet extends StoreInformation{

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
