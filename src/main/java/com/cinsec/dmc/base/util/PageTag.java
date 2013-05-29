package com.cinsec.dmc.base.util;

import com.opensymphony.xwork2.util.ValueStack;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

/**
 * 分页标签
 * 
 */
public class PageTag extends ComponentTagSupport {
	
	private static final long serialVersionUID = 7242423813230124088L;
	//这里传递的参数需要用字符串的形式
	private String pageNo;
	private String total;
	private String includes;

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new Pages(arg0);
	}

	protected void populateParams() {
		super.populateParams();

		Pages pages = (Pages) component;
		pages.setPageNo(pageNo);
		pages.setIncludes(includes);
		pages.setTotal(total);

	}
}