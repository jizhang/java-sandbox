package com.shzhangji.monad;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// https://thomasandolf.medium.com/write-a-monad-in-java-seriously-50a9047c9839
public abstract class Try<T> {
  public static <U> Try<U> ofThrowable(Supplier<U> f) {
    Objects.requireNonNull(f);
    try {
      return Try.successful(f.get());
    } catch (Throwable e) {
      return Try.failure(e);
    }
  }

  public static <U> Success<U> successful(U value) {
    return new Success<>(value);
  }

  public static <U> Failure<U> failure(Throwable e) {
    return new Failure<>(e);
  }

  public abstract T get() throws Throwable;

  public abstract T orElse(T value);

  public abstract <U> Try<U> flatMap(Function<? super T, Try<U>> f);

  public abstract <U> Try<U> map(Function<? super T, ? extends U> f);

  public abstract Try<T> filter(Predicate<T> p);
}
