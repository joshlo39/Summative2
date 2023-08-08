package com.company.bookstore.controllers;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;


    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @GetMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById(@PathVariable int id) {
        Optional<Publisher> resultPublisher = publisherRepository.findById(id);
        return resultPublisher.isPresent() ? resultPublisher.get() : null;
    }

    @GetMapping("/publishers")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @PutMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisherById(@PathVariable int id, @RequestBody Publisher updatedDetails) {
        Optional<Publisher> resultPublisher = publisherRepository.findById(id);
        resultPublisher.ifPresent(existingPublisher -> {
            existingPublisher.setName(getOrDefault(updatedDetails.getName(), existingPublisher.getName()));
            existingPublisher.setStreet(getOrDefault(updatedDetails.getStreet(), existingPublisher.getStreet()));
            existingPublisher.setCity(getOrDefault(updatedDetails.getCity(), existingPublisher.getCity()));
            existingPublisher.setState(getOrDefault(updatedDetails.getState(), existingPublisher.getState()));
            existingPublisher.setPostalCode(getOrDefault(updatedDetails.getPostalCode(), existingPublisher.getPostalCode()));
            existingPublisher.setPhone(getOrDefault(updatedDetails.getPhone(), existingPublisher.getPhone()));
            existingPublisher.setEmail(getOrDefault(updatedDetails.getEmail(), existingPublisher.getEmail()));

            publisherRepository.save(existingPublisher);
        });
    }

    private String getOrDefault(String newValue, String defaultValue) {
        return newValue != null ? newValue : defaultValue;
    }

    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisherById(@PathVariable int id) {
        Optional<Publisher> resultPublisher = publisherRepository.findById(id);
        if (resultPublisher.isPresent()) { publisherRepository.deleteById(id); }
    }


}
