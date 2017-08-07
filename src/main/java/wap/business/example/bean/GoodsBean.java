package wap.business.example.bean;

/**
 * 商品的bean
 * Created by ${XiaoHuiHui} on 2017/8/7 on 10:12.
 * XiaoHiiHui [704866169@qq.com]
 */
public class GoodsBean<T> {

    private String shop_id;
    private String price;
    private String goods_id;
    private String sort;
    private String seller_id;
    private String status;
    private String sale_total;
    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
    public String getShop_id() {
        return shop_id;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
    public String getGoods_id() {
        return goods_id;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getSort() {
        return sort;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }
    public String getSeller_id() {
        return seller_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setSale_total(String sale_total) {
        this.sale_total = sale_total;
    }
    public String getSale_total() {
        return sale_total;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "shop_id='" + shop_id + '\'' +
                ", price='" + price + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", sort='" + sort + '\'' +
                ", seller_id='" + seller_id + '\'' +
                ", status='" + status + '\'' +
                ", sale_total='" + sale_total + '\'' +
                '}';
    }
}
