package com.cinsec.dmc.user.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Repository;

import com.cinsec.dmc.base.util.Constants;
import com.cinsec.dmc.user.dao.IUserDao;
import com.cinsec.dmc.user.entity.Role;
import com.cinsec.dmc.user.entity.User;

@Repository
public class UserDaoImpl implements IUserDao,Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3956755670654127843L;
    @PersistenceContext(unitName = "MYSQLPU")
    private EntityManager entityManager;

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "select u from User u where u.username =:username";
        Query query = entityManager.createQuery(sql);
        query.setParameter("username", username);
        List<User> users = (List<User>) query.getResultList();
        if (CollectionUtils.isNotEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUsers(int pageNo) {
        Query query = entityManager.createQuery("from User u order by u.updatedTime desc");
        query.setFirstResult((pageNo - 1) * Constants.PAGE_SIZE).setMaxResults(Constants.PAGE_SIZE);
        return query.getResultList();
    }

    @Override
    public int createUser(User user) {
        entityManager.persist(user);
        return 1;
    }

    @Override
    public void deleteUser(int id) {
      User user = entityManager.find(User.class, id);
      if(user!=null){
          entityManager.remove(user);
      }
    }

    @Override
    public void deleteUsers(List<Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public int modifyUser(User user) {
        entityManager.merge(user);
        return 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(User user) {

        String sql = " select u.role.name from User u where u.username = :username";
        Query query = entityManager.createQuery(sql);
        query.setParameter("username", user.getUsername());
        String authoritie = (String) query.getSingleResult();
        return AuthorityUtils.createAuthorityList(authoritie);
    }

    @Override
    public long getUsersCount() {
        Query query = entityManager.createQuery("select count(u) from User u");
        return ((Long) query.getSingleResult()).longValue();
    }

    @Override
    public Map<Integer,String> getRoles() {
        Query query = entityManager.createQuery("select r from Role r");
        List<Role> roles =  query.getResultList();
        if(CollectionUtils.isEmpty(roles)){
            return null;
        }
        Map<Integer,String> result = new HashMap<Integer, String>(roles.size());
        for(Role role:roles){
            result.put(role.getId(), role.getDescn());
        }
        return result;
    }
    
}
