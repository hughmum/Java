> https://docs.docker.com/engine/
>
> - 镜像(image):应用的模板.联合文件系统
>
> - 容器(container):一个或者一组应用的启动停止删除。
>
> - 仓库(responsitory):存放镜像的仓库
>
>   docker hub：
>   
> - 卷:用于挂载目录和文件。

# 安装

https://www.runoob.com/docker/centos-docker-install.html



```shell
$ sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

//设置下载源



yum -y install yum-utils

```shell
sudo yum-config-manager \
    --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

```shell
sudo yum install docker-ce docker-ce-cli containerd.io
```

```shell
sudo systemctl start docker
```

```shell
yum remove docker-ce
```





```shell
rm -rf /var/lib/docker
```



## 配置镜像地址加速

![image-20210910191930202](image/image-20210910191930202.png)

![image-20210910192026963](image/image-20210910192026963.png)

# 可视化

## protainer

> 可以用来可是化docker



# 卸载

# 命令

## docker命令

### docker version

### docker info 

### docker --help	



## 镜像命令

### docker run imageID

> - --name:给容器命名
> - -v:    主机目录:容器目录    -V /mysql:/var/mysql  将主机的mysql目录映射到容器的mysql目录  -v挂载目录  ，如果没有主机目录就是匿名挂载
> - -p:   主机端口:容器端口  3306:3306
> - -it: 以交互模式启动
> - -P:  随机端口
> - -t:交互式启动
> - -d:守护式启动

### docker search imageName

### docker pull imageName

docker push imageName

### docker rmi imageId

> 移除镜像

### docker images

### 查看镜像创建过程

docker histroy imageId

## 容器命令

![点击查看源网页](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.mscto.com%2Fwp-content%2Fuploads%2F2020%2F02%2F20200216170310480.png&refer=http%3A%2F%2Fwww.mscto.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633852229&t=c50abdce75da1a1cd18ae5de9fe4af8c)

### 以交互模式进入container

docker exec -it containerId /bin/bash

> 以交互模式进入container

### 进入容器正在运行的终端

docker attach containerId

> 进入容器正在运行的终端

### 重启容器

docker restart containerId

### 强制退出容器

docker kill containerId

### 停止容器

docker stop containerId

### 开启容器

docker start containerId

### 更新容器配置

docker update redis --restart=always 

> 自动启动容器

### 重命名容器

docker rename oldname newname

### 移除容器

docker rm containerId

### 生成容器

docker commit

> - m:描述信息
> - a:作者信息



### 同步卷

docker --volumes-from containerId1  容器名

```shell
docker run -it --name docker02 --volumes-from docker01  centos7.0
```

> 可以将一个容器挂载的卷，也挂在到新得容器一份。



### 下载centos镜像

docker pull centos

# Docker数据卷

docker volume ls



docker volume create volume_name

docker volume inspect volume_name

# DockerFile

> 用来构建docker镜像文件，命令参数脚本。
>
> - 指令，命令大写
> - 从上往下执行
> - #是注释

- 编写dockerfile文件
- docker build构建成为镜像
- docker run运行镜像
- docker push发布镜像(docker hub 、阿里云镜像仓库)



```dockerfile
FROM    	 # 基础镜像，一切从这里开始构建，99%的镜像都是从FROM scratch，然后配置所需要的软件和配置来进行构建
MAINTAINER   #镜像是谁写了
RUN      	 #docker镜像  构建得时候需要运行得命令
ADD    		 #步骤:添加压缩包到docker，它会自动解压。  一般放到/usr/local
WORKDIR   	 #docker启动后在哪个目录工作的   
VOLUME  	 #挂载卷到哪个位置
EXPOSE  	 #指定镜像暴露端口
CMD     	 #指定这个容器启动的时候要运行的命令   CMD  echo，一个CMD 就是一个命令    CMD   ["ls","-a"] 启动容器后要执行的命令，run的时候会被用户输入的命令替换
ENTRYPOINT   #指定这个容器启动的时候要运行的命令   ，可以追加命令.CMD ls  之后再用ENTRYPOINT -l，最后这条命令就是ls -l，run的时候是吧用户的命令追加到ENTRYPOINT命令的后面。
ONBUILD  	 #当构建一个被继承的dockerfile，这个时候就会运行ONBUILD指令，是触发指令
COPY         #类似ADD命令，将我们的文件拷贝到镜像中。
ENV     	 #构建的时候，设置环境变量。
```

![image-20210915095814314](image/image-20210915095814314.png)

## 构建实战

> 创建dockerfile文件

