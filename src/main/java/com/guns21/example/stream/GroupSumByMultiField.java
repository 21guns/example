package com.guns21.example.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.awt.SystemTray;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分组同时求和多个字段
 */
public class GroupSumByMultiField {


    public static void main(String[] args) {
        List<B> list = new ArrayList<>();
        list.add(new B(new BigDecimal(12.23),1,"1"));
        list.add(new B(new BigDecimal(22.2),1,"2"));
        list.add(new B(new BigDecimal(66.2),1,"2"));
        list.add(new B(new BigDecimal(44.1),1,"1"));
        list.add(new B(new BigDecimal(1.2),1,"1"));
        list.add(new B(new BigDecimal(3.2),1,"1"));

//        Collector.of();
        Long now = System.currentTimeMillis();
        Map<String, BigDecimal> collect = list.stream().collect(Collectors.groupingBy(B::getId, Collectors
                .reducing(BigDecimal.ZERO,
                        B::getAmount, BigDecimal::add)));

        Map<String, IntSummaryStatistics> collect1 = list.stream().collect(Collectors.groupingBy(B::getId, Collectors
                .summarizingInt(B::getCount)));
        System.out.println( System.currentTimeMillis()-now);
        System.err.println(collect);
        System.err.println(collect1);
//        LocalTime
//        B b = list.stream().reduce((x, y) -> new B(x.amount+y.amount,x.count+y.count,"")).orElse(new B(BigDecimal.ZERO, 0, ""));

//        list.stream().collect(Collectors.collectingAndThen(Collectors.groupingBy(B::getId),
//                Collector.of(new B(BigDecimal.ZERO,0,""),(x,y)->{}),));
        now = System.currentTimeMillis();
        List<B> collect2 = list.stream()
                .collect(Collectors.groupingBy(B::getId))
                .entrySet().stream().map(e -> new B(
                        e.getValue().stream().map(b -> b.amount).reduce(BigDecimal.ZERO,(b, b2) -> b.add(b2)),
                        e.getValue().size(),
                        e.getKey()))
                .collect(Collectors.toList());
        System.out.println( System.currentTimeMillis()-now);
        System.err.println(collect2);

    }



    @Getter
    @ToString
    @AllArgsConstructor
    public static class B {
        private BigDecimal amount;
        private int count;
        private String id;


        public void add(B b) {
            this.amount = this.amount == null? BigDecimal.ZERO.add(b.amount):this.amount.add(b.amount);
            this.count = this.count+ b.count;
        }
    }

}
