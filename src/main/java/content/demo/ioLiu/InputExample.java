package content.demo.ioLiu;

/**
 * 包下有许多InputStrem类的实现，我们来测试一个最实用的类，
 * TeeInputStream，将InputStream以及OutputStream作为参数传入其中，
 * 自动实现将输入流的数据读取到输出流中。而且，通过传入第三个参数，一个boolean类型参数，
 * 可以在数据读取完毕之后自动关闭输入流和输出流。
 * Created by 70486 on 2017/7/24 on 23:38.
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.input.XmlStreamReader;

public final class InputExample {

    private static final String XML_PATH =
            "C:UsersLilykosworkspaceApacheCommonsExampleInputOutputExampleFolderweb.xml";

    private static final String INPUT = "This should go to the output.";

    public static void runExample() {
        System.out.println("Input example...");
        XmlStreamReader xmlReader = null;
        TeeInputStream tee = null;

        try {

            // XmlStreamReader

            // 我们可以读取一个xml文件并获取它的编码。
            File xml = FileUtils.getFile(XML_PATH);

            xmlReader = new XmlStreamReader(xml);
            System.out.println("XML encoding: " + xmlReader.getEncoding());

            // TeeInputStream

            // 这个非常有用的类将输入流复制到输出流
            //并且只使用一个close（）方法来关闭（通过定义第3个）
            //构造函数参数为true）
            ByteArrayInputStream in = new ByteArrayInputStream(INPUT.getBytes("US-ASCII"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            tee = new TeeInputStream(in, out, true);
            tee.read(new byte[INPUT.length()]);

            System.out.println("Output stream: " + out.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { xmlReader.close(); }
            catch (IOException e) { e.printStackTrace(); }

            try { tee.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }
}
