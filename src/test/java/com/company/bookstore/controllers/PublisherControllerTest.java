package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherController.class)
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() {}

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

        String inputJson = mapper.writeValueAsString(publisher);
        mockMvc.perform(
                        post("/publishers")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetPublisherById() throws Exception {
        mockMvc.perform(
                        get("/publishers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAllPublishers() throws Exception {
        mockMvc.perform(
                        get("/publishers")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
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

        String inputJson = mapper.writeValueAsString(publisher);
        mockMvc.perform(
                        put("/publishers/1")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeletePublisherById() throws Exception {
        mockMvc.perform(
                        delete("/publishers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
