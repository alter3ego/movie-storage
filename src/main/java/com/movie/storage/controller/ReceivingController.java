package com.movie.storage.controller;

import com.movie.storage.entity.Type;
import com.movie.storage.entity.Movie;
import com.movie.storage.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie-api/v1/getBy")
@RequiredArgsConstructor
public class ReceivingController {
    private final MovieService movieService;

    @RequestMapping("/genre/{genre}")
    ResponseEntity<Slice<Movie>> getMoviesByGenre(@PathVariable(value = "genre") String genre, Pageable pageable) {
        Slice<Movie> movies = movieService.getMoviesByGenre(genre, pageable);
        return getSliceResponseEntity(movies);
    }

    @RequestMapping("/title/{title}")
    ResponseEntity<Slice<Movie>> getMoviesByTitle(@PathVariable(value = "title") String title, Pageable pageable) {
        Slice<Movie> movies = movieService.getMoviesByTitle(title, pageable);
        return getSliceResponseEntity(movies);
    }

    @RequestMapping("/type/{type}")
    ResponseEntity<Slice<Movie>> getMoviesByType(@PathVariable(value = "type") String type, Pageable pageable) {
        if (Type.enumExist(type.toUpperCase())) {
            Type enumType = Type.valueOf(type.toUpperCase());
            Slice<Movie> movies = movieService.getMoviesByType(enumType, pageable);

            return getSliceResponseEntity(movies);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/year/{year}")
    ResponseEntity<Slice<Movie>> getMoviesByYear(@PathVariable(value = "year") int year, Pageable pageable) {
        Slice<Movie> movies = movieService.getMoviesByYear(year, pageable);
        return getSliceResponseEntity(movies);
    }

    private static ResponseEntity<Slice<Movie>> getSliceResponseEntity(Slice<Movie> movies) {
        return movies.hasContent()
                ? new ResponseEntity<>(movies, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
