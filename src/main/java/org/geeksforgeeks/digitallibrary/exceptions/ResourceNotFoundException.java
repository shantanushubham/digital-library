package org.geeksforgeeks.digitallibrary.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends RuntimeException {

    public  ResourceNotFoundException(Class<?> clazz, String fieldName, String fieldValue) {
        super("Resource of type: " + clazz + " with the field: " + fieldName + "=" + fieldValue + " could not be found.");
        log.error("Resource of type: {}  with the field:{}={} could not be found.", clazz, fieldName, fieldValue);
    }
}
