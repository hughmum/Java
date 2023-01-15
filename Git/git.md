## 本地连接远程仓库教程 (github)

[(192条消息) 关于git的问题：error: src refspec main does not match any_TripleGold.的博客-CSDN博客](https://blog.csdn.net/gongdamrgao/article/details/115032436?ops_request_misc=%7B%22request%5Fid%22%3A%22166079383016781683951728%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=166079383016781683951728&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-115032436-null-null.142^v41^pc_search_integral,185^v2^control&utm_term=error%3A src refspec main does not match any&spm=1018.2226.3001.4187)

```
git init
```

```
git remote add origin git@github.com:用户名/仓库名.git
```

修改默认创建的本地分支名称，因为github默认main

```git
git branch -M main 
```

拉取或者推送

```
git pull origin main

git add .
git commit -m "***"
git push origin main
```

### 如果是gitee平台

```
git remote add origin https://gitee.com/hughmum/body-test.git

git clone 远程仓库地址
例如 git clone  https://gitee.com/hughmum/physical-testing.git

git add .
git commit -m "***"
git push origin master
```





## SSH密钥

用户名和邮箱

```
查看
git config user.name
git config user.emai

修改
git config --global user.name 用户名
git config --global user.email 邮箱
```

[(192条消息) git连接远程仓库_Sout xza的博客-CSDN博客_git链接到远程仓库](https://blog.csdn.net/ooblack/article/details/115462360?ops_request_misc=%7B%22request%5Fid%22%3A%22166078921116780357263987%22%2C%22scm%22%3A%2220140713.130102334..%22%7D&request_id=166078921116780357263987&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_click~default-2-115462360-null-null.142^v41^pc_search_integral,185^v2^control&utm_term=git连接远程仓库&spm=1018.2226.3001.4187)

```
//查看本地秘钥
cat ~/.ssh/id_rsa.pub
```

[(192条消息) gitlab添加SSH密钥——查看本地密钥 & 生成ssh密钥_viceen的博客-CSDN博客_gitlab生成ssh密钥详细步骤](https://blog.csdn.net/weixin_44867717/article/details/121751451?ops_request_misc=&request_id=&biz_id=102&utm_term=查看本地秘钥git&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-4-121751451.142^v41^pc_search_integral,185^v2^control&spm=1018.2226.3001.4187)