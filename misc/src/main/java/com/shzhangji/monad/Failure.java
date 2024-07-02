package com.shzhangji.monad;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Failure<T> extends Try<T> {
  private final Throwable e;

  Failure(Throwable e) {
    this.e = e;
  }

  @Override
  public T get() throws Throwable {
    throw e;
  }

  @Override
  public T orElse(T value) {
    return value;
  }

  @Override
  public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
    Objects.requireNonNull(f);
    return Try.failure(e);
  }

  @Override
  public <U> Try<U> map(Function<? super T, ? extends U> f) {
    Objects.requireNonNull(f);
    return Try.failure(e);
  }

  @Override
  public Try<T> filter(Predicate<T> p) {
    Objects.requireNonNull(p);
    return this;
  }
}
