package com.elcom.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;

public class ResponseDataPaging {

    private int status;
    private String message;
    private BigInteger total;
    private Object data;
    
    static final ObjectMapper mapper = new ObjectMapper();
    
    public String toJsonStr() {
        try {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            String jsonInString = mapper.writeValueAsString(this);
            return jsonInString;
        } catch (JsonProcessingException e) {
            return "Error RequestMessage Json Objec:" + e.getMessage();
        }
    }

    public ResponseDataPaging() {
    }

    public ResponseDataPaging(int status, String message, BigInteger total, Object data) {

        this.status = status;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
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
