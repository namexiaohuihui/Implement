package wap.business.example.bean;

import common.tool.enumTool.ChineseToEnglish;

/**
 * Created by ${XiaoHuiHui} on 2017/8/7 on 17:45.
 * XiaoHiiHui [704866169@qq.com]
 */
public class EnumProgramBean {
    String zero = ChineseToEnglish.Zero.getName();
    String one = ChineseToEnglish.One.getName();
    String two = ChineseToEnglish.Two.getName();
    String three = ChineseToEnglish.Three.getName();
    String four = ChineseToEnglish.Four.getName();
    String five = ChineseToEnglish.Five.getName();
    String six = ChineseToEnglish.Six.getName();
    String seven = ChineseToEnglish.Seven.getName();
    String eight = ChineseToEnglish.Eight.getName();
    String nine = ChineseToEnglish.Nine.getName();
    String ten = ChineseToEnglish.Ten.getName();

    public String getZero() {
        return zero;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }

    public String getThree() {
        return three;
    }

    public String getFour() {
        return four;
    }

    public String getFive() {
        return five;
    }

    public String getSix() {
        return six;
    }

    public String getSeven() {
        return seven;
    }

    public String getEight() {
        return eight;
    }

    public String getNine() {
        return nine;
    }

    public String getTen() {
        return ten;
    }

    @Override
    public String toString() {
        return "EnumProgramBean{" +
                "zero='" + zero + '\'' +
                ", one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                ", four='" + four + '\'' +
                ", five='" + five + '\'' +
                ", six='" + six + '\'' +
                ", seven='" + seven + '\'' +
                ", eight='" + eight + '\'' +
                ", nine='" + nine + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }
}
