package common.tool.Interface;

/**
 *
 * Created by ${XiaoHuiHui} on 2017/7/28 on 17:18.
 * XiaoHiiHui [704866169@qq.com]
 */
public interface InheritInput {
    public boolean accordingToId(String id);
    public boolean accordingToName(String name);
    public boolean accordingTagName(String tagname);
    public boolean accordingToCssSelector(String css);
    public boolean accordingToCssName(String css);
    public boolean accordingToXpath(String xpath);
    public boolean accordingToLinkText(String linkText);
}
