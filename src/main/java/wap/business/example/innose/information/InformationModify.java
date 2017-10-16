package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.caninput.InfoSelect;
import common.tool.conversion.CharacterString;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import wap.business.example.bean.EnumProgramBean;
import wap.business.example.bean.elementBean.InformationBean;

import java.io.IOException;
import java.util.Date;

/**
 * 数据类的设置
 * Created by 70486 on 2017/8/3 on 23:23.
 */
public class InformationModify extends StoreInformation {

    EnumProgramBean epb;

    public InformationModify(EnumProgramBean epb) {
        this.epb = epb;
    }

    protected void modifyInformation() {
        try{
        //SystemOut.getStringOut("2开始执行的数据？" + epb.toString());
        switch (epb.getFour()) {
            case "名字":
                //读取数据内容
                String shopName = StoreStatic.bean.getShop_name();

                //元素路径
                String shopLoad = InformationBean.needNmae();

                //如果元素报错说明不需要进行输入。此时就是程序问题
                super.elementInput(shopName,shopLoad, epb.getFive(), epb.getZero());

                break;

            case "LOGO"://logo直接上传
                    //打印说明
                    //SystemOut.caseSuccess(epb.getZero(), epb.getFive());
                    //对象路径
                    //String logoLoad = "#SWFUpload_0";
                    //实现上传
                    //bedGoToPicture(logoLoad, epb.getFive(), epb.getZero());

                break;

            case "执照":
                    //读取数据库中的图片数量
                    //String license = StoreStatic.bean.getLicense_number();
/*
                    //找到上传按钮
                    String licenseLoad = "#SWFUpload_1";

                    //判断需要上传的数量
                    int imgNumber = 2 - Integer.parseInt(license);

                    if (imgNumber == 0) {
                        SystemOut.getStringOut(epb.getZero(), "不需要上传");
                    } else {
                        //图片上传
                        imgGoToPicture(licenseLoad, imgNumber);
                    }
*/
                    //打印错误信息
                break;

            case "实拍":
                    //读取数据库中的图片数量
                   // String photo = StoreStatic.bean.getPhoto_number();
/*
                    //找到上传按钮
                    String photoLoad = "#SWFUpload_2";

                    //判断需要上传的数量
                    int imgNumber = 5 - Integer.parseInt(photo);

                    if (imgNumber == 0) {
                        SystemOut.getStringOut(epb.getZero(), "不需要上传");
                    } else {
                        //循环上传
                        imgGoToPicture(photoLoad, imgNumber);
                    }
*/
                    //打印错误信息
                break;

            case "联系":
                //读取数据内容
                String phone = StoreStatic.bean.getShop_phone();

                //元素对象的css路径
                String phoneLoad = InformationBean.needPhoneLoad();

                //如果元素报错说明不需要进行输入。此时就是程序问题
                elementInput(phone,phoneLoad, epb.getFive(), epb.getZero());
                break;

            case "省区":
                //获取元素对象
                String provinceSele = InformationBean.needProvinceSele();

                //读取数据内容
                String province = StoreStatic.bean.getProvince();

                //传入数据内容以及对象位置。让其判断是否符合
                dropDownScreen(province,provinceSele);

                break;

            case "城市":
                //获取元素对象
                String citySele = InformationBean.needCitySele();

                //读取数据内容
                String city = StoreStatic.bean.getCity();

                //传入数据内容以及对象位置。让其判断是否符合
                dropDownScreen(city,citySele);
                break;

            case "区县":
                //获取元素对象
                String countySele = InformationBean.needCountySele();

                //读取数据内容
                String county = StoreStatic.bean.getCounty();

                //传入数据内容以及对象位置。让其判断是否符合
                dropDownScreen(county,countySele);

                break;

            case "位置":
                //获取元素对象
                String detailed = InformationBean.needDetailed();

                //读取数据内容
                String address = StoreStatic.bean.getAddress();

                //需要输入的内容
                String messega = epb.getFive() + new Date();

                super.elementInput(address,detailed, messega, epb.getZero());

                break;

            case "经度":
                //获取元素对象
                String longitude = InformationBean.needLongitude();

                //读取数据内容
                String lng = StoreStatic.bean.getLng();

                super.elementInput(lng,longitude, epb.getFive(), epb.getZero());

                break;

            case "纬度":
                //获取元素对象
                String latitude = InformationBean.needLatitude();

                //读取数据内容
                String lat = StoreStatic.bean.getLat();

                super.elementInput(lat,latitude, epb.getFive(), epb.getZero());
                break;

            case "介绍":
                StoreMovesWindow(900);
                String iframeLoad = InformationBean.needIframeLoad();
                String bodyLoad = InformationBean.needBodyLoad();
                iframeInput(iframeLoad, bodyLoad, epb.getFive() + new Date());
                break;

            default:
                SystemOut.getStringOut("没有这个内容数据" + epb.getFour());
                break;
        }
        }
        //编辑失败的打印
        catch (InvalidElementStateException e) {

            //获取类名
            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

            //获取方法名
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();

            super.caseOutInformation(clazz, method, e, epb.getZero());
        }
    }


