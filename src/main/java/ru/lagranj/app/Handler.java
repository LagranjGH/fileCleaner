package ru.lagranj.app;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler {
  private static final Map<Long, List<File>> ALL_FILES_IN_DIRECTORY = new HashMap<>();
  private static final Map<Long, File> DUPLICATES = new HashMap<>();

  public static void doAction(String[] args) throws FileCleanerException {
    Checker.checkArguments(args);
    String rootDirectory = args[0];
    String mode = args[1];
    Checker.checkMode(mode);
    Checker.checkDirectory(rootDirectory);
    Mode modeE = Mode.forName(mode);
    switch (modeE) {
      case SEARCH:
        doSearch(rootDirectory);
        break;
      case DELETE:
        doDelete(rootDirectory);
        break;
    }
  }

  private static void doSearch(String rootDirectory) throws FileCleanerException {
    Util.info("Поиск дубликатов файлов в директории " + rootDirectory);
    doCommonActions(rootDirectory);
  }

  private static void doDelete(String rootDirectory) throws FileCleanerException {
    Util.info("Удаление дубликатов файлов в директории " + rootDirectory);
    doCommonActions(rootDirectory);
    clearDuplicates();
  }

  private static void doCommonActions(String rootDirectory) throws FileCleanerException {
    ALL_FILES_IN_DIRECTORY.clear();
    DUPLICATES.clear();
    searchRecursive(rootDirectory);
    if (DUPLICATES.isEmpty()) {
      Util.info("Дубликатов не найдено");
    } else {
      Util.info("Найдено " + DUPLICATES.size() + " дубликатов");
    }
  }

  private static void searchRecursive(String dirFileName) throws FileCleanerException {
    File dirFile = new File(dirFileName);
    if (dirFile.isDirectory()) {
      if (dirFile.listFiles() != null && dirFile.listFiles().length > 0) {
        for (File childFile : dirFile.listFiles()) {
          searchRecursive(childFile.getAbsolutePath());
        }
      }
    } else {
      Long fileSize = dirFile.length();
      List<File> filesBySize = ALL_FILES_IN_DIRECTORY.get(fileSize);
      if (filesBySize == null) {
        filesBySize = new ArrayList<>();
        ALL_FILES_IN_DIRECTORY.put(fileSize, filesBySize);
      } else {
        for (File currentFile : filesBySize) {
          if (isEqualsFiles(dirFile, currentFile)) {
            Util.warn("Найден дубликат: [" + currentFile.getAbsolutePath() + "] и [" + dirFileName + "]", null);
            DUPLICATES.put(fileSize, dirFile);
            break;
          }
        }
      }
      filesBySize.add(dirFile);
    }
  }

  private static boolean isEqualsFiles(File dirFile, File currentFile) throws FileCleanerException {
    try {
      return FileUtils.contentEquals(dirFile, currentFile);
    } catch (IOException e) {
      throw new FileCleanerException("Ошибка ввода/вывода ", e);
    }
  }

  private static void clearDuplicates() throws FileCleanerException {
    if (!DUPLICATES.isEmpty()) {
      for (File deletingFile : DUPLICATES.values()) {
        try {
          if (!deletingFile.delete()) {
            //Util.error("Ошибка при удалении файла " + deletingFile.getAbsolutePath(), null);
            throw new FileCleanerException("Ошибка при удалении файла " + deletingFile.getAbsolutePath());
          }
        } catch (SecurityException e) {
          throw new FileCleanerException("Ошибка доступа к файлам ", e);
        }
      }
      Util.info("Дубликаты успешно удалены");
    }
  }

}
