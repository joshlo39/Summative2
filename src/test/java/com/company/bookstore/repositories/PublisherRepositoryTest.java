package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PublisherRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublisherRepository publisherRepository;


    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldCreatePublisher() throws Exception {
        Publisher publisher = new Publisher(
                "Pengun",
                "101 Nw Street",
                "Doral",
                "Fl",
                "33122",
                "111-111-1111",
                "Penguin@gmail.com"
        );

        publisher = publisherRepository.save(publisher);
        Optional<Publisher> authorFromDB = publisherRepository.findById(publisher.getId());
        assertEquals(authorFromDB.get(), publisher);
    }

    @Test
    public void shouldGetPublisherById() throws Exception {
        Publisher publisher = new Publisher(
                "Pengun",
                "101 Nw Street",
                "Doral",
                "Fl",
                "33122",
                "111-111-1111",
                "Penguin@gmail.com"
        );

        publisher = publisherRepository.save(publisher);
        Optional<Publisher> authorFromDB = publisherRepository.findById(publisher.getId());
        assertEquals(authorFromDB.get(), publisher);
    }

    @Test
    public void shouldGetAllPublishers() throws Exception {
        Publisher publisher1 = new Publisher(
                "Pengun",
                "101 Nw Street",
                "Doral",
                "Fl",
                "33122",
                "111-111-1111",
                "Penguin@gmail.com"
        );

        Publisher publisher2 = new Publisher(
                "Peter Griffin",
                "48th Street",
                "Atlanta",
                "GA",
                "111111",
                "555-555-5555",
                "petergriffin@gmail.com"
        );

        publisher1 = publisherRepository.save(publisher1);
        publisher2 = publisherRepository.save(publisher2);
        List<Publisher> publishers = publisherRepository.findAll();
        assertEquals(2, publishers.size());
    }

    @Test
    public void shouldUpdatePublisherById() throws Exception {
        Publisher publisher = new Publisher(
                "Pengun",
                "101 Nw Street",
                "Doral",
                "Fl",
                "33122",
                "111-111-1111",
                "Penguin@gmail.com"
        );
        publisher = publisherRepository.save(publisher);
        publisher.setPhone("123-456-7890");
        Publisher updatedPublisher = publisherRepository.save(publisher);

        Optional<Publisher> resultPublisher = publisherRepository.findById(publisher.getId());
        assertEquals(updatedPublisher, resultPublisher.get());
    }

    @Test
    public void shouldDeletePublisherById() throws Exception {
        Publisher publisher = new Publisher(
                "Pengun",
                "101 Nw Street",
                "Doral",
                "Fl",
                "33122",
                "111-111-1111",
                "Penguin@gmail.com"
        );
        publisher = publisherRepository.save(publisher);
        publisherRepository.deleteById(publisher.getId());
        assertEquals(false, publisherRepository.findById(publisher.getId()).isPresent());
    }

}
