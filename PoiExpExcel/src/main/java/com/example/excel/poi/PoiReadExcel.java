package com.example.excel.poi;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 利用poi读取文件内容
 * @author Administrator
 *
 */
public class PoiReadExcel {

	public static void main(String[] args) {
		try {
			//引入需要解析的excel文件
			File file =new File("G:/test/poi_test.xls");
			HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(file));
			//获取工作表，方式一
			//HSSFSheet sheet=workbook.getSheet("mysheet");
			//获取工作表，方式二
			HSSFSheet sheet=workbook.getSheetAt(0);
			//获取行
			int firstRowNum=0;
			int lastRowNum=sheet.getLastRowNum();//最后一行的行号
			for (int i = firstRowNum; i <=lastRowNum; i++) {
				HSSFRow row=sheet.getRow(i);
				//获取当前行最后一个单元格的列号
				int lastCellNum=row.getLastCellNum();
				for (int j = 0; j <lastCellNum; j++) {
					HSSFCell cell=row.getCell(j);
					String value=cell.getStringCellValue();
					System.out.print(value+" ");
				}
				System.out.println();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
