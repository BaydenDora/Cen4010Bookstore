package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.Author;
import app.bookstore.domain.Book;
import app.bookstore.domain.Publisher;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"Publisher ID", "Publisher Name", "Authors Published", "Books Published"}) 
public class PublisherDTO {

    @JsonProperty("Publisher ID") // ensure this matches the JSON key
    private int publisherID;

    @JsonProperty("Publisher Name") // ensure this matches the JSON key
    private String publisherName;

    @JsonProperty("Authors Published") // ensure this matches the JSON key
    private List<Integer> authorsPublished;

    @JsonProperty("Books Published") // ensure this matches the JSON key
    private List<Long> booksPublished;

    public PublisherDTO(){};

    public PublisherDTO(Publisher publisher){
        this(
            publisher.getID(), 
            publisher.getName(),
            publisher.getAuthors().stream()
                        .map(Author::getAuthorID)
                        .collect(Collectors.toList()), 
            publisher.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toList())
        );
    }

    private PublisherDTO(int publisherID, String publisherName, List<Integer> authorsPublished,
            List<Long> booksPublished) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.authorsPublished = authorsPublished;
        this.booksPublished = booksPublished;
    }
    
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

    public List<Long> getBooksPublished() {
        return booksPublished;
    }

    public void setBooksPublished(List<Long> booksPublished) {
        this.booksPublished = booksPublished;
    }
}