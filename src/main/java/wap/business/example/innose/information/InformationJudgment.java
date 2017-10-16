package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.caninput.ElementObtain;
import common.tool.conversion.RegularExpression;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import wap.business.example.bean.EnumProgramBean;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 设置过店铺信息的类判断...
 * Created by 70486 on 2017/8/3 on 23:24.
 */
public class InformationJudgment extends StoreInformation {

    EnumProgramBean epb;

    public InformationJudgment(EnumProgramBean epb) {
        this.epb = epb;
    }

    protected void judgmentInformation() {
        try {

            //SystemOut.getStringOut("开始执行的数据？" + epb.toString());
            switch (epb.getFour()) {
                case "名字":
                    //读取数据内容
                    String shopName = StoreStatic.bean.getShop_name();

                    //元素对象的css路径
                    String shopLoad = "input#name";

                    //需要打印的日志
                    String shopMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterAttribute(shopName, shopLoad, "value", shopMessage);

                    break;

                case "中心":

                    //读取数据内容
                    String warehouse = StoreStatic.bean.getWarehouse_name();

                    //元素对象的css路径
                    String wpCss = " .referdata > tbody > tr:nth-child(3) > td";

                    //需要打印的日志
                    String wM = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterText(warehouse, wpCss, wM);

                    break;

                case "品类":

                    //读取数据内容
                    String category = StoreStatic.bean.getCategory_name();

                    //元素对象的css路径
                    String cpCss = " .referdata > tbody > tr:nth-child(4) > td";

                    //需要打印的日志
                    String categoryMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterText(category, cpCss, categoryMessage);

                    break;

                case "LOGO":
                    //读取数据内容
              /*      String logo = StoreStatic.bean.getShop_logo();

                    //元素对象的css路径
                    String logoLoad = "div#shop-logo > img";

                    //需要打印的日志
                    String logoMessage = epb.getFour() + "进行验证";
                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterAttribute(logo, logoLoad, "src", logoMessage);
*/
                    break;

                case "执照":
/*
                    //读取数据内容
                    String license = StoreStatic.bean.getLicense_number();

                    //需要打印的日志
                    String licenseMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterNumber(license, super.piczzFile, epb.getFour() + "进行验证");
*/
                    break;

                case "实拍":
/*
                    //读取数据内容
                    String photo = StoreStatic.bean.getPhoto_number();

                    //需要打印的日志
                    String photoMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterNumber(photo, super.picFile, photoMessage);
*/
                    break;

                case "联系":
                    StoreMovesWindow(900);
                    //读取数据内容
                    String phone = StoreStatic.bean.getShop_phone();

                    //元素对象的css路径
                    String phoneLoad = "input[id ='contact'][name='contact']";

                    //需要打印的日志
                    String pM = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterAttribute(phone, phoneLoad, "value", pM);

                    break;

                case "省区":
                    StoreMovesWindow(900);
                    StoreMovesWindow(900);
                    //读取数据内容
                    String province = StoreStatic.bean.getProvince();

                    //元素对象的css路径
                    //String provinceLoad = "#J_sel select:nth-child(1) > option";

                    //需要打印的日志
                    String provinceMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterText(province, super.provinceSele, provinceMessage);

                    break;

                case "城市":

                    StoreMovesWindow(900);
                    //读取数据内容
                    String city = StoreStatic.bean.getCity();

                    //元素对象的css路径
                    //String cityLoad = "#J_sel select:nth-child(2) > option";

                    //需要打印的日志
                    String cityMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterText(city, super.citySele, cityMessage);

                    break;

                case "区县":

                    StoreMovesWindow(900);

                    //读取数据内容
                    String county = StoreStatic.bean.getCounty();

                    //元素对象的css路径
                    //String countyLoad = "#J_sel select:nth-child(1) > option";

                    //需要打印的日志
                    String countyMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterText(county, super.countySele, countyMessage);

                    break;

                case "位置":

                    StoreMovesWindow(900);

                    //读取数据内容
                    String address = StoreStatic.bean.getAddress();

                    //元素对象的css路径
                    //String countyLoad = "#J_sel select:nth-child(1) > option";

                    //需要打印的日志
                    String addressMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterAttribute(address, super.detailed, "value", addressMessage);

                    break;

                case "经度":

                    StoreMovesWindow(900);

                    //读取数据内容
                    String lng = StoreStatic.bean.getLng();

                    //需要打印的日志
                    String lngMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterAttribute(lng, super.longitude, "value", lngMessage);

                    break;

                case "纬度":

                    StoreMovesWindow(900);

                    //读取数据内容
                    String lat = StoreStatic.bean.getLat();

                    //需要打印的日志
                    String latMessage = epb.getFour() + "进行验证";

                    //传入参数判断数据内容跟界面显示的是否一致
                    judgmentParameterAttribute(lat, super.latitude, "value", latMessage);
                    break;

                case "介绍":

                    StoreMovesWindow(900);

                    //读取数据内容
                    String info = StoreStatic.bean.getInfo();

                    //通过正则筛选出自己想要的内容
                    String strInfo = RegularExpression.regularExpression(info);

                    //打印数据
                    SystemOut.getStringOut("数据库中的介绍" + strInfo);

                    //定义css路径
                    String iframeLoad = ".ke-edit-iframe";
                    String bodyLoad = ".ke-content";

                    //获取数据
                    ElementObtain obtain = new ElementObtain();
                    String textInfo = obtain.operation(iframeLoad, bodyLoad);
                    SystemOut.getStringOut("页面的介绍" + textInfo);

                    break;

                default:
                    SystemOut.getStringOut("没有这个内容数据" + epb.getFour());
                    break;
            }
        } catch (Exception e) {
            //获取类名
            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

            //获取方法名
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();

            //输出发生错误的地方
            super.caseOutInformation(clazz, method, e, epb.getZero());
        }
    }

    /**
     * 目前针对可直接获取text的内容，然后直接进行比较的对象
     *
     * @param argument  数据库中查询到该页面的内容
     * @param parameter css路径对象
     * @param message   打印的数据
     */
    private void judgmentParameterText(String argument, String parameter, String message) {
        WebElement eleName = driver.findElement(By.cssSelector(parameter));
        String eleText = eleName.getText();
        super.assertEqualsMessage(message, argument, eleText);
    }

    /**
     * 目前针对于需要通过Attribute来读取数据，并进行比较的对象
     *
     * @param argument  sql读取的数据
     * @param parameter css的路径
     * @param name      Attribute的读取数据
     * @param message   打印的信息
     */
    private void judgmentParameterAttribute(String argument, String parameter, String value, String message) {
        WebElement eleName = driver.findElement(By.cssSelector(parameter));
        String eleText = eleName.getAttribute(value);
        int indexOf = getLastIndexOf(argument, eleText);
        if (indexOf!=-1){
            eleText = eleText.substring(indexOf, argument.length());
            super.assertEqualsMessage(message, argument, eleText);
        }else {
            SystemOut.getStringOut("图片验证错误。。。。。。。。。。坑呀");
        }
    }

    /**
     * 目前针对于统计数量的比较
     *
     * @param argument  数据库中的数据
     * @param parameter css路径
     * @param message   打印的信息
     */
    private void judgmentParameterNumber(String argument, String parameter, String message) {
        List<WebElement> phEles = driver.findElements(By.cssSelector(parameter));
        String phSize = phEles.size() + "";
        judgmentParameterText(argument, phSize, epb.getFour() + "进行验证");
    }
}
