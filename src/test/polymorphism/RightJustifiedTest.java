package test.polymorphism;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import lab.polymorphism.BoxedBlock;
import lab.polymorphism.RightJustified;
import lab.polymorphism.TextBlock;
import lab.polymorphism.TextLine;
import lab.polymorphism.VComposition;

public class RightJustifiedTest {
  TextBlock numberBlock = new TextLine("012345678901234567890123456789");
  TextBlock rightTest = new VComposition(
      new RightJustified(new BoxedBlock(new TextLine("Hello")), numberBlock.width()),
      numberBlock
    );

  @Test
  public void testRow() throws Exception {
    assertEquals("                       |Hello|", rightTest.row(1), "Centered line is centered");
  }

  @Test
  public void testHeight() {
    assertEquals(4, rightTest.height(), "Centered test block has right height");
  }

  @Test
  public void testWidth() {
    assertEquals(30, rightTest.width(), "Centered test block has right width");
  }
}