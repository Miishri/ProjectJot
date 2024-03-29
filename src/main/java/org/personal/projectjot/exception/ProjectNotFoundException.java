package org.personal.projectjot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super();
    }
    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ProjectNotFoundException(String message) {
        super(message);
    }
    public ProjectNotFoundException(Throwable cause) {
        super(cause);
    }
}
