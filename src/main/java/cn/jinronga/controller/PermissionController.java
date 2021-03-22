package cn.jinronga.controller;

import cn.jinronga.core.Subject;
import cn.jinronga.entity.Permission;
import cn.jinronga.entity.TUser;
import cn.jinronga.service.PermissionService;
import cn.jinronga.service.TRoleService;
import cn.jinronga.service.TUserService;
import cn.jinronga.util.R;
import cn.jinronga.util.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * (Permission)表控制层
 *
 * @author makejava
 * @since 2021-03-13 23:16:34
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
    /**
     * 服务对象
     */
    @Resource
    private PermissionService permissionService;
    @Resource
    private TUserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Permission selectOne(Integer id) {
        return this.permissionService.queryById(id);
    }

    @GetMapping("/allocation")
    public R allocation(Integer rId, Integer pId) {
        permissionService.allocation(rId, pId);

        //你修改的那些权限
        //从online 看一看谁有这个权限，谁有谁的权限重新加载
        List<TUser> user = userService.getUsersByRoleId(rId);
        Map<String, HttpSession> onlineMessage = Subject.onlineMessage();
        Set<String> onlines = onlineMessage.keySet();
        for (String online : onlines) {
            for (TUser tUser : user) {
                //这个在线的用户有这个角色
                if (online.equals(tUser.getUsername())) {
                    //重新加载
                    Subject.reloadPermission(online);
                }
                break;
            }
        }

        return R.of(ResultCode.SUCCESS);
    }


}
