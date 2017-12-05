package ru.lagranj;

import ru.lagranj.app.FileCleanerException;
import ru.lagranj.app.Handler;
import ru.lagranj.app.Util;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        try {
            Util.info("Начало работы приложения");
            Handler.doAction(args);
        } catch (FileCleanerException e) {
            Util.error("Произошла ошибка: ", e);
        } finally {
            Util.info("Завершение работы приложения");
        }
    }
}
