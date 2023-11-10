package lab.polymorphism;

/**
 * A text block where every single row is right justified.
 *
 * @author Julian Kim
 */
public class RightJustified implements TextBlock {
  public TextBlock block;
  public int width;

  public RightJustified(TextBlock block, int width) {
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
    String blockRow = TBUtils.truncateStr(block.row(i), this.width);

    int totalPadding = this.width - blockRow.length();

    return TBUtils.padStr(blockRow, totalPadding, 0);
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

  public boolean eqv(TextBlock other) {
    if (!this.getClass().equals(other.getClass())) {
      return false;
    }

    RightJustified otherCasted = this.getClass().cast(other);

    return this.block.eqv(otherCasted.block) && this.width == otherCasted.width;
  }

}
