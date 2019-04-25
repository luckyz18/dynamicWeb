package com.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadtestServlet extends HttpServlet {
	public static final long serialVersionUID = 1L;

	/**
	 * upload 文件上传 test
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//磁盘文件项工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//核心上传对象
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//解析请求
		try {
			List<FileItem> list = upload.parseRequest(request);
			
			//遍历请求 
			for (FileItem fi : list) {
				//普通上传组件 还是文件上传组件
				if (fi.isFormField()) {
					String fieldName = fi.getFieldName();   //name 属性的值
					String value = fi.getString("utf-8");   // 值
					
					System.out.println("fieldname:"+fieldName+"   "+ "value:"+value);
					
				}else {
					String fieldName = fi.getFieldName();
					String fname = fi.getName();  //文件名称
					InputStream is = fi.getInputStream();    //文件内容
					
					System.out.println("fieldName:"+fieldName+"fname:"+fname+"is:"+is);
 				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	
	}

}
