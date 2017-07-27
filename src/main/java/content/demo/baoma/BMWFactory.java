package content.demo.baoma;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 17:43.
 * XiaoHiiHui [704866169@qq.com]
 */
//造车厂实现类
public class BMWFactory implements CarFactory {

    private static BMWFactory ins;

    BMWFactory() {
    }

    public static BMWFactory getIns() {
        if (null == ins) {
            ins = new BMWFactory();
        }
        return ins;
    }

    public Car build(String name) {

        if (name.equals("BMW")) {

            return new BMW();
        }

        return null;
    }

}
