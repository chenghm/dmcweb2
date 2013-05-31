package com.cinsec.dmc.user.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinsec.dmc.base.exception.DmcBizException;
import com.cinsec.dmc.user.dao.IUserDao;
import com.cinsec.dmc.user.entity.User;
import com.cinsec.dmc.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private User currentUser;

    @Autowired
    public UserServiceImpl(IUserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUser(int id) {

        return userDao.getUser(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    @Transactional
    public int createUser(User user) throws DmcBizException {
        User u = findUserByUsername(user.getUsername());
        if (u != null) {
            throw new DmcBizException("用户名已存在！");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Integer currentUserId = this.getCurrentUser().getId();
        Date currentTime = new Date();
        user.setCreatedTime(currentTime);
        user.setCreatedUserId(currentUserId);
        user.setUpdatedTime(currentTime);
        user.setUpdatedUserId(currentUserId);
        userDao.createUser(user);
        return 1;
    }

    @Override
    public List<User> getUsers(int pageNo) {
        return userDao.getUsers(pageNo);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void deleteUsers(String userIds) {
        String[] ids = userIds.split(",");
        for (String id : ids) {
            deleteUser(Integer.valueOf(id));
        }
    }

    @Override
    @Transactional
    public int modifyUser(User user) throws DmcBizException {
        User u = findUserByUsername(user.getUsername());
        if (u != null && u.getId() != user.getId()) {
            throw new DmcBizException("用户名已存在！");
        }
        String password = userDao.getUser(user.getId()).getPassword();// 原有password
        if (!password.equals(user.getPassword())) {// 如果密码有修改 就加密
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }

        Integer currentUserId = this.getCurrentUser().getId();
        Date currentTime = new Date();
        user.setUpdatedTime(currentTime);
        user.setUpdatedUserId(currentUserId);
        userDao.modifyUser(user);
        return 1;
    }

    @Override
    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }

        return (User) authentication.getPrincipal();
    }

    @Override
    public boolean validatePassword(String password) {
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println( currentUser.getPassword());
        System.out.println(encodedPassword);
        return currentUser.getPassword().equals(encodedPassword);
    }

    @Override
    public void setCurrentUser(User user) {
        Collection<? extends GrantedAuthority> authorities = userDao.getAuthorities(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public long getUsersCount() {
        return userDao.getUsersCount();
    }

    @Override
    public Map<Integer, String> getRoles() {
        return userDao.getRoles();
    }

    @Override
    public void modifyPassword(String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        currentUser.setPassword(encodedPassword);
        currentUser.setUpdatedTime(new Date());
        currentUser.setUpdatedUserId(currentUser.getId());
        userDao.modifyUser(currentUser);
    }

}
