package toolskit.tools.excelfile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import toolskit.tools.SystemOut;
import toolskit.tools.enumTool.EmployEnum;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.*;


/**
 * 读取excle文档中格式为xlsx的文件
 * Created by ${XiaoHuiHui} on 2017/2/21.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ReadExcel extends ExcelOperating {

//    public static void main(String[] args) {
//        try {
//            ReadExcel obj = new ReadExcel();
//            File file = new File("C:\\Users\\DingDonf\\Desktop\\红包发放.xlsx");
//            InputStream inputStream = new FileInputStream(file);
//            List<Map<String, String>> excelList = obj.readExcel(inputStream, "发放");
//            System.out.println("从excel读取数据并开始使用:");
//            for (Map<String, String> list : excelList) {
//                System.out.println(list.get("用户ID"));
//                System.out.println(list.get("发放原因"));
//                System.out.println(list.get("详细说明"));
//                System.out.println();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }

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
        if (numSheet <= xssfWorkbook.getNumberOfSheets()) {
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
        } else {
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

    /**
     * 读取Excel文件的内容
     *
     * @param inputStream excel文件，以InputStream的形式传入
     * @param sheetName   sheet名字
     * @return 以List返回excel中内容
     */
    public static List<Map<String, String>> readExcel(InputStream inputStream, String sheetName) {

        //定义工作簿
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            System.out.println("Excel data file cannot be found!");
        }

        //定义工作表

        XSSFSheet xssfSheet;
//        XSSFSheet xssfSheet;
        if (sheetName.equals("")) {
            // 默认取第一个子表
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } else {
            xssfSheet = xssfWorkbook.getSheet(sheetName);
        }

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        //定义行
        //默认第一行为标题行，index = 0
        XSSFRow titleRow = xssfSheet.getRow(0);

        //循环取每行的数据
        for (int rowIndex = 1; rowIndex < xssfSheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowIndex);
            if (xssfRow == null) {
                continue;
            }

            Map<String, String> map = new LinkedHashMap<String, String>();
            //循环取每个单元格(cell)的数据
            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
                XSSFCell titleCell = titleRow.getCell(cellIndex);
                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                map.put(getString(titleCell), getString(xssfCell));
            }
            list.add(map);
        }

        return list;
    }

    /**
     * 把单元格的内容转为字符串
     * 高版本的import org.apache.poi.ss.usermodel.CellType变为了import org.apache.poi.ss.usermodel.Cell; 
     * 同时cellRowName.setCellType(CellType.STRING);变为了cellRowName.setCellType(Cell.CELL_TYPE_STRING);
     * 原文：https://blog.csdn.net/qq_20200047/article/details/82223898
     * 并且xssfCell.getCellTypeEnum()变成xssfCell.getCellType()
     * CellType	                类型	        值
     * CELL_TYPE_NUMERIC	    数值型	        0
     * CELL_TYPE_STRING	        字符串型	    1
     * CELL_TYPE_FORMULA	    公式型	        2
     * CELL_TYPE_BLANK	        空值	        3
     * CELL_TYPE_BOOLEAN	    布尔型	        4
     * CELL_TYPE_ERROR	        错误	        5
     * 原文:https://blog.csdn.net/nanshaowei/article/details/52815483
     *
     * @param xssfCell 单元格
     * @return 字符串
     */
    public static String getString(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }

        if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else {
            return xssfCell.getStringCellValue();
        }
//        if (xssfCell.getCellTypeEnum() == CellType.NUMERIC) {
//            return String.valueOf(xssfCell.getNumericCellValue());
//        } else if (xssfCell.getCellTypeEnum() == CellType.BOOLEAN) {
//            return String.valueOf(xssfCell.getBooleanCellValue());
//        } else {
//            return xssfCell.getStringCellValue();
//        }
    }

    /**
     * 把一个Map中的所有键和值分别放到一个list中，
     * 再把这两个list整个放到一个大的list里面，即 [ [key1,key2,key3...] , [value1,value2,value3...] ]
     *
     * @param map
     * @return
     */
    public static List<List> convertMapToList(Map map) {
        List<List> list = new ArrayList<List>();
        List<String> key_list = new LinkedList<String>();
        List<String> value_list = new LinkedList<String>();

        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> iter1 = set.iterator();
        while (iter1.hasNext()) {
            key_list.add(iter1.next().getKey());
        }
        list.add(key_list);

        Collection<String> value = map.values();
        Iterator<String> iter2 = value.iterator();
        while (iter2.hasNext()) {
            value_list.add(iter2.next());
        }
        list.add(value_list);
        return list;
    }


}
