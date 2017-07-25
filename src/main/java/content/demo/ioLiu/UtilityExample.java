package content.demo.ioLiu;

import org.apache.commons.io.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 *  里面多数类都是完成文件操作以及字符串比较的功能，下面列举了一下常用的工具类：
 *  FilenameUtils： 这个工具类是用来处理文件名（译者注：包含文件路径）的，
 *      他可以轻松解决不同操作系统文件名称规范不同的问题（比如windows和Unix）
 *      （在Unix系统以及Linux系统中文件分隔符是“/”，不支持”\“，windows中支持”\“以及”/“）。
    FileUtils：提供文件操作（移动文件，读取文件，检查文件是否存在等等）的方法。
    IOCase：提供字符串操作以及比较的方法。
    FileSystemUtils：提供查看指定目录剩余空间的方法。
 * Created by 70486 on 2017/7/24 on 23:12.
 */
public class UtilityExample {
    // 我们在文件夹ExampleFolder中使用文件exampleTxt.txt，
    // 我们需要提供实用程序类的完整路径。

    private final String EXAMPLE_TXT_PATH =
            "C:\\Users\\70486\\Desktop\\操作指令.txt";

    private final String PARENT_DIR =
            "C:\\Users\\70486\\Desktop";

    @Test
    public void runExample() throws IOException {
        System.out.println("实用程序类示例...");

        // FilenameUtils

        System.out.println("示例Txt的完整路径: " +
                FilenameUtils.getFullPath(EXAMPLE_TXT_PATH));

        System.out.println("text的全名: " +
                FilenameUtils.getName(EXAMPLE_TXT_PATH));

        System.out.println("扩展示例Txt: " +
                FilenameUtils.getExtension(EXAMPLE_TXT_PATH));

        System.out.println("示例Txt的基本名称: " +
                FilenameUtils.getBaseName(EXAMPLE_TXT_PATH));

        // FileUtils

        //我们可以使用新的File对象来创建 FileUtils.getFile(String)
        // 然后使用此对象从文件中获取信息.
        File exampleFile = FileUtils.getFile(EXAMPLE_TXT_PATH);
        LineIterator iter = FileUtils.lineIterator(exampleFile, "UTF-8");

        System.out.println("示例Txt的内容...");
        while (iter.hasNext()) {
            System.out.println("t" + iter.next());
        }
        iter.close();

        // 我们可以检查一个文件是否存在于某个目录的某处.
        File parent = FileUtils.getFile(PARENT_DIR);
        System.out.println("父目录包含示例Txt文件: " +
                FileUtils.directoryContains(parent, exampleFile));

        // IOCase

        String str1 = "This is a new String.";
        String str2 = "This is another new String, yes!";

        System.out.println("以字符串结尾（区分大小写）Ends with string (case sensitive): " +
                IOCase.SENSITIVE.checkEndsWith(str1, "string."));
        System.out.println("以字符串结尾（区分大小写）Ends with string (case insensitive): " +
                IOCase.INSENSITIVE.checkEndsWith(str1, "string."));

        System.out.println("字符串相等String equality: " +
                IOCase.SENSITIVE.checkEquals(str1, str2));

        // FileSystemUtils
        System.out.println("可用磁盘空间（KB）Free disk space (in KB): " + FileSystemUtils.freeSpaceKb("C:"));
        System.out.println("可用磁盘空间（MB）Free disk space (in MB): " + FileSystemUtils.freeSpaceKb("C:") / 1024);
    }
}