```dockerfile
FROM centos
MAINTAINER yyc<2426029308@qq.com>
ENV MyPath /usr/local
WORKDIR $MyPath
RUN yum -y install vim
RUN yum -y install net-tools
EXPOSE 80
CMD echo $MyPath
CMD echo "------end--------"
CMD /bin/bash
```

> 构建dokcerImage
>
> 1.0是版本号,最后有一个.

```shell
docker build -f mydockerfile -t mycentos:1.0 . 
```

- --build-arg 参数名=value
- -f 文件名
- -t 镜像名
- . 从当前目录查找



## 制作tomcat镜像

> 准备压缩包



> 创建DockerFile  如果是这个命名，就不需要指定-f

```dockerfile
FROM centos
MAINTAINER yyc<2426029308@qq.com>
COPY readme.txt /user/local/readme.txt
ADD jdk-8u11-linux-x64.tar.gz /usr/local/
ADD apache-tomcat-9.0.22.tar.gz /usr/local/
RUN yum -y install vim
ENV MYPATH /user/local
WORKDIR $MYPATH
ENV JAVA_HOME /usr/local/jdk1.8.0_11
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/localapache-tomcat-9.0.22
ENV CATALINA_BASH /usr/local/apache-tomcat-9.0.22
ENV PATH $PATH:$JAVA_HOME/bin:/$CATALINA_HOME/lib:$CATALINA_HOME/bin
EXPOSE 8080
CMD /usr/local/apache-tomacat-9.0.22/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.22/bin/logs/catalina.out
```

> 构建镜像

```dockerfile
docker build -t mytomcat -f DockerFile .  #最后有一个.
```

> 运行镜像

```dockerfile
docker ru n-d -p 9909:8080 --name mytomcat -v /yxc/build/tomcat/test:/url/local/apache-tomcat-9.0.22/webapps/test -v /yxc/build/tomcat/logs:/url/local/apache-tomcat-9.0.22/logs
```







# Docker网络

> docker创建得时候有一个默认网络docker01，它就像一个路由器，通过它，可以实现docker容器之间得通信，但为了网络隔离，我们需要自己创建网络，创建后的网络，可以直接通过容器名进行网络通信。

## 网络模式

- bridge:桥接模式
- non:不配置网络
- host:和宿主共享网络

## 创建网络

docker network create --driver 网络模式 --subnet 192.168.0.1/16

## 查看所有网络

docker network ls

## 检查某个网络

docker network inspect 网络Id

## 运行容器时指定网络

docker run -d -p --name tomacat01 --net 网络id tomcat

## 添加容器到网络

docker network connect 网络ID    容器ID

# Docker Compose

> 服务(service):一个应用的容器
>
> 项目(project):一个项目由多个服务组成。一个compose就是一个项目。

## 安装

```shell
curl -L https://github.com/docker/compose/releases/download/1.18.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose


sudo chmod +x /usr/local/bin/docker-compose

docker-compose --version
```

docker-compose卸载



## 命令

docker-compose ps

### 启动compose

> 需要存在docker-compose.yml

docker-compose up

- -f：如果文件名不是docker-compose.yaml需要手动指定
- -p: 用来指定项目名称，如果不指定，就是文件夹名称。
- -d: 后台启动



docker-compose exec

> docker-compose exec -it serviceName /bin/bash

docker-compose ps

docker-compse restart 

> 如果不值当服务名或者id，默认重启所有服务。

docker-compose down

docker-compose rm

> 删除exit状态的service

- -f: 强制删除
- -v: 删除容器的所有卷

docker-compose build

> 用来将指定的Dockerfile文件打包成镜像文件。

docker-compose top serviceName

> 查看容器的 进程。

## 语法

```yaml
version: "3.0"  #当前compose版本号,随便写
services:    #服务列表
  yxc-tomcat:   #服务名，容器名,服务名唯一
    image: tomcat:9    #创建哪个镜像
    build:  #用来指定dockerfile文件的位置，先构建镜像，再自动运行容器
      context: .  #指定dockerfile所在目录
      dockerfile: Dockerfile   #指定dockerfile文件名。
    container_name: yxc-tomcat01
    ports:          #端口映射
      - "18080:8080"  #尽量用引号包起来，因为yaml解析可能会出错xx:yy
    volumes:  #挂载数据卷  宿主机:容器    数据卷名:容器
      - /root/yxc/docker-compose/tomcat/webapps:/usr/local/tomcat/webapps
    depends_on:  #部署依赖，只有在某些容器部署之后才进行部署，其他容器不一定完全部署。
    healthcheck:  #心跳监测
      test: ["CMD","curl","-f","http://localhost"]
      interval: 1m30s
      timeout: 10s
      retries: 3
    sysctls:   #设置系统内核参数
      - net.core.somaxconn=1024
      - net.ipv4.tcp_syncookies=0
    ulimits:  #用来修改容器中系统内部进程数限制  日后使用时可根据当前容器运行服务要求进行修改
      nproc: 65535   #设置进程数
        soft: 20000
        hard: 40000
    command: "echo whoami"
    environment:  #设置环境变量
      yxc-myname="root"
    env_file:   #用*.env文件设置环境变量
      - tomcat01.env
    networks:
      - yxc-netword01
networks:   //
  yxc-netword01:
    external: false  #如果为true，就不创建新的网络
    name:   #映射外部网络

volumes:
  yxc-volume1:
    external: false  #如果为true，就不创建新的卷
    name:   #映射外部卷
```

