package org.personal.projectjot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException() {
        super();
    }
    public BlogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public BlogNotFoundException(String message) {
        super(message);
    }
    public BlogNotFoundException(Throwable cause) {
        super(cause);
    }
}
