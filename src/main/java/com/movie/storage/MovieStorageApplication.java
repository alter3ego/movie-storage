package com.movie.storage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Movie-Storage API", version = "1.0"),
        tags = {@Tag(name = "GetMoviesEndpoint", description = "Endpoints to receive movies"),
                @Tag(name = "AddMovieEndpoint", description = "Endpoints to adding the movie")}
)
public class MovieStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieStorageApplication.class, args);
    }
}
