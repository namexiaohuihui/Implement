package toolskit.tools.excelfile;

import toolskit.tools.SystemOut;
import toolskit.tools.informationException.ErrorException;
import org.apache.commons.io.IOCase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ${XiaoHuiHui} on 2017/8/8 on 11:57.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ExcelOperating  {

    private final String XLS_VERSION = "xls";
    private final String XLSX_VERSION = "xlsx";

    public Workbook distinguishWorkbook(String fileName) {
        Workbook workbook = null;
        InputStream is = null ;
        try {
            File file = new File(fileName);
            is = new FileInputStream(file);

            if (IOCase.SENSITIVE.checkEndsWith(fileName, XLS_VERSION)) {

                workbook = new HSSFWorkbook(is);

            } else if (IOCase.SENSITIVE.checkEndsWith(fileName, XLSX_VERSION)) {

                workbook = new XSSFWorkbook(is);

            } else {
                SystemOut.getStringOut("该文件不是excle表格:" + fileName);
            }
        } catch (IOException e) {

            String clazz = Thread.currentThread().getStackTrace()[1].getClassName();
            String method = Thread.currentThread().getStackTrace()[1].getMethodName();
            new ErrorException(clazz, method, e);

        } finally {
           if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }




    /**
     * 判断获取当前内容的格式，然后进行返回内容
     * @param cell
     * @return
     */
    public static String cellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

}

