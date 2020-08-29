package com.yurif.comicbooks.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class ComicBook {

    @Id
    private String id;

    private String hero;
    private String title;
    private Integer edition;
    private String writer;

    public ComicBook(String id, String hero, String title, Integer edition, String writer) {
        this.id = id;
        this.hero = hero;
        this.title = title;
        this.edition = edition;
        this.writer = writer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicBook comicBook = (ComicBook) o;
        return Objects.equals(id, comicBook.id) &&
                Objects.equals(hero, comicBook.hero) &&
                Objects.equals(title, comicBook.title) &&
                Objects.equals(edition, comicBook.edition) &&
                Objects.equals(writer, comicBook.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hero, title, edition, writer);
    }

    @Override
    public String toString() {
        return "ComicBook{" +
                "id='" + id + '\'' +
                ", hero='" + hero + '\'' +
                ", title='" + title + '\'' +
                ", edition=" + edition +
                ", writer='" + writer + '\'' +
                '}';
    }
}
