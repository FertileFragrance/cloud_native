package com.cloudnative.demo.vo;

public class ResultVO {

    private Integer code;
    private String message;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultVO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
