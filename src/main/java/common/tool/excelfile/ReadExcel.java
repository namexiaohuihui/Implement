package common.tool.excelfile;

import com.csvreader.CsvReader;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取excle文档中格式为xlsx的文件
 * Created by ${XiaoHuiHui} on 2017/2/21.
 * XiaoHiiHui [704866169@qq.com]
 */
public class ReadExcel {

    /**
     * 利用CsvReader读取xlsx格式的excel,整张表中的数据都读取
     * @param csvFilePath
     * @throws IOException
     */
    public void readCSV(String csvFilePath) throws IOException {
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));    //一般用这编码读就可以了
        reader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        while (reader.readRecord()) { //逐行读入除表头的数据
            csvList.add(reader.getValues());
        }
        reader.close();
        for (int row = 0; row < csvList.size(); row++) {//获取行
            for (int col = 0; col < csvList.get(row).length; col++) {//获取列
                String cell = csvList.get(row)[col]; //取得第row行第col列的数据
                System.out.println(cell + "\t");
            }
            System.out.println("\n");
        }
    }
    /**
     * 利用HSSFRow读取xls的数据---整个表的数据
     *
     * @throws IOException
     */
    public List<List> getReadXls() throws IOException {
        File file = new File("E:\\大哥.xls");
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
     * 读取xlsx格式的excle数据.读取指定行的数据
     * @param load
     * @param rowNum
     * @return
     * @throws IOException
     */
    public List<String> getSingleReadXlsx(String load, int rowNum) throws IOException {
        File file = new File(load);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<String> listRow = new ArrayList();
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 获取当前工作薄的每一行,从第rowNum行开始读取数据
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);//获取该行的全部数据

            int firstCellNum = (int) xssfRow.getFirstCellNum();// 首列
            int lastCellNum = (int) xssfRow.getLastCellNum();// 最后一列

            if (xssfRow != null) {
                for (int col = firstCellNum; col < lastCellNum; col++) {
                    listRow.add(xssfRowValue(xssfRow.getCell(col)));
                }
            }
        }
        return listRow;
    }

    /**
     * 返回表中数据的长度
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
            row =  xssfSheet.getLastRowNum();
        }
        return row;

    }


    /**
     * 利用XSSFCell读取xlsx的数据，将其转换成string
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
}
