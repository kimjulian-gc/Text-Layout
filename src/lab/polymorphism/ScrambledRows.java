package lab.polymorphism;

public class ScrambledRows implements TextBlock {
  public TextBlock block;

  public ScrambledRows(TextBlock block) {
    this.block = block;
  }

    /**
   * Get one row from the block.
   * 
   * @pre i < this.height()
   * @exception Exception if the row number is invalid.
   */
  public String row(int i) throws Exception {
    String blockRow = block.row(i);

    return TBUtils.scrambleStr(blockRow);
  }

  /**
   * Determine how many rows are in the block.
   */
  public int height() {
    return this.block.height();
  }

  /**
   * Determine how many columns are in the block.
   */
  public int width() {
    return this.block.width();
  }
}