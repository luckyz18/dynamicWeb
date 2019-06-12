package com.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.domain.Customer;
import com.domain.Dict;
import com.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.CustomerService;
import com.utils.FastJsonUtil;
import com.utils.UploadUtils;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Customer customer = new Customer();
	//封装数据
	@Override
	public Customer getModel() {
		return customer;
	} 
	
	/*文件上传*/
	private File upload;  /* 要上传的文件 */
	private String uploadFileName;  /* 文件的名称 */
	private String uploadContentType;  /* 文件的MIME类型 */
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 添加客户
	 * 包括文件上传
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException {
		if (uploadFileName != null) {
			//文件上传
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			String path="E:\\Tool\\apache-tomcat-7.0.52\\wtpwebapps\\upload\\";
			File file = new File(path+uuidName);
			FileUtils.copyFile(upload, file);  //简单的方式
			//保存到客户表
			customer.setFilePath(path+uuidName);
		}
		
		customerService.add(customer);
		return "findByPageAction";
	}
	/**
	 * 	客户列表
	 * 	  分页查询
	 * @return
	 */
	private Integer pageSize = 2;
	public void setPageSize(Integer pageSize) {
		if (pageSize != null) {
			this.pageSize = pageSize;
		}
	}
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if (pageCode != null) {
			this.pageCode = pageCode;
		}
	}
	public String findByPage() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		String name = customer.getCust_name();
		if (name != null && !name.trim().isEmpty()) {
			criteria.add(Restrictions.like("cust_name", "%"+name+"%"));
		}
		Dict level = customer.getCust_level();
		if (level != null && !level.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("cust_level.dict_id", level.getDict_id()));
		}
		Dict source = customer.getCust_source();
		if (source != null && !source.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("cust_source.dict_id", source.getDict_id()));
		}
		
		//接收参数
		PageBean<Customer> page = customerService.findByPage(pageCode,pageSize,criteria);
		
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		return "customer_list";
	}
	
	/**
	 * 	跳转到添加客户页面
	 * @return
	 */
	public String initAddUI() {
		return "initAddUI";
	}
	
	/**
	 * 删除客户
	 * 同时要删除上传的文件
	 */
	public String delete() {
		customer = customerService.findById(customer.getCust_id());
		if (customer == null) {
			return null;
		}
		String filePath = customer.getFilePath();
		if (filePath!=null) {
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
		}
		customerService.delete(customer);
		return "delete";
	}
	
	/**
	 * 修改客户 先查跳转到修改页面
	 * @return
	 */
	public String initUpdate() {
		customer = customerService.findById(customer.getCust_id()); /*customer本来就在值栈中*/
		return "initUpdate";
	}
	/**
	 * 修改客户
	 * @throws IOException 
	 */
	public String update() throws IOException {
		if (uploadFileName != null) {
			//上传了新的文件  先删除旧的文件
			String oldFilePath = customer.getFilePath();
			if (oldFilePath!=null && !oldFilePath.trim().isEmpty()) {
				File file = new File(oldFilePath);
				file.delete();
			}
			//上传新文件
			String path="E:\\Tool\\apache-tomcat-7.0.52\\wtpwebapps\\upload\\";
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			File f = new File(path+uuidName);
			FileUtil.copyFile(upload, f);
			//保存到数据库
			customer.setFilePath(path+uuidName);
		}
		customerService.update(customer);
		return "update_result";
	}
	
	public String findAll() {
		List<Customer> list = customerService.findAll();
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return null;
	}
	
}
