package org.csu.common.exception;

public class SystemException extends RuntimeException{
    private Integer code;//异常编号

    public SystemException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
