package com.yurif.comicbooks.repository;

import com.yurif.comicbooks.model.ComicBook;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ComicBookRepository extends ReactiveMongoRepository<ComicBook, String> {

}
