package lab.polymorphism;

import java.io.PrintWriter;

/**
 * A series of experiments with the text block layout classes.
 * 
 * @author Samuel A. Rebelsky
 * @author Julian Kim
 * @version 1.3 of September 2019
 */
public class TBExpt {
  // +------+--------------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args) throws Exception {
    // Prepare for input and output
    PrintWriter pen = new PrintWriter(System.out, true);

    // Create a block to use
    TextBlock block = new BoxedBlock(new VComposition(
      new TextLine("Hello World!"), 
      new TextLine("1234567890")
    ));

    // Print out the block
    TBUtils.print(pen, block);
    TBUtils.print(pen, new Truncated(block, 5));

    TextBlock numberBlock = new TextLine("012345678901234567890123456789");
    TextBlock centerTest1 = new VComposition(
      new Centered(new BoxedBlock(new TextLine("Hello")), 21),
      numberBlock
    );
    TBUtils.print(pen, centerTest1);

    TextBlock centerTest2 = new VComposition(
      new BoxedBlock(new Centered(new TextLine("Hello"), 21)),
      numberBlock
    );
    TBUtils.print(pen, centerTest2);

    TextBlock rightTest = new VComposition(
      new RightJustified(new BoxedBlock(new TextLine("Hello")), numberBlock.width()),
      numberBlock
    );
    TBUtils.print(pen, rightTest);

    TextBlock hFlipped = new HorizontallyFlipped(rightTest);
    TBUtils.print(pen, hFlipped);

    TextBlock vFlipped = new VerticallyFlipped(rightTest);
    TBUtils.print(pen, vFlipped);

    TextBlock scrambledTest1 = new ScrambledRows(rightTest);
    TBUtils.print(pen, scrambledTest1);
    TBUtils.print(pen, scrambledTest1);

    TextBlock scrambledTest2 = new VComposition(
      new Centered(
        new ScrambledRows(new BoxedBlock(new TextLine("Hello"))),
        21
      ),
      numberBlock
    );
    TBUtils.print(pen, scrambledTest2);

    TextBlock equalTest1 = new VComposition(
      new Centered(new BoxedBlock(new TextLine("Hello")), 21),
      numberBlock
    );
    TextBlock equalTest2 = new VComposition(
      new Centered(new BoxedBlock(new TextLine("Hello")), 21),
      numberBlock
    );
    pen.println(TBUtils.equal(equalTest1, equalTest2));

    TextBlock equalTest3 = new VComposition(
      new Centered(new BoxedBlock(new TextLine("Hello")), 19),
      numberBlock
    );
    pen.println(TBUtils.equal(equalTest1, equalTest3));

    pen.println(TBUtils.eqv(equalTest1, equalTest2));
    pen.println(TBUtils.eqv(equalTest1, equalTest3));

    TextBlock equalTest4 = equalTest1;
    pen.println(TBUtils.eq(equalTest1, equalTest4));
    pen.println(TBUtils.eq(equalTest1, equalTest2));

    // Clean up after ourselves.
    pen.close();
  } // main(String[])

} // class TBExpt
