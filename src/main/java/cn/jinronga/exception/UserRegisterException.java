package cn.jinronga.exception;

import cn.jinronga.util.ResultCode;

/**
 * @ClassName UserRegisterException
 * @Author 郭金荣
 * @Date 2021/3/14 13:22
 * @Description UserRegisterException
 * @Version 1.0
 */
public class UserRegisterException extends   RuntimeException{
    private int code;
    private String msg;

    public UserRegisterException(ResultCode resultCode) {
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
