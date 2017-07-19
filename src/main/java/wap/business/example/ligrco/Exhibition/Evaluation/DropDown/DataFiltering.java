package wap.business.example.ligrco.Exhibition.Evaluation.DropDown;

import common.FoxDriver;
import common.tool.caninput.InfoSelect;
import common.tool.caninput.Preservation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * select数据筛选
 * Created by 70486 on 2017/6/12 on 20:54.
 */
public class DataFiltering {

    private String gradeload;

    private String replyload;

    private String buttonload;

    private WebDriver driver = FoxDriver.getWebDrivaer();

    InfoSelect infoSelect = new InfoSelect();
    // -------------------------构造方法------------------------
    public DataFiltering() {
    }

    /**
     * 评价分数的选择
     * @param date 数据
     */
    public void gradeSelcect(String grade){

        By by = By.cssSelector(gradeload);
        infoSelect.categoryValue(by,gradeload);

    }

    /**
     * 是否回复的选择
     * @param date 数据
     */
    public void replySelcect(String reply){
        By by = By.cssSelector(replyload);
        infoSelect.categoryValue(by,reply);
    }

    public void selectFreed(String grade,String reply){

        By bygrade = By.cssSelector(replyload);
        By byreply = By.cssSelector(replyload);
        infoSelect.categoryValue(bygrade,reply);
        infoSelect.categoryValue(byreply,reply);
    }

    //按钮
    public void buttonClick() throws InterruptedException {
        new Preservation().buttonCssSelector(buttonload);
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
