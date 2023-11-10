package lab.polymorphism;

/**
 * A text block where every single row is truncated to a certain max width.
 *
 * @author Julian Kim
 */
public class Truncated implements TextBlock {
  public TextBlock block;
  public int maxWidth;

  public Truncated(TextBlock block, int maxWidth) {
    this.block = block;
    this.maxWidth = maxWidth;
  }

  /**
   * Get one row from the block.
   * 
   * @pre i < this.height()
   * @exception Exception if the row number is invalid.
   */
  public String row(int i) throws Exception {
    String blockRow = block.row(i);

    return TBUtils.truncateStr(blockRow, this.maxWidth);
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
    return Math.min(this.maxWidth, this.block.width());
  }

  public boolean eqv(TextBlock other) {
    if (!this.getClass().equals(other.getClass())) {
      return false;
    }

    Truncated otherCasted = this.getClass().cast(other);

    // should be safe to cast since we checked before
    return this.block.eqv(otherCasted.block) && this.maxWidth == otherCasted.maxWidth;
  }
}
