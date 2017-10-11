package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.caninput.InfoSelect;
import common.tool.conversion.CharacterString;
import common.tool.informationException.ErrorException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wap.business.example.bean.EnumProgramBean;

import java.util.Date;

/**
 * 数据类的设置
 * Created by 70486 on 2017/8/3 on 23:23.
 */
public class InformationModify extends StoreInformation {

    EnumProgramBean epb;

    private String parameter = "你已经进入了编辑页面，跟规划不一致。。";

    public InformationModify(EnumProgramBean epb) {
        this.epb = epb;
    }

    protected void modifyInformation() {
        switch (epb.getFour()) {
            case "名字":
                //读取数据内容
                String shopName = super.bean.getShop_name();
                if (shopName.equals("") | shopName == null) {

                    SystemOut.caseStringInput(epb.getZero(), epb.getFive());
                    String shopLoad = "input#name";
                    //如果元素报错说明不需要进行输入。此时就是程序问题
                    super.elementInput(shopLoad, epb.getFive(), epb.getZero(), parameter);

                } else {
                    SystemOut.caseStringInput(epb.getZero(), "");
                }
                break;

            case "LOGO":
                //读取数据内容
                String logo = super.bean.getShop_logo();
                if (logo.equals("") | logo == null) {

                    //打印说明
                    SystemOut.caseStringInput(epb.getZero(), epb.getFive());
                    //对象路径
                    String logoLoad = "#SWFUpload_0";
                    //实现上传
                    bedGoToPicture(logoLoad, epb.getFive(), epb.getZero());

                } else {
                    SystemOut.caseStringInput(epb.getZero(), "");
                }
                break;

            case "执照":
                try {
                    //读取数据库中的图片数量
                    String license = super.bean.getLicense_number();

                    //找到上传按钮
                    String licenseLoad = "#SWFUpload_1";

                    //判断需要上传的数量
                    int imgNumber = 2 - Integer.parseInt(license);

                    //切割用例中的参数
                    String five = epb.getFive();

                    //判断参数中是否有逗号，有说明需要上传多个数据此时就切割数据。
                    //返回-1说明参数中没有逗号
                    if (five.indexOf(",") == -1) {
                        for (int i = 0; i < imgNumber; i++) {
                            bedGoToPicture(licenseLoad, five, epb.getZero());
                        }
                    } else {
                        CharacterString cha = new CharacterString();
                        String[] strings = cha.stringsToString(five, "");
                        for (int i = 0; i < imgNumber; i++) {
                            bedGoToPicture(licenseLoad, strings[i], epb.getZero());
                        }
                    }

                } catch (NumberFormatException e) {
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();
                    new ErrorException(clazz, method, e);
                }
                break;

            case "实拍":
                break;

            case "联系":
                break;

            case "地址":
                break;

            case "位置":
                WebElement element = driver.findElement(By.cssSelector(super.detailed));
                super.elementInput(element, epb.getFive() + new Date());
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

    public void capitalProvincialIndex(int number) {
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryIndex(By.cssSelector(super.provinceSele), number);
    }

    public void capitalProvincialValue(String content, String sql) {
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryValue(By.cssSelector(super.citySele), content, sql);
    }

    public void capitalProvincialText(String content) {
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryText(By.cssSelector(super.countySele), content);
    }
}
