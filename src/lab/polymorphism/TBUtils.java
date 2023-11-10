package lab.polymorphism;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilities for TextBlocks.
 * 
 * @author Samuel A. Rebelsky
 * @author Julian Kim
 * @version 1.3 of September 2014
 */
public class TBUtils {
  // +--------------+------------------------------------------------------
  // | Class Fields |
  // +--------------+

  /**
   * A really big sequence of dashes. This sequence may grow as the program operates.
   */
  static String lotsOfDashes = "--";

  /**
   * A really big sequence of spaces. This sequence may grow as the program operates.
   */
  static String lotsOfSpaces = "  ";

  // +----------------+----------------------------------------------------
  // | Static Methods |
  // +----------------+

  /**
   * Build a sequence of dashes of a specified length.
   */
  static String dashes(int len) {
    // Note: This strategy probably represents an overkill in
    // attempts at efficiency.
    // Make sure the collection of dashes is big enough
    while (lotsOfDashes.length() < len) {
      lotsOfDashes = lotsOfDashes.concat(lotsOfDashes);
    } // while
    // Extract an appropriate length substring
    return lotsOfDashes.substring(0, len);
  } // dashes(int)

  /**
   * Print a TextBlock to the specified destination.
   */
  public static void print(PrintWriter pen, TextBlock block) {
    for (int i = 0; i < block.height(); i++) {
      // Even though we only call block.row with a valid i,
      // we need to put the call in a try/catch block.
      try {
        pen.println(block.row(i));
      } catch (Exception e) {
        pen.println();
      } // catch (Exception)
    } // for
  } // print(PrintWriter, TextBlock)

  /**
   * Build a sequence of spaces of a specified length.
   */
  static String spaces(int len) {
    // As with dashes, this is probably overkill.
    // Make sure the collection of dashes is big enough
    while (lotsOfSpaces.length() < len) {
      lotsOfSpaces = lotsOfSpaces.concat(lotsOfSpaces);
    } // while
    // Extract an appropriate length substring
    return lotsOfSpaces.substring(0, len);
  } // spaces(int)

  public static String truncateStr(String str, int width) {
    String returnStr = str;

    if (str.length() > width) {
      returnStr = str.substring(0, width);
    }

    return returnStr;
  }

  public static String padStr(String str, int leftPadding, int rightPadding) {
    String returnStr = str;

    for (int p = 0; p < leftPadding || p < rightPadding; p++) {
      if (p < leftPadding) {
        returnStr = " " + returnStr;
      }
      if (p < rightPadding) {
        returnStr = returnStr + " ";
      }
    }

    return returnStr;
  }

  public static String reverseStr(String str) {
    String returnStr = "";
    char[] strArr = str.toCharArray();

    for (int i = strArr.length - 1; i >= 0; i--) {
      returnStr += strArr[i];
    }

    return returnStr;
  }

  public static String scrambleStr(String str) {
    String returnStr = "";
    // following code adapted from https://stackoverflow.com/a/34055302
    // woohoo functional programming
    List<Character> strList =
        str.chars().mapToObj(c -> Character.valueOf((char) c)).collect(Collectors.toList());
    // end citation

    while (strList.size() > 0) {
      int randomI = (int) (Math.random() * strList.size());
      returnStr += strList.remove(randomI);
    }

    return returnStr;
  }

  public static boolean equal(TextBlock t1, TextBlock t2) throws Exception {
    if (t1.height() != t2.height() || t1.width() != t2.width()) {
      return false;
    }

    boolean isEqual = true;
    for (int i = 0; i < t1.height() && isEqual; i++) {
      isEqual = t1.row(i).equals(t2.row(i));
    }

    return isEqual;
  }

  public static boolean eqv(TextBlock t1, TextBlock t2) {
    return t1.eqv(t2);
  }

  public static boolean eq(TextBlock t1, TextBlock t2) {
    return t1 == t2;
  }

} // class TBUtils
