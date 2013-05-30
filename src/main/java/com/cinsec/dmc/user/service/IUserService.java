package com.cinsec.dmc.user.service;

import java.util.List;
import java.util.Map;

import com.cinsec.dmc.base.exception.DmcBizException;
import com.cinsec.dmc.user.entity.User;

public interface IUserService {

    User getUser(int id);

    User findUserByUsername(String username);

    int createUser(User user) throws DmcBizException;

    List<User> getUsers(int pageNo);

    void deleteUser(int id);

    void deleteUsers(String ids);

    int modifyUser(User user) throws DmcBizException;

    User getCurrentUser();

    void setCurrentUser(User user);

    long getUsersCount();

    Map<Integer, String> getRoles();

}
