package com.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.domain.PageBean;
import com.domain.SaleVisit;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.service.SaleVisitService;

@SuppressWarnings("all")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{

	private static final long serialVersionUID = 1L;

	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	//筛选时截止时间，为了数据回显， 没有封装到model里，所以提供get方法
	private Date visit_end_time;
	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	@Resource(name = "saleVisitService")
	private SaleVisitService saleVisitService;
	

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
	
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);
		if (saleVisit.getVisit_time()!=null) {
			criteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		System.out.println(saleVisit.getVisit_time());

		if (visit_end_time!=null) {
			criteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		System.out.println(visit_end_time);
		
		PageBean<SaleVisit> page = saleVisitService.findByPage(pageCode,pageSize,criteria);
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("page", page);
		return "findByPage";
	}
	
	public String saveUI() {
		return "saveUI";
	}
	public String save() {
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
	
}
