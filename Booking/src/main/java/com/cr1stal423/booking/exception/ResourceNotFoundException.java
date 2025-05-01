package com.cr1stal423.booking.exception;

import org.apache.kafka.common.protocol.types.Field;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("Resource %s not found with %s : %s",resourceName,fieldName,fieldValue));
    }
}
