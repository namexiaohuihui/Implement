package content.demo.gongchang;

import org.apache.commons.io.IOCase;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 16:47.
 * XiaoHiiHui [704866169@qq.com]
 */
public class Factory {

    public static Animal getInstance(String className) {
        Animal animal = null;
        //根据系统来划分是否要大小写来进行判断
        if (IOCase.SENSITIVE.checkEndsWith(className, "Cat")) {
            animal = new Cat();
        }
        if (IOCase.SENSITIVE.checkEndsWith(className, "Dog")) {
            animal = new Dog();
        }
        return animal;
    }
}
