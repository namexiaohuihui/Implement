package common.tool.excelfile;

import common.tool.SystemOut;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by ${XiaoHuiHui} on 2017/7/27 on 16:06.
 * XiaoHiiHui [704866169@qq.com]
 */
public class WriteFile {

    /**
     * 将指定内容写到文件中
     *
     * @param fileName
     * @param encoding
     * @param data
     */
    public static void fileWrite(String fileName, String encoding, String data) {
        File file = new File(fileName);//创建文件
        FileOutputStream fos = null;//创建文件输入流
        OutputStreamWriter osw = null;//创建输入流

        if (file.isFile() && file.exists())// 判断文件是否存在
        {
            try {
                //定义参数
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos, encoding);

                osw.write(data);//定义参数

            } catch (Exception e) {
                SystemOut.getStringOut("读取文件内容出错");
                e.printStackTrace();
            } finally {
                //判断流是否被创建之后在进行关闭
                if (fos != null || !fos.equals("")) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (osw != null || !osw.equals("")) {
                    try {
                        osw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            SystemOut.getStringOut(fileName, "文件不存在");
        }
    }


    /**
     * 在父目录中找到指定的文件
     *
     * @param PARENT_DIR
     * @param acceptedNames
     * @return
     */
    public static String fileSeek(String PARENT_DIR, String acceptedNames, String name) {
        File dir = FileUtils.getFile(PARENT_DIR);
        File fi = new File(acceptedNames);
        if (dir.isFile() && dir.exists()) {//判断文件夹是否存在
            switch (name) {//根据要求来筛选文件
                case "\u5168\u79f0"://全匹配
                    //INSENSITIVE无论系统如何，不区分大小写
                    //SENSITIVE无论系统如何，区分大小写
                    //SYSTEM :由当前操作系统确定的区分大小写的常数。
                    for (String file : dir.list(new NameFileFilter(acceptedNames, IOCase.SYSTEM))) {
                        return file;
                    }
                    break;
                case "\u901a\u914d"://通过大概的查找来找到文件。？用于1个缺少的字符，*用于多个缺少的字符
                    for (String file : dir.list(new WildcardFileFilter(acceptedNames))) {
                        return file;
                    }
                    break;
                case "\u524d\u7f00"://查找指定前缀的文件
                    for (String file : dir.list(new PrefixFileFilter(acceptedNames))) {
                        return file;
                    }
                    break;
                case "\u540e\u7f00"://查找指定后缀的文件
                    for (String file : dir.list(new SuffixFileFilter(acceptedNames))) {
                        return file;
                    }
                    break;
                default:
                    SystemOut.getStringOut(PARENT_DIR, "没有找到该文件: ", acceptedNames);
                    break;
            }
        } else {
            SystemOut.getStringOut(PARENT_DIR, "目录不存在");
        }
        return null;
    }


    /**
     * 根据两个条件来查找文件是否存在
     *
     * @param PARENT_DIR
     * @param one
     * @param two
     * @param bl
     * @return
     */
    public static String fileSeek(String PARENT_DIR, String one, String two, boolean bl) {
        File dir = FileUtils.getFile(PARENT_DIR);
        if (dir.isFile() && dir.exists()) {//判断文件夹是否存在
            if (bl) {
                //找到包含one的文件并且后缀名必须是two的文件
                for (String file : dir.list(new AndFileFilter( // 我们将匹配2个过滤器...
                        new WildcardFileFilter("*ample*"), //第一是通配符...
                        new NotFileFilter(new SuffixFileFilter(".txt"))))) { //和第二个不是 .txt.
                    System.out.println("和/找不到文件，命名: " + file);
                }
            } else {
                //找到包含one的文件或者后缀名为two的文件
                for (String file : dir.list(new OrFileFilter(
                        new WildcardFileFilter(one), new SuffixFileFilter(two)))) {
                    return file;
                }
            }
        } else {
            SystemOut.getStringOut(PARENT_DIR, "目录不存在");
        }

        return null;
    }
}
