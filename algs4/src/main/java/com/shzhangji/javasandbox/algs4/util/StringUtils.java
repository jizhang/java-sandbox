package com.shzhangji.javasandbox.algs4.util;

import java.util.Arrays;
import java.util.Iterator;

public class StringUtils {

    public static String join(Iterable<?> iterable, String separator) {
        StringBuilder sb = new StringBuilder();
        Iterator<?> iter = iterable.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (iter.hasNext()) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    public static String join(Object[] array, String separator) {
        return join(Arrays.asList(array), separator);
    }

}
