# 项目基础框架 
## 缓存模块框架 framework-cache
> **缓存模块**
> 封装redis缓存

## 公告模块框架 framework-common
> **项目通用模块**
> 包括各种util,例如:日期DateUtil,金额AmountCountUtil等等

## 压缩框架 framework-commpress
> **压缩通用模块**
> 提供各种压缩方式
1.bzip2
2.gzip
3.jar
4.tar
5.zip
6.zlib
7.shell命令(gzip,targz)

## 核心框架 framework-core
> **核心设计模块(不通用),核心代码,提供基础信息**
1.基础的service,dao
2.文件上传,nfs/ftp/sftp
3.mybatis分页支持(目前mysql,可扩展)
4.整合easyui分页功能
5.验证码
6.整合easyui树结构
7.统一前端请求后的返回参数
8.发送邮件,包括html邮件

## 异常体系框架 framework-exception
> **异常体系模块,封装异常信息**
1.mysql数据库返回的错误信息,转成可识别信息
2.oracle数据库返回的错误信息,转成可识别信息
3.通用的异常返回的错误信息,转成可识别信息

## IP框架 framework-ip
> **ip模块**
1.QQ ip地址库

## poi框架 framework-poi
> **poi模块**
1.csv读取及下载
2.excel读取

## 加密解密框架 framework-security
> **加密解密模块**
1.不可逆:base64,MD5
2.对称密钥:AES,DES,3DES
3.非对称密钥:RSA

#项目搭建
##maven依赖
> 地址 http://pan.baidu.com/s/1o7RxCqY
> IDE 推荐使用idea 地址 http://pan.baidu.com/s/1mijcCM8

##JDK
> 1.6

## 本地安装

```
mvn install -DskipTests=true
```

因为没有公布到网上，所以需要下载完成后安装到本地
