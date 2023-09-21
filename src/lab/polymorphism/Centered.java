package lab.polymorphism;

public class Centered implements TextBlock {
  public TextBlock block;
  public int width;

  public Centered(TextBlock block, int width) {
    this.block = block;
    this.width = width;
  }

    /**
   * Get one row from the block.
   * 
   * @pre i < this.height()
   * @exception Exception if the row number is invalid.
   */
  public String row(int i) throws Exception {
    String blockRow = block.row(i);

    if (blockRow.length() > this.width) {
      blockRow = blockRow.substring(0, this.width);
      return blockRow;
    }

    int totalPadding = this.width - blockRow.length();
    int rightPadding = totalPadding / 2;
    int leftPadding = totalPadding - rightPadding;

    for (int p = 0; p < leftPadding || p < rightPadding; p++) {
      if (p < leftPadding) {
        blockRow = " " + blockRow;
      }
      if (p < rightPadding) {
        blockRow = blockRow + " ";
      }
    }

    return blockRow;
  }

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return block.height();
  }

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.width;
  }
}
