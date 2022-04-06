package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import com.toptal.soccer.rest.dto.request.LogInParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.toptal.soccer.rest.controller.TestUtils.asJsonString;
import static com.toptal.soccer.rest.controller.TestUtils.getValueFromResponse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamE2eTest {

  private String token = null;
  @Autowired private MockMvc mockMvc;
  @Autowired private UserController controller;

  @Test
  public void getWithoutAuthentication() throws Exception {

    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(ApiEndPoints.TEAMS_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  public void getWithAuthentication() throws Exception {
    setupAuthToken();
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get(ApiEndPoints.TEAMS_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token))
        .andDo(print())
        .andExpect(status().isOk());
  }

  //  @Test
  public void setupAuthToken() throws Exception {
    if (token == null) {
      // data is already inserted by SoccerGameApplication
      MvcResult result =
          this.mockMvc
              .perform(
                  MockMvcRequestBuilders.post(ApiEndPoints.USERS_BASE + ApiEndPoints.USERS_LOGIN)
                      .content(
                          asJsonString(
                              LogInParam.builder().username("user1").password("user1").build()))
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON))
              .andReturn();

      this.token = getValueFromResponse(result, "body");
    }
  }
}
