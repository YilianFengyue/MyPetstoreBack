package org.csu.controller;

import lombok.Data;

@Data
public class Result {
    private Object data;
    private String msg;
    private Integer code;

    public Result(Integer code,Object data) {
        this.data = data;
        this.code = code;
    }
    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
