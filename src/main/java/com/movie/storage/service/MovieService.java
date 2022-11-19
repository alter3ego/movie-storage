package com.movie.storage.service;

import com.movie.storage.entity.Type;
import com.movie.storage.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface MovieService {
    Slice<Movie> getMoviesByGenre(String genre, Pageable pageable);

    Slice<Movie> getMoviesByTitle(String title, Pageable pageable);

    Slice<Movie> getMoviesByType(Type type, Pageable pageable);

    Slice<Movie> getMoviesBetweenDates(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
