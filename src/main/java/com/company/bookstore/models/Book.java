package com.company.bookstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String isbn;
    private LocalDate publish_date;

    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Author authorId;

    private String title;

    @ManyToOne()
    @JoinColumn(name = "publisher_id" )
    private Publisher publisherId;

    private BigDecimal price;

    public int getId() {
        return id;
    }

    public Book(String isbn, LocalDate publish_date, Author authorId, String title, Publisher publisherId, BigDecimal price) {
        this.isbn = isbn;
        this.publish_date = publish_date;
        this.authorId = authorId;
        this.title = title;
        this.publisherId = publisherId;
        this.price = price;
    }

    public Book() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(LocalDate publish_date) {
        this.publish_date = publish_date;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Publisher publisherId) {
        this.publisherId = publisherId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(book.authorId, getAuthorId()) && Objects.equals(getPublisherId(), book.getPublisherId()) && Objects.equals(isbn, book.isbn) && Objects.equals(publish_date, book.publish_date) && Objects.equals(title, book.title) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, publish_date, authorId, title, publisherId, price);
    }
}