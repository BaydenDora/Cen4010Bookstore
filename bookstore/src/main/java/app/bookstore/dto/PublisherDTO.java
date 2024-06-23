package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PublisherDTO {

    @JsonProperty("publisherID") // ensure this matches the JSON key
    private int publisherID;

    @JsonProperty("publisherName") // ensure this matches the JSON key
    private String publisherName;

    @JsonProperty("authorsPublished") // ensure this matches the JSON key
    private List<Integer> authorsPublished;

    @JsonProperty("booksPublished") // ensure this matches the JSON key
    private List<Integer> booksPublished;

    // Getters and Setters

    @Override
    public String toString() {
        return "PublisherDTO{" +
                "publisherID=" + publisherID +
                ", publisherName='" + publisherName + '\'' +
                ", authorsPublished=" + authorsPublished +
                ", booksPublished=" + booksPublished +
                '}';
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public List<Integer> getAuthorsPublished() {
        return authorsPublished;
    }

    public void setAuthorsPublished(List<Integer> authorsPublished) {
        this.authorsPublished = authorsPublished;
    }

    public List<Integer> getBooksPublished() {
        return booksPublished;
    }

    public void setBooksPublished(List<Integer> booksPublished) {
        this.booksPublished = booksPublished;
    }
}