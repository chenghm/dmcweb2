package com.cinsec.dmc.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Transient;
import javax.sql.DataSource;

import com.cinsec.dmc.base.dao.IBaseAppDao;
import com.cinsec.dmc.base.util.AppConstant;
import com.cinsec.dmc.base.vo.SysInfoBean;
import com.cinsec.dmc.base.vo.UniqueKey;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseAppDao<T extends Object, PK extends Serializable> implements IBaseAppDao<T, PK> {

    private static final int _0 = 0;

    private static Type[] getActualTypeArguments(Type t) {

        Type[] p = ((ParameterizedType) t).getActualTypeArguments();

        return p;
    }

    private static Type getGenericSuperclass(Class c) {

        Type t = c.getGenericSuperclass();

        return t;
    }

//    @Resource(mappedName = AppConstant.DATASOURCE_NAME_APP)
    private DataSource dataSource;

    private Class<T> entityClass;

//    @PersistenceContext(unitName = AppConstant.JPA_DB_UNIT_APP)
    private EntityManager entityManager;

    public BaseAppDao() {
        Class c = getClass();
        Type t = BaseAppDao.getGenericSuperclass(c);
        if (t instanceof ParameterizedType) {
            Type[] p = BaseAppDao.getActualTypeArguments(t);
            this.entityClass = (Class<T>) p[BaseAppDao._0];
        }
    }

    @Override
    public T create(T entity) throws SQLException {

        entityManager.persist(entity);

        return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#createList(java.util.List)
     */
    @Override
    public List<T> createList(List<T> entities) throws SQLException {
        List<T> list = new ArrayList<T>();
        for (T entity : entities) {
            T entityRet = this.create(entity);
            list.add(entityRet);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#excuteJpl(java.lang.String)
     */
    @Override
    public int executeJpl(String jql) {
        return this.entityManager.createQuery(jql).executeUpdate();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#excuteJpl(java.lang.String, java.util.Map)
     */
    @Override
    public int executeJpl(String jql, Map<String, Object> parVals) {
        Query query = entityManager.createQuery(jql);
        setQueryParamters(query, parVals);
        return query.executeUpdate();
    }

    private Map<String, Object> generateConParVals(T entity) throws SQLException {

        Map<String, Object> parVals = new HashMap<String, Object>();

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            Transient tra = field.getAnnotation(Transient.class);
            if (null != tra) {
                continue;
            }
            Object fieldValue = null;
            field.setAccessible(true);

            try {
                Column col = field.getAnnotation(Column.class);
                if (null != col) {
                    fieldValue = field.get(entity);
                } else {
                    JoinColumn joinCol = field.getAnnotation(JoinColumn.class);
                    if (null != joinCol) {
                        fieldValue = field.get(entity);
                    }
                }
            } catch (IllegalArgumentException e) {
                throw new SQLException(e);
            } catch (IllegalAccessException e) {
                throw new SQLException(e);
            }

            if (null != fieldValue) {
                parVals.put(field.getName(), fieldValue);
            }
        }

        return parVals;
    }

    private String generateJql(Class c, Set<String> fieldNames, String operator) {

        StringBuffer jql = new StringBuffer();
        jql.append(AppConstant.SQL_FROM).append(AppConstant.EMPTY_ONE_STR);
        jql.append(c.getName());
        StringBuffer whereJql = new StringBuffer();
        String whereKey = AppConstant.EMPTY_ONE_STR + AppConstant.SQL_WHERE + AppConstant.EMPTY_ONE_STR;

        for (String fieldName : fieldNames) {
            whereJql.append(AppConstant.EMPTY_ONE_STR).append(operator).append(AppConstant.EMPTY_ONE_STR).append(
                    fieldName).append(AppConstant.EQUAL_SIGN).append(AppConstant.MAO_SIGN).append(fieldName);
        }

        if (whereJql.length() != BaseAppDao._0) {
            jql.append(whereKey).append(whereJql.substring(whereJql.indexOf(operator) + operator.length()));
        }

        return jql.toString();
    }

    private Query generateQuery(Class c, Map<String, Object> parVals, String operator) {

        String jql = generateJql(c, parVals.keySet(), operator);
        Query query = entityManager.createQuery(jql);
        setQueryParamters(query, parVals);

        return query;
    }

    private Map<String, Object> generateReUniqueParVals(T entity) throws SQLException {

        Map<String, Object> parVals = new HashMap<String, Object>();

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            Object fieldValue = null;
            field.setAccessible(true);
            try {

                UniqueKey uniqueKey = field.getAnnotation(UniqueKey.class);
                if (null == uniqueKey) {
                    continue;
                }
                fieldValue = field.get(entity);
                if (null != fieldValue) {
                    parVals.put(field.getName(), fieldValue);
                }
            } catch (IllegalArgumentException e) {
                throw new SQLException(e);
            } catch (IllegalAccessException e) {
                throw new SQLException(e);
            }
        }

        return parVals;
    }

    private Map<String, Object> generateUniqueParVals(T entity) throws SQLException {

        Map<String, Object> parVals = new HashMap<String, Object>();

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            Object fieldValue = null;
            field.setAccessible(true);
            Id id = field.getAnnotation(Id.class);
            try {
                if (null != id) {
                    fieldValue = field.get(entity);
                } else {
                    Column col = field.getAnnotation(Column.class);
                    if (null == col) {
                        continue;
                    }
                    if (!col.unique()) {
                        continue;
                    }
                    fieldValue = field.get(entity);
                }
                if (null != fieldValue) {
                    parVals.put(field.getName(), fieldValue);
                }
            } catch (IllegalArgumentException e) {
                throw new SQLException(e);
            } catch (IllegalAccessException e) {
                throw new SQLException(e);
            }
        }

        return parVals;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#getEntitiesByIds(java.util.List)
     */
    @Override
    public List<T> getEntitiesByIds(List<PK> ids) throws SQLException {

        List<T> list = new ArrayList<T>();
        for (PK id : ids) {
            T entityRet = this.getEntityById(id);
            list.add(entityRet);
        }
        return list;
    }

    @Override
    public T getEntityById(PK id) throws SQLException {

        return entityManager.find(entityClass, id);
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Integer getQryMaxResultNum() {
        // Application app = (Application) ContextFactory.getFromSession(AppConstant.APPLICATION_NAME);
        // return app.get

        return AppConstant.RET_MAX_NUM;
    }

    @Override
    public List<T> getResultList(String jql) throws SQLException {

        return entityManager.createQuery(jql).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#getResultList(java.lang.String, java.util.Map)
     */
    @Override
    public List<T> getResultList(String jql, Map<String, Object> parVals) throws SQLException {

        Query query = entityManager.createQuery(jql);
        setQueryParamters(query, parVals);

        return query.getResultList();
    }

    @Override
    public List<T> getResultList(T entity) throws SQLException {

        Map<String, Object> parVals = generateConParVals(entity);
        Query query = generateQuery(entity.getClass(), parVals, AppConstant.SQL_AND);

        return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#getUniqueResult(java.lang.Object)
     */
    public T getReUniqueResult(T entity) throws SQLException {

        try {

            Map<String, Object> parVals = generateReUniqueParVals(entity);
            Query query = generateQuery(entity.getClass(), parVals, AppConstant.SQL_AND);

            List<T> list = query.getResultList();

            return getSingleResult(list);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    private T getSingleResult(List<T> list) {

        if (null != list && list.size() > BaseAppDao._0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public T getSingleResult(String jql) throws SQLException {
        List<T> list = getResultList(jql);

        return getSingleResult(list);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#getSingleResult(java.lang.String, java.util.Map)
     */
    @Override
    public T getSingleResult(String jql, Map<String, Object> parVals) throws SQLException {

        List<T> list = this.getResultList(jql, parVals);

        return getSingleResult(list);
    }

    @Override
    public T getSingleResult(T entity) throws SQLException {

        List<T> list = this.getResultList(entity);

        return getSingleResult(list);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#getUniqueResult(java.lang.Object)
     */
    @Override
    public T getUniqueResult(T entity) throws SQLException {

        try {

            Map<String, Object> parVals = generateUniqueParVals(entity);
            Query query = generateQuery(entity.getClass(), parVals, AppConstant.SQL_OR);

            List<T> list = query.getResultList();

            return getSingleResult(list);
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Boolean isEntitiesExist(List<T> entities) throws SQLException {

        for (T entity : entities) {
            if (isEntityExist(entity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isEntityExist(String jql) throws SQLException {

        boolean result = false;

        if (null != getSingleResult(jql)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#isEntityExist(java.lang.String, java.util.Map)
     */
    @Override
    public Boolean isEntityExist(String jql, Map<String, Object> parVals) throws SQLException {

        if (null != getSingleResult(jql, parVals)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean isEntityExist(T entity) throws SQLException {

        try {
            if (null != getUniqueResult(entity)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#isEntityExist(java.io.Serializable)
     */
    @Override
    public Boolean isEntityIdExist(PK id) throws SQLException {

        T entity = this.getEntityById(id);
        if (null == entity) {
            return true;
        } else {
            return false;
        }

    }

    private Boolean isEntityIdExist(T entity) throws IllegalArgumentException, IllegalAccessException {

        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {

            Id id = field.getAnnotation(Id.class);
            if (null == id) {
                continue;
            }
            Object fieldValue = null;
            field.setAccessible(true);

            fieldValue = field.get(entity);

            if (null != fieldValue) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#isEntityExist(java.io.Serializable)
     */
    @Override
    public Boolean isEntityIdsExist(List<PK> ids) throws SQLException {

        for (PK id : ids) {
            if (isEntityIdExist(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isReEntitiesExist(List<T> entities) throws SQLException {

        for (T entity : entities) {
            if (isReEntityExist(entity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isReEntityExist(T entity) throws SQLException {

        try {
            if (null != getReUniqueResult(entity)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#logicRemove(java.lang.Object)
     */
    @Override
    public T logicRemove(T entity) throws SQLException {

        T entityRet = this.getUniqueResult(entity);
        try {
            Field recordState = entityRet.getClass().getDeclaredField(AppConstant.RECORD_STATE);
            Field updatedTime = entityRet.getClass().getDeclaredField(AppConstant.UPDATED_TIME);
            Field updatedUserId = entityRet.getClass().getDeclaredField(AppConstant.UPDATED_USER_ID);
            recordState.setAccessible(true);
            recordState.set(entityRet, AppConstant.STOP);
            updatedTime.setAccessible(true);
            updatedTime.set(entityRet, new Date());
            updatedUserId.setAccessible(true);
            updatedUserId.set(entityRet, SysInfoBean.getSysInfoBean().getUserProfile().getEmpNumber());
        } catch (SecurityException e) {
            throw new SQLException(e);
        } catch (NoSuchFieldException e) {
            throw new SQLException(e);
        } catch (IllegalArgumentException e) {
            throw new SQLException(e);
        } catch (IllegalAccessException e) {
            throw new SQLException(e);
        } catch (Exception e) {
            throw new SQLException(e);
        }

        return entityRet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#logicRemoveList(java.util.List)
     */
    @Override
    public List<T> logicRemoveList(List<T> entities) throws SQLException {

        List<T> list = new ArrayList<T>();

        for (T entity : entities) {
            T entityRet = this.logicRemove(entity);
            list.add(entityRet);
        }
        return list;
    }

    @Override
    public T remove(T entity) throws SQLException {
        entityManager.remove(entity);

        return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#removeList(java.util.List)
     */
    @Override
    public List<T> removeList(List<T> entities) throws SQLException {

        List<T> list = new ArrayList<T>();
        for (T entity : entities) {
            T entityRet = this.remove(entity);
            list.add(entityRet);
        }
        return list;
    }

    @Override
    public T saveOrUpdate(T entity) throws SQLException {

        T entityRet = null;
        try {
            if (!isEntityIdExist(entity)) {
                entityRet = this.create(entity);
            } else {
                entityRet = this.update(entity);
            }
        } catch (IllegalArgumentException e) {
            throw new SQLException(e);
        } catch (IllegalAccessException e) {
            throw new SQLException(e);
        }
        return entityRet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#saveOrUpdateList(java.util.List)
     */
    @Override
    public List<T> saveOrUpdateList(List<T> entities) throws SQLException {
        List<T> list = new ArrayList<T>();
        for (T entity : entities) {
            T entityRet = this.saveOrUpdate(entity);
            list.add(entityRet);
        }
        return list;
    }

    private void setQueryParamters(Query query, Map<String, Object> parVals) {

        for (String fieldName : parVals.keySet()) {
            Object fieldValue = parVals.get(fieldName);
            query.setParameter(fieldName, fieldValue);
        }
    }

    @Override
    public T update(T entity) throws SQLException {
        return entityManager.merge(entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.amway.frm.base.dao.IBaseDao#updateList(java.util.List)
     */
    @Override
    public List<T> updateList(List<T> entities) throws SQLException {
        List<T> list = new ArrayList<T>();
        for (T entity : entities) {
            T entityRet = this.update(entity);
            list.add(entityRet);
        }
        return list;
    }
}
