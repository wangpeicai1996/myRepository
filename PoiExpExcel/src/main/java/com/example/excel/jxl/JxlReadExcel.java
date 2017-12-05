package com.example.excel.jxl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.example.excel.entity.User;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 利用jxl解析读取excel文件内容
 * @author Administrator
 *
 */
public class JxlReadExcel {

	public static void main(String[] args) {
		try {
		//创建workbook
		Workbook workbook=Workbook.getWorkbook(new File("G:/test/jxl_test.xls"));
		//获取sheet
		Sheet sheet=workbook.getSheet(0);
		//循环取出行列的值
		Map<String,User> users=new HashMap<String,User>();       
		for (int i = 1; i < sheet.getRows(); i++) {//循环行
			//for (int j = 0; j < sheet.getColumns(); j++) {//循环列
//				Cell cell=sheet.getCell(j, i);
//				System.out.print(cell.getContents()+" ");
//			}
//			System.out.println();
			User user=new User();
			Cell id=sheet.getCell(0, i);
			user.setId(id.getContents());
			Cell username=sheet.getCell(1, i);
			user.setUsername(username.getContents());
			Cell password=sheet.getCell(2, i);
			user.setPassword(password.getContents());
			users.put(user.getId(), user);		
			//}
			}
		for(User user:users.values()) {
			System.out.print(user.getId()+"  ");
			System.out.print(user.getUsername()+" ");
			System.out.print(user.getPassword()+"  ");
			System.out.println();
		}
		workbook.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
