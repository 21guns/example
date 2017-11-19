package com.guns21.example.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * java分组排序
 */
public class GroupBySort {

    public static void main(String[] args) {

        List<A> list = new ArrayList<>();
        list.add(new A(2,"1"));
        list.add(new A(1,"1"));
        list.add(new A(1,"2"));
        list.add(new A(2,"3"));
        Map<String, List<A>> collect = list.stream()
                .sorted(Comparator.comparingInt(A::getSort))
                .collect(Collectors.groupingBy(A::getId));
        System.err.println(collect);

        Map map = new HashMap();
        list.forEach(l ->{
            List<A> collect1 = list.stream()
                    .filter(a -> l.id.equals(a.id))
                    .sorted(Comparator.comparingInt(A::getSort))
                    .collect(Collectors.toList());
            map.put(l.id,collect1);
        });
        System.err.println(map);
    }

    public static class A {
        private int sort;
        private String id;

        public A(int sort, String id) {
            this.sort = sort;
            this.id = id;
        }

        public int getSort() {
            return sort;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "A{" +
                    "sort=" + sort +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
