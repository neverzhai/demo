## RocketMQ 的事务消息

### 消息中间件的事务机制

### RocketMQ的事务消息原理

https://sourcegraph.com/github.com/Wasabi1234/Java-Interview-Tutorial/-/blob/%E6%95%B0%E6%8D%AE%E5%AD%98%E5%82%A8/%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97/Java%E9%AB%98%E6%80%A7%E8%83%BD%E7%B3%BB%E7%BB%9F%E7%BC%93%E5%AD%98%E7%9A%84%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5.md
https://rocketmq.apache.org/rocketmq/the-design-of-transactional-message/

### RocketMQ事务消息的最佳实践
https://zhuanlan.zhihu.com/p/249233648
场景: 订单创建成功后发送消息, 去清空购物车.
```sql

CREATE TABLE `test_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
  `order_id` varchar(32) NOT NULL COMMENT '订单号',
  `user_id` varchar(64)  NOT NULL COMMENT '业务用户ID',
  `goods_id` varchar(64)  NOT NULL COMMENT '订单中商品ID',
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