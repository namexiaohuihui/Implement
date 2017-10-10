package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.caninput.ElementInput;
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

    protected void judgmentInformation(){
        switch (epb.getFour()) {
            case "名字":
                String shop_name = super.bean.getShop_name();
                //判断店名是否有内容、有就说明设置过内容然后就调用判断的方法
                // 没有内容就对其进行设置
                WebElement storeName = driver.findElement(By.id("name"));
                String value = storeName.getAttribute("value");
                if (storeName.equals(shop_name)){//数据库储存的跟页面读取的进行比较
                    super.assertEqualsMessage("店铺名字比较正确",shop_name,storeName);
                }else {
                    SystemOut.getStringOut("店铺没有设置过名字，现在进行设置");
                    super.elementInput(storeName,epb.getFour() + new Date());
                }
                break;

            case "中心":

                String warehouse = super.bean.getWarehouse_name();
                String warehouseParameter = " #shopinfoform > table > tbody > tr:nth-child(3) > td";
                judgmentParameterText(warehouse,warehouseParameter,epb.getFour() +"进行判断");
                break;

            case "品类":

                String category = super.bean.getCategory_name();
                String categoryParameter = " #shopinfoform > table > tbody > tr:nth-child(4) > td";
                judgmentParameterText(category,categoryParameter,epb.getFour() +"进行判断");
                break;

            case "LOGO":
                String logo = super.bean.getShop_logo();
                String logoLoad = "div#shop-logo > img";
                judgmentParameterAttribute(logo,logoLoad,"src",epb.getFour() +"进行判断");
                break;

            case "执照":
                String license = super.bean.getLicense_number();
                judgmentParameterNumber(license,super.piczzFile,epb.getFour() +"进行判断");
                break;

            case "实拍":
                String photo = super.bean.getPhoto_number();
                judgmentParameterNumber(photo,super.picFile,epb.getFour() +"进行判断");
                break;

            case "联系":
                String phone = super.bean.getShop_phone();
                String phoneLoad = "input[id ='contact'][name='contact']";
                judgmentParameterAttribute(phone,phoneLoad,"value",epb.getFour() +"进行判断");
                break;

            case "省区":
                String province = super.bean.getProvince();
                //String provinceLoad = "#J_sel select:nth-child(1) > option";
                judgmentParameterText(province,super.provinceSele,epb.getFour() +"进行判断");
                break;

            case "城市":
                String city = super.bean.getCity();
                //String cityLoad = "#J_sel select:nth-child(2) > option";
                judgmentParameterText(city,super.citySele,epb.getFour() +"进行判断");
                break;

            case "区县":
                String county = super.bean.getCounty();
                //String countyLoad = "#J_sel select:nth-child(1) > option";
                judgmentParameterText(county,super.countySele,epb.getFour() +"进行判断");
                break;

            case "位置":
                String address = super.bean.getAddress();
                //String countyLoad = "#J_sel select:nth-child(1) > option";
                judgmentParameterAttribute(address,super.detailed,"value",epb.getFour() +"进行判断");
                break;

            case "经度":
                String lng = super.bean.getLng();
                judgmentParameterAttribute(lng,super.longitude,"value",epb.getFour() +"进行判断");
                break;

            case "纬度":
                String lat = super.bean.getLat();
                judgmentParameterAttribute(lat,super.latitude,"value",epb.getFour() +"进行判断");
                break;

            case "介绍":
                String info = super.bean.getInfo();

                break;

            default:
                SystemOut.getStringOut("没有这个内容数据" + epb.getFour());
                break;
        }
    }

    /**
     *  目前针对可直接获取text的内容，然后直接进行比较的对象
     * @param argument  数据库中查询到该页面的内容
     * @param parameter css路径对象
     * @param message 打印的数据
     */
    private void judgmentParameterText(String argument,String parameter,String message){
        WebElement eleName = driver.findElement(By.cssSelector(parameter));
        String eleText = eleName.getText();
        super.assertEqualsMessage(message,argument,eleText);
    }

    /**
     * 目前针对于需要通过Attribute来读取数据，并进行比较的对象
     * @param argument sql读取的数据
     * @param parameter css的路径
     * @param name Attribute的读取数据
     * @param message 打印的信息
     */
    private void judgmentParameterAttribute(String argument,String parameter,String value,String message){
        WebElement eleName = driver.findElement(By.cssSelector(parameter));
        String eleText = eleName.getAttribute(value);
        super.assertEqualsMessage(message,argument,eleText);
    }

    /**
     * 目前针对于统计数量的比较
     * @param argument 数据库中的数据
     * @param parameter css路径
     * @param message 打印的信息
     */
    private void judgmentParameterNumber(String argument,String parameter,String message){
        List<WebElement> phEles = driver.findElements(By.cssSelector(parameter));
        String phSize = phEles.size()+"";
        judgmentParameterText(argument,phSize,epb.getFour() +"进行判断");
    }
}
