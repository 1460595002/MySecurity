package cn.jinronga.entity;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2021-03-13 23:16:31
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = -69185821940286704L;

    private Integer id;

    private String permissionName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

}
