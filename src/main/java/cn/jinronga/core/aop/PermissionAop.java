package cn.jinronga.core.aop;

import cn.jinronga.core.Subject;
import cn.jinronga.core.annotation.HasPermission;
import cn.jinronga.entity.Permission;
import cn.jinronga.exception.NotHasPermissionException;
import cn.jinronga.util.ResultCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Stream;

/**
 * @ClassName PermissionAop
 * @Author 郭金荣
 * @Date 2021/3/14 23:10
 * @Description PermissionAop
 * @Version 1.0
 */
@Aspect
@Component
public class PermissionAop {
/*    @Before("@annotation(cn.jinronga.core.annotation.HasPermission)")
    public void around(JoinPoint joinPoint) {

        //获取session
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = sra.getRequest().getSession();
        //拿到权限
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        //获取访问接口所需要的权限
        //拿到注解里面的值  //getTarget被代理对象
      //  HasPermission annotation = joinPoint.getTarget().getClass().getAnnotation(HasPermission.class);
        HasPermission annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(HasPermission.class);

        //拿到注解里面的值
        String[] needPermissions = annotation.value();
        //权限是否匹配是否拥有
        boolean flag = Stream.of(needPermissions).allMatch(permissions::contains);

        if (!flag) {
            throw new NotHasPermissionException(ResultCode.NOT_PERMISSION);
        }
    }*/

    @Around("@annotation(cn.jinronga.core.annotation.HasPermission)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        //获取session
        HttpSession session = Subject.getSession();

        //拿到权限
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        //获取访问接口所需要的权限
        //拿到注解里面的值  //getTarget被代理对象
       // HasPermission annotation = proceedingJoinPoint.getTarget().getClass().getAnnotation(HasPermission.class);//拿到类
        HasPermission annotation = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod().getAnnotation(HasPermission.class);
        //拿到注解里面的值
        String[] needPermissions = annotation.value();
        //权限是否匹配是否拥有
        boolean flag = Stream.of(needPermissions).allMatch(permissions::contains);

        if (!flag) {
            throw new NotHasPermissionException(ResultCode.NOT_PERMISSION);
        }
        Object jObject = null;
        try {
            jObject = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return jObject;
    }
}
