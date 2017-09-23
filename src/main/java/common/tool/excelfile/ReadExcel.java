package common.tool.excelfile;

import common.tool.SystemOut;
import common.tool.enumTool.EmployEnum;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 读取excle文档中格式为xlsx的文件
 * Created by ${XiaoHuiHui} on 2017/2/21.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ReadExcel extends ExcelOperating {

    /**
     * 通过Workbook来读取excle表格上的数据
     *
     * @param load
     * @return
     * @throws IOException
     */
    public List<List> wholeReadXlsx(String load) {
        Workbook xssfWorkbook = distinguishWorkbook(load);
        List<List> listRow = new ArrayList();
        List listCol = null;
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 获取当前工作薄的每一行,从第rowNum行开始读取数据
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                listCol = new ArrayList();
                Row xssfRow = xssfSheet.getRow(rowNum);//获取该行的全部数据

                int firstCellNum = (int) xssfRow.getFirstCellNum();// 首列
                int lastCellNum = (int) xssfRow.getLastCellNum();// 最后一列

                if (xssfRow != null) {
                    for (int col = firstCellNum; col < lastCellNum; col++) {
                        listCol.add(cellValue(xssfRow.getCell(col)));
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
    public Map<String, String> singleReadXlsx(String load, int numSheet, int rowNum) {
        Workbook xssfWorkbook = distinguishWorkbook(load);
        Map<String, String> aMap = new HashMap<>();
        EmployEnum employEnum = new EmployEnum();
        if (numSheet <= xssfWorkbook.getNumberOfSheets()){
            // 获取指定工作薄
            Sheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet != null) {
                // 获取指定行
                Row xssfRow = xssfSheet.getRow(rowNum);//获取该行的全部数据
                if (xssfRow != null) {

                    int firstCellNum = (int) xssfRow.getFirstCellNum();// 首列
                    int lastCellNum = (int) xssfRow.getLastCellNum();// 最后一列

                    for (int col = firstCellNum; col < lastCellNum; col++) {
                        String sEnum = employEnum.employChineseToEnglish(col);
                        aMap.put(sEnum, cellValue(xssfRow.getCell(col)));
                    }
                } else {
                    SystemOut.getStringOut("xssfRow为空");
                }
            }
        }else {
            SystemOut.getStringOut("想获取的表格簿位置大于了当前表格的最大值。");
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
    public int singleXlsx(String load, int numSheet) {
        int row = 0;
        // 获取每一个工作薄
        Sheet sheetAt = distinguishWorkbook(load).getSheetAt(numSheet);
        if (sheetAt != null) {
            row = sheetAt.getLastRowNum();
        }
        return row;

    }
}
