package utils.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static String getCellData(String filePath,
            String sheetName,
            int row,
            int col) {

			try {
				FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = WorkbookFactory.create(fis);
			
				return wb.getSheet(sheetName)
				.getRow(row)
				.getCell(col)
				.getStringCellValue();
			
			} 
			
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
	
	public static Object[][] getData(String filePath, String sheetName) {

        Object[][] data = null;

        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowCount - 1][colCount];

            for (int i = 1; i < rowCount; i++) { // skip header

                Row row = sheet.getRow(i);

                for (int j = 0; j < colCount; j++) {

                    Cell cell = row.getCell(j);

                    data[i - 1][j] = getCellValue(cell);
                }
            }

            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // 🔥 Handles all data types
    private static Object getCellValue(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                return cell.getNumericCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();

            default:
                return "";
        }
    }

}
