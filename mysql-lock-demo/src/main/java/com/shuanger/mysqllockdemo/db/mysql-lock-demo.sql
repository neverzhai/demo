CREATE TABLE `test_lock_table_one`  (
                                      `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
                                      `custom_id` varchar(256)  NOT NULL COMMENT '自定义id',
                                      `name` varchar(256)  NOT NULL COMMENT '名称',
                                      `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
                                      `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_customId`(`custom_id`) USING BTREE COMMENT '用户ID'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '测试锁表' ROW_FORMAT = Dynamic;


CREATE TABLE `test_lock_table_two`  (
                                        `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
                                        `custom_id` varchar(32) NOT NULL COMMENT '自定义id',
                                        `name` varchar(32) NOT NULL COMMENT '名称',
                                        `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
                                        `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '测试index对锁的影响' ROW_FORMAT = Dynamic;
