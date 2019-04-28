package com.rjwl.api.common.exception;

/**
 *
 * 自定义异常
 * @author aniu
 */
public class IException extends RuntimeException{
    private static final long serialVersionUID = 7144771828212718116L;
    private String message;

    public IException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
