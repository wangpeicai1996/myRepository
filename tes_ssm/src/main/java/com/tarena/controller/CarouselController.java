package com.tarena.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tarena.vo.Result;

@Controller
@RequestMapping("/carousel")
public class CarouselController {
	@RequestMapping(value="/carouselImage",method=RequestMethod.GET)
	@ResponseBody
	public List<String> carouuselImage(HttpServletRequest request) {
		List<String> list=new ArrayList<String>();
		String realPath=request.getServletContext().getRealPath("/carouselimage");
		System.out.println("realPath="+realPath);
		
		File file=new File(realPath);
		if(file.exists()) {
			String[] contents=file.list();
			for(String content:contents) {
				if(new File(realPath+File.separator+content).isFile()) {
					list.add(content);
					System.out.println("content="+content);
				}
			}
		}else {
			list.add("no carousel image");
		}
		return list;
		
	}
}
