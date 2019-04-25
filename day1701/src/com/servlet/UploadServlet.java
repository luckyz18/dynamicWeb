package com.servlet;

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

/**
 * 	上传文件
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		System.out.println(username);
		
		Part part = request.getPart("f");
		
		//获取文件名
		String dis = part.getHeader("Content-Disposition");
		String fname = dis.substring(dis.indexOf("filename=")+10,dis.length()-1);
//		String fname = dis.substring(dis.lastIndexOf("=")+2,dis.length()-1);
		System.out.println(fname);
		
		//获取流==获取文件内容
		InputStream is = part.getInputStream();
		FileOutputStream os = new FileOutputStream("E:/临时下载/" + fname);
		IOUtils.copy(is, os);	
		//关闭流
		os.close();
		is.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
