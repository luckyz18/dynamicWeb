package com.web.action;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.domain.Customer;
import com.domain.Linkman;
import com.domain.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.LinkmanService;

public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman>{

	private static final long serialVersionUID = 1L;
	
	private Linkman linkman = new Linkman();
	@Override
	public Linkman getModel() {
		return linkman;
	}
	
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	/**
	 *	联系人列表
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
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		String lkm_name = linkman.getLkm_name();
		if (lkm_name!=null && !lkm_name.trim().isEmpty()) {
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		Customer c = linkman.getCustomer();
		if (c!=null && c.getCust_id()!=null) {
			criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
		}
		
		PageBean<Linkman> pageBean = linkmanService.findByPage(pageSize,pageCode,criteria);
		ActionContext.getContext().getValueStack().set("page", pageBean);
		return "page";
	}
	
	/**
	 * 新增联系人
	 */
	public String initAdd() {
		
		return "initAdd";
	}
	public String add() {
		linkmanService.add(linkman);
		return "add";
	}

}
