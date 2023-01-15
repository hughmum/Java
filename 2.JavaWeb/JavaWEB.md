笔记链接

[JavaWeb教程目录 | 代码重工 (gitee.io)](https://heavy_code_industry.gitee.io/code_heavy_industry/pro001-javaweb/lecture/)

# jQuery

[(41条消息) 学习jQuery这一篇就够了_轻松的小希的博客-CSDN博客_jquery-1.9.1.min](https://blog.csdn.net/qq_38490457/article/details/109683256?ops_request_misc=%7B%22request%5Fid%22%3A%22166573300716800180631163%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=166573300716800180631163&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-109683256-null-null.142^v56^pc_search_v3,201^v3^control_1&utm_term=jQuery&spm=1018.2226.3001.4187)

jQuery对象的本质是dom对象的数组 + jquery提供的一系列功能函数

jquery对象与dom对象互相转化

```html
var $obj = $(dom对象);
var dom = $obj[下标];
```

全选全不选，反选案例

```js
 $(function () {
       $("#checkedAllBtn").click(function () {
            $(":checkbox").prop("checked", true);
        });}

```

# XML

[(41条消息) XML——XML介绍和基本语法_aidem_brown的博客-CSDN博客_xml](https://blog.csdn.net/aidem_brown/article/details/82217481?ops_request_misc=%7B%22request%5Fid%22%3A%22166573210816800184191934%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=166573210816800184191934&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-82217481-null-null.142^v56^pc_search_v3,201^v3^control_1&utm_term=xml&spm=1018.2226.3001.4187)

html语言本身是有一些缺陷的 
（1）不能自定义标签 
（2）html本身缺少含义 
（3）html没有真正的国际化

有一个中间过渡语言,xhtml： 
html->xhtml->xml

- 1969 gml(通用标记语言)，主要目的是要在不同的机器之间进行通信的数据规范
- 1985 sgml(标准通用标记语言)
- 1993 html(超文本标记语言，www网)

- 1998 xml extensiable markup language 可扩展标记语言

### **为什么需要XML**

1.需求1 
两个程序间进行数据通信？ 
2.需求2 
给一台服务器，做一个配置文件，当服务器程序启动时，去读取它应当监听的端口号，还有连接数据库的用户名和密码？

在XML语言中，它允许用户自定义标签。一个标签用于描述一段数据；一个标签可以分为开始标签和结束标签，在开始标签和结束标签之间，又可以使用其他标签描述其他数据，以此来实现数据关系的描述。

### **XML常见应用**

1.XML的出现解决了程序间数据传输的问题： 
比如QQ之间的数据传送，用XML格式来传送数据，具有良好的可读性，可维护性

2.XML可以做配置文件 
XML文件做配置文件可以说非常普遍，比如我们的Tomcat服务器的server.xml，web.xml。再比如我们的structs中的structs-config.xml文件，和hibernate的hibernate.cfg.xml等等。

3.XML可以充当小型的数据库 
XML文件可以做小型数据库，也是不错的选择，我们程序中可能用到一些经常要人工配置的数据，如果放在数据库中读取不合适（因为这会增加维护数据库的工作），则可以考虑直接用XML来做小型数据库。这种方式直接读取文件显然要比读数据库快。比如msn中保存用户聊天记录就是用XML文件。

入门案例：用XML来记录一个班级信息。

```XML
<?xml version="1.0" encoding="gb2312"?>
 
<class>
    <stu id="001">
        <name>杨过</name> 
        <sex>男</sex>
        <age>20</age>
    </stu>  
    <stu id="002">
        <name>小龙女</name>    
        <sex>女</sex>
        <age>21</age>
    </stu>
</class>
```

>因为xml文件的默认编码是ANSI，即美国国家标准协会制定的编码，它根据不同的国家和地区制定了不同的标准，那么在中国就是GB2312，所以我们用GB2312编码不会出错，而用UTF-8会报错。

> 解决办法就是将该XML文件更改为UTF-8的编码模式即可。

### **XML语法**

一个XML文件分为如下几部分内容： 
1.文档声明 
2.元素 
3.属性 
4.注释 
5.CDATA区、特殊字符 
6.处理指令（processing instruction）

1 .**XML语法-文档声明**

```XML
<?xml version="1.0" encoding="utf-8" standalone="yes" ?>
```

XML声明由以下几个部分组成：

>version –文档符合XML1.0规范，我们学习1.0 
>encoding –文档字符编码，比如”GB2312”或者”UTF-8” 
>standalone –文档定义是否独立使用 
>standalone=”no”为默认值。yes代表是独立使用，而no代表不是独立使用

2. ##### **XML语法-元素（或者叫标记、节点）**

>  1)每个XML文档必须有且只有一个**根元素**

- 根元素是一个完全包括文档中其他所有元素的元素
- 根元素的起始标记要放在所有其他元素的起始标记之前
- 跟元素的结束标记要放在所有其他元素的结束标记之后

3. ##### **XML语法-属性**

```XML
<student id="100">
    <name>Tom</name>
</student>
```

> (1)属性值用双引号（”）或单引号（’）分隔，如果属性值中有单引号，则用双引号分隔；如果有双引号，则用单引号分隔。那么如果属性值中既有单引号还有双引号怎么办？这种要使用实体（转义字符，类似于html中的空格符），XML有5个预定义的实体字符，如下：

(2)一个元素可以有多个属性，它的基本格式为：

```XML
<元素名 属性名1="属性值1" 属性名2="属性值2">
```

4. ##### **XML语法-注释**

<!--这是一个注释-->

5. ##### **XML语法-CDATA节**

假如有这么一个需求，需要通过XML文件传递一幅图片，怎么做呢？其实我们看到的电脑上的所有文件，本质上都是字符串，不过它们都是特殊的二进制字符串。我们可以通过XML文件将一幅图片的二进制字符串传递过去，然后再解析成一幅图片。那么这个字符串就会包含大量的<,>,&或者“等一些特殊的不合法的字符。这时候解析引擎是会报错的。

所以，有些内容可能不想让解析引擎解析执行，而是当做原始内容处理，用于把整段文本解释为纯字符数据而不是标记。这就要用到CDATA节。

```XML
<![CDATA[
    ......
]]>
```

```xml
<stu id="001">
    <name>杨过</name> 
    <sex>男</sex>
    <age>20</age>
    <intro><![CDATA[ad<<&$^#*k]]></intro>
</stu>
```

6. ##### **XML语法-处理指令**

### xml文件的解析

**dom4j解析**

```java
package com.xinsi.qi.utils;
 
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
 
import java.io.File;
import java.util.List;
 
public class Dom4jXml {
    public void test(){
        try {
            File inputFile = new File("F:\\J2EE学习资料\\demoLes03\\web\\WEB-INF\\test.xml");
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputFile);
            System.out.println("Root element :"+document.getRootElement().getName());
 
            Element classElement = document.getRootElement();
 
            List<Node> nodes = document.selectNodes("/class/part[@id='02']");
 
            System.out.println("--------------------");
 
            for (Node node:nodes){
                System.out.println("标签名=:"+node.getName());
                System.out.println("姓名:"+node.selectSingleNode("name").getText());
                System.out.println("年龄:"+node.selectSingleNode("age").getText());
                System.out.println("性别:"+node.selectSingleNode("sex").getText());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
 
}
```

# JSP

[(41条消息) JSP基础_w͏l͏j͏的博客-CSDN博客](https://blog.csdn.net/qq_40624026/article/details/113866531?ops_request_misc=%7B%22request%5Fid%22%3A%22166573752216781432943758%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=166573752216781432943758&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-113866531-null-null.142^v56^pc_search_v3,201^v3^control_1&utm_term=jsp&spm=1018.2226.3001.4187)

JSP全称Java Server Pages，是一种动态网页开发技术。它使用JSP标签在HTML网页中插入Java代码。标签通常以<%开头以%>结束。
JSP是一种Java servlet，主要用于实现Java web应用程序的用户界面部分。JSP通过网页表单获取用户输入数据、访问数据库及其他数据源，然后动态地创建网页。
JSP是Servlet的扩展，在没有JSP之前，就已经出现了Servlet技术。Servlet是利用输出流动态生成HTML页面，包括每一个HTML标签和每个在HTML页面中出现的内容。

jsp执行流程

1. 浏览器发送一个 HTTP 请求给服务器。
2. Web 服务器识别出这是一个对 JSP 网页的请求，并且将该请求传递给 JSP 引擎。
3. JSP 引擎从磁盘中载入 JSP 文件，然后将它们转化为 Servlet。
4. JSP 引擎将 Servlet 编译成可执行类，并且将原始请求传递给 Servlet 引擎。
5. Web 服务器的某组件将会调用 Servlet 引擎，然后载入并执行 Servlet 类。在执行过程中，Servlet 产生 HTML 格式的输出并将其内嵌于 HTTP response 中上交给 Web 服务器。
6. Web 服务器以静态 HTML 网页的形式将 HTTP response 返回到浏览器中。
7. Web 浏览器处理 HTTP response 中动态产生的HTML网页。

### **语法**

```html
1.<% %>叫做脚本片段，其中写的内容会翻译在Servlet的Service方法中，显然我们可以在Service方法中定义局部变量或者调用其他方法，但是不能在Service中再定义其他的方法，也就是我们可以在<%%>中定义局部变量或者调用方法，但不能定义方法。在jsp页面可以有多个脚本片段，但是多个脚本片段之间要保证结构完整。
2.<%!%>称作声明，其中写的内容将来会直接翻译在Servlet类中，因为我们可以在类中定义方法和属性以及全局变量，所以我们可以在<%!%>中声明方法、属性、全局变量。
3.<%=%>称作jsp表达式，用于将已经声明的变量或者表达式输出到网页上面。
4.直接写在jsp页面<body></body>中的代码称作模板元素，将来会Servlet的Service方法out.write("___")中，作为输出内容。
编写与其等价的XML语句<jsp:scriptlet>代码片段</jsp:scriptlet>。
<%-- jsp注释 --%>注释内容不会被发送至浏览器甚至不会被编译
<!-- 注释 -->	HTML注释，通过浏览器查看网页源代码时可以看见注释内容
```

### **登录注册案例**

登录页面

```jsp
<%@ page language="java" contentType="text/html;
charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD
HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
content="text/html; charset=ISO-utf-8">
<title>登录</title>
<style>
 #a {

    width:50%;
    height:200px;
    border: 1px dashed ;
    background-color:lightyellow;
    text-align:center;
}
body{
background-color:lightblue;
}
</style>
</head>
<body>
<div id="a">
<h1>登录界面</h1>
<form action="check.jsp" method="post">
账号:<input type="text" name="id"/>
<br>
密码:<input type="password"name="password"/>
<br>

<input type="submit" value="login"/>
没有账号？<a href ="register.jsp">注册账号</a>
</form>
</div>
</body>
</html>
```

注册页面

```jsp
<%@ page language="java" contentType="text/html;
charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD
HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
content="text/html; charset=utf-8">
<title>账号注册</title>
</head>
<style>
 #a {
    width:50%;
    height:50%;
    border: 1px dashed ;
    background-color:lightgreen;
    text-align:center;
}

body{
background-color:lightyellow;
}
</style>
<body>
<%--注册框 --%>
<div id="a">
<h1>注册账号</h1>
<form action="registersuccess.jsp"  method="post">
账&nbsp;&nbsp;&nbsp;号:
<input type="text"
name="id">
<br>

密&nbsp;&nbsp;&nbsp;码:
<input type="password"name="password">
<br>

姓名:
<input type="text" name="name">
<br>

手机号:
<input type="text" name="phonenumber">
<br>

<input type="submit" value="注册">

<input type="submit" value="重置">
</form>
</div>
</body>
</html>
```

注册成功

```jsp

<%@ page language="java" contentType="text/html;
charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD
HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
content="text/html; charset=UTF-8">
<title>注册成功</title>
</head>
<style>
 #a {

    width:50%;
    height:200px;
    border: 1px dashed ;
    background-color:lightyellow;
    text-align:center;
}

</style>
<body>
<div id="a">
<form action="check.jsp"
method="post">
 <%
request.setCharacterEncoding("UTF-8");
   String id=request.getParameter("id");
   session.setAttribute("id", id);
   String name=request.getParameter("name");
   session.setAttribute("name", name);
   String password=request.getParameter("password");
   session.setAttribute("password", password);
 %>
 恭喜您注册成功！<br>
 您的账号为:<%=id %><br>
 您的密码为:<%=password %><br>
 请妥善保管好您的密码！<br>
</form>
<a href="login.jsp"
style="color:#FAAF46 font-size:10px">返回登录页面</a>
</div>
</body>
</html>
```

登陆成功

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录成功</title>
<style>
body{
background-color:yellow;
}
 #a {
    width:50%;
 height:200px;
 border: 1px dashed ;
    background-color:lightyellow;
    text-align:center;
}
</style>
</head>
<body>
<div id="a">
<h1 align="center">
页面仅供测试。
</h1>
<h2 align="center">登录成功</h2>
</div>
</body>
</html>
```

登陆失败

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录成功</title>
<style>
body{
background-color:grey;
}
 #a {
    width:50%;
 height:200px;
 border: 1px dashed ;
    background-color:lightyellow;
    text-align:center;
}
</style>
</head>
<body>
<div id="a">
<h1 align="center">
页面仅供测试。
</h1>
<h2 align="center">登录失败</h2>
<h3 align="center"><a href="login.jsp" style="color:#FAAF46 font-size:10px">返回登录页面</a></h3>
</div>
</body>
</html>

```

检查页面（check.jsp）主要代码（在body里面写）

```jsp
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-utf-8">
<title>check</title>
</head>
<body>
<form method="post" action="">
<% 
   String id=(String)session.getAttribute("id");
   String password=(String)session.getAttribute("password");
   String name=request.getParameter("id");
   session.setAttribute("name", name);
   String password1=request.getParameter("password");
   session.setAttribute("password", password1);
if(id.equals(name)&&password1.equals(password))
{ response.sendRedirect("loginsuccess.jsp");}
else
response.sendRedirect("loginfailure.jsp");
%>
</form>
</body>
</html>
```

这个只是用session来暂存我们的数据，没有存到数据库中，

# Web

cs：客户端服务器架构模式

优点 ： 一部分安全要求不高的计算任务存储任务在客户端执行，不需要把所有的计算和存储都放在服务器端执行，从而能够减轻服务器的压力，也能够减轻网络负荷

BS： 浏览器服务器架构模式

# Servlet



Servlet=Server+applet

Server：服务器

applet：小程序

Servlet含义是服务器端的小程序

![image-20221107084852606](https://myimage-1314195759.cos.ap-chengdu.myqcloud.com/img/202211070848734.png)



### 生命周期

servlet初始化时机，

可以通过load-on-startup配置来设置servlet启动的先后顺序，数字越小，启动越靠前

Servlet在容器中是单例的，线程不安全的

### HTTP

HTTP：**H**yper **T**ext **T**ransfer **P**rotocol超文本传输协议。HTTP最大的作用就是确定了请求和响应数据的格式。浏览器发送给服务器的数据：请求报文；服务器返回给浏览器的数据：响应报文。

| 名称           | 功能                                                 |
| -------------- | ---------------------------------------------------- |
| Host           | 服务器的主机地址                                     |
| Accept         | 声明当前请求能够接受的『媒体类型』                   |
| Referer        | 当前请求来源页面的地址                               |
| Content-Length | 请求体内容的长度                                     |
| Content-Type   | 请求体的内容类型，这一项的具体值是媒体类型中的某一种 |
| Cookie         | 浏览器访问服务器时携带的Cookie数据                   |

### 会话

通过会话跟踪技术来解决无状态问题

客户端第一次发请求给服务器，服务器获取session，获取不到，则创建新的，然后相应给客户端，下次客户端给服务器发请求时，会把sessionid带给服务器，服务器就获取到了

##### session保存作用域

是和具体的某一个session对映的

常用的api

void session.setAttribute(k, v);

Object session.getAttribute(k)

void removeAttribute(k)

<img src="https://myimage-1314195759.cos.ap-chengdu.myqcloud.com/img/202211071218127.png" alt="image-20221107121818305" style="zoom:50%;" />

**302表示重定向**

### 保存作用域

page级别，几乎不用

request一次请求响应范围

session一次会话范围

application整个应用程序范围  ： 不同浏览器也能访问

### 路径问题

<link th: href="@{/css/shopping.css}">

# Thymeleaf

[Thymeleaf入门到吃灰 - 鞋破露脚尖儿 - 博客园 (cnblogs.com)](https://www.cnblogs.com/msi-chen/p/10974009.html)

# Filter

```java
public class Target01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 1.打印一句话表明Filter执行了
        System.out.println("过滤器执行：Target01Filter");

        // 2.检查是否满足过滤条件
        // 人为设定一个过滤条件：请求参数message是否等于monster
        // 等于：放行
        // 不等于：将请求跳转到另外一个页面
        // ①获取请求参数
        String message = request.getParameter("message");

        // ②检查请求参数是否等于monster
        if ("monster".equals(message)) {

            // ③执行放行
            // FilterChain对象代表过滤器链
            // chain.doFilter(request, response)方法效果：将请求放行到下一个Filter，
            // 如果当前Filter已经是最后一个Filter了，那么就将请求放行到原本要访问的目标资源
            chain.doFilter(request, response);

        }else{

            // ④跳转页面
            request.getRequestDispatcher("/SpecialServlet?method=toSpecialPage").forward(request, response);

        }

    }

    @Override
    public void destroy() {

    }
```

# TreadLocal

本地线程

get()  set(obj)

我们可以通过set方法在当前线程上存储数据、通过get方法在当前线程上获取数据

# Listener

[代码重工 (gitee.io)](https://heavy_code_industry.gitee.io/code_heavy_industry/pro001-javaweb/lecture/chapter11/verse02.html)

将来学习SpringMVC的时候，会用到一个ContextLoaderListener，这个监听器就实现了ServletContextListener接口，表示对ServletContext对象本身的生命周期进行监控。