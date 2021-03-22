package cn.jinronga.exception;

import cn.jinronga.util.ResultCode;

/**
 * 統一的业务异常
 * @author zn
 * @date
 */
public class BusinessException extends RuntimeException {

    private int code;
    private String msg;

    public BusinessException(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
