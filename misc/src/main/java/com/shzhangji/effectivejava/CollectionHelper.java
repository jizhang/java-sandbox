package com.shzhangji.effectivejava;

import java.util.Collection;
import java.util.Optional;

public class CollectionHelper {
  public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
    E result = null;
    for (E e : c) {
      if (e == null) {
        continue;
      }
      if (result == null || e.compareTo(result) > 0) {
        result = e;
      }
    }
    return Optional.ofNullable(result);
  }
}