# 常用环境安装

> 容器安装
>
> apt-get update
>
>  apt-get install vim

## 安装portainer

> 8000与docker通信
>
> 9000与web通信

```shell
docker pull portainer/portainer


docker run -d -p 8000:8000 -p 8001:9000 --name portainer  --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v /home/yxc/portainer_data:/data portainer/portainer
```

admin	yang1123

## mysql5.7

```shell
sudo docker pull mysql:5.7

sudo docker run -p 8348:3306 -u root --name mysql -v /mydata/mysql/log:/var/log/mysql -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
```

### 修改mysql编码

/mydata/mysql/conf/my.cnf

```shell
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
```



## redis

```shell
sudo docker pull redis

mkdir -p /mydata/redis/conf
touch /mydata/redis/conf/redis.conf

docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```

### 测试

### docker  exec -it redis redis-cli

### 修改配置

> 变为持久化，关闭不丢失数据，修改配置文件

redis.conf

```conf
appendonly yes
```

## nacos

```shell
docker  run --name nacos -d -p 8346:8848 --privileged=true --restart=always -e JVM_XMS=256m -e JVM_XMX=256m -e MODE=standalone -e PREFER_HOST_MODE=hostname -v /mydata/nacos/logs:/home/nacos/logs -v /mydata/nacos/init.d/custom.properties:/home/nacos/init.d/custom.properties nacos/nacos-server
```

## jdk8

```shell
docker pull openjdk:8-jdk-alpine
```

## 安装zookeeper

```shell
docker pull zookeeper
docker run --privileged=true -d -name zookeeper -p 8031:2181  zookeeper:latest

#使用
docker exec -it zookeeper /bin/bash
./bin/zkCli.sh


ls /servers  列出所有服务
```

## 安装consul

```shell
docker pull consul


docker run -d -p 8045:8500 --restart=always --name=consul consul:latest agent -server -bootstrap -ui -node=1 -client='0.0.0.0'


agent: 表示启动 Agent 进程。

server：表示启动 Consul Server 模式

client：表示启动 Consul Cilent 模式。

bootstrap：表示这个节点是 Server-Leader ，每个数据中心只能运行一台服务器。技术角度上讲 Leader 是通过 Raft 算法选举的，但是集群第一次启动时需要一个引导 Leader，在引导群集后，建议不要使用此标志。

ui：表示启动 Web UI 管理器，默认开放端口 8500，所以上面使用 Docker 命令把 8500 端口对外开放。

node：节点的名称，集群中必须是唯一的，默认是该节点的主机名。

client：consul服务侦听地址，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1所以不对外提供服务，如果你要对外提供服务改成0.0.0.0

join：表示加入到某一个集群中去。 如：-json=192.168.0.11。
```

1.14.107.3:8045访问



## 安装rabbitmq

### 安装

```bash
# for RabbitMQ 3.9, the latest series
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```

安装可视化插件

```bash
rabbitmq-plugins enable rabbitmq_management  
```

可用网页访问：

http://localhost:15672/   账号密码：guest,guest 

httpweb管理是：15672

Tcp是:5672

集群式：25672

## 安装gitlab

```shell
docker pull gitlab/gitlab-ce
```

```shell
docker run -d  -p 8443:443 -p 8880:82 -p 222:22 --name gitlab --restart always -v /home/yxc/gitlab/config:/etc/gitlab -v /home/yxc/gitlab/logs:/var/log/gitlab -v /home/yxc/gitlab/data:/var/opt/gitlab gitlab/gitlab-ce
```

修改配置

```shell
# gitlab.rb文件内容默认全是注释
$ vim /home/gitlab/config/gitlab.rb
```

