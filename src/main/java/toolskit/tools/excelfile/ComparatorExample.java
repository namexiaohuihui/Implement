package toolskit.tools.excelfile;

/**
 * 使用org.apache.commons.io.comparator 包下的类可以让你轻松的对文件或目录进行比较或者排序。
 * 你只需提供一个文件列表，选择不同的类就可以实现不同方式的文件比较。
 * Created by 70486 on 2017/7/24 on 23:31.
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;

import java.io.File;
import java.util.Date;

public final class ComparatorExample {

    private static final String PARENT_DIR =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFolder";

    private static final String FILE_1 =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFolderexample";

    private static final String FILE_2 =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFolderexampleTxt.txt";

    public static void runExample() {
        System.out.println("Comparator example...");

        //NameFileComparator

        //将目录作为File对象获取
        //并对其所有文件进行排序。
        File parentDir = FileUtils.getFile(PARENT_DIR);
        NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
        File[] sortedFiles = comparator.sort(parentDir.listFiles());

        System.out.println("Sorted by name files in parent directory: ");
        for (File file: sortedFiles) {
            System.out.println("t"+ file.getAbsolutePath());
        }

        // SizeFileComparator

        //我们可以根据大小来比较文件。
        //构造函数中的布尔值是关于目录。
        // true：目录的内容数量大小。
        // false：目录被认为是零大小。
        SizeFileComparator sizeComparator = new SizeFileComparator(true);
        File[] sizeFiles = sizeComparator.sort(parentDir.listFiles());

        System.out.println("按照父目录中的大小文件进行排序: ");
        for (File file: sizeFiles) {
            System.out.println("t"+ file.getName() + " with size (kb): " + file.length());
        }

        // LastModifiedFileComparator

        //我们可以使用此类来查找最近修改的文件。
        LastModifiedFileComparator lastModified = new LastModifiedFileComparator();
        File[] lastModifiedFiles = lastModified.sort(parentDir.listFiles());

        System.out.println("由父目录中的最后修改的文件排序: ");
        for (File file: lastModifiedFiles) {
            Date modified = new Date(file.lastModified());
            System.out.println("t"+ file.getName() + " last modified on: " + modified);
        }


    }

    /**
     * 让我们来看看这里用到了哪些类：

     NameFileComparator：通过文件名来比较文件。

     SizeFileComparator：通过文件大小来比较文件。

     LastModifiedFileComparator：通过文件的最新修改时间来比较文件。

     */
}
