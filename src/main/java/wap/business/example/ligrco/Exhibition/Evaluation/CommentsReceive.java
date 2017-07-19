package wap.business.example.ligrco.Exhibition.Evaluation;

import LnsmInitialize.FoxDriver;
import LnsmOperation.CommodityOperation.Comments;
import wap.business.example.ligrco.ExhibitionShow.Evaluation.DropDown.dataFiltering;
import org.openqa.selenium.WebDriver;

/**
 * 商品管理-->收到评论：
 * 评价内容的核对
 * 1. 数据筛选是否能使用
 * 2.分页数量以及总数、翻页按钮的个数
 * 3.是否能进行回复/回复过的是否用回复按钮
 * Created by XiaoHuiHui on 2016/12/27.
 */
public class CommentsReceive extends Comments {

    private WebDriver driver = FoxDriver.getWebDrivaer();

    public CommentsReceive(String url) {
        super(url);
        entranceEvaluation();
    }


    /**
     * 评价页面的入口。。
     */
    private void entranceEvaluation() {

    }

    /**
     * select数据筛选
     */
    private void dataDropDown() {

        //数据准备

        //创建对象执行用例
        dataFiltering filtering = new dataFiltering();
        filtering.setGradeload("select[id=grade][name=grade]");
        filtering.setReplyload("select[id=reply][name=reply]");
        filtering.setButtonload("class.btn.btn-xs.btn-danger.btn-search");


        //评分的调用
        filtering.gradeSelcect("一星");

        //回复状态的调用
        filtering.replySelcect("已回复");

        //筛选之后按钮的点击
        filtering.buttonClick();

        //释放select筛选条件的内容。。
        filtering.selectFreed("全部评论","选择类型");
        filtering.buttonClick();
    }

    /**
     * 商家回复按钮的判断
     */
    private void dateReply() {

    }

    /**
     * 评价页面数据的对比
     */
    private void EvaluationPage() {

    }

}
