package wap.business.example.bean;

/**
 * Created by 70486 on 2017/9/17 on 14:50.
 */
public class StoreInformationBean {
    private String shop_name;
    private String warehouse_name;
    private String category_name;
    private String shop_logo;
    private String license_number;
    private String photo_number;
    private String shop_phone;
    private String province;
    private String city;
    private String county;
    private String address;
    private String lng;
    private String info;
    private String lat;

    @Override
    public String toString() {
        return "StoreInformationBean{" +
                "shop_name='" + shop_name + '\'' +
                ", warehouse_name='" + warehouse_name + '\'' +
                ", category_name='" + category_name + '\'' +
                ", shop_logo='" + shop_logo + '\'' +
                ", license_number='" + license_number + '\'' +
                ", photo_number='" + photo_number + '\'' +
                ", shop_phone='" + shop_phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", address='" + address + '\'' +
                ", lng='" + lng + '\'' +
                ", info='" + info + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }


    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getShop_logo() {
        return shop_logo;
    }

    public void setShop_logo(String shop_logo) {
        this.shop_logo = shop_logo;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getPhoto_number() {
        return photo_number;
    }

    public void setPhoto_number(String photo_number) {
        this.photo_number = photo_number;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
