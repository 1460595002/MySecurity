package cn.jinronga.service;

import cn.jinronga.entity.Permission;

import java.util.List;

/**
 * (Permission)表服务接口
 *
 * @author makejava
 * @since 2021-03-13 23:16:33
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过用户名获取权限
     *
     * @param name
     * @return
     */
    List<Permission> getPermissionByUserName(String name);

    void allocation(Integer rId, Integer pId);
}
