package cn.jinronga.util;

/**
 * @author itnanls
 * @date
 **/
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "success"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(10001, "未知错误"),

    /**
     * 用户名错误或不存在
     */
    USERNAME_ERROR(10002, "用户名错误或不存在"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR(10003, "密码错误"),

    /**
     * 用户名不能为空
     */
    USERNAME_EMPTY(10004, "用户名不能为空"),

    PASSWORD_EMPTY(10005, "输入密码为空"),
    NOT_PERMISSION(10006, "该用户没有权限");

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;


    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}