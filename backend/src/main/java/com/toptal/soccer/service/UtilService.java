package com.toptal.soccer.service;

import com.toptal.soccer.constants.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UtilService {
  public Set<String> getAllCountyNames() {
    return Country.getAllCountryValues();
  }
}
