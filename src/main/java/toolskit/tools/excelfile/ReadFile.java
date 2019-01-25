package toolskit.tools.excelfile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.input.XmlStreamReader;

import java.io.*;

import static java.lang.Thread.sleep;

/**
 * 该类为文件读取类：
 * 负责读取项目自带的txt文件。
 * Created by Administrator on 2016/11/14.
 */
public class ReadFile {

    /**
     * 返回文件的格式编码格式
     *
     * @param filename
     * @return
     */
    public static String fileFormat(String filename) {
        try {
            XmlStreamReader xml = new XmlStreamReader(new File(filename));
            filename = xml.getEncoding();
            return filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输入流：读取文本上的数据
     *
     * @param filename
     * @return
     */
    public static String readCommons(String filename) {
        File example = FileUtils.getFile(filename);
        String next = null;
        LineIterator lineIterator = null;
        if (example.isFile() && example.exists()) {
            try {
                lineIterator = FileUtils.lineIterator(example);
                while (lineIterator.hasNext()) {
                    next = lineIterator.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (lineIterator != null && lineIterator.equals("")) {
                    lineIterator.close();
                }
            }
        } else {
            System.out.println("找不到指定的文件");
        }

        return next;
    }

    /**
     * 需要换行的
     * 通过IO流来获取项目中的文件内容，将内容返回之后输入到店铺介绍里面
     *
     * @param route 文件在BigDataFile文件夹下面的名称
     * @return
     * @throws InterruptedException
     */
    public static String introductionFile(String route) throws InterruptedException {
        File file = new File("E://StopData//BigDataFile//" + route);
        StringBuffer sb = new StringBuffer();
        String str = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        sleep(2000);
        return sb.toString();
    }

    /**
     * 不需要需要换行的
     * 通过IO流来获取项目中的文件内容，将内容返回之后设置到店铺公告里面
     *
     * @param route 文件在BigDataFile文件夹下面的名称
     * @return
     */
    public static String noticesFile(String route) {
        File file = new File("E://StopData//BigDataFile//" + route);
        StringBuffer sb = new StringBuffer();
        String str = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 该方法用于读取txt文件
     *
     * @param filePath
     */
    public static void readTextFile(String filePath) {
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }




}
