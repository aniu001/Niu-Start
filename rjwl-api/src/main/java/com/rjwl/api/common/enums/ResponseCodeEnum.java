package com.rjwl.api.common.enums;

/**
 * 响应码
 */
public enum ResponseCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败");

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 代码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
