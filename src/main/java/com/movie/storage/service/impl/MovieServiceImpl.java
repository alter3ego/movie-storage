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
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

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
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void saveMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

    @Override
    public Slice<Movie> getMoviesByYear(int year, Pageable pageable) {
        LocalDate instance = LocalDate.now().withYear(year);
        LocalDate startDate = instance.with(firstDayOfYear());
        LocalDate endDate = instance.with(lastDayOfYear());

        return movieRepository.findAllByReleaseDateBetween(startDate, endDate, pageable);
    }
}
