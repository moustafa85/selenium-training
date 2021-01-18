package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcellReader {
    static FileInputStream fis = null;
    String filePath = System.getProperty("user.dir") + "\\resources\\";

    public FileInputStream getFileInputStream(String filename) {

        System.out.println("File: " + filename);
        File srcFile = new File(filename);

        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Test Data file not found. treminating Process !! : Check file path of TestData");
            System.exit(0);
        }
        return fis;
    }

    public Object[][] getData(String filename) throws IOException {

        String file = filePath+filename;
        fis = getFileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        int TotalNumberOfRows = (sheet.getLastRowNum() );
        int TotalNumberOfCols = sheet.getRow(0).getPhysicalNumberOfCells();
        System.out.println("Columns: \t"+TotalNumberOfCols +" \t Rows: \t" +TotalNumberOfRows);
        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];

        for (int i = 1; i < TotalNumberOfRows+1; i++) {
            System.out.println("Row " + i);
            for (int j = 0; j < TotalNumberOfCols; j++) {
                if (i > 0) {
                    XSSFRow row = sheet.getRow(i);
                    if(row.getCell(j) != null)
                        arrayExcelData[i-1][j] = "" + row.getCell(j).toString();
                    else
                        arrayExcelData[i-1][j] = "";

                    System.out.print("" + arrayExcelData[i-1][j] + "\t | ");
                }
            }
            System.out.println("");
        }

        wb.close();
        return arrayExcelData;
    }

    public Object[][] getSheetData(int SheetNo) throws IOException {
        fis = getFileInputStream("Add file name here");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(SheetNo);

        int TotalNumberOfRows = (sheet.getLastRowNum() + 1);
        int TotalNumberOfCols = 10;

        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];

        for (int i = 0; i < TotalNumberOfRows; i++) {
            for (int j = 0; j < TotalNumberOfCols; j++) {
                XSSFRow row = sheet.getRow(i);
                arrayExcelData[i][j] = row.getCell(j).toString();
            }
        }

        wb.close();
        return arrayExcelData;
    }

    public void SetTestCaseResult(String TCID, String ColumnIndex, String Result) {
        try {
            fis = getFileInputStream("Put Filename Here");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFRow row = sheet.getRow(Integer.valueOf(TCID));
            XSSFCell cell = row.getCell(Integer.valueOf(ColumnIndex));
            if (cell == null)
                cell = row.createCell(Integer.valueOf(ColumnIndex));

            cell.setCellValue(Result);
            FileOutputStream outFile = new FileOutputStream(new File(filePath));
            wb.write(outFile);
            outFile.close();

        } catch (Exception er) {
            er.printStackTrace();
        }

    }
}
