package common.tool.caninput;

import common.FoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 返回by对象
 * Created by 70486 on 2017/7/20 on 0:40.
 */
public class ConfirmByEle extends ElementExistence {

    private WebDriver driver = super.driver;

    /**
     * 根据id进行返回
     * @param id 元素id
     * @return
     */
    public By accordingToid(String id) {
        By by = null;
        boolean bl = super.accordingToId(id);
        if (bl) {
            by = By.id(id);
        }
        return by;
    }

    /**
     * 根据name进行返回
     * @param name 元素name
     * @return
     */
    public By accordingToname(String name) {
        By by = null;
        boolean bl = super.accordingToName(name);
        if (bl) {
            by = By.name(name);
        }
        return by;
    }


    /**
     * 根据cssselnictor进行返回
     * @param css 元素cssselector
     * @return
     */
    public By accordingToCss(String css) {
        By by = null;
        boolean bl = super.accordingToCssSelector(css);
        if (bl) {
            by = By.cssSelector(css);
        }
        return by;
    }


    /**
     * 根据xpath进行返回
     * @param xpath 元素xpath
     * @return
     */
    public By accordingToxpath(String xpath) {
        By by = null;
        boolean bl = super.accordingToXpath(xpath);
        if (bl) {
            by = By.xpath(xpath);
        }
        return by;
    }


    /**
     * 根据文字描述进行返回
     * @param linkText 元素描述
     * @return
     */
   public  By accordingTolinktext(String linkText){
       By by = null;
       boolean bl = super.accordingToLinkText(linkText);
       if (bl) {
           by = By.linkText(linkText);
       }
       return by;
   }

}
