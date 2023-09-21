package lab.polymorphism;

public class VerticallyFlipped implements TextBlock {
  public TextBlock block;

  public VerticallyFlipped(TextBlock block) {
    this.block = block;
  }

    /**
   * Get one row from the block.
   * 
   * @pre i < this.height()
   * @exception Exception if the row number is invalid.
   */
  public String row(int i) throws Exception {
    int numRows = this.height();
    // rows are zero-indexed i think
    String blockRow = block.row(this.height() - 1 - i);

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
    return this.block.width();
  }
}