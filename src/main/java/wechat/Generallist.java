package wechat;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Created by 70486 on 2017/7/6 on 22:34.
 */
public class Generallist {
    //商品
    private List<Map<String, Double>> goods;
    //满减
    private Double fullCut;
    //红包
    private Double red;
    //数量
    private Double number;
    //配送
    private Double distribution;
    //合计
    private Double total;

    public Generallist() {
    }

    public Generallist(List<Map<String, Double>> goods, Double fullCut,
                       Double red, Double number, Double distribution, Double total) {
        this.goods = goods;
        this.fullCut = fullCut;
        this.red = red;
        this.number = number;
        this.distribution = distribution;
        this.total = total;
    }

    public List<Map<String, Double>> getGoods() {
        return goods;
    }

    public void setGoods(List<Map<String, Double>> goods) {
        this.goods = goods;
    }

    public Double getFullCut() {
        return fullCut;
    }

    public void setFullCut(Double fullCut) {
        this.fullCut = fullCut;
    }

    public Double getRed() {
        return red;
    }

    public void setRed(Double red) {
        this.red = red;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getDistribution() {
        return distribution;
    }

    public void setDistribution(Double distribution) {
        this.distribution = distribution;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
