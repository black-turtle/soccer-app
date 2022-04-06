package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.rest.dto.request.LogInParam;
import com.toptal.soccer.rest.dto.request.SignUpParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.toptal.soccer.rest.controller.TestUtils.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserE2eTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private UserController controller;

  @Test
  public void testLoginWithValidData() throws Exception {

    // data is already inserted by SoccerGameApplication, just checking we can get token
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(ApiEndPoints.USERS_BASE + ApiEndPoints.USERS_LOGIN)
                .content(
                    asJsonString(LogInParam.builder().username("user1").password("user1").build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").exists());
  }

  @Test
  public void testLoginWithInValidData() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(ApiEndPoints.USERS_BASE + ApiEndPoints.USERS_LOGIN)
                .content(
                    asJsonString(
                        LogInParam.builder().username("abdsfsdf").password("user1").build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testSignUpWithInValidData() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(ApiEndPoints.USERS_BASE + ApiEndPoints.USERS_SIGNUP)
                .content(
                    asJsonString(
                        SignUpParam.builder()
                            .email("abc@gmail.com")
                            .username("user1")
                            .password("user1")
                            .build())) // username taken
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  public void testSignUpWithValidData() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post(ApiEndPoints.USERS_BASE + ApiEndPoints.USERS_SIGNUP)
                .content(
                    asJsonString(
                        SignUpParam.builder()
                            .email("abc@gmail.com")
                            .username("abc")
                            .password("abc")
                            .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.jsonPath("$.body").exists());
  }
}
