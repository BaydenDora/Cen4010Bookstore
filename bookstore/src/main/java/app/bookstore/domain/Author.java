package app.bookstore.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Author_ID")
    private int authorID;

    @Column(name = "FirstName", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 100)
    private String lastName;

    @Column(name = "Biography", nullable = false, length = 1000)
    private String biography;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "Author_Publisher", 
        joinColumns = @JoinColumn(name = "Author_ID"), 
        inverseJoinColumns = @JoinColumn(name = "Publisher_ID")
    )
    private List<Publisher> publishers = new ArrayList<>();

    @JsonManagedReference(value = "author_books")
    @OneToMany(mappedBy = "myAuthor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> booksWritten = new ArrayList<>();

    public Author() {
        setAuthorID(000000000);
        setFirstName("Jo");
        setLastName("Doe");
        setBiography("Biography");
        setPublishers(new ArrayList<>());
    }

    public Author(int ID, String firstName, String lastName, String bio, List<Publisher> publishers) {
        setAuthorID(ID);
        setFirstName(firstName);
        setLastName(lastName);
        setBiography(bio);
        setPublishers(publishers);
    }

    public Author(Author cloneAuthor) {
        setAuthorID(cloneAuthor.getAuthorID());
        setFirstName(cloneAuthor.getFirstName());
        setLastName(cloneAuthor.getLastName());
        setBiography(cloneAuthor.getBiography());
        setPublishers(cloneAuthor.getPublishers());
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getBiography() {
        return biography;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public List<Book> getBooksWritten() {
        return booksWritten;
    }

    protected void setAuthorID(int ID) {
        authorID = ID;
        if (authorID < 0) authorID = 0;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setBiography(String bio) {
        biography = bio;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }
}