package org.csu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private Object data;
    private String msg;

    // 便捷构造方法
    public Result(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    // 静态工厂方法
    public static Result success() {
        return new Result(Code.GET_OK, null, "success");
    }

    public static Result success(Object data) {
        return new Result(Code.GET_OK, data, "success");
    }

    public static Result error(String msg) {
        return new Result(Code.GET_ERR, null, msg);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, null, msg);
    }
}