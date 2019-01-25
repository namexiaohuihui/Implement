package toolskit.tools.excelfile;

import toolskit.tools.informationException.ErrorException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;


/**
 * Created by 70486 on 2017/8/7 on 22:46.
 */
public class WriteExcel extends ExcelOperating {
    //当前文件已经存在
    private String excelPath = "E:/MyFirstExcel.xlsx";
    //从第几行插入进去
    private int insertStartPointer;
    //在当前工作薄的那个工作表单中插入这行数据
    private String sheetName;
    private int sheetInsert = 0;

    public WriteExcel(String excelPath, int insertStartPointer, String sheetName, int sheetInsert) {
        this.excelPath = excelPath;
        this.insertStartPointer = insertStartPointer;
        this.sheetName = sheetName;
        this.sheetInsert = sheetInsert;
    }

    public WriteExcel() {
    }

    /**
     * 总的入口方法
     */
    public static void main(String[] args) throws IOException, ErrorException {
        WriteExcel crt = new WriteExcel();
        int i = new ReadExcel().singleXlsx(crt.excelPath, 0);
        crt.insertStartPointer = i + 1;
        crt.insertRows();
    }

    /**
     * 在已有的Excel文件中插入一行新的数据的入口方法
     */
    public void insertRows() {
        Workbook wb = returnWorkBookGivenFileHandle();
        // XSSFSheet sheet1 = wb.getSheet(sheetName);
        sheetName = wb.getSheetName(sheetInsert);
        Sheet sheet = wb.getSheet(sheetName);
        Row row = createRow(sheet, insertStartPointer);
        createCell(row);
        saveExcel(wb);

    }

    /**
     * 保存工作薄
     *
     * @param wb
     */
    private void saveExcel(Workbook wb) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(excelPath);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建要出入的行中单元格
     *
     * @param row
     * @return
     */
    private Cell createCell(Row row) {
        Cell cell = row.createCell((short) 0);
        cell.setCellValue(999999);
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("This is a string cell");
        return cell;
    }

    /**
     * 得到一个已有的工作薄的POI对象
     *
     * @return
     */
    private Workbook returnWorkBookGivenFileHandle() {
        Workbook wb = null;
        File f = new File(excelPath);
        try {
            if (f != null) {
                wb = distinguishWorkbook(excelPath);
            }
        } catch (Exception e) {
            return null;
        }
        return wb;
    }

    /**
     * 找到需要插入的行数，并新建一个POI的row对象
     *
     * @param sheet
     * @param rowIndex
     * @return
     */
    private Row createRow(Sheet sheet, Integer rowIndex) {
        Row row = null;
        if (sheet.getRow(rowIndex) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex, lastRowNo, 1);
        }
        row = sheet.createRow(rowIndex);
        return row;
    }

}
