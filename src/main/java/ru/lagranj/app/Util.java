package ru.lagranj.app;

import org.apache.log4j.Logger;

public class Util {
  private static final Logger LOGGER = Logger.getLogger(Util.class);

  public static void info(String message) {
    log(message, LogLevel.INFO, null);
  }

  public static void warn(String message, Exception e) {
    log(message, LogLevel.WARN, e);
  }

  public static void error(String message, Exception e) {
    log(message, LogLevel.ERROR, e);
  }

  private static void log(String message, LogLevel logLevel, Exception e) {
    switch (logLevel) {
      case INFO:
        LOGGER.info(message);
        break;
      case WARN:
        if (e == null) {
          LOGGER.warn(message);
        } else {
          LOGGER.warn(message, e);
        }
        break;
      case ERROR:
        LOGGER.error(message, e);
    }
  }

  private enum LogLevel {
    INFO, WARN, ERROR;
  }

}
