package com.toptal.soccer.rest.controller;

import com.toptal.soccer.constants.ApiEndPoints;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckE2eTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void checkHealthCheckResponse() throws Exception {
    this.mockMvc
        .perform(get(ApiEndPoints.HEALTH_BASE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("OK")));
  }
}
