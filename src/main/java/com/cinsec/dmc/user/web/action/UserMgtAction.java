package com.cinsec.dmc.user.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.base.exception.DmcBizException;
import com.cinsec.dmc.base.util.Constants;
import com.cinsec.dmc.user.entity.Role;
import com.cinsec.dmc.user.entity.User;
import com.cinsec.dmc.user.service.IUserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope(value = "prototype")
public class UserMgtAction extends ActionSupport {
    private IUserService userService;

    // private UserVo userVo;
    // private Map<String,Object> dataMap;

    @Autowired
    public UserMgtAction(IUserService userService) {
        this.userService = userService;
    }

    private long pages;
    private int pageNo = 1;
    private Map<Integer, String> roles;
    private String username;
    private String password;
    private int roleId;
    private String usernameMsg;
    private String passwordMsg;
    private String roleIdMsg;
    private String msg;
    private int userId;
    private List<Integer> userIds;
    private User user;
    private int success = 0;

    private static final long serialVersionUID = 5239501706741520424L;
    private List<User> users;

    public String findAll() throws Exception {
        // users = userService.getUsers(pageNo);
        return SUCCESS;
    }

    public long getPages() {
        long total = userService.getUsersCount();
        pages = (total - 1) / Constants.PAGE_SIZE + 1;
        return pages;
    }

    public String initAdd() {
        return "initAddSuccess";
    }

    public String add() {
        if (!validateData()) {
            return Action.INPUT;
        }
        /*
         * User user = new User(); user.setUsername(username); user.setPassword(password); Role role = new Role();
         * role.setId(roleId); role.addUser(user); user.setRole(role);
         */
        try {
            userService.createUser(user);
            user = new User();
            // this.addActionMessage( "添加成功！");
        } catch (DmcBizException e) {
            this.addActionMessage("添加失败：" + e.getMessage());
            // msg = "添加失败：" + e.getMessage();
            return Action.INPUT;
        }
        return "addSuccess";

    }

    public String delete() {
        if (userId == 0) {
            return null;
        }
        userService.deleteUser(userId);
        return "deleteSuccess";
    }

    public String batchDelete() {
        if (CollectionUtils.isEmpty(userIds)) {
            return null;
        }

        userService.deleteUsers(userIds);
        return "deleteSuccess";
    }

    public String initModify() {
        if (userId == 0) {
            return null;
        }
        user = userService.getUser(userId);
        return "initModifySuccess";

    }

    public String modify() {
        if (!validateData()) {
            return Action.INPUT;
        }

        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        Role role = new Role();
        role.setId(roleId);
        role.addUser(user);
        user.setRole(role);
        try {
            userService.modifyUser(user);
        } catch (DmcBizException e) {
            this.addActionMessage("修改失败：" + e.getMessage());
            // msg = "修改失败：" + e.getMessage();
            return Action.INPUT;
        }
        return "modifySuccess";

    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<User> getUsers() {
        users = userService.getUsers(pageNo);
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<Integer, String> getRoles() {
        roles = userService.getRoles();
        return roles;
    }

    // public UserVo getUserVo() {
    // return userVo;
    // }
    //
    // public void setUserVo(UserVo userVo) {
    // this.userVo = userVo;
    // }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    private boolean validateData() {
        boolean result = true;

        if (StringUtils.isEmpty(user.getUsername())) {

            this.addFieldError("user.username", "用户名不能为空！");
            // usernameMsg = "用户名不能为空！";
            result = false;
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            this.addFieldError("user.password", "密码不能为空！");
            // passwordMsg = "密码不能为空！";
            result = false;
        } else if (user.getPassword().length() < 6) {
            this.addFieldError("user.password", "密码长度不能小于6位！");
            // passwordMsg = "密码长度不能小于6位！";
            result = false;
        }

        if (user.getRole().getId() == 0) {
            this.addFieldError("user.role.id", "请选择角色！！");
            // roleIdMsg = "清选择角色！";
            result = false;
        }

        return result;
    }

    public String getUsernameMsg() {
        return usernameMsg;
    }

    public void setUsernameMsg(String usernameMsg) {
        this.usernameMsg = usernameMsg;
    }

    public String getPasswordMsg() {
        return passwordMsg;
    }

    public void setPasswordMsg(String passwordMsg) {
        this.passwordMsg = passwordMsg;
    }

    public String getRoleIdMsg() {
        return roleIdMsg;
    }

    public void setRoleIdMsg(String roleIdMsg) {
        this.roleIdMsg = roleIdMsg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        if (user.getRole() == null) {
            user.setRole(new Role());
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

}
