package toolskit.tools.excelfile;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.io.monitor.FileEntry;

import java.io.File;
import java.io.IOException;

/**
 * org.apache.commons.io.monitor包下的类包含的方法可以获取文件的指定信息，
 * 不过更重要的是，它可以创建处理器（handler）来跟踪指定文件或目录的变化并且可以在文件或
 * 目录发生变化的时候进行一些操作。
 * Created by 70486 on 2017/7/24 on 23:18.
 */

public final class FileMonitorExample {

    private static final String EXAMPLE_PATH =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFolderexampleFileEntry.txt";

    private static final String PARENT_DIR =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFolder";

    private static final String NEW_DIR =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFoldernewDir";

    private static final String NEW_FILE =
            "C:UsersLilykosworkspaceApacheCommonsExampleExampleFoldernewFile.txt";

    public static void runExample() {
        System.out.println("File Monitor example...");

        // FileEntry

        // We can monitor changes and get information about files
        // using the methods of this class.
        FileEntry entry = new FileEntry(FileUtils.getFile(EXAMPLE_PATH));

        System.out.println("已监控文件: " + entry.getFile());
        System.out.println("文件名: " + entry.getName());
        System.out.println("这个文件是一个目录?: " + entry.isDirectory());

        // File Monitoring

        // 为该文件夹创建一个新的观察者并添加一个监听器
        // 这将处理特定目录中的事件并采取行动.
        File parentDir = FileUtils.getFile(PARENT_DIR);

        FileAlterationObserver observer = new FileAlterationObserver(parentDir);
        observer.addListener(new FileAlterationListenerAdaptor() {

            @Override
            public void onFileCreate(File file) {
                System.out.println("文件已创建: " + file.getName());
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("文件已删除: " + file.getName());
            }

            @Override
            public void onDirectoryCreate(File dir) {
                System.out.println("目录创建: " + dir.getName());
            }

            @Override
            public void onDirectoryDelete(File dir) {
                System.out.println("目录删除: " + dir.getName());
            }
        });

        // 添加一个将每xms检查事件的monior,
        // 并附上我们想要的所有不同的观察者。
        FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
        try {
            monitor.start();

            // 我们连接显示器后，我们可以创建一些文件和目录
            // 看看会发生什么!
            File newDir = new File(NEW_DIR);
            File newFile = new File(NEW_FILE);

            newDir.mkdirs();
            newFile.createNewFile();

            Thread.sleep(1000);

            FileDeleteStrategy.NORMAL.delete(newDir);
            FileDeleteStrategy.NORMAL.delete(newFile);

            Thread.sleep(1000);

            monitor.stop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * org.apache.commons.io.monitor包下的类创建了一个处理器来监听一些特定的事件
         * （在上面的例子中就是我们对文件或目录所做的所有操作事件），为了获得这些信息，
         * 我们需要做以下几步操作：
         1、创建一个File对象，这个对象指向我们需要监听变化的目录。
         2、创建一个FileAlterationObserver对象，这个对象会观察这些变化。
         3、通过调用addListener()方法，为observer对象添加一个
         FileAlterationListenerAdaptor对象。你可以通过很多种方式来创建一个适配器，
         在我们的例子中我们使用内部类的方式进行创建并且只实现其中的一部分方法
         （只需要实现我们例子中需要用的方法即可）。
         4、创建一个FileAlterationMonitor 对象，将已经创建好的observer对象添加其中并且传入时间
         间隔参数（单位是毫秒）。
         5、调用start()方法即可开启监视器，如果你想停止监视器，调用stop()方法即可。
         */
    }
}
