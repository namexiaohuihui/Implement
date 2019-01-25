package toolskit.tools.enumTool;

/**
 * Created by ${XiaoHuiHui} on 2017/8/7 on 17:08.
 * XiaoHiiHui [704866169@qq.com]
 */
public enum ChineseToEnglish {
    Zero("zero",0),One("one", 1), Two("two", 2), Three("three", 3), Four("four", 4), Five("five", 5), Six("six", 6),
    Seven("seven", 7), Eight("eight", 8), Nine("nine", 9), Ten("ten", 10);

    private String name;
    private int index;

    private ChineseToEnglish(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public String toString() {
        return "ChineseToEnglish{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}
