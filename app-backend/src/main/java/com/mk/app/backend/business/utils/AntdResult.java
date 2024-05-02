package com.mk.app.backend.business.utils;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * Antd 返回值规范：
 * <p>
 * https://umijs.org/zh-CN/plugins/plugin-request
 */
@Getter
@Setter
public class AntdResult<T> {

    /**
     * if request is success
     */
    private boolean success;

    /**
     * response data
     */
    private T data;

    /**
     * code for errorType
     */
    private String errorCode;

    /**
     * message display to user
     */
    private String errorMessage;

    /**
     * error display type：
     * <p>
     * 0 silent; 1 message.warn; 2 message.error; 4 notification; 9 page
     */
    private int showType;

    public AntdResult() {
        this(null);
    }

    public AntdResult(T data) {
        this.success = true;
        this.data = data;
    }

    public AntdResult(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public AntdResult(String errorCode, String errorMessage, int showType) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.showType = showType;
    }

    public static <T> AntdResult<T> success() {
        return new AntdResult<>();
    }

    public static <T> AntdResult<T> success(T data) {
        return new AntdResult<>(data);
    }

    public static <T> AntdResult<T> error(String errorCode, String errorMessage) {
        return new AntdResult<>(errorCode, errorMessage);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

}
