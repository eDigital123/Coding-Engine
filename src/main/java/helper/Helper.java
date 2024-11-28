package helper;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.coding.entity.ICDCode;

public class Helper {

	
	//check that file is of excel type or not
	public static boolean checkExcelFormat(MultipartFile file) {
		 
		String contentType = file.getContentType();
		
		if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
		{
			
			return true; 
		} else {
			return false;
		}
		
	}
	
	//convert excel to list of ICDCode
	
	public static List<ICDCode> convertExcelToListOfICDCode(InputStream is)
	{
		List<ICDCode> list=new ArrayList<>();
		
		try
		{
				
		XSSFWorkbook workbook = new XSSFWorkbook(is);
			
		XSSFSheet sheet = workbook.getSheet("data");
		
		
		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();
		
		while(iterator.hasNext())
		{
			Row row = iterator.next();
			
			if(rowNumber==0)
			{
				rowNumber++;
				continue;
			}
			
			Iterator<Cell> cells = row.iterator();
			
			int cid=0;
			
			ICDCode i=new ICDCode(); 
			
			while(cells.hasNext()) {
				
				Cell cell = cells.next();
				
				switch (cid)
				{
				case 0:
					i.setCodeId((int)cell.getNumericCellValue());
					break;
				case 1:
					i.setDescription(cell.getStringCellValue());
					break;
				case 2:
					i.setIcd_code((int)cell.getNumericCellValue());
					break;
				case 3:
					i.setIcd_index(cell.getStringCellValue());
					break;
                  default:
                	  break;
				}
				cid++;
			}
			list.add(i);
		}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
