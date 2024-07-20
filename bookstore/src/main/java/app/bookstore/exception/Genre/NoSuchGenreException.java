package app.bookstore.exception.Genre;

public class NoSuchGenreException extends RuntimeException{

    public NoSuchGenreException(String dbGenre) {
        super("Unknown database genre value '" + dbGenre + "'");
    }

}
