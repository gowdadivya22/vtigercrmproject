package genericlibraries;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelUtility implements IAutoConstants
{
	public String readSingleData(String sheetname, int rowNum, int coulumnNumber) throws EncryptedDocumentException, IOException
	{
		File file = new File(EXCELPATH);
		Workbook workbook=WorkbookFactory.create(file);
		 return workbook.getSheet(sheetname).getRow(rowNum).getCell(coulumnNumber).toString();
		 
	}
	@DataProvider(name="testdata")
	public Object[][] readingMultipleData() throws EncryptedDocumentException, IOException
	{
		File file = new File(EXCELPATH);
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data=new Object[rowCount][cellCount];
	for(int row=0;row<rowCount;row++)
	{
		Row actualRow = sheet.getRow(row);
		for(int cell = 0;cell<actualRow.getLastCellNum();cell++)
		{
			data[row][cell]=actualRow.getCell(cell).toString();
		}
	}
		 
	
	return data;	
	}
	                                                                                                                                                  

}
