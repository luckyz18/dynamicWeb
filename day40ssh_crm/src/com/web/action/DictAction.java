package com.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.domain.Dict;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.DictService;
import com.utils.FastJsonUtil;

public class DictAction extends ActionSupport implements ModelDriven<Dict>{
	private static final long serialVersionUID = 1L;

	private Dict dict = new Dict();
	@Override
	public Dict getModel() {
		return dict;
	}
	
	private DictService dictService;
	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}
	
	/**
	 * 异步获取所有客户级别
	 * @return
	 */
	public String findByCode() {
		List<Dict> list = dictService.findByCode(dict.getDict_type_code());
//		List<Dict> list = dictService.findByCode("002");
		String jsonString = FastJsonUtil.toJSONString(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		return NONE;
	}
	
}
