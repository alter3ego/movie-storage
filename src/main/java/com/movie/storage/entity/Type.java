package com.movie.storage.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.movie.storage.exception.TypeParsingException;

public enum Type {
    FULL,
    SHORT,
    SERIAL;

    @JsonCreator
    public static Type jsonParsing(String value) {
        try {
            return Type.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TypeParsingException();
        }
    }
}
