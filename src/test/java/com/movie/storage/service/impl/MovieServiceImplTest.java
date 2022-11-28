package com.movie.storage.service.impl;

import com.movie.storage.entity.Movie;
import com.movie.storage.entity.Type;
import com.movie.storage.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.time.LocalDate;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
@DisplayName("MovieServiceImplTest")
class MovieServiceImplTest {
    @InjectMocks
    MovieServiceImpl movieService;
    @Mock
    MovieRepository movieRepository;

    Slice<Movie> expectedMovies = createMovieSlice();

    @Test
    @DisplayName("should return movies by exist genre")
    void shouldReturnMoviesByExistGenre() {
        Mockito.when(movieRepository.findAllByGenreIgnoreCaseContains(Mockito.anyString(),
                Mockito.any(PageRequest.class))).thenReturn(expectedMovies);
        PageRequest pageRequest = PageRequest.ofSize(5);

        Slice<Movie> actual = movieService.getMoviesByGenre("Drama", pageRequest);

        Assertions.assertEquals(expectedMovies, actual);
    }

    @Test
    @DisplayName("should return movies by exist title")
    void shouldReturnMoviesByExistTitle() {
        Mockito.when(movieRepository.findAllByTitleIgnoreCaseContains(Mockito.anyString(),
                Mockito.any(PageRequest.class))).thenReturn(expectedMovies);
        PageRequest pageRequest = PageRequest.ofSize(5);

        Slice<Movie> actual = movieService.getMoviesByTitle("Name", pageRequest);

        Assertions.assertEquals(expectedMovies, actual);
    }

    @Test
    @DisplayName("should return movies by correct type")
    void shouldReturnMoviesByCorrectType() {
        Mockito.when(movieRepository.findAllByType(Mockito.any(Type.class),
                Mockito.any(PageRequest.class))).thenReturn(expectedMovies);
        PageRequest pageRequest = PageRequest.ofSize(5);

        Slice<Movie> actual = movieService.getMoviesByType(Type.FULL, pageRequest);

        Assertions.assertEquals(expectedMovies, actual);
    }

    @Test
    @DisplayName("should return movies by date")
    void shouldReturnMoviesByDate() {
        Mockito.when(movieRepository.findAllByReleaseDateBetween(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class),
                Mockito.any(PageRequest.class))).thenReturn(expectedMovies);
        PageRequest pageRequest = PageRequest.ofSize(5);

        Slice<Movie> actual = movieService.getMoviesByYear(1999, pageRequest);

        Assertions.assertEquals(expectedMovies, actual);
    }

    private Slice<Movie> createMovieSlice() {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        Movie movie1 = new Movie(1L, "Title", "Description", Type.FULL, "Drama", LocalDate.now());
        Movie movie2 = new Movie(1L, "Title2", "Description2", Type.FULL, "Drama", LocalDate.now());

        movieArrayList.add(movie1);
        movieArrayList.add(movie2);

        return new SliceImpl<>(movieArrayList);
    }
}