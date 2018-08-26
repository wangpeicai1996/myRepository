package pcwang.weather.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import pcwang.weather.utils.HttpUtils;

public class CityCall {

	public  String  callCity() {
	    String host = "https://jisutqybmf.market.alicloudapi.com";
	    String path = "/weather/city";
	    String method = "GET";
	    String appcode = "1370629459b24932b4e9d8f08a661987";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    
	    String json="";
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	//System.out.println(response.toString());
	    	//获取response的body
	    	json=EntityUtils.toString(response.getEntity());
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    System.out.println(json);
	    return json;
	}
}
