package wap.business.example.ligrco.Exhibition.Grouping.delect;

import common.tool.SystemOut;
import common.tool.caninput.Preservation;
import org.openqa.selenium.WebDriver;
import wap.business.example.ligrco.Exhibition.Grouping.Operating;

import java.io.IOException;


/**
 * Created by 70486 on 2017/6/13 on 23:16.
 */
public class ExecutionDelect extends Operating {
    
    
    /**
     * 如果选择删除的话，就通过这个方法进行区分数据。
     * 并打印出对话框的内容
     */
    public void executionJudgment(String str) {
        if (str.equals(tr)) {
            SystemOut.getStringOut("商品分组执行点击确定按钮", str);
            String alert = new Preservation().alertSystem(true);
            SystemOut.getStringOut("系统对话框的标题是", alert);
        } else if (str.equals(fa)) {
            SystemOut.getStringOut("商品分组执行点击取消按钮", str);
            String alert =  new Preservation().alertSystem(false);
            SystemOut.getStringOut("系统对话框的标题是", alert);
        } else {
            SystemOut.getStringOut("商品分组执行出现第三方", str);
        }
    }

    public void delectGroup(WebDriver driver) throws InterruptedException, IOException {
        //        点击修改按钮
        modifyEdit(driver,3);
    }
}