```shell
# 配置http协议所使用的访问地址,不加端口号默认为80
external_url 'http://127.0.0.1'

# 配置ssh协议所使用的访问地址和端口
gitlab_rails['gitlab_ssh_host'] = '127.0.0.1:82'
gitlab_rails['gitlab_shell_ssh_port'] = 222 # 此端口是run时22端口映射的222端口
external_url 'http://192.168.66.77:82'
unicorn['port'] = 8099
```

修改管理员密码

```shell

docker exec -it <容器id> bash  实例如下图。
cd /opt/gitlab/bin
gitlab-rails console -e production
user = User.where(username: ‘root’).first
user.password = ‘password’
user.save!
```





### 命令

启动gitlab服务

sudo gitlab-ctl start

 

gitlab服务停止

sudo gitlab-ctl stop

 

重启gitlab服务

sudo gitlab-ctl restart

## 安装jenkins

```shell
docker pull jenkins/jenkins
```

```shell
mkdir /home/yxc/jenkins_mount
chmod 777 /home/yxc/jenkins_mount
```

```shell
docker run -d  -u root  -p 8240:8080 -p 8241:50000 -v /home/yxc/jenkins_mount:/var/jenkins_home -v /etc/localtime:/etc/localtime -v /usr/local/apache-maven-3.6.3:/usr/local/maven --restart=always --name jenkins jenkins/jenkins  
```



配置镜像加速

```shell
cd /home/yxc/jenkins_mount/
vi  hudson.model.UpdateCenter.xml	
#将 url 修改为 清华大学官方镜像：https://mirrors.tuna.tsinghua.edu.cn/jenkins/updates/update-center.json


```



网址

ip:10240



获取管理员密码

```
vi  /home/yxc/jenkins_mount/secrets/initialAdminPassword
```

## 安装tomcat

```shell
docker pull tomcat:9
```

```shell
docker run -d -p 8032:8080 -v /home/yxc/tomcat:/home/yxc/tomcat --name tomcat tomcat:9
```



容器的tomcat在

/usr/local/tomcat

添加用户权限

tomcat/conf/tomcat-user.xml

```xml
<tomcat-users>
    
<role rolename="tomcat"/>
<role rolename="role1"/>
<role rolename="manager-script"/>
<role rolename="manager-gui"/>
<role rolename="manager-status"/>
<role rolename="admin-gui"/>
<role rolename="admin-script"/>
<user username="tomcat" password="tomcat" roles="manager-gui,manager-script,tomcat,admin-gui,admin-script"/>
    
</tomcat-users>

```

/tomcat/webapps/manager/META-INF/context.xml

```xml
<!--
<Valve className="org.apache.catalina.valves.RemoteAddrValve"
allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1" />
-->

```

安装harbor



## 安装jar包

制作Dockerfile

```dockerfile
FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ./target/${JAR_FILE} app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","./app.jar"]
```

```shell
docker build --build-arg JAR_FILE=jar包 -t test-jar:v1 .
```

```shell
docker run -d -p 8084:8084 --name test -u root test-jar:v1 
```

```shell
docker stop test
docker rm test
docker rmi test-jar:v1
docker build --build-arg JAR_FILE=git-test-1.0-SNAPSHOT.jar -t test-jar:v1 .
docker run -d -p 8084:8084 --name test -u root test-jar:v1
```

## 安装harbor

```shell
#1）先安装Docker并启动Docker（已完成）
参考之前的安装过程
#2）先安装docker-compose
#3）给docker-compose添加执行权限
sudo chmod +x /usr/local/bin/docker-compose
#4）查看docker-compose是否安装成功
docker-compose -version
#5）下载Harbor的压缩包（本课程版本为：v1.9.2）
https://github.com/goharbor/harbor/releases
#6）上传压缩包到linux，并解压
tar -xzf harbor-offline-installer-v1.9.2.tgz
mkdir /opt/harbor
mv harbor/* /opt/harbor
cd /opt/harbor
#7）修改Harbor的配置
sudo curl -L https://github.com/docker/compose/releases/download/1.21.2/dockercompose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
vi harbor.yml
#修改hostname和port
hostname: 192.168.66.102
port: 85
#8）安装Harbor
./prepare
./install.sh
#9）启动Harbor
docker-compose up -d 启动
docker-compose stop 停止
docker-compose restart 重新启动
#10）访问Harbor
http://192.168.66.102:85
#默认账户密码：admin/Harbor12345
```

![image-20210921214325222](image/image-20210921214325222.png)

上传镜像

1.  打标签

   1. docker tag test-jar :v1 192.168.56.10:85/test/test-jar:v1

   

