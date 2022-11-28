package com.movie.storage.controller;

import com.movie.storage.entity.Type;
import com.movie.storage.entity.Movie;
import com.movie.storage.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie-api/v1/movies")
@RequiredArgsConstructor
@Log4j2
public class GetController {
    private final MovieService movieService;

    @Operation(
            tags = {"GetMoviesEndpoint"},
            summary = "Get movies by genre",
            description = "Endpoint for getting movies by genre",
            parameters = {@Parameter(name = "genre", description = "The genre you are looking for", example = "drama")},
            responses = {@ApiResponse(responseCode = "200", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Slice.class)))}
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/genre/{genre}")
    Slice<Movie> getMoviesByGenre(@PathVariable(value = "genre") String genre, Pageable pageable) {
        log.info("Query for reading by genre with value: " + genre);
        return movieService.getMoviesByGenre(genre, pageable);
    }

    @Operation(
            tags = {"GetMoviesEndpoint"},
            summary = "Get movies by title",
            description = "Endpoint for getting movies by title",
            parameters = {@Parameter(name = "title", description = "The title you are looking for", example = "Alien")},
            responses = {@ApiResponse(responseCode = "200", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Slice.class)))}
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/title/{title}")
    Slice<Movie> getMoviesByTitle(@PathVariable(value = "title") String title, Pageable pageable) {
        log.info("Query for reading by title with value: " + title);
        return movieService.getMoviesByTitle(title, pageable);
    }

    @Operation(
            tags = {"GetMoviesEndpoint"},
            summary = "Get movies by type",
            description = "Endpoint for getting movies by type",
            parameters = {@Parameter(name = "type", description = "The type you are looking for. Available values are [FULL, SHORT, SERIAL]"
                    , example = "FULL")},
            responses = {@ApiResponse(responseCode = "200", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Slice.class)))}
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/type/{type}")
    Slice<Movie> getMoviesByType(@PathVariable(value = "type") Type type, Pageable pageable) {
        log.info("Query for reading by type with value: " + type);
        return movieService.getMoviesByType(type, pageable);
    }

    @Operation(
            tags = {"GetMoviesEndpoint"},
            summary = "Get movies by year",
            description = "Endpoint for getting movies by year",
            parameters = {@Parameter(name = "year", description = "The year you are looking for", example = "1972")},
            responses = {@ApiResponse(responseCode = "200", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Slice.class)))}
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/year/{year}")
    Slice<Movie> getMoviesByYear(@PathVariable(value = "year") int year, Pageable pageable) {
        log.info("Query for reading by year with value: " + year);
        return movieService.getMoviesByYear(year, pageable);
    }
}
