package cn.jinronga.entity;

import java.io.Serializable;

/**
 * (TRole)实体类
 *
 * @author makejava
 * @since 2021-03-13 23:16:35
 */
public class TRole implements Serializable {
    private static final long serialVersionUID = 491066523513611768L;

    private Integer id;

    private String roleName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
