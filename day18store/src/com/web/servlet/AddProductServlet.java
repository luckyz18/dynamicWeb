package com.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.domain.Category;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;
import com.utils.UUIDUtils;
import com.utils.UploadUtils;

/**
 * 	添加商品  文件上传 upload 实现 
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HashMap<String, Object> map = new HashMap<>();
		
		//获取参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> list = upload.parseRequest(request); //解析请求
			for (FileItem fi : list) {
				if (fi.isFormField()) {
					//普通上传组件的话
					map.put(fi.getFieldName(), fi.getString("utf-8"));
				} else {
					//文件上传组件的话
					
					//获取文件名称
					String fname = fi.getName();   //由于浏览器不同  可能文件名称带路径
					String realName = UploadUtils.getRealName(fname);
					
					//文件随机名称
					String uuidName = UploadUtils.getUUIDName(realName);
					
					//保存到磁盘上  写的是服务器的位置
					String path = this.getServletContext().getRealPath("/products/1");  
//					String path = "E:\\Program\\GithubProgram\\dynamicweb\\dynamicWeb\\day18store\\WebContent\\products\\1"; 有错
					System.out.println("path:"+path);
					
					InputStream is = fi.getInputStream();
					FileOutputStream os = new FileOutputStream(new File(path, uuidName));
					IOUtils.copy(is, os);
					os.close();
					is.close();
					
					//删除临时文件
					fi.delete();
					
					//写路径到数据库中 == 写这个字段的值
					map.put(fi.getFieldName(), "/products/1/"+uuidName);

				}
			}
			
			//封装参数
			Product p=new Product();
			BeanUtils.populate(p, map);
			
			/**
			 * private String pid;
				private String pname;
				private double market_price;
				
				private double shop_price;
				private String pimage;
				private Date pdate;
				
				private Integer is_hot;  //1热门 0不热门
				private String pdesc;
				private Integer pflag;  //1下架 0未下架
				
				private Category category;  //属于哪一个分类
			 */
			p.setPid(UUIDUtils.getId());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
		    p.setPdate(sdf.parse(date));
			
			Category c = new Category();
			c.setCid((String) map.get("cid"));
			p.setCategory(c);
			
			//service
			ProductService ps = (ProductService) BeanFactory.getbean("ProductService");
			ps.add(p);
			
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");  //重定向 避免重复提交
	
		} catch ( Exception  e) {
			e.printStackTrace();
			request.setAttribute("msg", "文件上传失败");
			request.getRequestDispatcher("/jsp/msg.jsp").forward(request, response);
			return;
		}  
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

}
