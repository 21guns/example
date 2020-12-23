package com.guns21.example.stream.parallel;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author jliu
 * @date 2020/6/29
 */
public class StreamParallel {

    public static void main(String[] args) throws ExecutionException, InterruptedException {



        List<Integer> lists = Lists.newArrayList();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            lists.add(i);
        }
        Date start = new Date();
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        ForkJoinPool forkJoinPool2 = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(2);
        taskProcess(lists, forkJoinPool1, countDownLatch);
        taskProcess(lists, forkJoinPool2, countDownLatch);
        countDownLatch.await();
        Date end = new Date();
        System.out.println((end.getTime() - start.getTime()) / 1000);
    }

    /**
     * 自定义 线程池数量
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void customThreadPool(String[] args) throws ExecutionException, InterruptedException {

        long firstNum = 1;
        long lastNum = 100_000;
        Set<String> threads = Sets.newConcurrentHashSet();
        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());
        Long reduce = aList.parallelStream().peek(aLong -> {
            threads.add(Thread.currentThread().getName());
//            System.out.println(Thread.currentThread().getName() + ":" + aLong);
        }).reduce(0L, Long::sum);
        System.err.println(Runtime.getRuntime().availableProcessors() + ":" + reduce);
        System.err.println(threads.size());

        Set<String> _threads =  Sets.newConcurrentHashSet();
        ForkJoinPool customThreadPool = new ForkJoinPool(30);
        long actualTotal = customThreadPool.submit(
                () -> aList.parallelStream()
                        .peek(aLong -> {
//                            System.out.println(Thread.currentThread().getName() + ":" + aLong);
                            _threads.add(Thread.currentThread().getName());
                        })
                        .reduce(0L, Long::sum)
        ).get();
        System.err.println(Runtime.getRuntime().availableProcessors() + ":" + actualTotal);
        System.err.println(_threads.size());

    }

    private static void taskProcess(List<Integer> lists, ForkJoinPool forkJoinPool, CountDownLatch countDownLatch) {
        forkJoinPool.submit(() -> {
            lists.parallelStream().forEach(e -> {
                try {
                    //do something
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });
            countDownLatch.countDown();
        });
    }
}
