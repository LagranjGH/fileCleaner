package ru.lagranj;

import org.junit.Assert;
import org.junit.Test;
import ru.lagranj.app.Checker;
import ru.lagranj.app.FileCleanerException;

/**
 * Unit test for simple App.
 */
public class CheckerTest {

    @Test
    public void testCheckArguments() {
        String[] args = null;
        Exception ex = null;
        try {
            Checker.checkArguments(args);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Не переданы аргументы.", ex.getMessage());

        ex = null;
        args = new String[]{"dfsd"};
        try {
            Checker.checkArguments(args);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Необходимо передать корневую директорию и режим работы.", ex.getMessage());

        ex = null;
        args = new String[]{"dir", "mode"};
        try {
            Checker.checkArguments(args);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNull(ex);
    }

    @Test
    public void testCheckMode() {
        Exception ex = null;
        String mode = "";
        try {
            Checker.checkMode(mode);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Не введен параметр <Режим работы>", ex.getMessage());

        ex = null;
        mode = "dfdgfg";
        try {
            Checker.checkMode(mode);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Допустимые режимы работы [SEARCH] и [DELETE]", ex.getMessage());

        ex = null;
        mode = "search";
        try {
            Checker.checkMode(mode);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNull(ex);

        mode = "DELETE";
        try {
            Checker.checkMode(mode);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNull(ex);
    }

    @Test
    public void testCheckDirectory() {
        Exception ex = null;
        String rootDirectory = "";
        try {
            Checker.checkDirectory(rootDirectory);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Не введен параметр <Корневая директория>", ex.getMessage());

        ex = null;
        rootDirectory = "C:/blabla/dir666";
        try {
            Checker.checkDirectory(rootDirectory);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Указанный файл/директория не существует", ex.getMessage());

        ex = null;
        rootDirectory = "C:/Windows/WindowsUpdate.log";
        try {
            Checker.checkDirectory(rootDirectory);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Необходимо указать директорию, а не файл", ex.getMessage());

        ex = null;
        rootDirectory = "C:/Data/common/empty";
        try {
            Checker.checkDirectory(rootDirectory);
        } catch (FileCleanerException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("Указанная директория пуста", ex.getMessage());
    }
}
