package cn.jinronga.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName UserNameAndPasswordToken
 * @Author 郭金荣
 * @Date 2021/3/15 20:48
 * @Description UserNameAndPasswordToken
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class UserNameAndPasswordToken {
    private String username;
    private String password;
}
