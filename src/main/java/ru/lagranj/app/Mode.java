package ru.lagranj.app;

public enum Mode {
  SEARCH, DELETE;

  public static Mode forName(String name) {
    for (Mode mode: Mode.values()) {
      if (mode.name().equalsIgnoreCase(name)) {
        return mode;
      }
    }
    throw new IllegalArgumentException("No enum constant with name " + name);
  }

}
