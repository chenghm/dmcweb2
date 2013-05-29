package com.cinsec.dmc.base.exception;

public class DmcSysException extends RuntimeException {

    public DmcSysException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    public DmcSysException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 3154117131283341542L;
    private String message;
    private Throwable cause;

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
