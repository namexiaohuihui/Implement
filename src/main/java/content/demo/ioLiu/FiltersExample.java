package content.demo.ioLiu;

/**
 * 过滤器可以以组合的方式使用并且它的用途非常多样。它可以轻松的区分不同的文件并且找到满足我们条件的文件。
 * 我们可以组合不同的过滤器来执行文件的逻辑比较并且精确的获取我们所需要文件，
 * 而无需使用冗余的字符串比较来寻找我们的文件
 * Created by 70486 on 2017/7/24 on 23:25.
 */
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public final class FiltersExample {

    private static final String PARENT_DIR =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFolder";

    public static void runExample() {
        System.out.println("File Filter example...");
        // NameFileFilter
        //现在，在父目录中我们有3个文件：
        //目录示例
        // file exampleEntry.txt
        // file exampleTxt.txt

        //获取指定目录中的所有文件
        //命名为“example”。
        File dir = FileUtils.getFile(PARENT_DIR);
        String[] acceptedNames = {"example", "exampleTxt.txt"};
        for (String file: dir.list(new NameFileFilter(acceptedNames, IOCase.INSENSITIVE))) {
            System.out.println("找到文件，命名: " + file);
        }

        // WildcardFileFilter
        //我们可以使用通配符来获得较少的具体结果
        //？用于1个缺少的字符
        // *用于多个缺少的字符
        for (String file: dir.list(new WildcardFileFilter("*ample*"))) {
            System.out.println("找到通配符文件，命名: " + file);
        }

        // PrefixFileFilter
        //我们也可以使用等效的startsWith
        //用于过滤文件
        for (String file: dir.list(new PrefixFileFilter("example"))) {
            System.out.println("前缀文件找到，命名: " + file);
        }

        // SuffixFileFilter
        //我们也可以使用相当于的结果
        //用于过滤文件。
        for (String file: dir.list(new SuffixFileFilter(".txt"))) {
            System.out.println("找到后缀文件，命名: " + file);
        }

        // OrFileFilter
        //我们可以使用一些过滤器过滤器。
        //在这种情况下，我们使用过滤器来应用逻辑
        //或我们的过滤器之间。
        for (String file: dir.list(new OrFileFilter(
                new WildcardFileFilter("*ample*"), new SuffixFileFilter(".txt")))) {
            System.out.println("或找到文件，命名为： " + file);
        }

        //这可以变得非常详细。
        //例如，获取名称中“ample”的所有文件
        //但是它们不是文本文件（因此它们没有“.txt”扩展名。
        for (String file: dir.list(new AndFileFilter( // 我们将匹配2个过滤器...
                new WildcardFileFilter("*ample*"), //第一是通配符...
                new NotFileFilter(new SuffixFileFilter(".txt"))))) { //和第二个不是 .txt.
            System.out.println("和/找不到文件，命名: " + file);
        }
    }
}
