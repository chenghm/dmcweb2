/**
 * 
 */
package com.cinsec.dmc.base.service;

import java.util.List;

import com.cinsec.dmc.base.vo.ReturnMessage;
import com.cinsec.dmc.base.vo.SysInfoBean;

/**
 * Created by MyElipse
 * 
 * @author huangweijin Date: 2011-3-22 Time: 10:47:54 Declare：Service基类
 */
public interface BaseAppService<T extends Object> extends BaseService<T> {

    /**
     * Declare：增加对象
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> addApp(T obj);

    /**
     * Declare：增加复合对象
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> addAppCom(T obj);

    /**
     * Declare：增加复合对象集合
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> addAppComList(List<T> objs);

    /**
     * Declare：增加对象集合
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> addAppList(List<T> objs);

    /**
     * Declare：增加或更新对象,根据ID存在则修改,不存在则新增
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> addOrUpdateApp(T obj);

    /**
     * Declare：增加或更新对象,根据对象Id存在则修改,不存在则新增
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> addOrUpdateAppList(List<T> objs);

    /**
     * Declare：删除对象
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> deleteApp(T obj);

    /**
     * Declare：删除对象集合
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> deleteAppList(List<T> objs);

    /**
     * Declare：取得Spring上下文Bean
     * 
     * @param name
     *            名称
     * @return Object 上下文Bean
     */
    @Override
    Object getBean(String name);

    /**
     * Declare：取得当前系统信息
     * 
     * @param void
     * @return SysInfoBean 系统信息
     */
    @Override
    SysInfoBean getSysInfo();

    /**
     * Declare：逻辑删除对象
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> logicDeleteApp(T obj);

    /**
     * Declare：逻辑删除对象集合
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> logicDeleteAppList(List<T> objs);

    /**
     * Declare：以对象为条件查询
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> queryApp(T obj);

    /**
     * Declare：以对象为条件查询
     * 
     * @param obj
     *            对象
     * @return List<T> 返回对象列表
     */
    List<T> queryAppList(T obj);

    /**
     * Declare：以对象为条件查询
     * 
     * @param obj
     *            对象
     * @return T 返回对象
     */
    T queryAppSingle(T obj);

    /**
     * Declare：更新对象
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> updateApp(T obj);

    /**
     * Declare：更新对象集合
     * 
     * @param obj
     *            对象
     * @return ReturnMessage<T> 返回信息
     */
    ReturnMessage<T> updateAppList(List<T> objs);
}
