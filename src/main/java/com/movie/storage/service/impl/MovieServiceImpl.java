package com.movie.storage.service.impl;

import com.movie.storage.entity.Movie;
import com.movie.storage.entity.Type;
import com.movie.storage.repository.MovieRepository;
import com.movie.storage.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Slice<Movie> getMoviesByGenre(String genre, Pageable pageable) {
        return movieRepository.findAllByGenreIgnoreCaseContains(genre, pageable);
    }

    @Override
    public Slice<Movie> getMoviesByTitle(String title, Pageable pageable) {
        return movieRepository.findAllByTitleIgnoreCaseContains(title, pageable);
    }

    @Override
    public Slice<Movie> getMoviesByType(Type type, Pageable pageable) {
        return movieRepository.findAllByType(type, pageable);
    }

    @Override
    public Slice<Movie> getMoviesBetweenDates(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return movieRepository.findAllByReleaseDateBetween(startDate, endDate, pageable);
    }
}
