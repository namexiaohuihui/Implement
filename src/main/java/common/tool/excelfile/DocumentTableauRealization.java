package common.tool.excelfile;

import common.tool.SystemOut;

import java.io.File;
import java.io.IOException;

/**
 * Created by ${XiaoHuiHui} on 2017/8/9 on 11:24.
 * XiaoHiiHui [704866169@qq.com]
 */
public class DocumentTableauRealization implements DocumentTableau {

    //判断文件是否存在如果不存在就创建
    @Override
    public File mkdirDocumentTableau(String excelPath) {
        File file = new File(excelPath);
        //exists判断该文件所处的父类文件夹是否存在
        if (file.exists() && !file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
