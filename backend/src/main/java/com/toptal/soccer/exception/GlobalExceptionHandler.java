package com.toptal.soccer.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @Bean
  public ErrorAttributes errorAttributes() {
    // Hide exception field in the return object
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(
          WebRequest webRequest, ErrorAttributeOptions options) {
        return super.getErrorAttributes(
            webRequest,
            ErrorAttributeOptions.defaults().including(ErrorAttributeOptions.Include.MESSAGE));
      }
    };
  }

  @ExceptionHandler(HttpException.class)
  public void handleCustomException(HttpServletResponse res, HttpException ex) throws IOException {
    res.sendError(ex.getHttpStatus().value(), ex.getMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
    res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
  }

  @ExceptionHandler(TransactionSystemException.class)
  public void handleTransactionSystemException(
      HttpServletResponse res, TransactionSystemException ex) throws IOException {
    // get root cause
    Throwable cause = ex;
    while (cause.getCause() != null) {
      cause = cause.getCause();
    }

    // handle constraintViolationException
    if (cause instanceof ConstraintViolationException) {
      ConstraintViolationException constraintViolationException =
          (ConstraintViolationException) cause;
      StringBuilder message = new StringBuilder();
      Set<ConstraintViolation<?>> violations =
          constraintViolationException.getConstraintViolations();
      for (ConstraintViolation<?> violation : violations) {
        message.append(violation.getMessage().concat("\n"));
      }
      res.sendError(HttpStatus.BAD_REQUEST.value(), message.toString());
    } else {
      handleException(res, ex);
    }
  }

  @ExceptionHandler(Exception.class)
  public void handleException(HttpServletResponse res, Exception ex) throws IOException {
    ex.printStackTrace();
    res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
  }
}
