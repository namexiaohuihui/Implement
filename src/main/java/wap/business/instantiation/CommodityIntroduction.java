package wap.business.instantiation;


import java.util.Arrays;
import java.util.List;

/**
 * 这是发布商品时，通过excel文档读取新商品的操作内容。。。。
 * Created by ${XiaoHuiHui} on 2017/2/22.
 * XiaoHiiHui [704866169@qq.com]
 */
public class CommodityIntroduction {
    /*--------------------------------改类的参数--------------------------*/
    //    商品主类目
    private String firstCid;

    //    商品子类目的设置
    private String secondCid;

    //    商品分组的选择
    private String[] checkbox;

    //    管理分组
    private String blank;

    //    分组展示店铺首页刷新按钮
    private String groupinfo;

    //    商品名称的设置
    private String productname;

    //    商品的价格.因为价格可能为小数，所以需要设置为精度型
    private String price;

    //    商品的图片名称.在项目AutoPicture里面读取，可能上传多张图片
    private String[] logo;

    //    商品详情描述.通过项目BigDataFo;e里面记事本名来读取数据并输入到输入框中
    private String content;

    //    预览按钮的点击
    private String preview;

    //    保存按钮的点击
    private String preservation;


    /*--------------------------------该类的构造方法------------------------*/
    public CommodityIntroduction() {

    }

    public CommodityIntroduction(String firstCid, String secondCid, String[] checkbox,
                                 String blank, String groupinfo, String productname,
                                 String price, String[] logo, String content, String preview,
                                 String preservation) {
        this.firstCid = firstCid;
        this.secondCid = secondCid;
        this.checkbox = checkbox;
        this.blank = blank;
        this.groupinfo = groupinfo;
        this.productname = productname;
        this.price = price;
        this.logo = logo;
        this.content = content;
        this.preview = preview;
        this.preservation = preservation;
    }

    /*-------------------------------------以下为参数的get和set方法----------------------*/
    public String getFirstCid() {
        return firstCid;
    }

    public void setFirstCid(String firstCid) {
        this.firstCid = firstCid;
    }

    public String getSecondCid() {
        return secondCid;
    }

    public void setSecondCid(String secondCid) {
        this.secondCid = secondCid;
    }

    public String[] getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String[] checkbox) {
        this.checkbox = checkbox;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    public String getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(String groupinfo) {
        this.groupinfo = groupinfo;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String[] getLogo() {
        return logo;
    }

    public void setLogo(String[] logo) {
        this.logo = logo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPreservation() {
        return preservation;
    }

    public void setPreservation(String preservation) {
        this.preservation = preservation;
    }


    /* ----------------该类的tostring----------------*/
    @Override
    public String toString() {
        return "CommodityIntroduction{" +
                "主类目='" + firstCid + '\'' +
                ", 子类目='" + secondCid + '\'' +
                ", 商品分组='" + Arrays.toString(checkbox) + '\'' +
                ", 管理分组按钮=" + blank +
                ", 分组刷新按钮='" + groupinfo + '\'' +
                ", 商品名称='" + productname + '\'' +
                ", 价格='" + price + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", 描述='" + content + '\'' +
                ", 预览='" + preview + '\'' +
                ", 保存='" + preservation + '\'' +
                '}';
    }

}
