package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int rowNum, int celNum) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("./testData/testScriptdata.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).getStringCellValue();
		return data;
		
	}
	
	public int getRowcount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis= new FileInputStream("./testData/testScriptdata.xlsx");
				Workbook wb=WorkbookFactory.create(fis);
				int rowCount=wb.getSheet(sheetName).getLastRowNum();
		       return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName, int rowNum, int celnum, String data) throws EncryptedDocumentException, IOException	{
		FileInputStream fis=new FileInputStream("./testData/testScriptdata.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(celnum);
		FileOutputStream fos= new FileOutputStream("./testData/testScriptdata.xlsx");
		wb.write(fos);
		wb.close();
		
		
	}

	    public void readAllDataFromExcel(String sheetName) throws EncryptedDocumentException, IOException {

	        FileInputStream fis = new FileInputStream("./testData/testScriptdata.xlsx");
	        Workbook wb = WorkbookFactory.create(fis);
	        Sheet sheet = wb.getSheet(sheetName);

	        // Get total rows
	        int rowCount = sheet.getLastRowNum();

	        // Iterate rows
	        for (int i = 0; i <= rowCount; i++) {
	            Row row = sheet.getRow(i);

	            // Null check to avoid NullPointerException
	            if (row == null) continue;

	            int cellCount = row.getLastCellNum();

	            // Iterate columns
	            for (int j = 0; j < cellCount; j++) {
	                Cell cell = row.getCell(j);
	                String data = "";

	                if (cell != null) {
	                    // Handling different cell types
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            data = cell.getStringCellValue();
	                            break;
	                        case NUMERIC:
	                            data = String.valueOf(cell.getNumericCellValue());
	                            break;
	                        case BOOLEAN:
	                            data = String.valueOf(cell.getBooleanCellValue());
	                            break;
	                        case FORMULA:
	                            data = cell.getCellFormula();
	                            break;
	                        case BLANK:
	                            data = "";
	                            break;
	                        default:
	                            data = "";
	                            break;
	                    }
	                }

	                System.out.print(data + "  ");
	            }
	            System.out.println();
	        }

	        wb.close();
	        fis.close();
	    }

}
