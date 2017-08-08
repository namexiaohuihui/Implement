package common.tool.excelfile;

import common.tool.SystemOut;
import common.tool.informationException.ErrorException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;


/**
 * Created by 70486 on 2017/8/7 on 22:46.
 */
public class WriteExcel extends ExcelOperating {

    public void excelWriteExp(String fileName) throws ErrorException {
        File file = new File(fileName);
        Workbook workbook = null;
        OutputStream out = null;
        if (file.isFile() && file.exists()) {// 判断文件是否存在
            try {
                workbook = distinguishWorkbook(fileName);
                Sheet sheet = workbook.createSheet("步骤");//创建工作薄的名字
                sheet.setColumnWidth(0, 18 * 256);

                SystemOut.getStringOut(sheet.getLastRowNum() + "");
                Row row = sheet.createRow(sheet.getLastRowNum());//新建一行
                row.createCell(0).setCellValue("ip1");
                row.createCell(1).setCellValue("comm1");
                row.createCell(2).setCellValue("resu1");
                out = new FileOutputStream(file);
                workbook.write(out);
            } catch (FileNotFoundException e) {
                throw new ErrorException("out文件不存在", e, true, false);
            } catch (IOException e) {
                throw new ErrorException("workbook关闭有误", e, true, false);
            }
        } else {
            SystemOut.getStringOut("文件不存在:" + fileName);
        }
        if (out != null && out.equals("")) {
            try {
                out.close();
            } catch (IOException e) {
                throw new ErrorException("out关闭有误", e, true, false);
            }
        }
    }

    public void apachePOIExcelWrite(String FILE_NAME) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
        Object[][] datatypes = {
                {"Datatype", "Type", "Size(in bytes)"},
                {"int", "Primitive", 2},
                {"float", "Primitive", 4},
                {"double", "Primitive", 8},
                {"char", "Primitive", 1},
                {"String", "Non-Primitive", "No fixed size"}
        };

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }


}
