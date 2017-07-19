package common.tool.conversion;

/**
 * 字符串的操作
 * Created by ${XiaoHuiHui} on 2017/7/17 on 16:26.
 * XiaoHiiHui [704866169@qq.com]
 */
public class CharacterString {
    //使用Integer的parseInt方法
    public int stringToInt(String number) {
        return Integer.parseInt(number);
    }

    //调用Long类型的parseLong方法
    public Long stringToLong(String number) {
        return Long.parseLong(number);
    }


    /**
     * 根据字符串里面的数据进行切割
     *
     * @param prv    字符串
     * @param number 切割的起始点
     * @param data   切割的判断点
     * @return
     */
    public double cuttingCharacter(String prv, int number, String data) {

        prv = prv.trim();//将字符串里面的空格进行清空

        //替换字符：将字符串中出现的k转换成n
        // prv = prv.replace("k", "n");

        if (data == null || data.equals("")) {//判断切割符
            prv = prv.substring(number, prv.length());
        }
        //判断切割符
        else if (data.equals(".")) {
            //从number开始切割，一直到data出现的位置
            prv = prv.substring(number, prv.lastIndexOf(data));
        }
        //判断切割符
        else if (data.equals("KEY")) {
            //从number开始切割，一直到data出现的位置
            prv = prv.substring(number, prv.length());
        }
        //判断切割符
        else {
            //从number开始切割，一直到data出现的位置
            prv = prv.substring(number, prv.length() - 1);
        }

        //将切割之后的数据进行转换然后传出去
        return Double.parseDouble(prv);
    }

    public Boolean contentOperation(String operation, String content) {
        return operation.equals(content)?false:true;
    }
}
