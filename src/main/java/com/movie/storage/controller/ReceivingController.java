package com.movie.storage.controller;

import com.movie.storage.entity.Type;
import com.movie.storage.entity.Movie;
import com.movie.storage.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie-api/v1/movies")
@RequiredArgsConstructor
@Log4j2
public class ReceivingController {
    private final MovieService movieService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/genre/{genre}")
    Slice<Movie> getMoviesByGenre(@PathVariable(value = "genre") String genre, Pageable pageable) {
        log.info("Query for reading by genre with value: " + genre);
        return movieService.getMoviesByGenre(genre, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/title/{title}")
    Slice<Movie> getMoviesByTitle(@PathVariable(value = "title") String title, Pageable pageable) {
        log.info("Query for reading by title with value: " + title);
        return movieService.getMoviesByTitle(title, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/type/{type}")
    Slice<Movie> getMoviesByType(@PathVariable(value = "type") Type type, Pageable pageable) {
        log.info("Query for reading by type with value: " + type);
        return movieService.getMoviesByType(type, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/year/{year}")
    Slice<Movie> getMoviesByYear(@PathVariable(value = "year") int year, Pageable pageable) {
        log.info("Query for reading by year with value: " + year);
        return movieService.getMoviesByYear(year, pageable);
    }
}
