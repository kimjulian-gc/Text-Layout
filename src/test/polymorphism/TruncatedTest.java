package test.polymorphism;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import lab.polymorphism.BoxedBlock;
import lab.polymorphism.TextBlock;
import lab.polymorphism.TextLine;
import lab.polymorphism.Truncated;
import lab.polymorphism.VComposition;

public class TruncatedTest {
  TextBlock tooLongTest = new Truncated(new BoxedBlock(new VComposition(
      new TextLine("Hello World!"), 
      new TextLine("1234567890")
    )), 5);

  TextBlock tooShortTest = new Truncated(new BoxedBlock(new VComposition(
    new TextLine("abcd e"),
    new TextLine("au29 9q 9"))),
  15);

  @Test
  public void testRow() throws Exception {
    assertEquals("Too long row 0 is truncated properly", "+----", tooLongTest.row(0));
    assertEquals("Too long row 1 is truncated properly", "|Hell", tooLongTest.row(1));
    assertEquals("Too long row 2 is truncated properly", "|1234", tooLongTest.row(2));
    assertEquals("Too long row 3 is truncated properly", "+----", tooLongTest.row(3));

    assertEquals("Too short row 0 is truncated properly", "+---------+", tooShortTest.row(0));
    assertEquals("Too short row 1 is truncated properly", "|abcd e   |", tooShortTest.row(1));
    assertEquals("Too short row 2 is truncated properly", "|au29 9q 9|", tooShortTest.row(2));
    assertEquals("Too short row 3 is truncated properly", "+---------+", tooShortTest.row(3));
  }

  @Test
  public void testHeight() {
    assertEquals("Too long height is correct", 4, tooLongTest.height());
    assertEquals("Too short height is correct", 4, tooShortTest.height());
  }

  @Test
  public void testWidth() {
    assertEquals("Too long width is correct", 5, tooLongTest.width());
    assertEquals("Too short width is correct", 11, tooShortTest.width());
  }
}
