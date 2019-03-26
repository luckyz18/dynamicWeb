package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.utils.UploadUtils;

import sun.nio.ch.IOUtil;

/**
 *	 随机文件名  随机路径
 */
@WebServlet("/upload2")
@MultipartConfig
public class Upload2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		System.out.println(username);
		
		Part part = request.getPart("f");
		//2.1获取文件名称
//		String dis = part.getHeader("Content-Disposition");
//		String realname = dis.substring(dis.indexOf("filename=")+10,dis.length()-1);
//		System.out.println("文件名称："+realname);
		
		String dis = part.getHeader("Content-Disposition");
		String realname = dis.substring(dis.indexOf("filename=")+10,dis.length()-1);
		System.out.println(realname);
		//2.2获取文件随机名称
		String uuidName = UploadUtils.getUUIDName(realname);
		System.out.println(uuidName);
		
		//2.3 获取文件存放目录
		String dir = UploadUtils.getDir(realname);
		String realPath = this.getServletContext().getRealPath("/upload"+dir);  //拼接成完整路径
		
		
		File file = new File(realPath);
		
		if (!file.exists()) {
			file.mkdirs();
		}
		
		System.out.println("文件的目录:"+realPath);
		
		//流
		InputStream is = part.getInputStream();
		FileOutputStream os =  new FileOutputStream(new File(file,uuidName));   //构造器   跟上一个文件的目录  +  真实名称
		IOUtils.copy(is, os);
		os.close();
		is.close();
		
		//删除临时文件
		part.delete();	
		//test git
	
	}	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
