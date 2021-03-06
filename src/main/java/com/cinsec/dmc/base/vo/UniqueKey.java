/**
 * 
 */
package com.cinsec.dmc.base.vo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huangweijin
 *
 * 2011-5-14 上午09:39:49
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.FIELD})
public @interface UniqueKey{
	
}
