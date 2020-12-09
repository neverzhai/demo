## 锁的作用

### 全局锁

### 表锁

### 行锁
#### 无索引情况下行锁退化成表锁---不是简单的生成表锁, 有个记录锁
需要了解都有什么锁的类型和锁的模式, 以及他们起作用的策略

#### 行锁的实现是基于索引实现的

### Lock modes 
- S锁
- X锁

### 死锁 VS 死锁检测

### INNODB的锁的策略

### 常用的跟锁有关的表
```sql
show processlist;

// 当前运行的所有事务
 SELECT * FROM information_schema.INNODB_TRX;
 //当前出现的锁
 SELECT * FROM information_schema.INNODB_LOCKs;
 
// 锁等待的对应关系
 SELECT * FROM information_schema.INNODB_LOCK_WAITS;
 
 SHOW VARIABLES like "autocommit"
 
 KILL 4742
 
 show processlist;
 
  SELECT * FROM information_schema.INNODB_TRX;
  
  SELECT * FROM information_schema.INNODB_LOCKs;
  
  SELECT * FROM information_schema.INNODB_LOCK_WAITS;
  
  
  show status like 'thread%';
  SHOW VARIABLES like "autocommit"
  
  KILL 4801
  
  SET autocommit  = 1
  
  select now(),(UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(a.trx_started)) diff_sec,b.id,b.user,b.host,b.db,d.SQL_TEXT from information_schema.innodb_trx a inner join information_schema.PROCESSLIST b
 on a.TRX_MYSQL_THREAD_ID=b.id and b.command = 'Sleep'
 inner join performance_schema.threads c ON b.id = c.PROCESSLIST_ID
```

### lock mode and lock type 

## 参考资料
https://blog.csdn.net/qq_44961149/article/details/108420073
https://www.cnblogs.com/kunjian/p/11552646.html