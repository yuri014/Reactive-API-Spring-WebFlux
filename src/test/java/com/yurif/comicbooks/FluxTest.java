package com.yurif.comicbooks;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxTest {

    @Test
    void fluxTestOne() {
        Flux.empty();
    }

    @Test
    void fluxTestTwo() {
        Flux<String> flux = Flux.just("ComicBook");
        flux.subscribe(System.out::println);
    }

    @Test
    void fluxTestThree() {
        Flux<String> flux = Flux.just("The Amazing Spider-Man", "Daredevil");
        flux.subscribe(System.out::println);
    }

}
