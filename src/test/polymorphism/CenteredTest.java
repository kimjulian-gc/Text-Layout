package test.polymorphism;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import lab.polymorphism.BoxedBlock;
import lab.polymorphism.Centered;
import lab.polymorphism.TextBlock;
import lab.polymorphism.TextLine;
import lab.polymorphism.VComposition;

public class CenteredTest {
  TextBlock numberBlock = new TextLine("012345678901234567890123456789");
  TextBlock centerTest1 = new VComposition(
    new Centered(new BoxedBlock(new TextLine("Hello")), 21),
    numberBlock
  );

  @Test
  public void testRow() throws Exception {
    assertEquals("       |Hello|                ", centerTest1.row(1), "Centered line is centered");
  }

  @Test
  public void testHeight() {
    assertEquals(4, centerTest1.height(), "Centered test block has right height");
  }

  @Test
  public void testWidth() {
    assertEquals(30, centerTest1.width(), "Centered test block has right width");
  }
}