    /**
     * 通过下拉设置参数
     * 判断表格中需要输入的参数类型
     * 如果有逗号的说明是通过value值上传，此时需要sql语句一起
     * 没有逗号的返回-1，说明只是对文字进行上传而已，。
     * @param parameter 数据库中读取到数据信息
     * @param cssLoad 元素的路径
     */
    private void dropDownScreen(String parameter,String cssLoad) {
        if (parameter == "" | parameter.equals(null) | parameter.equals("")) {
            //需要打印的日志
            String provinceMessage = epb.getFive();
            if (provinceMessage.indexOf(",") == -1) {
                capitalProvincialText(cssLoad, provinceMessage);

                SystemOut.caseSuccess(epb.getZero(), provinceMessage);
            } else {
                //切割输入参数
                CharacterString cha = new CharacterString();
                String[] strings = cha.stringsToString(provinceMessage, "");
                capitalProvincialValue(cssLoad, strings[0], strings[1]);

            }
        } else {
            SystemOut.caseEditSuccess(epb.getZero());
        }
    }

    /**
     * 目前实现上传，并判断需要上传的数量
     *
     * @param licenseLoad 路径
     * @param imgNumber   数量
     * @throws NumberFormatException 错误
     */
    private void imgGoToPicture(String licenseLoad, int imgNumber) throws NumberFormatException, IOException, InterruptedException {

        //切割用例中的参数
        String five = epb.getFive();
        //判断参数中是否有逗号，有说明需要上传多个数据此时就切割数据。
        //返回-1说明参数中没有逗号
        if (five.indexOf(",") == -1) {
            for (int i = 0; i < imgNumber; i++) {//循环上传输入参数
                bedGoToPicture(licenseLoad, five, epb.getZero());
            }
        } else {

            //切割输入参数
            CharacterString cha = new CharacterString();
            String[] strings = cha.stringsToString(five, "");

            for (int i = 0; i < imgNumber; i++) {//循环上传输入参数
                bedGoToPicture(licenseLoad, strings[i], epb.getZero());
            }
        }
    }

    /**
     * 通过编码值进行设置下拉框
     *
     * @param cssLoad css对象路径
     * @param content 设置的参数
     * @param sql     查询的sql
     */
    private void capitalProvincialValue(String cssLoad, String content, String sql) {
        StoreMovesWindow(cssLoad);
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryValue(By.cssSelector(cssLoad), content, sql);
    }

    /**
     * 通过字符串来进行设置编码值
     *
     * @param cssLoad css对象路径
     * @param content 设置的参数
     */
    private void capitalProvincialText(String cssLoad,String content) {
        StoreMovesWindow(cssLoad);
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryText(By.cssSelector(cssLoad), content);
    }


}
