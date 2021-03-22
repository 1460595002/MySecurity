package cn.jinronga.core;

import cn.jinronga.entity.Permission;
import cn.jinronga.entity.TRole;
import cn.jinronga.entity.TUser;
import cn.jinronga.exception.UserRegisterException;
import cn.jinronga.util.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName Subject
 * @Author 郭金荣
 * @Date 2021/3/15 20:48
 * @Description Subject
 * @Version 1.0
 */
@Component
public class Subject {
    @Resource
    private Realm realm;
    private static Realm myRealm;

    private final static Map<String, HttpSession> ONLINE_SESSION = new HashMap<>();

    //给静态变量赋值
    @PostConstruct
    public void init() {
        myRealm = realm;
    }

    public static void login(String name, String pwd) {
        /**
         * 1、对用户进行验证
         * 用户名空不空
         */
        UserNameAndPasswordToken token = myRealm.authentication(name);
        String username = token.getUsername();
        //判断session是否存在
        boolean containsKey = ONLINE_SESSION.containsKey(username);
        if (containsKey) {
            ONLINE_SESSION.get(username).invalidate();
        }
//        if (StringUtils.isEmpty(username)) {
//            throw new UserRegisterException(ResultCode.USERNAME_EMPTY);
//        }


//        if (user.size() < 0 || user == null) {
//            throw new UserRegisterException(ResultCode.USERNAME_ERROR);
//        }
        String password = token.getPassword();
        if (StringUtils.isEmpty(password)) {
            throw new UserRegisterException(ResultCode.PASSWORD_EMPTY);
        }

        //得到用户，对比密码验证
        if (!password.equals(pwd)) {
            throw new UserRegisterException(ResultCode.USERNAME_ERROR);
        }
        //登录成功！将用户设置到session
        getSession().setAttribute("user", token.getUsername());
        //查询角色信息放入session 根据用户拿到角色
//        List<TRole> roles = roleService.getRolesByUserId(user.get(0).getId());
        //放入session
        getSession().setAttribute("roles", myRealm.authorization(token.getUsername()));
        //通过角色id获取权限
//        List<Permission> permissions = permissionService.getPermissionByRoleId(user.get(0).getId());
//        List<String> permissionStr = permissions.stream()
//                .filter(t -> Objects.nonNull(t) && Objects.nonNull(t.getId()))
//                .map(Permission::getPermissionName).collect(Collectors.toList());
//        System.out.println(permissionStr);
        //查询权限放入session
        getSession().setAttribute("permissions", myRealm.authorization(token.getUsername()).getPermissions());

        ONLINE_SESSION.put(token.getUsername(), getSession());
        reloadPermission(username);
    }

    public static HttpSession getSession() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest().getSession();
    }

    /**
     * 加载权限到session
     * @return
     */
    public static Map<String, HttpSession> onlineMessage(){
        return ONLINE_SESSION;
    }
    public static void reloadPermission(String username) {
        ONLINE_SESSION.get(username).setAttribute("permissions", myRealm.authorization(username));
        ONLINE_SESSION.get(username).setAttribute("roles", myRealm.authorization(username));
    }
}
