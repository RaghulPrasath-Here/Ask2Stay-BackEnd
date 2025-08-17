package com.example.Ask2Stay.payload;

import java.util.Map;

public class ExtractionRequest {
    private String text;
    private Map<String, Object> template;

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Map<String, Object> getTemplate() { return template; }
    public void setTemplate(Map<String, Object> template) { this.template = template; }
}
