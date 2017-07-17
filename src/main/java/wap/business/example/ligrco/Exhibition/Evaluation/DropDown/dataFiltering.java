package wap.business.example.ligrco.Exhibition.Evaluation.DropDown;

import LnsmInitialize.FoxDriver;
import LnsmUitl.LnsmPreservation;
import LnsmUitl.InfoSelect;
import org.openqa.selenium.WebDriver;

/**
 * select数据筛选
 * Created by 70486 on 2017/6/12 on 20:54.
 */
public class dataFiltering {

    private String gradeload;

    private String replyload;

    private String buttonload;

    private WebDriver driver = FoxDriver.getWebDrivaer();

    // -------------------------构造方法------------------------
    public dataFiltering() {
    }

    /**
     * 评价分数的选择
     * @param date 数据
     */
    public void gradeSelcect(String grade){

        new InfoSelect().getGoodsStatus(driver, gradeload, grade);
    }

    /**
     * 是否回复的选择
     * @param date 数据
     */
    public void replySelcect(String reply){
        new InfoSelect().getGoodsStatus(driver, replyload, reply);
    }

    public void selectFreed(String grade,String reply){
        new InfoSelect().getGoodsStatus(driver, gradeload, grade);
        new InfoSelect().getGoodsStatus(driver, replyload, reply);
    }

    //按钮
    public void buttonClick(){
        LnsmPreservation.getButtonCssSelector(buttonload);
    }

    // -------------------------get和set方法----------------------

    public String getGradeload() {
        return gradeload;
    }

    public void setGradeload(String gradeload) {
        this.gradeload = gradeload;
    }

    public String getReplyload() {
        return replyload;
    }

    public void setReplyload(String replyload) {
        this.replyload = replyload;
    }

    public String getButtonload() {
        return buttonload;
    }

    public void setButtonload(String buttonload) {
        this.buttonload = buttonload;
    }
}
