package com.devsecops.employeeservice.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void resourceNotFoundTest() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleResourceNotFound(
                        new ResourceNotFoundException("Employee not found"));

        assertEquals(404, response.getStatusCode().value());

        assertEquals("Employee not found",
                response.getBody().get("message"));
    }

    @Test
    void genericExceptionTest() {

        ResponseEntity<Map<String,Object>> response =
                handler.handleGeneric(new RuntimeException("Unexpected"));

        assertEquals(500, response.getStatusCode().value());

        assertEquals("Unexpected",
                response.getBody().get("message"));
    }
}
