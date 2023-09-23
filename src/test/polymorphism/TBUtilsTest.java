package test.polymorphism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;
import lab.polymorphism.BoxedBlock;
import lab.polymorphism.Centered;
import lab.polymorphism.TBUtils;
import lab.polymorphism.TextBlock;
import lab.polymorphism.TextLine;
import lab.polymorphism.VComposition;

public class TBUtilsTest {
  TextBlock numberBlock = new TextLine("012345678901234567890123456789");
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
  public void eq() {
    assertTrue("Same place in memory is eq", TBUtils.eq(equalTest1, equalTest4));
    assertFalse("Not same place in memory is not eq", TBUtils.eq(equalTest1, equalTest2));
  }
}
