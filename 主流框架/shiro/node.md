[10 Minute Tutorial on Apache Shiro | Apache Shiro](https://shiro.apache.org/10-minute-tutorial.html)

shiro外部

subject 当前用户

shiro securityMananger 安全管理，所有的subject

Realm 数据管理控制，喝酒时安全管理要验证用户身份，那么他需要从realm获取相应的用户进行比较，来确定用户是否合法，也需要从realm得到对映的角色，权限，。可以看成datasource

```java
Subject currentUser = SecurityUtils.getSubject();
Session session = currentUser.getSession();
currentUser.isAuthenticated();
getPrincipal();
hasRole("user");
isPermitted("ddd:ddd");
logout();
```

[(202条消息) 一篇适合小白的Shiro教程_潮汐先生的博客-CSDN博客](https://blog.csdn.net/bbxylqf126com/article/details/110501155?ops_request_misc=%7B%22request%5Fid%22%3A%22166169401116781683986140%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=166169401116781683986140&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-110501155-null-null.142^v42^pc_ran_alice,185^v2^control&utm_term=shiro&spm=1018.2226.3001.4187)

连接mysql驱动





## thymeleaf整合shiro流程

### 1.依赖

```
<!--thymeleaf依赖-->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.4.1</version>
        </dependency>
        <!--thymeleaf依赖-->
        <dependency>
            <groupId>org.thymeleaf</groupId>
            <artifactId>thymeleaf-spring5</artifactId>
        </dependency>
        <dependency>
            <groupId>org.thymeleaf.extras</groupId>
            <artifactId>thymeleaf-extras-java8time</artifactId>
        </dependency>
        <dependency>
```



### 2.ShiroConfig.java

```java
package com.mu.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    //ShiroFilterFactoryBean3:
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        return bean;
    }

    //DefaultWebSecurityMannger:2
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //由于是中间商，需要管理我们的realm
        //关联realm
        securityManager.setRealm(userRealm);//由于realm通过spring托管了，所以要通过spring传参数
        return securityManager;
    }

    //创建 realm 对象 自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


}

```

### 3.自定义Ream

```java
package com.mu.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.parser.Authorization;

public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("souquan1");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("renzheng");
        return null;
    }
}

```

### 4.controller接口测试

```java
@Controller
public class MyController {

    @RequestMapping({"/index","/"})//页面的url地址
    public String toIndex(Model model){
        model.addAttribute("msg","hello,world");
        return "index";
    }
}
```

index.html

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>首页</h1>
<h1>首页</h1>
<p th:text="${msg}"></p>
</body>
</html>
```

### 5.添加过滤器

shiroConfig.java

```java
package com.mu.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    //ShiroFilterFactoryBean3:
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    //DefaultWebSecurityMannger:2
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //由于是中间商，需要管理我们的realm
        //关联realm
        securityManager.setRealm(userRealm);//由于realm通过spring托管了，所以要通过spring传参数
        return securityManager;
    }

    //创建 realm 对象 自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
```

接口

```java
    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }
```

界面

![image-20220829193617313](https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202208291936384.png)

测试

### 6.登录请求

shiroConfig.java

```java
package com.mu.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    //ShiroFilterFactoryBean3:
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    //DefaultWebSecurityMannger:2
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //由于是中间商，需要管理我们的realm
        //关联realm
        securityManager.setRealm(userRealm);//由于realm通过spring托管了，所以要通过spring传参数
        return securityManager;
    }

    //创建 realm 对象 自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
```

Realm中

```java
package com.mu.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.parser.Authorization;

public class UserRealm extends AuthorizingRealm {
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("souquan1");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("renzheng");
        String name  = "root";
        String password = "1234";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if(!userToken.getUsername().equals(name)){
            return null;
        }
        //密码认证.shiro做
        return new SimpleAuthenticationInfo("",password,"");
    }
}

```

login.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<p th:text="${msg}" style="color: red;"></p>
<form th:action="@{/login}">
    <p>用户名：<input type="text" name="username"></p>
    <p>密码：<input type="text" name="password"></p>
    <p><input type="submit"></p>
</form>
</body>
</html>
```

### 7.整合Mybatis

```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.12</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.12</version>
        </dependency>
		<dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>
		<dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.24</version>
        </dependency>
```

yml配置

```yml
server:
  port: 8080

spring:
  datasource:
    username: root
    password: 1234
    #serverTimezone=UTC解决时区的报错
    url: jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
mybatis:
  type-aliases-package: com.mu.pojo
  mapper-locations: classpath:mapper/*.xml


```

pojo.User

```java
package com.mu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;

}

```

UserMapper

```java
@Mapper
public interface UserMapper {
    public User queryUserByName(String name);
}
```

UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mu.mapper.UserMapper">

    <select id="queryUserByName" resultType="com.mu.pojo.User">
        select *
        from mybatis1.tb_user where username=#{username};
    </select>
</mapper>
```

UserService

```java
public interface UserService {
    public User queryUserByName(String name);
}
```

UserServiceImpl

```java
@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
```

测试

```
@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("zhangsan"));
    }

}
```

替换真实数据

```java
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("renzheng");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if(user==null){
            return null;
        }
        //密码认证.shiro做
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
```

授权

数据库新增perms字段，并授权

![image-20220829213644042](https://cdn.jsdelivr.net/gh/hughmum/blogImage/img/202208292136110.png)

Realm中,获取当前对象，并授权

```java
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("souquan1");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User)subject.getPrincipal();//拿到user对象

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("renzheng");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if(user==null){
            return null;
        }
        //密码认证.shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
```

config中 拦截

```java
@Bean
    //ShiroFilterFactoryBean3:
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        bean.setLoginUrl("/toLogin");
        //未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }
```

### 8.整合thymeleaf

1.依赖

```
<dependency>
            <groupId>com.github.theborakompanioni</groupId>
            <artifactId>thymeleaf-extras-shiro</artifactId>
            <version>2.0.0</version>
       </dependency>
```

2.方言

```java
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
```



index

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
      xmlns:shiro="http://www.thymeleaf.org/thymeleaf-extras-shiro">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>首页</h1>
<h1>首页</h1>
<p th:text="${msg}"></p>
<p>
    <a th:href="@{/toLogin}">rrgfgfd</a>
</p>
<div shiro:hasPermission="user:add">
    <a th:href="@{/user/add}">add</a>
</div>
<div shiro:hasPermission="user:update">
    <a th:href="@{/user/update}">update</a>
</div>

</body>
</html>
```



