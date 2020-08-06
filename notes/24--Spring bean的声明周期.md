https://mp.weixin.qq.com/s?__biz=MzI3ODcxMzQzMw==&mid=2247502636&idx=2&sn=4cc270e2b49f13d289fc0b3362a56902&chksm=eb504e1adc27c70c535b257e9d56b2b40fddcae3f2803ceb018cc2488bc61ee524f45eb58ade&mpshare=1&scene=1&srcid=0726RzRSjH4l0tkr6JwRtXLe&sharer_sharetime=1595758565118&sharer_shareid=1b68a65e52e16ef3674250c07d4f365d&key=6efd7c12e882e8cfd13f055285d444aa5bd23480ae2db8d2c6f93a6fde472d61f7a6c5bde3c0f1feb3376535898171c2aa54955a47eba76ce7e18763bbfe4f5c950da4331afca12c687902902976bc75&ascene=1&uin=MTMzNzk2NTY0MA%3D%3D&devicetype=Windows+10+x64&version=62090529&lang=zh_CN&exportkey=AaqpnEvghfYjJ47s4MH1RsY%3D&pass_ticket=KsQ0axHHAia1Nxalo5ERHodmUJ6HHwWYtl6U%2BKw2on0YIDFY%2FU77F88OQWqMGTlZ

## Spring中Bean的注入方式
- 	XML-based metadata
- Annotation-based configuration:https://docs.spring.io/spring/docs/5.2.8.RELEASE/spring-framework-reference/core.html#beans-annotation-config
- Java-based configuration: https://docs.spring.io/spring/docs/5.2.8.RELEASE/spring-framework-reference/core.html#beans-java
	
Annotation injection is performed before XML injection. Thus, the XML configuration overrides the annotations for properties wired through both approaches.

- Using AspectJ to dependency-inject domain objects with Spring.

http://c.biancheng.net/view/4254.html
https://www.cnblogs.com/xiaoxi/p/5846416.html

https://blog.csdn.net/u014634338/article/details/82865644
https://zhuanlan.zhihu.com/p/87382038
## BeanFactory
## ApplicationContext

了。对于上下文抽象接口，Spring也为我们提供了多种类型的容器实现，供我们在不同的应用场景选择——

　　　 ① AnnotationConfigApplicationContext:从一个或多个基于java的配置类中加载上下文定义，适用于java注解的方式；

　　　　② ClassPathXmlApplicationContext:从类路径下的一个或多个xml配置文件中加载上下文定义，适用于xml配置的方式；

　　　　③ FileSystemXmlApplicationContext:从文件系统下的一个或多个xml配置文件中加载上下文定义，也就是说系统盘符中加载xml配置文件；

　　　　④ AnnotationConfigWebApplicationContext:专门为web应用准备的，适用于注解方式；

　　　　⑤ XmlWebApplicationContext:从web应用下的一个或多个xml配置文件加载上下文定义，适用于xml配置方式。

Spring boot如何简化Spring配置
https://cloud.tencent.com/developer/article/1469427

https://blog.csdn.net/qq_40151840/article/details/104406031

## Bean 生命周期
- 创建
- 装配
- 销毁

https://www.cnblogs.com/chenbenbuyi/p/8166304.html
https://www.cnblogs.com/chenbenbuyi/p/8166304.html