## Future的作用

## Future的使用

## Callable VS Runnable
我们都知道，Callable<V>接口和Runnable接口都可以被提交给线程池执行，唯一不同的就是Callable<V>接口是有返回结果的，其中的泛型V就是返回结果，而Runnable接口是没有返回结果的。

The Future interface was added in Java 5 to serve as a result of an asynchronous computation, but it did not have any methods to combine these computations or handle possible errors.
##  ForkJoinPool

## CompletableFuture
https://www.baeldung.com/java-completablefuture