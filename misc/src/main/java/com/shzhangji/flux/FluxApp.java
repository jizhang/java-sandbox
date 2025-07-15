package com.shzhangji.flux;

import reactor.core.publisher.Flux;

public class FluxApp {
  public static void main(String[] args) throws Exception {
    Flux.just(1, 2, 3).doOnNext(System.out::println).subscribe();
  }
}
