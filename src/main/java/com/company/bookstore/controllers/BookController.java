package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    //Create
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    //Read
    @GetMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable int id) {
        Optional<Book> resultBook = bookRepository.findById(id);
        return resultBook.isPresent() ? resultBook.get() : null;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Update
    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookById(@PathVariable int id, @RequestBody Book updatedDetails) {
        Optional<Book> resultBook = bookRepository.findById(id);
        resultBook.ifPresent(existingBook -> {
            existingBook.setIsbn(getOrDefault(updatedDetails.getIsbn(), existingBook.getIsbn()));
            existingBook.setPublishDate(getOrDefault(updatedDetails.getPublishDate(), existingBook.getPublishDate()));
            existingBook.setAuthorId(getOrDefault(updatedDetails.getAuthorId(), existingBook.getAuthorId()));
            existingBook.setTitle(getOrDefault(updatedDetails.getTitle(), existingBook.getTitle()));
            existingBook.setPublisherId(getOrDefault(updatedDetails.getPublisherId(), existingBook.getPublisherId()));
            existingBook.setPrice(getOrDefault(updatedDetails.getPrice(), existingBook.getPrice()));

            bookRepository.save(existingBook);
        });
    }

    private <T> T getOrDefault(T newValue, T defaultValue) {
        return newValue != null ? newValue : defaultValue;
    }


    //Delete
    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable int id) {
        Optional<Book> resultBook = bookRepository.findById(id);
        if (resultBook.isPresent()) { bookRepository.deleteById(id); }
    }

    @GetMapping("/books/author/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> searchBooksByAuthorId(@PathVariable int id) {
        return bookRepository.findByAuthorId(id);
    }

}
