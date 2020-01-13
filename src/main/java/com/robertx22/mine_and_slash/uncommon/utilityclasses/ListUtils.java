package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import java.util.List;

public class ListUtils {

    public static <T> List<T> newList(List<T> list, T t) {
        list.add(t);
        return list;

    }

    public static <T> List<T> newList(List<T> list, T one, T two) {
        list.add(one);
        list.add(two);
        return list;

    }

    public static <T> List<T> newList(List<T> list, T one, T two, T three) {
        list.add(one);
        list.add(two);
        list.add(three);
        return list;

    }

    public static <T> List<T> newList(List<T> list, T one, T two, T three, T four) {
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        return list;

    }
}
