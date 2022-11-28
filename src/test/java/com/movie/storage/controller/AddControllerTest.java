package com.movie.storage.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("AddControllerTest")
class AddControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String controllerPath = getPath();

    @Test
    @DisplayName("should save the correct movie")
    public void shouldSaveTheCorrectMovie() throws Exception {
        mockMvc.perform(post(controllerPath + "/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                          "title": "Movie Title",
                                          "description": "Title Description",
                                          "type": "FULL",
                                          "genre": "crime, drama",
                                          "releaseDate": "2023-09-14"
                                        }""".indent(2)
                        ))

                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should return BadRequest status for incorrect movie")
    public void shouldReturnBadRequestStatusForIncorrectMovie() throws Exception {
        mockMvc.perform(post(controllerPath + "/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                          "title": "Movie Title",
                                          "description": "Title Description",
                                          "type": "FULLt",
                                          "genre": "crime, drama",
                                          "releaseDate": "2023-09-14"
                                        }""".indent(2)
                        ))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should save correct movies")
    public void shouldSaveCorrectMovies() throws Exception {
        mockMvc.perform(post(controllerPath + "/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        [
                                         {
                                           "title": "Movie Title 1",
                                           "description": "Title Description 1",
                                           "type": "FULL",
                                           "genre": "drama",
                                           "releaseDate": "2023-09-15"
                                         },
                                          {
                                           "title": "Movie Title 2",
                                           "description": "Title Description 2",
                                           "type": "SHORT",
                                           "genre": "drama",
                                           "releaseDate": "2023-09-16"
                                         },
                                          {
                                           "title": "Movie Title 3",
                                           "description": "Title Description 3",
                                           "type": "SERIAL",
                                           "genre": "action",
                                           "releaseDate": "2023-09-11"
                                         }
                                         ]""".indent(1)
                        ))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should return BadRequest status for incorrect movies")
    public void shouldReturnBadRequestStatusForIncorrectMovies() throws Exception {
        mockMvc.perform(post(controllerPath + "/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        [
                                         {
                                           "title": "Movie Title 1",
                                           "description": "Title Description 1",
                                           "type": "FULL",
                                           "genre": "drama",
                                           "releaseDate": "2023-09-15"
                                         },
                                          {
                                           "title": "Movie Title 2",
                                           "description": "Title Description 2",
                                           "type": "SHORT",
                                           "genre": "drama",
                                           "releaseDate": "2023-09-16"
                                         },
                                          {
                                           "title": "Movie Title 3",
                                           "description": "Title Description 3",
                                           "type": "SERIAlL",
                                           "genre": "action",
                                           "releaseDate": "2023-09-11"
                                         }
                                         ]""".indent(1)
                        ))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    private String getPath() {
        return Arrays.stream(AddController.class.getAnnotation(RequestMapping.class).value()).findAny().get();
    }
}