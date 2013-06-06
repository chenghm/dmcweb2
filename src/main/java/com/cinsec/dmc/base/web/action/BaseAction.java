package com.cinsec.dmc.base.web.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cinsec.dmc.user.entity.User;


public class BaseAction  {

	private Map<String,Object> dataMap = new HashMap<String, Object>();
    private Map<String, String> fieldErrors = new LinkedHashMap<String, String>();
    private List<String> actionMessages = new LinkedList<String>();
    private String actionStatus ;
    
    protected Map<String,Object> addDataMap(){
        dataMap = new HashMap<String, Object>();
        dataMap.put("actionStatus", getActionStatus());
        dataMap.put("fieldErrors", fieldErrors);
        dataMap.put("actionMessages", actionMessages);
        return dataMap;
    }
    
    protected Map<String,Object> addDataMap(String str,Object obj){
        dataMap = new HashMap<String, Object>();
        dataMap.put(str, obj);
        return dataMap;
    }
    protected Map<String, String> addFieldError(String field, String message) {
        fieldErrors.put(field, message);
        return fieldErrors;
    }

    
    protected List<String> addActionMessage(String message) {
        actionMessages.add(message);
        return actionMessages;
    }
    
    protected User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User)auth.getPrincipal();
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<String> getActionMessages() {
        return actionMessages;
    }

    public void setActionMessages(List<String> actionMessages) {
        this.actionMessages = actionMessages;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

}
