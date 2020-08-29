package com.yurif.comicbooks;

import com.yurif.comicbooks.model.ComicBook;
import com.yurif.comicbooks.repository.ComicBookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ComicbooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComicbooksApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ReactiveMongoOperations operations, ComicBookRepository comicBookRepository) {
        return args -> {
            Flux<ComicBook> comicBookFlux = Flux.just(
                    new ComicBook(null, "The Amazing Spider-Man", "The Night Gwen Stacy Died", 121, "Gerry Conway"),
                    new ComicBook(null, "Daredevil", "Born Again", 226, "Frank Miller")
            ).flatMap(comicBookRepository::save);
            comicBookFlux
                    .thenMany(comicBookRepository.findAll())
                    .subscribe(System.out::println);
        };
    }

}
