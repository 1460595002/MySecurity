package cn.jinronga.security;

import cn.jinronga.core.PermissionInfo;
import cn.jinronga.core.Realm;
import cn.jinronga.core.UserNameAndPasswordToken;
import cn.jinronga.entity.Permission;
import cn.jinronga.entity.TRole;
import cn.jinronga.entity.TUser;
import cn.jinronga.exception.UserRegisterException;
import cn.jinronga.service.PermissionService;
import cn.jinronga.service.TRoleService;
import cn.jinronga.service.TUserService;
import cn.jinronga.util.ResultCode;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserRealm
 * @Author 郭金荣
 * @Date 2021/3/15 23:02
 * @Description UserRealm
 * @Version 1.0
 */
@Component
public class UserRealm implements Realm {

    @Resource
    private TUserService userService;
    @Resource
    private TRoleService roleService;
    @Resource
    private PermissionService permissionService;

    @Override
    public UserNameAndPasswordToken authentication(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new UserRegisterException(ResultCode.USERNAME_EMPTY);
        }
        List<TUser> userByName = userService.findUserByName(username);
        if (userByName.size() < 0 || userByName == null) {
            throw new UserRegisterException(ResultCode.USERNAME_ERROR);
        }
        return new UserNameAndPasswordToken(userByName.get(0).getUsername(), userByName.get(0).getPassword());
    }

    @Override
    public PermissionInfo authorization(String name) {
        //查询所有的角色、权限 包装成PermissionInfo
        List<Permission> permissionByUserName = permissionService.getPermissionByUserName(name);
        List<String> permissionList = permissionByUserName.stream().map(Permission::getPermissionName).collect(Collectors.toList());

        List<TRole> rolesByUserName = roleService.getRolesByUserName(name);
        List<String> rolesList = rolesByUserName.stream().map(TRole::getRoleName).collect(Collectors.toList());
        PermissionInfo permissionInfo = new PermissionInfo();
        permissionInfo.setPermissions(permissionList);
        permissionInfo.setRoles(rolesList);
        return permissionInfo;
    }
}
