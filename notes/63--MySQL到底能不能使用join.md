```sql
CREATE TABLE `test_department_info` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
`depart_id` varchar(32) NOT NULL COMMENT '部门id',
`depart_name` varchar(64) NOT NULL COMMENT '部门名称',
`location` varchar(64) NOT NULL COMMENT '部门所在地',
`deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门信息表' ROW_FORMAT = Dynamic;


CREATE TABLE `test_employee_info` (
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID自增',
`depart_id` varchar(32) NOT NULL COMMENT '员工所在部门id',
`employee_id` varchar(32) NOT NULL COMMENT '员工id',
`employee_name` varchar(32) NOT NULL COMMENT '员工名称',
`status` tinyint(8) NOT NULL DEFAULT 1COMMENT '状态, 1-在职, 2-离职',
`deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '0：未删除 1：已删除',
`created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='员工信息表';

drop procedure idata;
delimiter ;;
create procedure idata()
begin
  declare i int;
  set i=1;
  while(i<=1000)do
    insert into test_department_info (id, depart_id, depart_name)values(i, (SELECT UPPER(REPLACE(UUID(), '-', ''))), concat(i, '部门', i));
    set i=i+1;
end while;
  
  set i=1;
  while(i<=1000000)do
    insert into test_employee_info (id, depart_id, employee_id, employee_name, status) values(i,
		(SELECT depart_id FROM test_department_info WHERE id IN ((SELECT CEIL(RAND() * 1000)), 500) LIMIT 1), 
		(SELECT UPPER(REPLACE(UUID(), '-', ''))),
		concat(i,'员工', i), 1);
    set i=i+1;
end while;

end;;
delimiter ;
call idata();
```

## 场景描述

## 使用join方式 -- 无索引

## 

