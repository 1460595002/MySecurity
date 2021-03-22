package cn.jinronga.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName HasPermission
 * @Author 郭金荣
 * @Date 2021/3/14 23:04
 * @Description HasPermission
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermission {
    String[] value();
}
