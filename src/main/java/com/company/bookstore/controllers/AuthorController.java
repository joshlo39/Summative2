package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    //Create
    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    //Read
    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> resultAuthor = authorRepository.findById(id);
        return resultAuthor.isPresent() ? resultAuthor.get() : null;
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @PutMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthorById(@PathVariable int id, @RequestBody Author updatedDetails) {
        Optional<Author> resultAuthor = authorRepository.findById(id);
        resultAuthor.ifPresent(existingAuthor -> {
            existingAuthor.setFirstName(getOrDefault(updatedDetails.getFirstName(), existingAuthor.getFirstName()));
            existingAuthor.setLastName(getOrDefault(updatedDetails.getLastName(), existingAuthor.getLastName()));
            existingAuthor.setStreet(getOrDefault(updatedDetails.getStreet(), existingAuthor.getStreet()));
            existingAuthor.setCity(getOrDefault(updatedDetails.getCity(), existingAuthor.getCity()));
            existingAuthor.setState(getOrDefault(updatedDetails.getState(), existingAuthor.getState()));
            existingAuthor.setPostalCode(getOrDefault(updatedDetails.getPostalCode(), existingAuthor.getPostalCode()));
            existingAuthor.setPhone(getOrDefault(updatedDetails.getPhone(), existingAuthor.getPhone()));
            existingAuthor.setEmail(getOrDefault(updatedDetails.getEmail(), existingAuthor.getEmail()));

            authorRepository.save(existingAuthor);
        });
    }

    private <T> T getOrDefault(T newValue, T defaultValue) {
        return newValue != null ? newValue : defaultValue;
    }

    //delete
    @DeleteMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthorById(@PathVariable int id) {
        Optional<Author> resultAuthor = authorRepository.findById(id);
        if (resultAuthor.isPresent()) { authorRepository.deleteById(id); }
    }


}
