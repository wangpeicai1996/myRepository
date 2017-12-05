package com.example.excel.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * jxl创建一个excel文件
 * @author Administrator
 *
 */
public class JxlExpExcel {

public static void main(String[] args) {
	String [] title= {"id","name","password"};//用数组存储表头
	//创建文件
	File file=new File("G:/test/jxl_test.xls");
	try {
	file.createNewFile();
	//创建工作簿
	WritableWorkbook  workbook=Workbook.createWorkbook(file);
	WritableSheet sheet=workbook.createSheet("mysheet", 0);
	//创建行
	Label label=null;
	//设置第一列名
	for (int i = 0; i < title.length; i++) {
		label=new Label(i,0,title[i]);//参数含义：列，行，值
		sheet.addCell(label);
	}
	//添加数据
	for (int i = 1; i <= 10; i++) {
		label=new Label(0,i,"a"+i);//参数含义:列，行，值
		sheet.addCell(label);
		label=new Label(1,i,"user"+i);//参数含义:列，行，值
		sheet.addCell(label);
		label=new Label(2,i,"mima");//参数含义:列，行，值
		sheet.addCell(label);
	}
	//将文件写出
	workbook.write();
	//关闭流
	workbook.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	
}
	
}
