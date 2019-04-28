package com.rjwl.api.common.json;

import com.alibaba.fastjson.JSONObject;
import com.rjwl.api.common.enums.JsonCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回JSON格式
 * @author aniu
 */
@Data
public class JsonResp<T> implements Serializable {
    /**
     * 代码消息
     */
    private JsonCodeEnum code;
    /**
     * 数据
     */
    private T data;
    /**
     * 消息
     */
    private String msg;
    /**
     * 成功标志
     */
    private boolean success = false;

    public JsonResp() {
        super();
    }

    public JsonResp(JsonCodeEnum code) {
        this.code = code;
        this.msg = code.getMessage();
    }
    public JsonResp(JsonCodeEnum code,T data,boolean success) {
        this.code = code;
        this.data = data;
        this.msg = code.getMessage();
        this.success = success;
    }

    public static JsonResp success(String msg) {
        JsonResp jsonResp = new JsonResp();
        jsonResp.setCode(JsonCodeEnum.SUCCESS);
        jsonResp.setMsg(msg);
        jsonResp.setSuccess(true);
        return jsonResp;
    }

    public static JsonResp success(Object data) {
        JsonResp jsonResp = new JsonResp();
        jsonResp.setCode(JsonCodeEnum.SUCCESS);
        jsonResp.setData(data);
        jsonResp.setMsg(JsonCodeEnum.SUCCESS.getMessage());
        jsonResp.setSuccess(true);
        return jsonResp;
    }

    public static JsonResp fail(String msg) {
        JsonResp jsonResp = new JsonResp();
        jsonResp.setCode(JsonCodeEnum.FAIL);
        jsonResp.setMsg(msg);
        return jsonResp;
    }

    public static JsonResp fail(Object data) {
        JsonResp jsonResp = new JsonResp();
        jsonResp.setCode(JsonCodeEnum.FAIL);
        jsonResp.setData(data);
        jsonResp.setMsg(JsonCodeEnum.FAIL.getMessage());
        return jsonResp;
    }
}
