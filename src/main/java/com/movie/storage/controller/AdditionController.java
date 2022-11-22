package com.movie.storage.controller;

import com.movie.storage.entity.Movie;
import com.movie.storage.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/movie-api/v1/add")
@RequiredArgsConstructor
@Log4j2
public class AdditionController {
    private final MovieService movieService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public void addMovie(@RequestBody @Valid Movie movie) {
        movieService.saveMovie(movie);
        log.info("successful save movie " + movie.getTitle());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public void addMovie(@RequestBody @Valid List<Movie> movies) {
        movieService.saveMovies(movies);
        log.info("successful save movies");
    }
}