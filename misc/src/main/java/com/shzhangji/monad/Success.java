package com.shzhangji.monad;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Success<T> extends Try<T> {
  private final T value;

  Success(T value) {
    this.value = value;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public T orElse(T value) {
    return this.value;
  }

  @Override
  public <U> Try<U> flatMap(Function<? super T, Try<U>> f) {
    Objects.requireNonNull(f);
    return f.apply(value);
  }

  @Override
  public <U> Try<U> map(Function<? super T, ? extends U> f) {
    Objects.requireNonNull(f);

    try {
      return new Success<>(f.apply(value));
    } catch (Throwable e) {
      return Try.failure(e);
    }
  }

  @Override
  public Try<T> filter(Predicate<T> p) {
    Objects.requireNonNull(p);
    if (p.test(value)) {
      return this;
    }
    return Try.failure(new NoSuchElementException("Predicate doesn't match for " + value));
  }
}
