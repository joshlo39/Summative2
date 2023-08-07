package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AuthorRepositoryTest{

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
    public void shouldCreateAuthor() throws Exception {
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
        Optional<Author> authorFromDB = authorRepository.findById(author.getId());
        assertEquals(authorFromDB.get(), author);
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
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
        Optional<Author> authorFromDB = authorRepository.findById(author.getId());
        assertEquals(authorFromDB.get(), author);
    }

    @Test
    public void shouldGetAllAuthors() throws Exception {
        Author author1 = new Author(
                "Ted",
                "Lasso",
                "110 Ave",
                "Doral",
                "Fl",
                "11111",
                "111-111-1111",
                "tedLasso@gmail.com"
        );

        Author author2 = new Author(
                "Julius R",
                "Oppenheimer",
                "111 NE Street",
                "Reno",
                "NV",
                "33333",
                "999-999-9999",
                "oppenheimer@gmail.com"
        );

        author1 = authorRepository.save(author1);
        author2 = authorRepository.save(author2);
        List<Author> authors = authorRepository.findAll();
        assertEquals(2, authors.size());
    }

    @Test
    public void shouldUpdateAuthorById() throws Exception {
        Author author = new Author(
                "Julius R",
                "Oppenheimer",
                "111 NE Street",
                "Reno",
                "NV",
                "33333",
                "999-999-9999",
                "oppenheimer@gmail.com"
        );
        author = authorRepository.save(author);
        author.setPhone("123-456-7890");
        Author updatedAuthor = authorRepository.save(author);

        Optional<Author> resultAuthor = authorRepository.findById(author.getId());
        assertEquals(updatedAuthor, resultAuthor.get());
    }

    @Test
    public void shouldDeleteAuthorById() throws Exception {
        Author author = new Author(
                "Julius R",
                "Oppenheimer",
                "111 NE Street",
                "Reno",
                "NV",
                "33333",
                "999-999-9999",
                "oppenheimer@gmail.com"
        );
        author = authorRepository.save(author);
        authorRepository.deleteById(author.getId());
        assertEquals(false, authorRepository.findById(author.getId()).isPresent());
    }

}
