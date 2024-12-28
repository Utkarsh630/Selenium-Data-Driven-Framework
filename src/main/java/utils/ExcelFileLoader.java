package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.checkerframework.checker.units.qual.C;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileLoader {

    private static final String EXCEL_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/testData.xlsx";


    public static Object[][] getExcelData (String sheetName) {
        // Code to read data from Excel file
        try(FileInputStream fileInputStream = new FileInputStream ( EXCEL_FILE_PATH )){

            Workbook workbook = new XSSFWorkbook ( fileInputStream );
            Sheet sheet = workbook.getSheet( sheetName );
            int rowCount = sheet.getPhysicalNumberOfRows();
            int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount-1][columnCount];
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow ( i);
                for (int j = 0; j < columnCount; j++) {
                    Cell cell = row.getCell ( j );
                    data[i-1][j]=getCellData ( cell ).toString ();
                    System.out.println ( data[i-1][j] );
                }
            }
            workbook.close ();
            return data;

        }catch (IOException e){
            throw new RuntimeException ( "File not found" + e );
            }
    }

    private static String getCellData(Cell cell){
        switch (cell.getCellType ()){
            case STRING:
                return cell.getStringCellValue ();
            case NUMERIC:
                return String.valueOf ( (long) cell.getNumericCellValue () );
            case BOOLEAN:
                return String.valueOf ( cell.getBooleanCellValue () );
            default:
                return cell.getStringCellValue ();
        }
    }

}
