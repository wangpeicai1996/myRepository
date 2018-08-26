package pcwang.weather.app;

import java.util.HashMap;
import java.util.Map;

import pcwang.weather.service.CityCall;
import pcwang.weather.service.WeatherCall;
import pcwang.weather.utils.PropertyUtils;

public class MainClass {

	public static void main(String[] args) throws Exception {
		PropertyUtils p = new PropertyUtils("F:\\workspace_math\\weather\\src\\main\\resources\\appInfo\\appKey.properties");
		String appCode = p.getValue("AppCode");
		call();
				
	}
	
	public static void call() throws Exception {
		WeatherCall wc = new WeatherCall();
		PropertyUtils p = new PropertyUtils("F:\\workspace_math\\weather\\src\\main\\resources\\appInfo\\appKey.properties");
		String appCode = p.getValue("AppCode");
		wc.setAppCode(appCode);
		String result=wc.callWeather("ÓÀ´¨Çø");
		System.out.println(result);
	}
	
}
