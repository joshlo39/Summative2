package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    //Create
    @PostMapping("/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addBook(@RequestBody  Book book) {
        bookRepository.save(book);
    }
    //Read
    @GetMapping("/books")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    @GetMapping("/books/authorID/{authorId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Book getBookByAuthorId(@PathVariable int authorId){
        Optional<Book> returnVal = bookRepository.findById(authorId);
        if (returnVal.isPresent()){
            return returnVal.get();
    }else {return null;}
    }
    //Update
    @PutMapping("/books")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book){
        bookRepository.save(book);
    }
    //Delete
    @DeleteMapping("books/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
    }




}
