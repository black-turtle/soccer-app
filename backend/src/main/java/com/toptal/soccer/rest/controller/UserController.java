package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.data.model.User;
import com.toptal.soccer.rest.dto.request.LogInParam;
import com.toptal.soccer.rest.dto.request.SignUpParam;
import com.toptal.soccer.rest.dto.response.HttpResponse;
import com.toptal.soccer.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndPoints.USERS_BASE)
@Api(tags = "users", description = "Api for users management")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final ModelMapper modelMapper;

  @PostMapping(value = ApiEndPoints.USERS_SIGNUP, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Creates user and returns its JWT token")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 422, message = "Username/Email is already in use")
      })
  public HttpResponse signup(@ApiParam("Signup User") @RequestBody SignUpParam params) {
    String token = userService.signup(modelMapper.map(params, User.class));

    return new HttpResponse(token, HttpStatus.CREATED);
  }

  @PostMapping(value = ApiEndPoints.USERS_LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Authenticates user and returns its JWT token")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Something went wrong"),
        @ApiResponse(code = 422, message = "Invalid username/password supplied")
      })
  public HttpResponse login(@ApiParam("Login User") @RequestBody LogInParam params) {
    String token = userService.login(params.getUsername(), params.getPassword());

    return new HttpResponse(token);
  }
}
