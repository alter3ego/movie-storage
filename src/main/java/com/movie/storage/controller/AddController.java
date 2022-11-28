package com.movie.storage.controller;

import com.movie.storage.entity.Movie;
import com.movie.storage.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class AddController {
    private final MovieService movieService;

    @Operation(
            tags = {"AddMovieEndpoint"},
            summary = "Add one movie",
            description = "Endpoint for saving the movie",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The Movie to save",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful save"),
                    @ApiResponse(responseCode = "400", description = "Wrong input data")}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public void addMovie(@RequestBody @Valid Movie movie) {
        movieService.saveMovie(movie);
        log.info("successful save movie " + movie.getTitle());
    }

    @Operation(
            tags = {"AddMovieEndpoint"},
            summary = "Add movies",
            description = "Endpoint for saving movies",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Movies to save",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Movie.class)))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successful save"),
                    @ApiResponse(responseCode = "400", description = "Wrong input data")}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public void addMovie(@RequestBody @Valid List<Movie> movies) {
        movieService.saveMovies(movies);
        log.info("successful save movies");
    }
}
