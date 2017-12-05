package ru.lagranj.app;

public class FileCleanerException extends Exception {

  public FileCleanerException() {
    super();
  }

  public FileCleanerException(String message) {
    super(message);
  }

  public FileCleanerException(String message, Exception e) {
    super(message, e);
  }
}
