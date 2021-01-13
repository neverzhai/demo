## 雪花算法的原理

## mybatis 中的雪花算法实现 

## Java 实现一个雪花算法

##
```sql
CREATE TABLE `man_worker_id_manager`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `app_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用名称',
  `worker_id` int(11) NOT NULL COMMENT 'worker id',
  `ref_count` int(11) NOT NULL DEFAULT 0 COMMENT '引用次数',
  `ip_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'IP 地址',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_app_id`(`app_name`, `worker_id`) USING BTREE COMMENT '应用-ID唯一键'
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '统一ID生成 workerId管理表' ROW_FORMAT = Dynamic;

```