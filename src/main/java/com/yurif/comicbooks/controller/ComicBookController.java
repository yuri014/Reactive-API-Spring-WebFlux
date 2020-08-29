package com.yurif.comicbooks.controller;

import com.yurif.comicbooks.model.ComicBook;
import com.yurif.comicbooks.model.ComicBookEvent;
import com.yurif.comicbooks.repository.ComicBookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/comicbooks")
public class ComicBookController {

    private ComicBookRepository comicBookRepository;

    public ComicBookController(ComicBookRepository comicBookRepository) {
        this.comicBookRepository = comicBookRepository;
    }

    @GetMapping
    public Flux<ComicBook> getAllComicBooks() {
        return comicBookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ComicBook>> getComicBook(@PathVariable String id) {
        return comicBookRepository.findById(id)
                .map(comicBook -> ResponseEntity.ok(comicBook))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ComicBook> saveComicBook(@RequestBody ComicBook comicBook) {
        return comicBookRepository.save(comicBook);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ComicBook>> updateComicBook(@PathVariable(value = "id") String id,
                                                           @RequestBody ComicBook comicBook) {
        return comicBookRepository.findById(id)
                .flatMap(existingComicBook -> {
                    existingComicBook.setHero(comicBook.getHero());
                    existingComicBook.setTitle(comicBook.getTitle());
                    existingComicBook.setEdition(comicBook.getEdition());
                    existingComicBook.setWriter(comicBook.getWriter());
                    return comicBookRepository.save(existingComicBook);
                })
                .map(updateComicBook -> ResponseEntity.ok(updateComicBook))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteComicBook(@PathVariable(value = "id") String id) {
        return comicBookRepository.findById(id)
                .flatMap(existingComicBook -> comicBookRepository.delete(existingComicBook)
                        .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllComicBooks() {
        return comicBookRepository.deleteAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ComicBookEvent> getComicBookEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(value ->
                        new ComicBookEvent(value, "Comic Book Event"));
    }

}
