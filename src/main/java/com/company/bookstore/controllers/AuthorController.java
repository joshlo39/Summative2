package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController{

    @Autowired
    AuthorRepository authorRepository;

    //CRUD plus find by author

    //Create
    @PostMapping("/authors")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }
    //Read
    @GetMapping("/authors")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    @GetMapping("/authors/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> returnVal = authorRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }
    //Update
    @PutMapping("/authors/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author, @PathVariable int id) {
        authorRepository.save(author);
    }
    //Delete
    @DeleteMapping("/authors/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        authorRepository.deleteById(id);
    }
}
