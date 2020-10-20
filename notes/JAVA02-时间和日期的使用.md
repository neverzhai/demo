### 时区惨案
https://www.jianshu.com/p/c37b11472151

### 数据库自己的时区设置

### 数据库连接:  
serverTimeZone 的作用
           指定timeZone: shanghai
           指定为UTC
           指定为:GMT+8
           
### 入参和出参的处理          
参数写出timezone  GMT+8
参数不写timezone    

### 注解@JsonFormat VS @DateTimeFormat的区别      



最近发生了一件奇怪的事情, 刚刚提测的一期需求, 测试已经连着给我提了六七个bug都是跟时间有关的, 比如我新创建的是时候明明选择的时间是2020-08-14,
咋我再取出来就是20200-08-13呢? 又比如我刚刚做了更新操作, 怎么系统显示的更新时间是明天的呀? 看着这些bug, 我突然想起那首抖音很火的歌: "小朋友,
你是否有很多问好". 我多想回答: 是的, 我明明什么都没改, 昨天还是好好的. 返回的时候我也使用@JsonFormat进行了时区的转换. 那是为什么呢?
于是我就开始排查问题, 惨痛的过往经历告诉我这一定是个时区问题, 于是我先去看了看我数据库存下时间,然后跟我接口调用取出的时间进行了对比, 二者相差了13个小时
所以可以认定一定是时区问题, 但是为什么之前是好的, 突然坏了呢? 本着代码不会欺骗我的原则, 我又开始继续查找, 一定是什么改动让曾经可以工作的代码突然
崩塌, 果然, 功夫不负有心人呀, 我发现在一次数据库改造中, 数据库链接url被改动了

改动前: 
```java
jdbc:mysql://xxxx:3306/db_name?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai
```
改动后:
```java
jdbc:mysql://xxxx:3306/db_name?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false
``` 

于是我怀疑是因为去掉了这个serverTimeZone导致的, 于是我把这个设置又加上去重新实验了一次, 果然时间相关的显示都正确了.
但是优秀的我怎么会止步于问题得到解决呢, 我必须要探究一下背后的原因呀.serverTimezone的作用什么? 如果我不设置还可以通过什么方式解决这个问题?
为什么使用@JsonFormat注解指定timezone不好用? 时间处理相关的最佳实践到底是什么? 带着这些疑问, 我开始了关于时间的探索, 并将其记录下来已避免自己再犯
同样的错误.

首先让我们创建一张表, 表结构如下: 
```sql
CREATE TABLE `activity_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `activity_name` varchar(64) NOT NULL COMMENT '活动名称',       
  `activity_desc` varchar(128) NOT NULL COMMENT '活动描述',
  `start_time` datetime NOT NULL COMMENT '活动起始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '活动信息表' ROW_FORMAT = Dynamic;
```

#### 实验一: 使用带有serverTimezone=Asia/Shanghai 或 serverTimezone=GMT%2b8 的连接


#### 实验二: 使用不带有serverTimezone的连接

#### 实验三：使用@JsonFormat注解设置timezone=GMT+8

#### 实验三: 设置serverTimezone=UTC

#### 总结
数据库使用的timezone图片----------

mysql> show variables like '%time_zone%';
+------------------+--------+
| Variable_name | Value |
+------------------+--------+
| system_time_zone | EDT |
| time_zone  | SYSTEM |
+------------------+--------+
2 rows in set (0.00 sec)
system_time_zone 表示系统使用的时区是 EDT即北美的东部夏令时(-4h)。
time_zone 表示 MySQL 采用的是系统的时区。也就是说，如果在连接时没有设置时区信息，就会采用这个时区配置


https://blog.csdn.net/qq_30553235/article/details/79612824


## Mysql time_zone and system_time_zone
参考文档: https://www.cnblogs.com/gaogao67/p/10686515.html
全局参数time_zone

用来设置每个连接会话的时区，默认为system时，使用全局参数system_time_zone的值。

The current time zone. This variable is used to initialize the time zone for each client that connects. By default, the initial value of this is 'SYSTEM' (which means, “use the value of system_time_zone”).


 
全局参数system_time_zone
系统时区，在MySQL启动时会检查当前系统的时区并根据系统时区设置全局参数system_time_zone的值。

The system time zone. When the server starts, it attempts to determine the time zone of the host machine automatically and uses it to set thesystem_time_zone system variable. The value does not change thereafter.
 
如何在启动MySQL时指定system_time_zone呢?????

### serverTimeZone 的作用
serverTimeZone的作用就是指定web服务器和mysql服务器的会话期间的mysql服务器时区，就是临时指定mysql服务器的时区。

### 疑问

Joda-Time 这是个啥呀???

 
 