package com.toptal.soccer.utils;

import java.util.concurrent.ThreadLocalRandom;

public abstract class CommonUtils {
  public static int getRandomIntegerInRange(int min, int max) {
    return ThreadLocalRandom.current().nextInt(min, max);
  }

  public static long getRandomLongInRange(long min, long max) {
    return ThreadLocalRandom.current().nextLong(min, max);
  }

  public static double getRandomDoubleInRange(double min, double max) {
    return ThreadLocalRandom.current().nextDouble(min, max);
  }
}