2. 设置配置/etc/docker/daemon.js

   1. ```js
      {
        "registry-mirrors": ["https://fmn79rbj.mirror.aliyuncs.com"],
      "insecure-registries": ["192.168.56.10:85"]
      }
      ```

   2. 重启docker   ：systemctl restart docker

3. 登录

   1. docker login -u admin -p Harbor12345 192.168.56.10:85

4.  上传镜像

   1. docker push 192.168.56.10:85/test/test-jar:v1

下载镜像

拉取镜像

1. 登录
   1. docker login -u admin -p Harbor12345 192.168.56.10:85
2.  下载镜像
   1. docker pull 192.168.56.10:85/test/test-jar:v1



## 安装elasticsearch

```shell
cat /proc/sys/vm/max_map_count
sysctl -w vm.max_map_count=262144

#拉取镜像
docker pull elasticsearch:7.7.0

#启动镜像
docker run --name elasticsearch -d -e ES_JAVA_OPTS="-Xms512m -Xmx512m" -e "discovery.type=single-node" -p 9200:9200 -p 9300:9300 elasticsearch:7.7.0
```

下载可视化Elasticsearch-head(只用于查看数据，不操作数据)

```shell
#拉取镜像
docker pull mobz/elasticsearch-head:5

#创建容器
docker create --name elasticsearch-head -p 9100:9100 mobz/elasticsearch-head:5

#启动容器
docker start elasticsearch-head
or
docker start 容器id （docker ps -a 查看容器id ）
```

开启es跨域

```shell
docker exec -it elasticsearch /bin/bash （进不去使用容器id进入）

vi config/elasticsearch.yml

exit
docker restart 容器id
```

elasticsearch.yml

```yaml
http.cors.enabled: true 
http.cors.allow-origin: "*"
```



修改ElasticSearch-head 操作时不修改配置，默认会报 406错误码

```shell
#复制vendor.js到外部
docker cp fa85a4c478bf:/usr/src/app/_site/vendor.js /usr/local/

#修改vendor.js
vim vendor.js

#修改完 复制回去
docker cp /usr/local/vendor.js  fa85a4c478bf:/usr/src/app/_site
#重启容器
docker restart 容器id
```

![image-20210924095117284](image/image-20210924095117284.png)

安装ik分词器

下载地址：

https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.7.0/elasticsearch-analysis-ik-7.7.0.zip



```shell
#将压缩包移动到容器中
docker cp /tmp/elasticsearch-analysis-ik-7.7.0.zip elasticsearch:/usr/share/elasticsearch/plugins

#进入容器
docker exec -it elasticsearch /bin/bash  

#创建目录
mkdir /usr/share/elasticsearch/plugins/ik

#将文件压缩包移动到ik中
mv /usr/share/elasticsearch/plugins/elasticsearch-analysis-ik-7.7.0.zip /usr/share/elasticsearch/plugins/ik

#进入目录
cd /usr/share/elasticsearch/plugins/ik

#解压
unzip elasticsearch-analysis-ik-7.7.0.zip

#删除压缩包
rm -rf elasticsearch-analysis-ik-7.7.0.zip
```



## 安装kibana

```shell
docker pull kibana:7.7.0
docker run --name kibana -e ELASTICSEARCH_URL=http://192.168.56.10:9200 -p 5601:5601 -d kibana:7.7.0
```

修改/usr/share/kibana/config/kibana.yml

```yaml
server.name: kibana
server.host: "0"
elasticsearch.hosts: [ "http://192.168.56.10:9200" ]
monitoring.ui.container.elasticsearch.enabled: true
```

设置中文

192.168.56.10:5601 访问

## 安装nginx

```shell
docker run -d -p 80:80 --name nginx -v /opt/nginx/html:/usr/share/nginx/html/  -v /opt/nginx/conf/:/etc/nginx/  -v /opt/nginx/logs:/var/log/nginx  -v /opt/nginx/conf/conf.d:/etc/nginx/conf.d nginx
```

## 安装zipkin

```shell
docker pull docker.io/openzipkin/zipkin
docker run --name zipkin -di -p 8311:9411  openzipkin/zipkin

```

http://1.14.107.3:8311/zipkin

## 安装minor

```shell
docker pull minio/minio

docker run -it -p 9000:9000 -p 8999:39871 -d --name minio \
-e "MINIO_ROOT_USER=root" \
-e "MINIO_ROOT_PASSWORD=admin1111" \
-v /mydata/minio1/data:/data \
-v /mydata/minio1/config:/root/.minio minio/minio  server /data \
--console-address ":39871"
```

