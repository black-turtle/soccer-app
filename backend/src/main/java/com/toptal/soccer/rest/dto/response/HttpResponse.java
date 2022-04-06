package com.toptal.soccer.rest.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class HttpResponse {
  private final Date timestamp;
  private final Object body;
  private final int status;

  public HttpResponse(Object message, HttpStatus status) {
    this.timestamp = new Date();
    this.body = message;
    this.status = status.value();
  }

  public HttpResponse(Object message) {
    this.timestamp = new Date();
    this.body = message;
    this.status = HttpStatus.OK.value();
  }
}
