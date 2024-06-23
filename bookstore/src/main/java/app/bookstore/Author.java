package app.bookstore;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Author_ID")
    private int Author_ID;

    @Column(name = "FirstName", nullable = false, length = 100)
    private String FirstName;

    @Column(name = "LastName", nullable = false, length = 100)
    private String LastName;

    @Column(name = "Biography", nullable = false, length = 1000)
    private String Biography;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "Author_Publisher", 
        joinColumns = @JoinColumn(name = "Author_ID"), 
        inverseJoinColumns = @JoinColumn(name = "Publisher_ID")
    )
    private List<Publisher> publishers = new ArrayList<>();

    @JsonManagedReference(value = "author-books")
    @OneToMany(mappedBy = "myAuthor")
    private List<Book> BooksWritten = new ArrayList<>();

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
        return Author_ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getName() {
        return FirstName + " " + LastName;
    }

    public String getBiography() {
        return Biography;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    protected void setAuthorID(int ID) {
        Author_ID = ID;
        if (Author_ID < 0) Author_ID = 0;
    }

    protected void setFirstName(String firstName) {
        FirstName = firstName;
    }

    protected void setLastName(String lastName) {
        LastName = lastName;
    }

    protected void setName(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    protected void setBiography(String bio) {
        Biography = bio;
    }

    protected void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }
}