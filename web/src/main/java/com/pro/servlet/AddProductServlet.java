package com.pro.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.pro.constant.Constant;
import com.pro.domain.Category;
import com.pro.domain.Product;
import com.pro.service.ProductService;
import com.pro.utils.BeanFactory;
import com.pro.utils.UUIDUtils;
import com.pro.utils.UploadUtils;

/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.使用fileupload保存图片和将商品的信息放入map中
			//创建map集合，存放商品信息
			Map<String,Object> map=new HashMap<>();
			//创建磁盘文件工厂
			DiskFileItemFactory factory=new DiskFileItemFactory();
			//创建核心上传对象
			ServletFileUpload upload=new ServletFileUpload(factory);
			//解析request对象
			List<FileItem> list=upload.parseRequest(request);
			//遍历集合，获取每一个文件项
			for(FileItem fi:list) {
				//获取name属性值
				String key=fi.getFieldName();
				//判断是否是普通的上传组件
				if(fi.isFormField()) {
					//普通文件项
					map.put(key, fi.getString("utf-8"));
				}else {
				//文件项
					//获取文件的名称
					String name = fi.getName();
					if(name!="") {
					//获取文件真实名称
					String realName = UploadUtils.getRealName(name);
					//获取文件随机名称
					String uuidName = UploadUtils.getUUIDName(realName);
					//获取文件随机目录
					String dir = UploadUtils.getDir();
					//获取文件内容(输入流)
					InputStream is = fi.getInputStream();
					//创建输出流
					String productPath = getServletContext().getRealPath("products");
					File dirFile=new File(productPath,dir);
					if(!dirFile.exists()) {
						dirFile.mkdirs();
					}
					FileOutputStream fos = new FileOutputStream(new File(dirFile,uuidName));
					//拷贝到服务器目录
					IOUtils.copy(is, fos);
					//释放资源
					fos.close();
					is.close();
					//释放临时文件
					fi.delete();
					//将商品的路径放入map
					map.put(key, "products"+dir+"/"+uuidName);
					}
				}
			}
			
			//2.封装product对象
			Product p=new Product();
			map.put("pid", UUIDUtils.getId());
			map.put("pdate", new Date());;
			map.put("pflag", Constant.PRODUCT_IS_UP);
			BeanUtils.populate(p, map);
			Category c=new Category();
			c.setCid((String)map.get("cid"));
			p.setCategory(c);
			//3.调用service
			ProductService ps=(ProductService) BeanFactory.getBean("ProductService");
			ps.save(p);
			//4.重定向
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll&state=onsale");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存商品失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
