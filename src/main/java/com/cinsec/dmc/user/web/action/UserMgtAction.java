package com.cinsec.dmc.user.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import com.cinsec.dmc.base.exception.DmcBizException;
import com.cinsec.dmc.base.util.Constants;
import com.cinsec.dmc.base.util.LogUtils;
import com.cinsec.dmc.base.web.action.BaseAction;
import com.cinsec.dmc.user.entity.Role;
import com.cinsec.dmc.user.entity.User;
import com.cinsec.dmc.user.service.IUserService;
import com.opensymphony.xwork2.Action;

@Controller
@Scope(value = "prototype")
public class UserMgtAction extends BaseAction{
    
    private static Log log = LogFactory.getLog(UserMgtAction.class);
    private IUserService userService;

    @Autowired
    public UserMgtAction(IUserService userService) {
        this.userService = userService;
    }
    
    public UserMgtAction(){
        
    }

    private String actionStatus = Action.NONE;
    private long pages;
    private int pageNo = 1;
    private Map<Integer, String> roles;
    private int userId;
    private String userIds;
    private User user;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
    private List<User> users;
//    private Map<String, String> fieldErrors = new LinkedHashMap<String, String>();
//    private List<String> actionMessages = new LinkedList<String>();
//    private String currentUser;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String findAll() {
        LogUtils.info(log, "查询用户成功："+getCurrentUser().getUsername());
        return "findSuccess";
    }

    public long getPages() {
        long total = userService.getUsersCount();
        pages = (total - 1) / Constants.PAGE_SIZE + 1;
        return pages;
    }

    public String add() {
        String result = "json";
        if (!validateData()) {
                LogUtils.debug(log,"添加用户数据验证失败："+getCurrentUser().getUsername()); 
                addDataMap();
            return result;
        }
        try {
            userService.createUser(user);
           
            user = new User();
            setActionStatus(Action.SUCCESS);
            addDataMap();
            LogUtils.info(log,"添加用户成功："+getCurrentUser().getUsername());
            return result;
        } catch (DmcBizException e) {
            this.addActionMessage("添加用户失败：" + e.getMessage());
            addDataMap();
            LogUtils.error(log,"添加用户失败："+e.getMessage()+"："+getCurrentUser().getUsername());
            return result;
        }
    }

    public String delete() {
        if (userId == 0) {
            LogUtils.warn(log,"删除用户失败：用户ID不存在："+getCurrentUser().getUsername()); 
            return null;
        }
        userService.deleteUser(userId);
        LogUtils.info(log,"删除用户成功："+getCurrentUser().getUsername()); 
        return "deleteSuccess";
    }

    public String batchDelete() {
        if (StringUtils.isEmpty(userIds)) {
            LogUtils.warn(log,"删除用户失败：用户ID不存在："+getCurrentUser().getUsername());
            return null;
        }
        LogUtils.info(log,"删除用户成功："+getCurrentUser().getUsername());
        userService.deleteUsers(userIds);
        return "deleteSuccess";
    }

    public String modifyPassword() {
        String result = "json";
        boolean flag = true;
        if (StringUtils.isEmpty(currentPassword)) {
            this.addFieldError("error_current_password", "当前密码不能为空！");
            flag = false;
        }

        if (StringUtils.isEmpty(newPassword)) {
            this.addFieldError("error_new_password", "新密码不能为空！");
            flag = false;
        } else if (newPassword.length() < 6) {
            this.addFieldError("error_new_password", "新密码长度不能小于6位！");
            flag = false;
        }
        if (StringUtils.isEmpty(confirmPassword)) {
            this.addFieldError("error_confirm_password", "确认密码不能为空！");
            flag = false;
        }
        if (!flag) {
            addDataMap();
            LogUtils.debug(log,"更新密码数据验证失败："+getCurrentUser().getUsername());
            return result;
        }

        if (!userService.validatePassword(currentPassword)) {
            this.addFieldError("error_current_password", "当前密码不正确！");
            addDataMap();
            LogUtils.debug(log,"更新密码数据验证失败："+getCurrentUser().getUsername());
            return result;
        }

        if (!newPassword.equals(confirmPassword)) {
            this.addFieldError("error_confirm_password", "两次密码不一致！");
            addDataMap();
            LogUtils.debug(log,"更新密码数据验证失败："+getCurrentUser().getUsername());
            return result;
        }
        try {
            userService.modifyPassword(newPassword);
            setActionStatus(Action.SUCCESS);
            addDataMap();
            LogUtils.info(log,"更新密码成功："+getCurrentUser().getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String initModify() {
        if (userId == 0) {
            return null;
        }
        addDataMap("user",userService.getUser(userId));
        return "json";

    }

    public String modify() {
        String result ="json";
        if (!validateData()) {
            addDataMap();
            return result;
        }
        try {
            userService.modifyUser(user);
            setActionStatus(Action.SUCCESS);
            addDataMap();
        } catch (DmcBizException e) {
            this.addActionMessage("修改失败：" + e.getMessage());
            addDataMap();
        }
        return result;

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

    private boolean validateData() {
        boolean result = true;
        String username = user.getUsername();
        if (StringUtils.isEmpty(username)) {
            this.addFieldError("error_user_username", "用户名不能为空！");
            result = false;
        }

        if (StringUtils.isEmpty(user.getPassword())) {
            this.addFieldError("error_user_password", "密码不能为空！");
            result = false;
        } else if (user.getPassword().length() < 6) {
            this.addFieldError("error_user_password", "密码长度不能小于6位！");
            result = false;
        }
        if (user.getRole().getId() == 0) {
            this.addFieldError("error_user_role_id", "请选择角色！");
            result = false;
        }
        return result;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    @js
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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

//    public Map<String, String> getFieldErrors() {
//        return fieldErrors;
//    }
//
//    public void setFieldErrors(Map<String, String> fieldErrors) {
//        this.fieldErrors = fieldErrors;
//    }

    /*
     * public Map<String, Object> getDataMap() { return dataMap; }
     */

  /*  private Map<String, String> addFieldError(String field, String message) {
        fieldErrors.put(field, message);
        return fieldErrors;
    }

    private List<String> addActionMessage(String message) {
        actionMessages.add(message);
        return actionMessages;
    }
*/
   

}
