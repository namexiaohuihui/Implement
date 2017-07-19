package common.tool.excelfile;

/**
 * excle文档中根式为CSV的文件读写
 * Created by ${XiaoHuiHui} on 2017/2/17.
 * XiaoHiiHui [704866169@qq.com]
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReadWriteFile {
    // CSV文件编码
    public static final String ENCODE_UTF = "UTF-8";
    public static final String ENCODE_GBK = "GBK";

    private static List readerCsv() {
        File inFile = new File("E://--.csv"); // 读取的CSV文件
        String inString = "";
        List list = new ArrayList();
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(new FileInputStream(inFile), ENCODE_UTF);
            BufferedReader reader = new BufferedReader(isr);
            while ((inString = reader.readLine()) != null) {
                list.add(inString);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("没找到文件！");
        } catch (IOException ex) {
            System.out.println("读写文件出错！");
        }
        return list;
    }

    private static void writerCsv() {
        String[] str = {"省", "市", "区", "街", "路", "里", "幢", "村", "室", "园", "苑", "巷", "号"};
        File outFile = new File("E://out.csv");//写出的CSV文件
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(outFile),ENCODE_GBK);
            BufferedWriter writer = new BufferedWriter(osw);
               for (int i = 0;i <str.length;i++){
                   writer.write(str[i]);
                   writer.newLine();
               }
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("没找到文件！");
        } catch (IOException ex) {
            System.out.println("读写文件出错！");
        }
    }


    private void writerCsvReader(){
        File inFile = new File("E://int.csv"); // 读取的CSV文件
        File outFile = new File("E://out.csv");//写出的CSV文件
        String inString = "";
        String tmpString = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
            //写入文件（从人家哪里读取写入到自己这）
            while ((inString = reader.readLine()) != null) {
                writer.write(inString);//读取文件（从自己这里写出去给人家）
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println("没找到文件！");
        } catch (IOException ex) {
            System.out.println("读写文件出错！");
        }
    }
}