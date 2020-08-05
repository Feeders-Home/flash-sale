package com.feeder.flashsale.exception;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


import com.feeder.flashsale.logging.AutoNamingLoggerFactory;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import static org.springframework.http.HttpStatus.valueOf;

/**
 * the global exception handler.
 */
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = AutoNamingLoggerFactory.getLogger();

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorRepresentation.ErrorDetail> handleAppException(AppException ex,
                                                                              HttpServletRequest request) {
        this.logger.error("App error:", ex);
        ErrorRepresentation representation = new ErrorRepresentation(ex, request.getRequestURI());
        return new ResponseEntity<>(representation.getError(), new HttpHeaders(),
                valueOf(ex.getError().getStatus()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorRepresentation.ErrorDetail> handleConstrainViolation(
        ConstraintViolationException ex, HttpServletRequest request) {
        ErrorRepresentation.ErrorDetail error = this.buildNotValidError(ex, request);
        return new ResponseEntity<>(error, new HttpHeaders(), valueOf(error.getStatus()));
    }

    private ErrorRepresentation.ErrorDetail buildNotValidError(ConstraintViolationException ex,
        HttpServletRequest request) {
        String path = request.getRequestURI();

        Map<String, Object> error = ex.getConstraintViolations().stream()
            .collect(Collectors.toMap(c -> c.getPropertyPath().toString(), ConstraintViolation::getMessage));

        this.logger.error("Validation error for [{}]", request.getPathInfo(), ex);
        ErrorRepresentation representation =
            new ErrorRepresentation(new RequestValidationException(error), path);
        return representation.getError();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorRepresentation.ErrorDetail> handleGeneralException(Throwable ex,
                                                                                  HttpServletRequest request) {
        String path = request.getRequestURI();
        this.logger.error("Error occurred while access[{}]:", path, ex);
        ErrorRepresentation representation = new ErrorRepresentation(new SystemException(ex), path);
        return new ResponseEntity<>(representation.getError(), new HttpHeaders(),
                valueOf(representation.getError().getStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        String path;
        if (request instanceof ServletWebRequest) {
            path = ((ServletWebRequest) request).getRequest().getRequestURI();
        } else {
            path = request.getContextPath();
        }
        this.logger.error("exception while access[{}]", path, ex);
        return new ResponseEntity<>(
            new ErrorRepresentation.ErrorDetail(status.value(), status.value(), ex.getMessage(), path, null),
            headers, status);
    }

}

