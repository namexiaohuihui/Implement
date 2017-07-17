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
 * Created by ${XiaoHuiHui} on 2017/2/21.
 * XiaoHiiHui [704866169@qq.com]
 */
public class readExcel {

    //读取csv格式的excel
    public void getReadCSV() throws IOException {
        ArrayList<String[]> csvList = new ArrayList<String[]>();
        String csvFilePath = "E:\\商品列表操作.xlsx";
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


    //读取xlsx格式的excle数据.整张表中的数据都读取
    public List<List> getWholeReadXlsx(String load) throws IOException {
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
                        listCol.add(getValue(xssfRow.getCell(col)));
                    }
                }

                listRow.add(listCol);
            }
        }
        return listRow;
    }

    //内部随机获取某行
    public List<String> getSingleReadXlsx(String load) throws IOException {
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
            row = ((int) Math.random()) * xssfSheet.getLastRowNum();
        }
        return getSingleReadXlsx(load, row);
    }


    //读取xlsx格式的excle数据.读取指定行的数据
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
                    listRow.add(getValue(xssfRow.getCell(col)));
                }
            }
        }
        return listRow;
    }

    //返回表中数据的长度
    public int getSingleXlsx(String load) throws IOException {
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

    //返回表中数据的长度
    public int getXlsxNumber(String load) throws IOException {
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


    //读取xlsx格式的excle数据.读取单行单列的数据
    public String getSingleRowReadXlsx(String load, int rowNum) throws IOException {
        File file = new File(load);
        InputStream is = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        String value = null;
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 获取当前工作薄的每一行,从第rowNum行开始读取数据
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);//获取该行的全部数据

            if (xssfRow != null) {
                value = getValue(xssfRow.getCell((int) xssfRow.getFirstCellNum()));
            }
        }
        return value;
    }


    //根据xlsx读取的数据，将其转换成string
    public String getValue(XSSFCell xssfRow) {

        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }


    /**
     * 读取xls文件----指定行的数据
     *
     * @throws IOException
     */
    public List getReadXls(int row) throws IOException {
        File file = new File("E:\\大哥.xls");
        InputStream is = new FileInputStream(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List listRow = new ArrayList();
        // 获取每一个工作薄
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }

            // 获取当前工作薄的每一行
            HSSFRow hssfRow = hssfSheet.getRow(row);
            if (hssfRow != null) {
                int firstCellNum = hssfRow.getFirstCellNum();//获取首列的位置
                int lastCellNum = hssfRow.getLastCellNum();//获取末列的位置
                for (int col = firstCellNum; col < lastCellNum; col++) {
                    listRow.add(getValue(hssfRow.getCell(col)));
                }
            }
        }
        return listRow;
    }

    /**
     * 读取xls的数据---整个表的数据
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
                        listCol.add(getValue(hssfRow.getCell(col)));
                    }
                }

                listRow.add(listCol);
            }

        }
        return listRow;
    }

    // 转换数据格式
    public String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
