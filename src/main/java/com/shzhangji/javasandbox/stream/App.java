package com.shzhangji.javasandbox.stream;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.clearspring.analytics.stream.cardinality.HyperLogLog;
import com.clearspring.analytics.stream.cardinality.ICardinality;
import com.clearspring.analytics.stream.frequency.CountMinSketch;
import com.clearspring.analytics.stream.frequency.IFrequency;
import com.clearspring.analytics.stream.membership.BloomFilter;
import com.clearspring.analytics.stream.membership.Filter;
import com.clearspring.analytics.stream.quantile.TDigest;

public class App {

    public static void main(String[] args) throws Exception {

        // cardinality
        ICardinality card = new HyperLogLog(10);
        for (int i : new int[] { 1, 2, 3, 2, 4, 3 }) {
            card.offer(i);
        }
        System.out.println(card.cardinality());

        // membership
        Filter filter = new BloomFilter(100, 0.01);
        filter.add("google.com");
        filter.add("twitter.com");
        filter.add("facebook.com");
        System.out.println(filter.isPresent("twitter.com"));

        // frequency
        List<String> animals = new ArrayList<>();
        animals.addAll(Collections.nCopies(5, "cat"));
        animals.addAll(Collections.nCopies(15, "dog"));
        animals.addAll(Collections.nCopies(25, "rabbit"));
        animals.addAll(Collections.nCopies(35, "spider"));
        animals.addAll(Collections.nCopies(45, "bird"));
        Collections.shuffle(animals);

        IFrequency freq = new CountMinSketch(10, 5, 0);
        Map<String, Long> top = Collections.emptyMap();
        for (String animal : animals) {
            freq.add(animal, 1);
            top = Stream.concat(top.keySet().stream(), Stream.of(animal)).distinct()
                      .map(a -> new SimpleEntry<String, Long>(a, freq.estimateCount(a)))
                      .sorted(Comparator.comparing(SimpleEntry<String, Long>::getValue).reversed())
                      .limit(3)
                      .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
        }

        System.out.println(top);

        // quantile
        Random rand = new Random();
        List<Double> data = new ArrayList<>();
        TDigest digest = new TDigest(100);

        for (int i = 0; i < 1000000; ++i) {
            double d = rand.nextDouble();
            data.add(d);
            digest.add(d);
        }

        Collections.sort(data);

        for (double q : new double[] { 0.1, 0.5, 0.9 }) {
            System.out.println(String.format("quantile=%.1f digest=%.4f exact=%.4f",
                    q,
                    digest.quantile(q),
                    data.get((int) (data.size() * q))));
        }
    }

}
