## Future的作用

## Future的使用

## Callable VS Runnable
我们都知道，Callable<V>接口和Runnable接口都可以被提交给线程池执行，唯一不同的就是Callable<V>接口是有返回结果的，其中的泛型V就是返回结果，而Runnable接口是没有返回结果的。

The Future interface was added in Java 5 to serve as a result of an asynchronous computation, but it did not have any methods to combine these computations or handle possible errors.
##  ForkJoinPool
参考: https://www.jianshu.com/p/a152c0a0d2d0
ForkJoinPool是ExecutorSerice的一个补充，而不是替代品
并不适合所有场景；
特别适合用于“分而治之”，递归计算的算法；

## CompletableFuture
https://www.baeldung.com/java-completablefuture