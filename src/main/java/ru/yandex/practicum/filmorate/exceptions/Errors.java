package ru.yandex.practicum.filmorate.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class Errors {

    @ExceptionHandler({NotFoundException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundHandler(final RuntimeException e) {
        log.debug("Получен статус 404 Not found");
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestHandler(final ValidationException e) {
        log.debug("Получен статус 400 Bad request");
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse internalServerErrorHandler(final Throwable e) {
        log.debug("Получен статус 500 Internal server error");
        return new ErrorResponse(e.getMessage());
    }

    public class ErrorResponse {
        String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
