package com.response.download;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.utils.DownLoadUtils;

import sun.management.resources.agent;

/**
 * 下载文件
 */
public class DownloadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		//获取文件名称
		String filename = request.getParameter("name");
		filename = new String(filename.getBytes("iso-8859-1"),"utf-8");
//		  这里显示只是服务器正常显示filename 但是浏览器显示的是么有中文
//		System.out.println(filename+"  ========");
		
		//设置文件的mimeType
		String mimeType = context.getMimeType(filename );
		response.setContentType(mimeType);
		
		//设置下载头信息
//		response.setHeader("content-disposition", "attachment;filename="+filename);
		
		
//		下面这种方式，下载时显示的文件名称 一般的浏览器需要提供utf8的文件名称  ，才可以正常显示下载的文件名  ，不推荐   因为不兼容火狐 这是下载时显示的文件名称
//		response.setHeader("content-disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
		
//		//使用工具类 ，推荐   方案1		
		String filename_ = DownLoadUtils.getName(request.getHeader("User-Agent"), filename);	
		response.setHeader("content-disposition", "attachment;filename="+filename_);
		
		//方案2 
//		String filename_ =new String(filename.getBytes("gbk"),"iso-8859-1");
//		response.setHeader("content-disposition", "attachment;filename="+filename_);
		
		//对拷流
		//获取输入流 输出流
		ServletOutputStream os = response.getOutputStream();
		InputStream is = context.getResourceAsStream("/download/"+filename);
//		int len = -1;
//		byte[] b = new byte[1024];
//		while((len=is.read(b))!=-1) {
//			os.write(b, 0, len);
//		}
//		os.close();   //关闭的顺序
//		is.close();
		
		IOUtils.copy(is, os);
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
