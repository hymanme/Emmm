package com.hymane.emmm.network.response;

import java.io.Serializable;

/**
 * Author   :hymane
 * Email    :hymanmee@gmail.com
 * Create at 2018/11/14
 * Description: 基础实体
 */
public class BaseResp<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public BaseResp() {
        this(-1, "");
    }

    public BaseResp(int code, String message) {
        this(code, message, null);
    }

    public BaseResp(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
