package wap.business.example.bean.elementBean;

/**
 * 这是信息表中，元素路径的bean
 * Created by 70486 on 2017/10/16 on 22:25.
 */
public class InformationBean {

    //省/区元素的css路径
    public static  String needProvinceSele(){
        String provinceSele = "select[id='province'][name='province']";
        return provinceSele;
    }

    //获取市元素的css对象
    public static  String needCitySele(){
        String citySele = "select[id='city'][name='city']";
        return citySele;
    }

    //获取县/区元素的css对象
    public static  String needCountySele(){
        String countySele = "select[id='county'][name='county']";
        return countySele;
    }

    //获取详细地址元素的css对象
    public static  String needDetailed(){
        String detailed = "input[id='address'][name='address']";
        return detailed;
    }

    //获取经度元素的css对象
    public static  String needLongitude(){
        String longitude = "input[id='lng'][name='lng']";
        return longitude;
    }

    //获取纬度元素的css对象
    public static  String needLatitude(){
        String latitude = "input[id='lat'][name='lat']";
        return latitude;
    }

    //获取执照元素的css对象
    public static  String needPiczzFile(){
        String piczzFile = "ul[id='J_piczz-box'][class='uploadPict']>li";
        return piczzFile;
    }
    //获取执照元素的css对象
    public static  String needPicFile(){
        String picFile = "ul[id='J_pic-box'][class='uploadPict']>li";
        return picFile;
    }

    //获取联系电话元素的css对象
    public static  String needPhoneLoad(){
        String phoneLoad = "input[id ='contact'][name='contact']";
        return phoneLoad;
    }

    //获取name元素的css对象
    public static  String needNmae(){
        String name = "input#name";
        return name;
    }


    //获取隶属中心元素的css对象
    public static  String needWarehouse(){
        String warehouse = " .referdata > tbody > tr:nth-child(3) > td";
        return warehouse;
    }

    //获取店铺品类元素的css对象
    public static  String needCategory(){
        String category = " .referdata > tbody > tr:nth-child(4) > td";
        return category;
    }

    //获取iframe元素的css对象
    public static  String needIframeLoad(){
        String iframeLoad = ".ke-edit-iframe";
        return iframeLoad;
    }

    //获取iframe里面的body元素的css对象
    public static  String needBodyLoad(){
        String bodyLoad = ".ke-content";
        return bodyLoad;
    }
}
