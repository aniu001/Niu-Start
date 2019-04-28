package com.rjwl.api.common.enums;

/**
 * JSON格式类
 *
 */
public enum JsonCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    PARAMETER_INVALID(204, "参数不合法"),
    EXISTING(205, "已存在"),
    UNREGISTERED(206, "未注册"),
    TOKEN_NOT_FIND(207, "未找到"),
    OVERTIME(208, "已超时或过期"),
    EMPTY(209, "空数据"),
    PARAMETER_ERROR(210, "参数填写错误"),
    CONFLICT(211, "冲突"),
    INSUFFICIENT(212, "条件不足"),
    NOT_AUTHORIZATION(213, "没有权限"),
    STATUS_DISABLED(214, "状态不可用"),
    HANG_IN_AIR(215, "未完成"),
    NONSUPPORT(216,"不支持"),

    UNABLE_READ(400,"无法读取"),
    ARGUMENT_NOT_VALID(401,"参数验证失败"),
    INCORRECT_CREDENTIALS(402,"密码错误"),
    UNKNOWN_ACCOUNT(403,"用户名错误"),
    LOGIN_FAIL(404,"登录错误"),
    NO_AUTHORIZATION(405,"没有权限"),
    LOGIN_FIRST(406,"请先登录"),
    LOGOUT_SUCCESS(407,"注销成功"),

    MIONE_EXCEPTION(417,"自定义异常"),

    SERVER_EXCEPTION(500,"服务器内部错误");

    JsonCodeEnum(Integer code, String message) {
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

    @Override
    public String toString() {
        return "";
    }

    /**
     * 判断是否是正确返回
     *
     * @param jsonCode 枚举
     * @return 是=true
     */
    public static boolean isSuccess(JsonCodeEnum jsonCode) {
        if (jsonCode != null) {
            if (jsonCode.equals(JsonCodeEnum.SUCCESS)) {
                return true;
            }
        }
        return false;
    }
}
