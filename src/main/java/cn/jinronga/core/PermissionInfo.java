package cn.jinronga.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PermissionInfo
 * @Author 郭金荣
 * @Date 2021/3/15 21:02
 * @Description PermissionInfo
 * @Version 1.0
 * 用来存储权限信息
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PermissionInfo {
    private List<String> roles;
    private List<String> permissions;
}
