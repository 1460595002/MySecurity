package cn.jinronga.controller;

import cn.jinronga.core.Subject;
import cn.jinronga.core.UserNameAndPasswordToken;
import cn.jinronga.entity.Permission;
import cn.jinronga.entity.TRole;
import cn.jinronga.entity.TUser;
import cn.jinronga.exception.NotHasPermissionException;
import cn.jinronga.exception.UserRegisterException;
import cn.jinronga.service.PermissionService;
import cn.jinronga.service.TRoleService;
import cn.jinronga.service.TUserService;
import cn.jinronga.util.R;
import cn.jinronga.util.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName LoginController
 * @Author 郭金荣
 * @Date 2021/3/14 0:42
 * @Description LoginController
 * @Version 1.0
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public R login(HttpSession session, TUser tUser) {

        try {
            Subject.login(tUser.getUsername(), tUser.getPassword());
        } catch (UserRegisterException e) {
            e.printStackTrace();
            return R.of(e.getCode(), e.getMsg());
        } catch (NotHasPermissionException e) {
            e.printStackTrace();
            return R.of(e.getCode(), e.getMsg());
        }

        return R.of(ResultCode.SUCCESS);
    }
}
