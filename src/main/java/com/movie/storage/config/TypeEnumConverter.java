package com.movie.storage.config;

import com.movie.storage.entity.Type;
import com.movie.storage.exception.TypeParsingException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeEnumConverter implements Converter<String, Type> {

    @Override
    public Type convert(String type) {
        try {
            return Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TypeParsingException();
        }
    }
}
