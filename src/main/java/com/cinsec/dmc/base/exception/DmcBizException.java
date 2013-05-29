package com.cinsec.dmc.base.exception;

public class DmcBizException  extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = 2261074444278803732L;
    private String message;
    
    public DmcBizException(){
        super();
    }
    
    public DmcBizException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    
}
