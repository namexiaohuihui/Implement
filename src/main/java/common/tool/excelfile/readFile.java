package common.tool.excelfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * 该类为文件读取类：
 * 负责读取项目自带的txt文件。
 * Created by Administrator on 2016/11/14.
 */
public class readFile {

    /**
     * 不需要换行的
     * 通过IO流来获取项目中的文件内容，将内容返回之后输入到店铺介绍里面
     *
     * @param route 文件在BigDataFile文件夹下面的名称
     * @return
     * @throws InterruptedException
     */
    public static String getIntroductionFile(String route) throws InterruptedException {
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
     * 需要换行的
     * 通过IO流来获取项目中的文件内容，将内容返回之后设置到店铺公告里面
     *
     * @param route 文件在BigDataFile文件夹下面的名称
     * @return
     */
    public static String getNoticesFile(String route) {
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
}
