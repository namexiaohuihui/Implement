package common.tool.excelfile;

import java.io.*;

import static java.lang.Thread.sleep;

/**
 * 该类为文件读取类：
 * 负责读取项目自带的txt文件。
 * Created by Administrator on 2016/11/14.
 */
public class ReadFile {

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
            String encoding = "GBK";
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
