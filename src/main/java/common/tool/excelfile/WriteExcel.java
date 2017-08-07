package common.tool.excelfile;

import common.tool.SystemOut;
import org.apache.commons.io.IOCase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

/**
 * Created by 70486 on 2017/8/7 on 22:46.
 */
public class WriteExcel {
    private static final String XLS_VERSION = "xls";
    private static final String XLSX_VERSION = "xlsx";

    public void excelWriteExp(String fileName) throws IOException {
        File file = new File(fileName);
        Workbook workbook = null;
        OutputStream out = null;
        if (file.isFile() && file.exists()){// 判断文件是否存在
            workbook = distinguishWorkbook(fileName);
            Sheet sheet = workbook.createSheet("步骤");//创建工作薄的名字
            sheet.setColumnWidth(0,18*256);
            sheet.setColumnWidth(1,20*124);

            Sheet sheetAt = workbook.getSheetAt(0);
            Row row = sheet.createRow(sheetAt.getLastRowNum());//新建一行

            row.createCell(0).setCellValue("ip");
            row.createCell(1).setCellValue("comm");
            row.createCell(2).setCellValue("resu");
            out = new FileOutputStream(file);
            workbook.write(out);
        }else{
        SystemOut.getStringOut("文件不存在:" + fileName);
         }
            if (out!=null&&out.equals("")){
                out.close();
            }
    }

    private Workbook distinguishWorkbook(String fileName) throws FileNotFoundException {
        Workbook workbook = null;
        if (IOCase.SENSITIVE.checkEndsWith(fileName, XLS_VERSION)){
            workbook = new HSSFWorkbook();
        }else if (IOCase.SENSITIVE.checkEndsWith(fileName, XLSX_VERSION)){
            workbook = new XSSFWorkbook();
        }else {
            SystemOut.getStringOut("该文件不是excle表格:" + fileName);
        }
        return workbook;
    }

    @Test
    public void xx(){
        try {
            excelWriteExp("E:\\yuanyin.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
