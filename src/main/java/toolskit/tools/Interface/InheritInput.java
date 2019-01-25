package toolskit.tools.Interface;

/**
 * 元素判断的接口
 * Created by ${XiaoHuiHui} on 2017/7/28 on 17:18.
 * XiaoHiiHui [704866169@qq.com]
 */
public interface InheritInput {
    //根据id
    public boolean accordingToId(String id);

    //根据name
    public boolean accordingToName(String name);

    //根据标签名tagname
    public boolean accordingTagName(String tagname);

    //根据cssselector
    public boolean accordingToCssSelector(String css);

    //根据类名cssname
    public boolean accordingToCssName(String css);

    //根据xpath路径
    public boolean accordingToXpath(String xpath);

    //根据文字描述
    public boolean accordingToLinkText(String linkText);
}
