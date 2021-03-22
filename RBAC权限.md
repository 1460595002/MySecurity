文章转载于：https://zhuanlan.zhihu.com/p/158752542

### **RBAC模型**

RBAC模型（Role-Based Access Control：基于角色的访问控制）模型是比较早期提出的权限实现模型，在多用户计算机时期该思想即被提出，其中以美国George Mason大学信息安全技术实验室（LIST）提出的[RBAC96](https://link.zhihu.com/?target=https%3A//www.profsandhu.com/infs767/infs767fall03/lecture01-2.pdf)模型最具有代表，并得到了普遍的公认。

RBAC认为权限授权的过程可以抽象地概括为：Who是否可以对What进行How的访问操作，并对这个逻辑表达式进行判断是否为True的求解过程，也即是将权限问题转换为Who、What、How的问题，Who、What、How构成了访问权限三元组，具体的理论可以参考[RBAC96](https://link.zhihu.com/?target=https%3A//www.profsandhu.com/infs767/infs767fall03/lecture01-2.pdf)。

### **RBAC的组成**

在RBAC模型里面，有3个基础组成部分，分别是：用户、角色和权限，它们之间的关系如下图所示



![img](https://pic1.zhimg.com/80/v2-62c8366b65851e17534257534177c39c_720w.jpg)



- User（用户）：每个用户都有唯一的UID识别，并被授予不同的角色
- Role（角色）：不同角色具有不同的权限
- Permission（权限）：访问权限
- 用户-角色映射：用户和角色之间的映射关系
- 角色-权限映射：角色和权限之间的映射

例如下图，管理员和普通用户被授予不同的权限，普通用户只能去修改和查看个人信息，而不能创建用户和冻结用户，而管理员由于被授予所有权限，所以可以做所有操作。



![img](https://pic1.zhimg.com/80/v2-cb3159187af19714b9d2714840f93990_720w.jpg)



### **RBAC模型分类**

### **基本模型RBAC0**

RBAC0是基础，很多产品只需基于RBAC0就可以搭建权限模型了。在这个模型中，我们把权限赋予角色，再把角色赋予用户。用户和角色，角色和权限都是多对多的关系。用户拥有的权限等于他所有的角色持有权限之和。

![img](https://pic1.zhimg.com/80/v2-79788a87f6dc0d51ee8c78bdeb663224_720w.jpg)

举个栗子:

譬如我们做一款企业管理产品，如果按传统权限模型，给每一个用户赋予权限则会非常麻烦，并且做不到批量修改用户权限。这时候，可以抽象出几个角色，譬如销售经理、财务经理、市场经理等，然后把权限分配给这些角色，再把角色赋予用户。这样无论是分配权限还是以后的修改权限，只需要修改用户和角色的关系，或角色和权限的关系即可，更加灵活方便。此外，如果一个用户有多个角色，譬如王先生既负责销售部也负责市场部，那么可以给王先生赋予两个角色，即销售经理、市场经理，这样他就拥有这两个角色的所有权限。

### **角色分层模型RBAC1**

RBAC1建立在RBAC0基础之上，在角色中引入了继承的概念。简单理解就是，给角色可以分成几个等级，每个等级权限不同，从而实现更细粒度的权限管理。



![img](https://pic2.zhimg.com/80/v2-7eeef6edfad93fac2a58745ca72c0951_720w.jpg)



举个栗子:

基于之前RBAC0的例子，我们又发现一个公司的销售经理可能是分几个等级的，譬如除了销售经理，还有销售副经理，而销售副经理只有销售经理的部分权限。这时候，我们就可以采用RBAC1的分级模型，把销售经理这个角色分成多个等级，给销售副经理赋予较低的等级即可。

### **角色限制模型RBAC2**

RBAC2同样建立在RBAC0基础之上，仅是对用户、角色和权限三者之间增加了一些限制。这些限制可以分成两类，即静态职责分离SSD(Static Separation of Duty)和动态职责分离DSD(Dynamic Separation of Duty)。具体限制如下图：

![img](https://pic2.zhimg.com/80/v2-d2a03c768f4bb817a1240b424b5fcb51_720w.jpg)



举个栗子:

还是基于之前RBAC0的例子，我们又发现有些角色之间是需要互斥的，譬如给一个用户分配了销售经理的角色，就不能给他再赋予财务经理的角色了，否则他即可以录入合同又能自己审核合同；再譬如，有些公司对角色的升级十分看重，一个销售员要想升级到销售经理，必须先升级到销售主管，这时候就要采用先决条件限制了。

### **统一模型RBAC3**

RBAC3是RBAC1和RBAC2的合集，所以RBAC3既有角色分层，也包括可以增加各种限制。



![img](https://pic3.zhimg.com/80/v2-f6254050ec99d0ed17967d7287a0985a_720w.jpg)





> 最全的学习资料 → 卫星：lezijie007（程序员暗号：1024）



## **案例实操~RBAC0模型核心表结构**

通过以上分析看到RBAC存在四种模型，后面三种均在RBAC0基础模型延伸而来，这里主要来考虑基础模型RBAC0表设计，有了基础表结构后在其基础之上进行升级改造即可。

### **实体对应关系**

用户-角色-资源实体间对应关系图分析如下



![img](https://pic2.zhimg.com/80/v2-2daf1803de51213db874e78fe4b81301_720w.jpg)



这里用户与角色实体对应关系为多对多，角色与资源对应关系同样为多对多关系，所以在实体设计上用户与角色间增加用户角色实体，将多对多的对应关系拆分为一对多，同理，角色与资源多对多对应关系拆分出中间实体对象权限实体。



### **表结构设计**

从上面实体对应关系分析，权限表设计分为以下基本的五张表结构：用户表(t_user)，角色表(t_role)，t_user_role(用户角色表)，资源表(t_module)，权限表(t_permission)，表结构关系如下：



![img](https://pic4.zhimg.com/80/v2-7fba328514d28153f575abf89d7acccf_720w.jpg)

![img](https://pic2.zhimg.com/80/v2-897b971fd372b60afea5dc7844fcd659_720w.jpg)

### **模块划分**

从表结构设计可以看出：这里有三张主表(t_user，t_role，t_module)，功能实现上这里划分为三大模块：

### **用户管理**

- 用户基本信息维护
- 用户角色分配

### **角色管理**

- 角色基本信息维护
- 角色授权(给角色分配能够操作的菜单)
- 角色认证(给角色拥有的权限进行校验)

### **资源管理**

- 资源信息维护
- 菜单输出动态控制



## **扩展**

### **基于RBAC的延展—用户组**

基于RBAC模型，还可以适当延展，使其更适合我们的产品。譬如增加用户组概念，直接给用户组分配角色，再把用户加入用户组。这样用户除了拥有自身的权限外，还拥有了所属用户组的所有权限。



![img](https://pic2.zhimg.com/80/v2-ed4440093b5aec0c25aadcd36fda1c85_720w.jpg)



举个栗子:

譬如，我们可以把一个部门看成一个用户组，如销售部，财务部，再给这个部门直接赋予角色，使部门拥有部门权限，这样这个部门的所有用户都有了部门权限。用户组概念可以更方便的给群体用户授权，且不影响用户本来就拥有的角色权限。









1、登录 认证   authorization   登录相关的内容
2、权限管理   授权   authentication
把该用户拥有的权限付给他就是授权

前端要求

有权限的人能看到对应的模块 按钮

后端 contorller

有权限的才能访问没有权限的不能访问

角色与权限放进 session里

1	user:add
2	user:delete
3	user:update
4	user:query   permission



##### 自定义注解：

```java
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasPermission {
    String[] value();
}
```

拿到直接的value并且进行验证权限

```java
@Aspect
@Component
public class PermissionAop {
    @Before("@annotation(cn.jinronga.core.annotation.HasPermission)")
    public void around(JoinPoint joinPoint) {

        //获取session
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = sra.getRequest().getSession();
        //拿到权限
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        //获取访问接口所需要的权限
        //拿到注解里面的值  //getTarget被代理对象
        HasPermission annotation = joinPoint.getTarget().getClass().getAnnotation(HasPermission.class);
        //拿到注解里面的值
        String[] needPermissions = annotation.value();
        //权限是否匹配是否拥有
        boolean flag = Stream.of(needPermissions).allMatch(permissions::contains);

        if (!flag) {
            throw new NotHasPermissionException(ResultCode.NOT_PERMISSION);
        }
    }

    @Around("@annotation(cn.jinronga.core.annotation.HasPermission)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        //获取session
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = sra.getRequest().getSession();
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
```

