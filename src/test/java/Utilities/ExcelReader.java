package Utilities;

	
	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.LinkedHashMap;
	import java.util.List;

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;

	public class ExcelReader
	{
		public static List<LinkedHashMap<String,String>> getExcelData(String excelFileName, String sheetname) throws EncryptedDocumentException, IOException{
			
			List<LinkedHashMap<String,String>> dataFromExcel = new ArrayList<>();
			Workbook workbook = WorkbookFactory.create(new File("C:\\Users\\karth\\eclipse-workspace\\UserAPI\\src\\test\\resources\\ExcelData\\TestData.xlsx"));
			Sheet sheet=workbook.getSheet(sheetname);
			
			int totalRows = sheet.getPhysicalNumberOfRows();
			LinkedHashMap<String,String> mapData;
			List<String> allKeys = new ArrayList<>();
			DataFormatter dataFormatter = new DataFormatter();
			for(int i=0; i<totalRows ; i++)
			{
				mapData = new LinkedHashMap<>();
				if(i==0) {
			  int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
			  for(int j=0;j<totalCols ;j++)
			  {
				 allKeys.add(sheet.getRow(i).getCell(j).getStringCellValue()); 
			  }
			}
				else {
					 int totalCols = sheet.getRow(i).getPhysicalNumberOfCells();
					  for(int j=0;j<totalCols ;j++)
					  {
						String cellValue= dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
						  
						 mapData.put(allKeys.get(j), cellValue);
					  }
					  dataFromExcel.add(mapData);
					
				}
			}
			
			return dataFromExcel;
			
		}
		
	}
