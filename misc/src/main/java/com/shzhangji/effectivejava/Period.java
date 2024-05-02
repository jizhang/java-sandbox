package com.shzhangji.effectivejava;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public final class Period implements Serializable {
  private final Date start;
  private final Date end;

  public Period(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());

    if (this.start.compareTo(this.end) > 0) {
      throw new IllegalArgumentException(start + " after " + end);
    }
  }

  public Date start() {
    return new Date(start.getTime());
  }

  public Date end() {
    return new Date(end.getTime());
  }

  @Override
  public String toString() {
    return start + " - " + end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Period period = (Period) o;
    return Objects.equals(start, period.start) && Objects.equals(end, period.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("Proxy required");
  }

  private static class SerializationProxy implements Serializable {
    private static final long serialVersionUID = 826909938198023972L;

    private final Date start;
    private final Date end;

    SerializationProxy(Period period) {
      this.start = period.start;
      this.end = period.end;
    }

    private Object readResolve() {
      return new Period(start, end);
    }
  }
}
