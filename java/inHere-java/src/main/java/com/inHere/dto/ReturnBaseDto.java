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

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "BaseResultDto [code=" + code + ", status=" + status + ", message=" + message + ", data=" + data + "]";
    }

}
