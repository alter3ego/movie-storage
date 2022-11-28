package com.movie.storage.controller;

import com.movie.storage.entity.Movie;
import com.movie.storage.entity.Type;
import com.movie.storage.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("GetControllerTest")
class GetControllerTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MockMvc mockMvc;

    private final String controllerPath = getPath();
    private final Movie testMovie = configureTestMovie();


    @Test
    @DisplayName("should return OK status and movies by genre")
    public void shouldReturnIsOKAndMoviesByGenre() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/genre/{genre}", testMovie.getGenre()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content[0].genre").value(testMovie.getGenre()));
    }

    @Test
    @DisplayName("should return OK status and movies by title")
    public void shouldReturnIsOKAndMoviesByTitle() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/title/{title}", testMovie.getTitle()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content[0].title").value(testMovie.getTitle()));
    }

    @Test
    @DisplayName("should return OK status and movies by year")
    public void shouldReturnIsOKAndMoviesByYear() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/year/{year}", testMovie.getReleaseDate().getYear()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content[0].releaseDate")
                        .value(testMovie.getReleaseDate().minusDays(1).toString()));
    }

    @Test
    @DisplayName("should return OK status and empty Json for unknown year")
    public void shouldReturnOKStatusAndEmptyJsonForUnknownYear() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/year/{year}", testMovie.getReleaseDate().minusYears(1).getYear()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content").isEmpty());
    }

    @Test
    @DisplayName("should return OK status and movies by correct type")
    public void shouldReturnIsOKAndMoviesByCorrectType() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/type/{type}", testMovie.getType()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content[0].type")
                        .value(testMovie.getType().toString()));
    }

    @Test
    @DisplayName("should return OK status and movies by correct type ignored case")
    public void shouldReturnIsOKAndMoviesByCorrectTypeIgnoredCase() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/type/{type}", testMovie.getType().toString().toLowerCase()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.content[0].type")
                        .value(testMovie.getType().toString()));
    }

    @Test
    @DisplayName("should return BadRequest status for incorrect type")
    public void shouldReturnBadRequestStatusForIncorrectType() throws Exception {
        saveTestMovie();

        mockMvc.perform(get(controllerPath + "/type/{type}", "fulew"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"));
    }


    private Movie configureTestMovie() {
        return new Movie(1L, "Title", "Description", Type.FULL, "TestGenre", LocalDate.of(1001, 12, 13));
    }

    private void saveTestMovie() {
        movieService.saveMovie(testMovie);
    }

    private String getPath() {
        return Arrays.stream(GetController.class.getAnnotation(RequestMapping.class).value()).findAny().get();
    }
}