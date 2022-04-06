package com.toptal.soccer.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

public class TestUtils {
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String getValueFromResponse(MvcResult response, String key) throws Exception {
    Map<String, String> map =
        new ObjectMapper().readValue(response.getResponse().getContentAsString(), Map.class);
    return map.get(key);
  }
}
