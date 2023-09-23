package test.polymorphism;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import lab.polymorphism.TextBlock;
import lab.polymorphism.TextLine;

public class HorizontallyFlippedTest {
  TextBlock numberBlock = new TextLine("12345");

  @Test
  public void testRow() throws Exception {
    assertEquals("54321", numberBlock.row(0), "Row is h flipped");
  }

  @Test
  public void testHeight() {
    assertEquals(1, numberBlock.height(), "Height is correct");
  }

  @Test
  public void testWidth() {
    assertEquals(5, numberBlock.width(), "Width is correct");
  }
}