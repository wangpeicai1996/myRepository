package com.pro.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CodeServlet
 */
public class CodeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void validataCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random r=new Random();
		//0.创建空白图片
		BufferedImage image=new BufferedImage(100,30,BufferedImage.TYPE_INT_RGB);
		//1.准备图片上画，获取图片的画笔
		Graphics g=image.getGraphics();
		//2.设置画笔颜色(颜色随机)
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//3.绘制矩形背景
		g.fillRect(0, 0, 100, 30);
		//4.调用自定义方法获取长度为5的字母和数字的字符串
		String number=this.getNumber(5);
		//5.把随机生成的验证码存储到session中
		HttpSession session=request.getSession();
		session.setAttribute("code", number);
		//6.重新设置画笔,准备画验证码
		g.setColor(new Color(0,0,0));
		g.setFont(new Font(null,Font.BOLD,24));
		//7.绘制验证码字符串
		g.drawString(number, 5, 25);
		//8.绘制8条干扰线
		for(int i=0;i<8;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(100), r.nextInt(30));
		}
		//9.设置响应的内容类型，图片.jpeg格式
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		//10.将图片写出到响应流中
		ImageIO.write(image, "jpeg", os);
		os.flush();
		os.close();
	}
	
	private String getNumber(int size){
		String str="ABCDEFGHIJKLMNOPKRSTUVWXYZ0123456789";
		String number="";
		Random r=new Random();
		for(int i=0;i<size;i++){
			number+=str.charAt(r.nextInt(str.length()));
		}
		return number;
	}

}
