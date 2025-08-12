package com.example.Ask2Stay.payload;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class NuExtractResponse {
    @JsonProperty("extracted_data")
    private Map<String, Object> extractedData;

    @JsonProperty("confidence")
    private Double confidence;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    // Getters and setters
    public Map<String, Object> getExtractedData() { return extractedData; }
    public void setExtractedData(Map<String, Object> extractedData) { this.extractedData = extractedData; }

    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
