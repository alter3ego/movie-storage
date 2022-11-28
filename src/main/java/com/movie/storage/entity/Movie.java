package com.movie.storage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "The movie should have a title")
    @Size(max = 255, message = "Title must be less than 255 characters")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "The movie should have a description")
    @Size(max = 600, message = "Description must be less than 600 characters")
    @Column(nullable = false, length = 600)
    private String description;

    @NotNull(message = "The movie should have a type")
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Type type;

    @NotNull(message = "The movie should have a genre")
    @Size(max = 255, message = "Genre must be less than 600 characters")
    @Column(nullable = false)
    private String genre;

    @NotNull(message = "The movie should have a release date")
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
}