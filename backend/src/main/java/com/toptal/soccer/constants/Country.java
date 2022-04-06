package com.toptal.soccer.constants;

import com.toptal.soccer.exception.HttpException;
import com.toptal.soccer.utils.CommonUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public enum Country {
  BELGIUM("BELGIUM"),
  BRAZIL("BRAZIL"),
  FRANCE("FRANCE"),
  ARGENTINA("ARGENTINA"),
  ENGLAND("ENGLAND"),
  ITALY("ITALY"),
  SPAIN("SPAIN"),
  PORTUGAL("PORTUGAL"),
  DENMARK("DENMARK"),
  NETHERLANDS("NETHERLANDS"),
  ;

  // Caching enum values for faster lookup
  private static final Map<String, Country> BY_VALUE = new HashMap<>();

  static {
    for (Country country : values()) {
      BY_VALUE.put(country.value, country);
    }
  }

  private final String value;

  public static Country getCountryByValue(String value) {
    Country country = BY_VALUE.get(value);
    if (Objects.nonNull(country)) {
      return country;
    }

    throw new HttpException(
        String.format("Invalid country value '%s' passed", value), HttpStatus.BAD_REQUEST);
  }

  public static Country getRandomCountry() {
    int randomIdx = CommonUtils.getRandomIntegerInRange(0, Country.values().length - 1);
    return Country.values()[randomIdx];
  }

  public static Set<String> getAllCountryValues() {
    return BY_VALUE.keySet();
  }
}
