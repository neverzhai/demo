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
于是我就开始排查问题, 惨痛的过往经历告诉我这一定是个时区问题, 于是我先去看了看我数据库存在时间,然后跟我接口调用取出的时间进行了对比, 二者相差了13个小时
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

数据库使用的timezone图片




 
 