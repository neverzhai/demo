## RocketMQ 的事务消息

### 消息中间件的事务机制

### RocketMQ的事务消息原理

### RocketMQ事务消息的最佳实践
场景: 订单创建成功后发送消息, 去清空购物车.
```sql

CREATE TABLE `test_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `order_id` varchar(32) NOT NULL COMMENT '订单号',
  `user_id` varchar(64)  NOT NULL COMMENT '业务用户ID',
  `payment_amount` decimal(10,2) NOT NULL COMMENT '订单实付金额 单位',
  `order_status` tinyint(4) NOT NULL COMMENT '订单状态 0-未支付、1-已支付、2-已取消(终态)、3-已完成(终态)、4-支付失败、5-已过期',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_order_id` (`order_id`) USING BTREE COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='订单表';

CREATE TABLE `test_shopping_car` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `goods_id` varchar(32) NOT NULL COMMENT '商品id',
  `user_id` varchar(64)  NOT NULL COMMENT '业务用户ID',
  `goods_count` int(11) NOT NULL COMMENT '商品数量',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户购物车表';
```