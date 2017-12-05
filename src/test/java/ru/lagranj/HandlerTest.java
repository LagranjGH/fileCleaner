package ru.lagranj;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.lagranj.app.FileCleanerException;
import ru.lagranj.app.Handler;

public class HandlerTest {

  @Test
  @Ignore("Привязка к конкретной директории")
  public void searchTest() {
    String[] args = {"C:/Data/common/zz", "search"};

    Exception ex = null;
    try {
      Handler.doAction(args);
    } catch (FileCleanerException e) {
      ex = e;
    }
    Assert.assertNull(ex);
  }

}
