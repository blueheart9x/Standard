package com.elcom.util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PartitionListHelper {
	public static  <T> Collection<List<T>> partition(List<T> list, int size) {
        final AtomicInteger counter = new AtomicInteger(0);

        return list.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size))
                .values();
    }
}
