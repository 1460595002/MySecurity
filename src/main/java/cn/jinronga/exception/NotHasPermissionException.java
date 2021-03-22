package cn.jinronga.exception;

import cn.jinronga.util.ResultCode;

/**
 * @ClassName NotHasPermissionException
 * @Author 郭金荣
 * @Date 2021/3/14 19:39
 * @Description NotHasPermissionException
 * @Version 1.0
 */
public class NotHasPermissionException extends RuntimeException {
    private int code;
    private String msg;

    public NotHasPermissionException(ResultCode resultCode) {
        this.code = resultCode.USERNAME_ERROR.getCode();
        this.msg = resultCode.USERNAME_ERROR.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
