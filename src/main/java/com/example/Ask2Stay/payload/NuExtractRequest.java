package com.example.Ask2Stay.payload;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class NuExtractRequest {
    @JsonProperty("text")
    private String text;

    @JsonProperty("template")
    private Map<String, Object> template;

    public NuExtractRequest() {}

    public NuExtractRequest(String text, Map<String, Object> template) {
        this.text = text;
        this.template = template;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Map<String, Object> getTemplate() { return template; }
    public void setTemplate(Map<String, Object> template) { this.template = template; }
}
