package pcwang.weather.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

/**
 * 读取配置文件的工具类
 * @author Administrator
 *
 */
public class PropertyUtils {

	private Properties p;
	
	
	public PropertyUtils(String filePath) throws Exception{
		this.getProperty(filePath);
	}
	
	public String getValue(String key) {
		String value=p.getProperty(key);
		return value;
	}
	
	/*
	 * 返回配置文件资源
	 */
	private Properties getProperty(String filePath) throws Exception {
			this.p = new Properties();
			//File file = new File(filePath);
			//String path=file.getAbsolutePath();
			//System.out.println(path);
			FileInputStream in=new FileInputStream(filePath);
			p.load(in);
			in.close();
		return p;
	}
	
	
}
