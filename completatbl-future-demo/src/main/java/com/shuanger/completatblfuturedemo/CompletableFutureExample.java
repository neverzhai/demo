package com.shuanger.completatblfuturedemo;

import org.springframework.util.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhaixiaoshuang
 * @date: 2021-01-19 11:02
 * @description:
 */
public class CompletableFutureExample {

    // 当成简单的Future一样使用
    public static Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    // 使用静态方法构建
    public static Future<String> staticMethod() {
        Future<String> completable = CompletableFuture.completedFuture("Hello");

        return completable;
    }

    // 处理返回结果, 
    public static Future<String> thenApplyEx() {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenApply(s -> s + "World"); // thenApply 可以接收上一个结果作为入参, 同时又可以有返回值

        return completableFuture;
    }

    // 传递入参, 但是无返回值
    public static CompletableFuture<Void> thenAcceptEx() {
        CompletableFuture<Void> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenAccept(s -> System.out.println(s)); // thenApply 可以接收上一个结果作为入参, 同时又可以有返回值

        return completableFuture;
    }

    // 不传递参数, 且无返回值
    public static CompletableFuture<Void> thenRunEx() {
        CompletableFuture<Void> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
                .thenRun(() -> System.out.println("no args")); // thenApply 可以接收上一个结果作为入参, 同时又可以有返回值

        return completableFuture;
    }

    // 组合future, 与thenCombine的区别是啥呀
    public static CompletableFuture<String> thenCompose() {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> "Hello")
//        but the thenCompose (flatMap) method receives a function that returns another object of the same type
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + 4));

        return completableFuture;
    }

    // 组合2-- 有返回值, 且可以返回值类型不同
    public static CompletableFuture<String> thenCombine() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(() -> 7), (s1, s2) -> s1 + s2);

        return completableFuture;
    }

    // 组合无返回值
    public static CompletableFuture thenCombineVoid() {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> System.out.println(s1 + s2));
        return future;
    }

    //
    CompletableFuture<Integer> computeAnother(Integer i){
        return CompletableFuture.supplyAsync(() -> 10 + i);
    }

    public CompletableFuture<Integer> thenCombineD() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 4);

        CompletableFuture<Integer> finalResult = future.thenCompose(this::computeAnother);

        return finalResult;
    }
    public CompletableFuture<CompletableFuture<Integer>> thenCombineX() {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 4);

        CompletableFuture<CompletableFuture<Integer>> finalResult = future.thenApply(this::computeAnother);

        return finalResult;
    }

    // 多个Future并行 -- 无返回值
    public void parallel() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();
        Assert.isTrue(future1.isDone());
        Assert.isTrue(future2.isDone());
        Assert.isTrue(future3.isDone());
    }

    // 多个Future并行 -- 合并返回值
    public String parallelWithResult() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        return combined;
    }

    // 错误处理 -- 吃掉异常, handle
    public String handleException(String name) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        return completableFuture.get();
    }

    // 错误处理, 抛出异常
    public String throwException(String name) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        });
        CompletableFuture<String> completableFuture =  completableFuture1
                .exceptionally(throwable -> throwable.getMessage())
                .whenCompleteAsync((s, throwable) -> System.out.println(s));

        return completableFuture.get();
    }















    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureExample example = new CompletableFutureExample();
        String world = example.throwException("world");
//        System.out.println(world);
    }


}
