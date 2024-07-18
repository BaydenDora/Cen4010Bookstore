package app.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import app.bookstore.domain.User;

@JsonPropertyOrder({"User ID", "Username", "Name", "Password", "Email Address", "Home Address"})
public class UserDTO {
    @JsonProperty("User ID")
    private int userID;

    @JsonProperty("Username")
    private String username;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Email Address")
    private String emailAddress;

    @JsonProperty("Home Address")
    private String homeAddress;

    public UserDTO() {}

    public UserDTO(User user){
        this(user.getUserID(), user.getUsername(), user.getName(), 
        user.getPassword(), user.getEmailAddress(), user.getHomeAddress());
    }

    private UserDTO(int userID, String username, String name, String password, String emailAddress, String homeAddress) {
        this.userID = userID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.emailAddress = emailAddress;
        this.homeAddress = homeAddress;
    }

    
    // Getters and Setters


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}