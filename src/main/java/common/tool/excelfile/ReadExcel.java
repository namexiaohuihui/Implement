package common.tool.excelfile;

import common.tool.SystemOut;
import common.tool.enumTool.ChineseToEnglish;
import common.tool.enumTool.EmployEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取excle文档中格式为xlsx的文件
 * Created by ${XiaoHuiHui} on 2017/2/21.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ReadExcel {

    /**
     * 利用HSSFRow读取xls的数据---整个表的数据
     *
     * @throws IOException
     */
    public List<List> getReadXls(String load) throws IOException {
        File file = new File(load);
        InputStream is = new FileInputStream(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List> listRow = new ArrayList();
        List listCol = null;

        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }

            // 获取当前工作薄的每一行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);

                listCol = new ArrayList();

                int firstCellNum = hssfRow.getFirstCellNum();//获取首行的位置
                int lastCellNum = hssfRow.getLastCellNum();//获取末行的位置

                if (hssfRow != null) {

                    for (int col = firstCellNum; col < lastCellNum; col++) {
                        listCol.add(hssfCellValue(hssfRow.getCell(col)));
                    }
                }

                listRow.add(listCol);
            }

        }
        return listRow;
    }


    /**
     * 利用XSSFRow读取xlsx格式的excle数据.整张表中的数据都读取
     *
     * @param load
     * @return
     * @throws IOException
     */
    public List<List> wholeReadXlsx(String load) throws IOException {
        File file = new File(load);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List> listRow = new ArrayList();
        List listCol = null;
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 获取当前工作薄的每一行,从第rowNum行开始读取数据
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                listCol = new ArrayList();
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);//获取该行的全部数据

                int firstCellNum = (int) xssfRow.getFirstCellNum();// 首列
                int lastCellNum = (int) xssfRow.getLastCellNum();// 最后一列

                if (xssfRow != null) {
                    for (int col = firstCellNum; col < lastCellNum; col++) {
                        listCol.add(xssfRowValue(xssfRow.getCell(col)));
                    }
                }
                listRow.add(listCol);
            }
        }
        return listRow;
    }


    /**
     * 读取指定工作薄里的指定行的内容
     * load文档所在地
     * numSheet当前文档中所读写的工作薄
     * rowNum当前工作薄中的第几个数据
     *
     * @param load
     * @param numSheet
     * @param rowNum
     * @return
     * @throws IOException
     */
    public Map<String, String> singleReadXlsx(String load, int numSheet, int rowNum) throws IOException {
        File file = new File(load);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        Map<String, String> aMap = new HashMap<>();
        EmployEnum employEnum = new EmployEnum();
        // 获取每一个工作薄
        SystemOut.getStringOut(  xssfWorkbook.getNumberOfSheets() + "页面");
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
        if (xssfSheet != null) {
            // 获取当前工作薄的每一行,从第rowNum行开始读取数据
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);//获取该行的全部数据
            SystemOut.getStringOut( xssfSheet.getLastRowNum() + "长度");
            if (xssfRow != null) {

                int firstCellNum = (int) xssfRow.getFirstCellNum();// 首列
                int lastCellNum = (int) xssfRow.getLastCellNum();// 最后一列

                for (int col = firstCellNum; col < lastCellNum; col++) {
                    String sEnum = employEnum.employChineseToEnglish(col);
                    String sNalue = xssfRowValue(xssfRow.getCell(col));
                    aMap.put(sEnum, sNalue);
                }
            }else{
                SystemOut.getStringOut( "xssfRow为空");
            }
        }
        return aMap;
    }


    /**
     * 返回表中数据的长度
     *
     * @param load
     * @return
     * @throws IOException
     */
    public int singleXlsx(String load) throws IOException {
        File file = new File(load);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        int row = 0;
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            row = xssfSheet.getLastRowNum();
        }
        return row;

    }


    /**
     * 利用XSSFCell读取xlsx的数据，将其转换成string
     *
     * @param xssfRow
     * @return
     */
    private String xssfRowValue(XSSFCell xssfRow) {

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
