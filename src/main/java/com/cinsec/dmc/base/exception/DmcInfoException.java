package com.cinsec.dmc.base.exception;

public class DmcInfoException extends RuntimeException {


    public DmcInfoException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3154117131283341542L;
    private String message;

    public String getMessage() {
        return message;
    }
}
