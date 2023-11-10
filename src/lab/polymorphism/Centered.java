package lab.polymorphism;

/**
 * A text block where every single row is centered.
 *
 * @author Julian Kim
 */
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
    String blockRow = TBUtils.truncateStr(block.row(i), this.width);

    int totalPadding = this.width - blockRow.length();
    int rightPadding = totalPadding / 2;
    int leftPadding = totalPadding - rightPadding;

    return TBUtils.padStr(blockRow, leftPadding, rightPadding);
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

    Centered otherCasted = this.getClass().cast(other);

    // should be safe to cast since we checked before
    return this.block.eqv(otherCasted.block) && this.width == otherCasted.width;
  }
}
