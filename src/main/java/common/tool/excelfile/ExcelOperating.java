package common.tool.excelfile;

import common.tool.SystemOut;
import common.tool.informationException.ErrorException;
import org.apache.commons.io.IOCase;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ${XiaoHuiHui} on 2017/8/8 on 11:57.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ExcelOperating {

    private final String XLS_VERSION = "xls";
    private final String XLSX_VERSION = "xlsx";

    public Workbook distinguishWorkbook(String fileName) throws ErrorException {
        Workbook workbook = null;

        try {

            InputStream is = new FileInputStream(new File(fileName));

            if (IOCase.SENSITIVE.checkEndsWith(fileName, XLS_VERSION)) {

                workbook = new HSSFWorkbook(is);

            } else if (IOCase.SENSITIVE.checkEndsWith(fileName, XLSX_VERSION)) {

                workbook = new XSSFWorkbook(is);

            } else {
                SystemOut.getStringOut("该文件不是excle表格:" + fileName);
            }
        } catch (IOException e) {
            throw new ErrorException(System.getProperties().getProperty("java.class.path"),e,true,false);
        }
        return workbook;
    }

    /**
     * 利用XSSFCell读取xlsx的数据，将其转换成string
     *
     * @param xssfRow
     * @return
     */
    public String xssfRowValue(XSSFCell xssfRow) {

        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    /**
     * 利用Cell读取xlsx的数据，将其转换成string
     * @param xssfRow
     * @return
     */
    public String xssfRowValue(Cell xssfRow) {

        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }


    /**
     * 利用HSSFCell读取xlsx的数据，转换数据格式
     *
     * @param hssfCell
     * @return
     */
    public String hssfCellValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    public static String getCellValue(Cell cell) {
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

