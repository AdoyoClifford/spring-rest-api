package com.in28minutes.rest.webservices.restfulwebservices.ExceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request)  {
        ErrorDetails
    }

}
