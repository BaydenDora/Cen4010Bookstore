package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AuthorDTO {

    @JsonProperty("authorID")
    private int authorID;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("biography")
    private String biography;

    @JsonProperty("publisherIds")
    private List<Integer> publisherIds;

    // Default constructor
    public AuthorDTO() {
    }

    public AuthorDTO(int authorID, String firstName, String lastName, String biography, List<Integer> publisherIds) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.publisherIds = publisherIds;
    }


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

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "authorID=" + authorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", biography='" + biography + '\'' +
                ", publisherIds=" + publisherIds +
                '}';
    }
}