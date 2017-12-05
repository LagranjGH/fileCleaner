package ru.lagranj.app;

import java.io.File;

public class Checker {

  public static void checkArguments(String[] args) throws FileCleanerException {
    if (args == null || args.length == 0) {
      throw new FileCleanerException("Не переданы аргументы.");
    }

    if (args.length != 2) {
      throw new FileCleanerException("Необходимо передать корневую директорию и режим работы.");
    }
  }

  public static void checkDirectory(String rootDirectory) throws FileCleanerException {
    if (rootDirectory == null || rootDirectory.isEmpty()) {
      throw new FileCleanerException("Не введен параметр <Корневая директория>");
    }

    File file = new File(rootDirectory);

    if (!file.exists()) {
      throw new FileCleanerException("Указанный файл/директория не существует");
    }

    if (!file.isDirectory()) {
      throw new FileCleanerException("Необходимо указать директорию, а не файл");
    }

    if (file.listFiles().length == 0) {
      throw new FileCleanerException("Указанная директория пуста");
    }
  }

  public static void checkMode(String mode) throws FileCleanerException {
    if (mode == null || mode.isEmpty()) {
      throw new FileCleanerException("Не введен параметр <Режим работы>");
    }

    if (!mode.equalsIgnoreCase(Mode.SEARCH.name()) && !mode.equalsIgnoreCase(Mode.DELETE.name())) {
      throw new FileCleanerException("Допустимые режимы работы [SEARCH] и [DELETE]");
    }
  }

}
