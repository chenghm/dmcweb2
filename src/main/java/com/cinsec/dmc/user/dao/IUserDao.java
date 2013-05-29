package com.cinsec.dmc.user.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.cinsec.dmc.user.entity.User;

public interface IUserDao {
    User getUser(int id);

    User findUserByUsername(String username);

    List<User> getUsers(int pageNo);
    
   long getUsersCount();

    int createUser(User user);

    void deleteUser(int id);

    void deleteUsers(List<Integer> ids);

    int modifyUser(User user);
    
    Collection<? extends GrantedAuthority> getAuthorities(User user);
    
     Map<Integer, String> getRoles();

}
