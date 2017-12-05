package com.example.excel.poi;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * 用poi工具导出excel表格
 * @author Administrator
 *
 */
public class PoiExpExcel {

	public static void main(String[] args) {
		//表头信息
		String [] title= {"id","username","password"};
		//创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建一个工作表
		HSSFSheet sheet=workbook.createSheet("mysheet");
		//创建第一行
		HSSFRow row=sheet.createRow(0);
		//定义单元格
		HSSFCell cell=null;
		for (int i = 0; i < title.length; i++) {
			cell=row.createCell(i);
			cell.setCellValue(title[i]);
		}
		//添加数据
		for (int i = 1; i <= 10; i++) {
			HSSFRow nextrow=sheet.createRow(i);
			HSSFCell cell2=nextrow.createCell(0);
			cell2.setCellValue("a"+i);
			cell2=nextrow.createCell(1);
			cell2.setCellValue("user"+i);
			cell2=nextrow.createCell(2);
			cell2.setCellValue("mima");
		}
		//创建一个文件并写出
		try {
		File file=new File("G:/test/poi_test.xls");
		file.createNewFile();
		FileOutputStream fos=new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
