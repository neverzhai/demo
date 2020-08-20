https://blog.csdn.net/Mr_Mocha/article/details/103332026?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase


https://blog.csdn.net/sgambler/article/details/103488278?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase

### Sql 和Java的时间对应关系

## 描述问题
昨天测试又给我提了一个bug(我怎么觉得自己是个被bug驱动的渣渣程序员),说他更新了某条数据, 但是这调数据的更新时间没有变化. 我的第一反映是怎么可能,
我在创建数据库表的时候明明设置了更新时间会自动更新呀? 但是人家就是测试这么个bug呀, 我也只能认怂, 修吧. 

### 设计如下的数据库表结构
```sql
CREATE TABLE `user_info`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `username` varchar(64) NOT NULL COMMENT '用户名',       
  `mobile` varchar(128) NOT NULL COMMENT '手机号',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;
```

## 解决方法
#### 直接更新, 不要先查询----核心是保证modifiedTime 没有值

#### 使用@TableField + update注解

### 使用@TableFiled + fill 注解


