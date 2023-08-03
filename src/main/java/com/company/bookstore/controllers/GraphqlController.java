package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.AuthorRepository;
import com.company.bookstore.repositories.BookRepository;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphqlController {
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    //--------------------Book--------------------
    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }
    //    find book by id
    @QueryMapping
    public Book findBookById(@Argument int id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            Book toReturn= book.get();
            Author author = authorRepository.findById(toReturn.getAuthorId().getAuthor_id()).get();
            Publisher publisher=publisherRepository.findById(toReturn.getPublisherId().getId()).get();
            toReturn.setAuthorId(author);
            toReturn.setPublisherId(publisher);
            return toReturn;
        }
        return null;

    }
    //--------------------Author--------------------
    @QueryMapping
    public List<Author> authors() {
        return authorRepository.findAll();
    }
    @QueryMapping
    public Author findAuthorById(@Argument int id) {
        Optional<Author> possibleAuthor =  authorRepository.findById(id);
        if (possibleAuthor.isPresent()) {
            return possibleAuthor.get();
        } else {
            return null;
        }
    }

    //--------------------Publisher--------------------



}
