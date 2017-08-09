package common.tool.excelfile;

import java.io.File;

/**
 * Created by ${XiaoHuiHui} on 2017/8/9 on 11:21.
 * XiaoHiiHui [704866169@qq.com]
 */
public interface DocumentTableau {
    //判断文档是否存在，如果不存在就创建
    public File mkdirDocumentTableau(String excelPath);
}
