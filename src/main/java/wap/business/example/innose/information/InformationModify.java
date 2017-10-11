package wap.business.example.innose.information;

import common.tool.SystemOut;
import common.tool.caninput.InfoSelect;
import common.tool.conversion.CharacterString;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import wap.business.example.bean.EnumProgramBean;

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
        switch (epb.getFour()) {
            case "名字":
                //读取数据内容
                String shopName = super.bean.getShop_name();

                //判断是否要编辑
                if (shopName.equals("") | shopName == null) {
                    SystemOut.caseSuccess(epb.getZero(), epb.getFive());
                    String shopLoad = "input#name";
                    //如果元素报错说明不需要进行输入。此时就是程序问题
                    try {
                        super.elementInput(shopLoad, epb.getFive(), epb.getZero());
                    } //编辑失败的打印
                    catch (InvalidElementStateException e) {

                        //获取类名
                        String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                        //获取方法名
                        String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                        super.caseOutInformation(clazz, method, e, epb.getZero());
                    }

                } //需要编辑
                else {
                    super.caseOutInformation(epb.getZero());
                }
                break;

            case "LOGO"://logo直接上传
                try {
                    //打印说明
                    SystemOut.caseSuccess(epb.getZero(), epb.getFive());
                    //对象路径
                    String logoLoad = "#SWFUpload_0";
                    //实现上传
                    bedGoToPicture(logoLoad, epb.getFive(), epb.getZero());
                } catch (Exception e) {
                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    super.caseOutInformation(clazz, method, e, epb.getZero());
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

                    if (imgNumber == 0) {
                        SystemOut.getStringOut(epb.getZero(), "不需要上传");
                    } else {
                        //图片上传
                        imgGoToPicture(licenseLoad, imgNumber);
                    }

                    //打印错误信息
                } catch (Exception e) {//整体异常集合
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();
                    super.caseOutInformation(clazz, method, e, epb.getZero());
                }
                break;

            case "实拍":
                try {
                    //读取数据库中的图片数量
                    String photo = super.bean.getPhoto_number();

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

                    //打印错误信息
                } catch (Exception e) {//整体异常集合
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();
                    super.caseOutInformation(clazz, method, e, epb.getZero());
                }

                break;

            case "联系":
                //读取数据内容
                String phone = super.bean.getShop_phone();

                //元素对象的css路径
                String phoneLoad = "input[id ='contact'][name='contact']";

                //如果元素报错说明不需要进行输入。此时就是程序问题
                try {
                    elementInput(phoneLoad, epb.getFive(), epb.getZero());
                } catch (InvalidElementStateException e) {

                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());

                }
                break;

            case "省区":

                //读取数据内容
                String province = super.bean.getProvince();

                //元素对象的css路径
                //String provinceLoad = "#J_sel select:nth-child(1) > option";
                try {
                    if (province == "" | province.equals(null) | province.equals("")) {
                        dropDownScreen(super.provinceSele);
                    } else {
                        SystemOut.caseEditSuccess(epb.getZero());
                    }
                } catch (Exception e) {
                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());
                }

                break;

            case "城市":

                //读取数据内容
                String city = super.bean.getCity();

                //元素对象的css路径
                //String cityLoad = "#J_sel select:nth-child(2) > option";

                try {
                    if (city == "" | city.equals(null) | city.equals("")) {
                        dropDownScreen(super.citySele);
                    } else {
                        SystemOut.caseEditSuccess(epb.getZero());
                    }
                } catch (Exception e) {
                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());
                }

                break;

            case "区县":

                //读取数据内容
                String county = super.bean.getCounty();

                //元素对象的css路径
                //String countyLoad = "#J_sel select:nth-child(1) > option";

                try {
                    if (county == "" | county.equals(null) | county.equals("")) {
                        dropDownScreen(super.countySele);
                    } else {
                        SystemOut.caseEditSuccess(epb.getZero());
                    }
                } catch (Exception e) {
                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());
                }

                break;

            case "位置":

                //读取数据内容
                String address = super.bean.getAddress();

                //元素对象的css路径
                //String countyLoad = "#J_sel select:nth-child(1) > option";

                try {
                    if (address == "" | address.equals(null) | address.equals("")) {
                        super.elementInput(super.detailed, epb.getFive() + new Date(), epb.getZero());
                    } else {
                        SystemOut.caseEditSuccess(epb.getZero());
                    }
                } catch (InvalidElementStateException e) {

                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());

                }
                break;

            case "经度":

                //读取数据内容
                String lng = super.bean.getLng();

                try {
                    if (lng == "" | lng.equals(null) | lng.equals("")) {
                        super.elementInput(super.longitude, epb.getFive(), epb.getZero());
                    } else {
                        SystemOut.caseEditSuccess(epb.getZero());
                    }
                } catch (InvalidElementStateException e) {

                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());

                }

                break;

            case "纬度":

                //读取数据内容
                String lat = super.bean.getLat();

                try {
                    if (lat == "" | lat.equals(null) | lat.equals("")) {
                        super.elementInput(super.longitude, epb.getFive(), epb.getZero());
                    } else {
                        SystemOut.caseEditSuccess(epb.getZero());
                    }
                } catch (InvalidElementStateException e) {

                    //获取类名
                    String clazz = Thread.currentThread().getStackTrace()[1].getClassName();

                    //获取方法名
                    String method = Thread.currentThread().getStackTrace()[1].getMethodName();

                    //输出发生错误的地方
                    super.caseOutInformation(clazz, method, e, epb.getZero());

                }
                break;

            case "介绍":
                String iframeLoad = ".ke-edit-iframe";
                String bodyLoad = ".ke-content";
                iframeInput(iframeLoad, bodyLoad, epb.getFive() + new Date());
                break;

            default:
                SystemOut.getStringOut("没有这个内容数据" + epb.getFour());
                break;
        }
    }


    private void dropDownScreen(String cssLoad) {
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
        InfoSelect infoSelect = new InfoSelect();
        infoSelect.categoryText(By.cssSelector(cssLoad), content);
    }


}
