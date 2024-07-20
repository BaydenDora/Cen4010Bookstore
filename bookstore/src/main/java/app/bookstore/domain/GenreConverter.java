package app.bookstore.domain;

import app.bookstore.exception.Genre.NoSuchGenreException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply=true)
public class GenreConverter implements AttributeConverter<Genre, String>{

    @Override
    public String convertToDatabaseColumn(Genre genre) {
        return genre==null ? null : genre.name();
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        try {
            return dbData==null ? null : Genre.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            throw new NoSuchGenreException(dbData);
        }
    }
    
}
