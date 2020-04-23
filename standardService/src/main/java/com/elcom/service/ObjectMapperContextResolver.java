package com.elcom.service;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperContextResolver() {
    	objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    	objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) { return objectMapper; }

}