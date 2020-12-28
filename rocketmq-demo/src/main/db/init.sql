CREATE TABLE `sync_sys_user_info`  (
 `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
 `user_id` varchar(64) NOT NULL COMMENT '用户ID',
 `nick_name` varchar(128) NULL COMMENT '用户昵称',
 `mobile` varchar(128) NULL COMMENT '手机号',
 `email` varchar(128)  NULL COMMENT '邮箱',
 `gender` tinyint(4) DEFAULT 0 COMMENT '0 未知 1 男 2 女',
 `address` varchar(255) NULL DEFAULT NULL COMMENT '地址',
 `registered_time` datetime  NULL COMMENT '注册时间',
 `user_name` varchar(255) NULL COMMENT '姓名',
 `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
 `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'mq同步用户数据' ROW_FORMAT = Dynamic;
