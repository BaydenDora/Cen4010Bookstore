package app.bookstore;

public enum Genre {
    TEXTBOOK, ACADEMIC, REPORT, BIOGRAPHY, MANUAL, FANTASY, SCIENCEFICTION, ACTION, ADVENTURE, HISTORICAL, FICTION, NONFICTION, OTHER;

    public static Genre fromInt(int genreIndex) {
        Genre[] genres = Genre.values();
        if (genreIndex >= 0 && genreIndex < genres.length) {
            return genres[genreIndex];
        } else {
            throw new IllegalArgumentException("Invalid genre index: " + genreIndex);
        }
    }
}
