package com.elcom.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseData {

    private int status;
    private String message;
    private Object data;
    
    static final ObjectMapper mapper = new ObjectMapper();
    
    public String toJsonStr() {
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String jsonInString = mapper.writeValueAsString(this);
            System.out.println("jsonInString: " + jsonInString);
            return jsonInString;
        } catch (JsonProcessingException e) {
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }

    public ResponseData() {
    }

    public ResponseData(int status, String message, Object data) {

        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
