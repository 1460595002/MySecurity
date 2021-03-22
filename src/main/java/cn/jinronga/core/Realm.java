package cn.jinronga.core;

/**
 * @ClassName Realm
 * @Author 郭金荣
 * @Date 2021/3/15 21:01
 * @Description Realm
 * @Version 1.0
 */
public interface Realm {
    /**
     * 查询用户信息
     * @param username
     * @return
     */
    UserNameAndPasswordToken authentication(String username);

    /**
     * 查询权限信息
     *
     * @return
     */
    PermissionInfo authorization(String name);
}
