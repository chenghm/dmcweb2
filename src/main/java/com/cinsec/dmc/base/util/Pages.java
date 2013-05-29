package com.cinsec.dmc.base.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.components.Component;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 分页逻辑Bean
 * 
 */
public class Pages extends Component {

	private String pageNo;
	private String total;
	private String includes;

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Pages(ValueStack arg0) {
		super(arg0);
	}

	@Override
	public boolean start(Writer writer) {

		boolean result = super.start(writer);
		StringBuilder str = new StringBuilder();
		Map<String, Object> cont = stack.getContext();
		StrutsRequestWrapper req = (StrutsRequestWrapper) cont
				.get(StrutsStatics.HTTP_REQUEST);

		String url = (String) req
				.getAttribute("javax.servlet.forward.request_uri");

		// 从ValueStack中取出数值
		Object obj = stack.findValue(pageNo);
		pageNo = String.valueOf(obj);
		obj = stack.findValue(total);
		total = String.valueOf(obj);

		StringBuilder perUrl = new StringBuilder("");
		if (includes != null && includes.trim().length() > 0) {
			String[] perm = includes.split(",");
			for (int i = 0; i < perm.length; i++) {
				String permName = perm[i];
				Object obje = stack.findValue(permName);

				perUrl.append("&");
				perUrl.append(permName);
				perUrl.append("=");
				perUrl.append(obje);
			}
		}

		//用于计算的当前页整数形式
		int cpageInt = Integer.valueOf(pageNo);
		str.append("<div class='pagination'>");
		Integer totalInt = Integer.valueOf(total);

		// 如果只有一页，则无需分页

		if (totalInt == 1) {
			str.append("<span class='current'>1</span> ");
		} else {

			// 显示上一页与第一页
			if (cpageInt == 1) {
				str.append("<span class='disabled'><< 上一页</span>");
				str.append("<span class='current'>1</span>");
			} else {
				str.append("<a href='");
				str.append(url);
				str.append("?pageNo=");
				str.append(cpageInt - 1);
				str.append(perUrl);
				str.append("'>« 上一页</a>");
				
				str.append("<a href='");
				str.append(url);
				str.append("?pageNo=1");
				str.append(perUrl);
				str.append("'>1</a>");
			}

			// 当前页超过5时第一页后面加点，因为中间相隔了第二页
			if (cpageInt - 4 > 1)
				str.append("<span class='gap'>...</span>");

			// v,v1分别代表中间页数的最小值和最大值,3表示显示当前页的前后三页
			int v = (cpageInt - 3) > 1 ? (cpageInt - 3) : 2;
			int v1 = (cpageInt + 3) < totalInt ? (cpageInt + 3) : totalInt - 1;
			if (v1 == totalInt) {
				v = totalInt - 10;
			} else if (v == 1 && v1 < totalInt) {
				v1 = totalInt > 10 ? 10 : totalInt;
			}

			// 
			for (int i = v; i <= v1; i++) {
				if (cpageInt == i) { // 当前页要加粗显示
					
					str.append("<span class='current'>");
					str.append(i);
					str.append("</span>");
				} else {
					str.append("<a href='");
					str.append(url);
					str.append("?pageNo=");
					str.append(i);
					str.append(perUrl);
					str.append("'>");
					str.append(i);
					str.append("</a>");
				}
			}

			if (cpageInt < totalInt - 4)
				str.append("<span class='gap'>...</span>");
			// 显示最后一页

			str.append("<a href='");
			str.append(url);
			str.append("?pageNo=");
			str.append(totalInt);
			str.append(perUrl);
			str.append("'>");
			str.append(totalInt);
			str.append("</a>");
			
			if (cpageInt == totalInt) {
				str.append("<span class='disabled'>下一页 >></span>");
			} else {
				str.append("<a href='");
				str.append(url);
				str.append("?pageNo=");
				str.append(cpageInt + 1);
				str.append(perUrl);
				str.append("'>下一页 >></a>");
			}
		}

		str.append("</div>");

		try {
			writer.write(str.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
