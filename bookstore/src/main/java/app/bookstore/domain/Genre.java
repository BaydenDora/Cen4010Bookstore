package app.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    TEXTBOOK("Textbook"), ACADEMIC("Academic"), REPORT("Report"), BIOGRAPHY("Biography"), 
    MANUAL("Manual"), FANTASY("Fantasy"), SCIENCEFICTION("Science Fiction"), ACTION("Action"), 
    ADVENTURE("Adventure"),HISTORICAL("Historical"), FICTION("Fiction"), NONFICTION("Non-Fiction"), OTHER("Other");

    private String label;
    private Genre(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel(){
        return label;
    }

    public static Genre fromLabel(String label) {
        for (Genre genre : Genre.values()) {
            if (genre.label.equalsIgnoreCase(label)) {
                return genre;
            }
        }
        return null;
    }

    public static Genre fromInt(int genreIndex) {
        Genre[] genres = Genre.values();
        if (genreIndex >= 0 && genreIndex < genres.length) {
            return genres[genreIndex];
        } else {
            throw new IllegalArgumentException("Invalid genre index: " + genreIndex);
        }
    }

}