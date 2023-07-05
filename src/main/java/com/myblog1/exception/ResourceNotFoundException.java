package com.myblog1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
        super(String.format("%s not found with %s:'%s'",resourceName,fieldName,fieldvalue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = fieldvalue;
    }

    public String getResourceName() {

        return resourceName;
    }

    public String getFieldName() {

        return fieldName;
    }

    public long getFieldvalue() {
        return fieldvalue;
    }
}
