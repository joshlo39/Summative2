package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PublisherController {
    @Autowired
    PublisherRepository publisherRepository;

    //    read
    @GetMapping("/publishers")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Publisher> getAllPublishers(){return publisherRepository.findAll();}

}
