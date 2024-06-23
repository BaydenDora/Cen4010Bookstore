package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AuthorDTO {
    @JsonProperty("Author_ID")
    private int authorID;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Biography")
    private String biography;

    @JsonProperty("PublisherIds")
    private List<Integer> publisherIds;

    // Getters and setters

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Integer> getPublisherIds() {
        return publisherIds;
    }

    public void setPublisherIds(List<Integer> publisherIds) {
        this.publisherIds = publisherIds;
    }
}