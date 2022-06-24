package com.apoorv.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingExcel {
	
	public void ExcelReader() throws EncryptedDocumentException, IOException {
		String path="";
		File f = new File(path);
		FileInputStream fis = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fis);
		
		Sheet sheet0 = workbook.getSheetAt(0);

		
		for(Row row: sheet0) {
			for(Cell cell: row) {
				if(cell.getCellType()== CellType.STRING) {
					System.out.print(cell.getStringCellValue()+"\t");					
				}
				else if(cell.getCellType()== CellType.NUMERIC) {					
					System.out.print(cell.getNumericCellValue()+"\t");
				}
			}
			System.out.println();
		}
		
	}

}

