package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookrepositoryTest{

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
    public void shouldCreateBook() {
        Author author = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );
        author = authorRepository.save(author);

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

        Book book = new Book(
                "1111111111",
                LocalDate.of(2021, 9, 17),
                author.getId(),
                "Ted Lasso",
                publisher.getId(),
                new BigDecimal("19.99")
        );
        book = bookRepository.save(book);
        Optional<Book> bookFromDB = bookRepository.findById(book.getId());
        assertEquals(bookFromDB.get(), book);
    }

    @Test
    public void shouldGetBookById() throws Exception {
        Author author = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );
        author = authorRepository.save(author);

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

        Book book = new Book(
                "1111111111",
                LocalDate.of(2021, 9, 17),
                author.getId(),
                "Ted Lasso",
                publisher.getId(),
                new BigDecimal("19.99")
        );
        book = bookRepository.save(book);
        Optional<Book> bookFromDB = bookRepository.findById(book.getId());
        assertEquals(bookFromDB.get(), book);
    }

    @Test
    public void shouldGetAllBooks() throws Exception {
        Author author = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );
        author = authorRepository.save(author);

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

        Book book1 = new Book(
                "1111111111",
                LocalDate.of(2021, 9, 17),
                author.getId(),
                "Ted Lasso",
                publisher.getId(),
                new BigDecimal("19.99")
        );

        Book book2 = new Book(
                "54321",
                LocalDate.of(2003, 8, 11),
                author.getId(),
                "Ready Player One",
                publisher.getId(),
                new BigDecimal("24.99")
        );

        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    public void shouldUpdateBookById() throws Exception {
        Author author = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );
        author = authorRepository.save(author);

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

        Book book = new Book(
                "1111111111",
                LocalDate.of(2021, 9, 17),
                author.getId(),
                "Ted Lasso",
                publisher.getId(),
                new BigDecimal("19.99")
        );
        book = bookRepository.save(book);
        book.setPrice(new BigDecimal("4.99"));
        Book updatedBook = bookRepository.save(book);

        Optional<Book> resultBook = bookRepository.findById(book.getId());
        assertEquals(updatedBook, resultBook.get());
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        Author author = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );
        author = authorRepository.save(author);

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

        Book book = new Book(
                "1111111111",
                LocalDate.of(2021, 9, 17),
                author.getId(),
                "Ted Lasso",
                publisher.getId(),
                new BigDecimal("19.99")
        );
        book = bookRepository.save(book);
        bookRepository.deleteById(book.getId());
        assertEquals(false, bookRepository.findById(book.getId()).isPresent());
    }

    @Test
    public void shouldSearchBookByAuthorId() throws Exception {
        Author author = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );
        author = authorRepository.save(author);

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

        Book book1 = new Book(
                "1111111111",
                LocalDate.of(2021, 9, 17),
                author.getId(),
                "Ted Lasso",
                publisher.getId(),
                new BigDecimal("19.99")
        );

        Book book2 = new Book(
                "54321",
                LocalDate.of(2003, 8, 11),
                author.getId(),
                "Ready Player One",
                publisher.getId(),
                new BigDecimal("24.99")
        );

        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);

        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertEquals(2, books.size());
    }

}
