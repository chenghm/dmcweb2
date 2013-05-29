package com.cinsec.dmc.base.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

/**
 * Created by MyElipse
 * 
 * @author huangweijin Date: 2011-3-22 Time: 10:47:54 Declare：SpringBean工厂
 */
public class ContextFactory implements ApplicationContextAware {

   
    // Spring上下文
    private static ApplicationContext appContext;
    /*
     * 
     */
    private static final String ZH_CN = "zh_CN";

    /**
     * 取当前应用项目路径
     */
    public static String getBasePath() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return ContextFactory.getHttpDomain() + request.getContextPath();
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * Declare：取Spring上下文Bean
     * 
     * @param beanId
     *            bean名称
     * @return Object 实体
     */
    public static Object getBean(String beanId) {

        if (beanId == null) {
            return null;
        }

        if (null == ContextFactory.appContext) {
            return null;
        }

        return ContextFactory.appContext.getBean(beanId);
    }

    /**
     * 取类路径
     * 
     * @return
     */
    public static String getClassPath() {

        String classPath = Thread.currentThread().getContextClassLoader().getResource(AppConstant.UNIX_SEP).toString();
        return classPath.substring(6);

    }

    /**
     * 取上下文相对路径
     * 
     * @return
     */
    public static String getContextPath() {
        try {
            return ServletActionContext.getServletContext().getContextPath();
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * 取上下文真实路径
     * 
     * @return
     */
    public static String getContextRealPath() {
        try {
            return ServletActionContext.getServletContext().getRealPath(AppConstant.UNIX_SEP);
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * 取当前请求URL
     * 
     * @return
     */
    public static String getCurRequestUrl() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            String curModuleUrl = request.getContextPath() + request.getServletPath();
            String queryString = request.getQueryString();
            if (!DataValidater.isStrEmpty(queryString)) {
                curModuleUrl = curModuleUrl + AppConstant.QUS_SIGN + queryString;
            }
            return curModuleUrl;
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * Declare：取Context中对象
     * 
     * @param name
     *            名称
     * @return Object 对象
     */
    public static Object getFromContext(String name) {

        try {
            return ActionContext.getContext().getApplication().get(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Declare：取Request中对象
     * 
     * @param name
     *            名称
     * @return Object 对象
     */
    public static Object getFromRequest(String name) {

        try {
            return ServletActionContext.getRequest().getAttribute(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Declare：取Session中对象
     * 
     * @param name
     *            名称
     * @return Object 对象
     */
    public static Object getFromSession(String name) {

        try {
            return ActionContext.getContext().getSession().get(name);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getHttpDomain() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return request.getScheme() + AppConstant.MAO_SIGN + AppConstant.UNIX_SEP2 + request.getServerName()
                    + AppConstant.MAO_SIGN + request.getServerPort();
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * 取I18N值
     * 
     * @param value
     * @return
     */
    public static String getI18nValue(String value) {
        Locale.getDefault();
        return LocalizedTextUtil.findDefaultText(value, new Locale(ContextFactory.ZH_CN));
    }

    /**
     * 取I18N值
     * 
     * @param value
     * @param args
     * @return
     */
    public static String getI18nValue(String value, String[] args) {

        return LocalizedTextUtil.findDefaultText(value, new Locale(ContextFactory.ZH_CN), args);
    }

    public static String getLocalAddr() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return request.getLocalAddr();
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * 从Request得到参数值
     * 
     * @param name
     */
    public static String getParamFromRequest(String name) {

        try {
            return ServletActionContext.getRequest().getParameter(name);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从Request得到参数数组值
     * 
     * @param name
     */
    public static String[] getParamsFromRequest(String name) {

        try {
            return ServletActionContext.getRequest().getParameterValues(name);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getRemoteAddr() {

        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            return request.getRemoteAddr();
        } catch (Exception e) {
            return AppConstant.EMPTY_STR;
        }
    }

    /**
     * 从上下文移除属性
     * 
     * @param name
     */
    public static void removeFromContext(String name) {

        try {
            ServletActionContext.getServletContext().removeAttribute(name);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 从Request移除属性
     * 
     * @param name
     */
    public static void removeFromRequest(String name) {

        try {
            ServletActionContext.getRequest().removeAttribute(name);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 从Session移除属性
     * 
     * @param name
     */
    public static void removeFromSession(String name) {

        try {
            ServletActionContext.getRequest().getSession().removeAttribute(name);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * 设置属性于上下文
     * 
     * @param name
     * @param obj
     * @return void
     */
    public static void setToContext(String name, Object obj) {

        try {
            ActionContext.getContext().getApplication().put(name, obj);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Declare：设置值进Request对象中
     * 
     * @param name
     *            名称
     * @param obj
     *            对象
     * @return void
     */
    public static void setToRequest(String name, Object obj) {

        try {
            ServletActionContext.getRequest().setAttribute(name, obj);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Declare：设置值进Session对象中
     * 
     * @param name
     *            名称
     * @param obj
     *            对象
     * @return void
     */
    public static void setToSession(String name, Object obj) {

        try {
            ActionContext.getContext().getSession().put(name, obj);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Declare：保存Spring上下文
     * 
     * @param appContext
     *            Spring上下文
     * @return void
     */
    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {

        ContextFactory.appContext = appContext;
    }
}
