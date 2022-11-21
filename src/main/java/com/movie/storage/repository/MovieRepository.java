package com.movie.storage.repository;

import com.movie.storage.entity.Movie;
import com.movie.storage.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Slice<Movie> findAllByGenreIgnoreCaseContains(String genre, Pageable pageable);

    Slice<Movie> findAllByTitleIgnoreCaseContains(String title, Pageable pageable);

    Slice<Movie> findAllByType(Type type, Pageable pageable);

    Slice<Movie> findAllByReleaseDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
