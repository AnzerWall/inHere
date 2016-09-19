package com.inHere.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * <h1>基础数据传输类</h1>
 *
 * @author lwh
 */
@JsonInclude(Include.NON_NULL)
public class ReturnBaseDto<T> {

    private Integer code; // 响应码，0表示成功，其他表示生效，使用码段区分不同模块
    private String status; // 响应信息，失败时描述失败信息
    private String message; // 可选，指定错误更加详细的错误信息
    private T data; // 具体响应数据

    public Integer getCode() {
        return code;
    }

    public ReturnBaseDto<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ReturnBaseDto<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReturnBaseDto<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ReturnBaseDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "BaseResultDto [code=" + code + ", status=" + status + ", message=" + message + ", data=" + data + "]";
    }

}
