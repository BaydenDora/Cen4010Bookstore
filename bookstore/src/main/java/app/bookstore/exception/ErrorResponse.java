package app.bookstore.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"Error", "Status", "Details"})
public class ErrorResponse {

    @JsonProperty("Error")
    private String error;
    
    @JsonProperty("Status")
    private int status;
    
    @JsonProperty("Details")
    private List<String> details; 

    public ErrorResponse(String error, int status, List<String> details) {
        this.error = error;
        this.status = status;
        this.details = details;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

}
