package cn.jinronga.controller;

import cn.jinronga.core.annotation.HasPermission;
import cn.jinronga.entity.Permission;
import cn.jinronga.entity.TUser;
import cn.jinronga.exception.NotHasPermissionException;
import cn.jinronga.service.TUserService;
import cn.jinronga.util.R;
import cn.jinronga.util.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2021-03-13 23:16:36
 */
@RestController
@RequestMapping("tUser")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public TUser selectOne(@PathVariable("id") Integer id) {
        return this.tUserService.queryById(id);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public R userUpdate(TUser user) {
        tUserService.update(user);
        return R.of(ResultCode.SUCCESS);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @PostMapping("/userAdd")
    @HasPermission("user:add")
    public R userAdd(HttpSession session, TUser user) {

        tUserService.insert(user);
        return R.of(ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUser/{id}")
    @HasPermission("user:delete")
    public R deleteUser(@PathVariable Integer id) {
        tUserService.deleteById(id);
        return R.of(ResultCode.SUCCESS);
    }

}
