package app.bookstore.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Error", "Details"})
public class ErrorResponse {

    @JsonProperty("Error")
    private String status;
    
    @JsonProperty("Details")
    private List<String> details; 

    public ErrorResponse(String status, List<String> details) {
        this.status = status;
        this.details = details;
    }

}
