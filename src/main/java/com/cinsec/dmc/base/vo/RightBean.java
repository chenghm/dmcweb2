/**
 * 
 */
package com.cinsec.dmc.base.vo;

import java.util.HashMap;

import com.cinsec.dmc.base.util.AfwConstant;

/**
 * @author huangweijin
 *
 * 2011-6-27 下午02:11:43
 */
public class RightBean extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6059046501579653130L;

	@Override
	public Object get(Object key) {
		
		if(SysInfoBean.getSysInfoBean().isSuperRole()){
			return AfwConstant.RIGHT_YES;
		}
		
		Object object = super.get(key);
		if(null == object){
			object = AfwConstant.RIGHT_NO;
		}else{
			object = AfwConstant.RIGHT_YES;
		}
		
		return object;
	}

}
