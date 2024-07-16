package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.Author;
import app.bookstore.domain.Publisher;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"Author ID", "First Name", "Last Name", "Biography", "Publisher IDs"})
public class AuthorDTO {

    @JsonProperty("Author ID")
    private int authorID;

    @JsonProperty("First Name")
    private String firstName;

    @JsonProperty("Last Name")
    private String lastName;

    @JsonProperty("Biography")
    private String biography;

    @JsonProperty("Publisher IDs")
    private List<Integer> publisherIDs;

    // Default constructor
    public AuthorDTO() {}

    public AuthorDTO(Author author){
        this(author.getAuthorID(), author.getFirstName(), 
        author.getLastName(), author.getBiography(), 
        author.getPublishers().stream()
                        .map(Publisher::getID)
                        .collect(Collectors.toList()));
    }

    private AuthorDTO(int authorID, String firstName, String lastName, 
                String biography, List<Integer> publisherIds) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
        this.publisherIDs = publisherIds;
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

    public List<Integer> getPublisherIDs() {
        return publisherIDs;
    }

    public void setPublisherIDs(List<Integer> publisherIds) {
        this.publisherIDs = publisherIds;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "authorID=" + authorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", biography='" + biography + '\'' +
                ", publisherIds=" + publisherIDs +
                '}';
    }
}