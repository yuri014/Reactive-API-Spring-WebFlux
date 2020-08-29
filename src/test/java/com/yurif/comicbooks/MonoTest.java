package com.yurif.comicbooks;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void monoTestOne() {
        Mono<String> mono = Mono.empty();
    }

    @Test
    public void monoTestTwo() {
        Mono<String> mono = Mono.just("ComicBook");
        mono.subscribe(System.out::println);
    }

    @Test
    public void monoTestThree() {
        Mono<Integer> mono = Mono.just(10);
        mono.subscribe(System.out::println);
    }

    @Test
    public void monoTestFour() {
        Mono<String> mono = Mono.error(new RuntimeException("Exception found"));

    }

}
