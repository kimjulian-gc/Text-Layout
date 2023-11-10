package test.polymorphism;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import lab.polymorphism.BoxedBlock;
import lab.polymorphism.Centered;
import lab.polymorphism.HorizontallyFlipped;
import lab.polymorphism.RightJustified;
import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;
import lab.polymorphism.TextLine;
import lab.polymorphism.Truncated;
import lab.polymorphism.VComposition;

public class TextBlockTests {
  TextBlock numberBlock = new TextLine("012345678901234567890123456789");
  TextBlock centerTest1 = new VComposition(
    new Centered(new BoxedBlock(new TextLine("Hello")), 21),
    numberBlock
  );

  @Test
  public void centeredRowTest() throws Exception {
    assertEquals("       |Hello|                ", centerTest1.row(1));
  }

  @Test
  public void centeredHeightTest() {
    assertEquals(4, centerTest1.height());
  }

  @Test
  public void centeredWidthTest() {
    assertEquals(30, centerTest1.width());
  }

  TextBlock numberBlock2 = new TextLine("12345");
  TextBlock horizontalTest = new HorizontallyFlipped(numberBlock2);

  @Test
  public void horizontalRowTest() throws Exception {
    assertEquals("54321", horizontalTest.row(0));
  }

  @Test
  public void horizontalHeightTest() {
    assertEquals(1, horizontalTest.height());
  }

  @Test
  public void horizontalWidthTest() {
    assertEquals(5, horizontalTest.width());
  }

  TextBlock rightTest = new VComposition(
      new RightJustified(new BoxedBlock(new TextLine("Hello")), numberBlock.width()),
      numberBlock
    );

  @Test
  public void rightRowTest() throws Exception {
    assertEquals("                       |Hello|", rightTest.row(1));
  }

  @Test
  public void rightHeightTest() {
    assertEquals(4, rightTest.height());
  }

  @Test
  public void rightWidthTest() {
    assertEquals(30, rightTest.width());
  }

  TextBlock equalTest1 = new VComposition(
    new Centered(new BoxedBlock(new TextLine("Hello")), 21),
    numberBlock
  );
  TextBlock equalTest2 = new VComposition(
    new Centered(new BoxedBlock(new TextLine("Hello")), 21),
    numberBlock
  );
  TextBlock equalTest3 = new VComposition(
    new Centered(new BoxedBlock(new TextLine("Hello")), 19),
    numberBlock
  );
  TextBlock equalTest4 = equalTest1;

  @Test
  public void testTruncateStr() {
    assertEquals("Too long string gets truncated", "12345", TBUtils.truncateStr("123456", 5));
    assertEquals("Too short string does not get truncated", "1234", TBUtils.truncateStr("1234", 5));
  }

  @Test
  public void testPadStr() {
    assertEquals("Left padding works", " 12345", TBUtils.padStr("12345", 1, 0));
    assertEquals("Right padding works", "12345 ", TBUtils.padStr("12345", 0, 1));
    assertEquals("Both padding works", " 12345 ", TBUtils.padStr("12345", 1, 1));
    assertEquals("Unequal padding works", " 12345  ", TBUtils.padStr("12345", 1, 2));
  }

  @Test
  public void testReverseStr() {
    assertEquals("Reversed empty string is empty string", "", TBUtils.reverseStr(""));
    assertEquals("Reversing works", "54321", TBUtils.reverseStr("12345"));
    assertEquals("Reversed length 1 string is same string", "a", TBUtils.reverseStr("a"));
  }

  @Test
  public void testScrambleStr() {
    char[] abcdefArr = "abcdef".toCharArray();
    char[] scrambledArr = TBUtils.scrambleStr("abcdef").toCharArray();
    Arrays.sort(scrambledArr);

    assertTrue(
      "Scrambled string has same characters as original",
      Arrays.equals(
        abcdefArr, 
        scrambledArr
      )
    );
  }

  @Test
  public void testEqual() throws Exception {
    assertTrue("Contains same lines is equal", TBUtils.equal(equalTest1, equalTest2));
    assertFalse("Does not contain same lines is not equal", TBUtils.equal(equalTest1, equalTest3));
  }

  @Test
  public void testEqv() {
    assertTrue("Constructed in same exact way is eqv", TBUtils.eqv(equalTest1, equalTest2));
    assertFalse("Not constructed in same exact way is not eqv", TBUtils.eqv(equalTest1, equalTest3));
  }

  @Test
  public void testEq() {
    assertTrue("Same place in memory is eq", TBUtils.eq(equalTest1, equalTest4));
    assertFalse("Not same place in memory is not eq", TBUtils.eq(equalTest1, equalTest2));
  }

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
