package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Myles Bohrer-Purnell
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /** The two-dimentional array. */
  private T[][] contents;
  /** The width of the two-dimentional array. */
  private int width;
  /** The height of the two-dimentional array. */
  private int height;
  /** The default value of the two-dimentional array. */
  private T def;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param wid
   *   The width of the matrix.
   * @param h
   *   The height of the matrix.
   * @param defal
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int wid, int h, T defal) {
    if (h < 0 || wid < 0) {
      throw new NegativeArraySizeException();
    } // if
    this.width = wid;
    this.height = h;
    this.def = defal;
    this.contents = (T[][]) new Object[h][wid];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < wid; j++) {
        this.contents[i][j] = defal;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param wid
   *   The width of the matrix.
   * @param h
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int wid, int h) {
    if (h < 0 || wid < 0) {
      throw new NegativeArraySizeException();
    } // if
    this.width = wid;
    this.height = h;
    this.def = null;
    this.contents = (T[][]) new Object[h][wid];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < wid; j++) {
        this.contents[i][j] = null;
      } // for
    } // for
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row < 0 || col < 0 || row >= this.height || col >= this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    return this.contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row < 0 || col < 0 || row >= this.height || col >= this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    this.contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] largerMatrix = (T[][]) new Object[this.height + 1][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        largerMatrix[i][j] = this.contents[i][j];
      } // for
    } // for
    for (int i = this.height; i > row; i--) {
      for (int j = 0; j < this.width; j++) {
        largerMatrix[i][j] = largerMatrix[i - 1][j];
      } // for
    } // for
    for (int i = 0; i < this.width; i++) {
      largerMatrix[row][i] = def;
    } // if
    this.contents = largerMatrix;
    this.height += 1;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if/else if
    T[][] largerMatrix = (T[][]) new Object[this.height + 1][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        largerMatrix[i][j] = this.contents[i][j];
      } // for
    } // for
    for (int i = this.height; i > row; i--) {
      for (int j = 0; j < this.width; j++) {
        largerMatrix[i][j] = largerMatrix[i - 1][j];
      } // for
    } // for
    for (int i = 0; i < this.width; i++) {
      largerMatrix[row][i] = vals[i];
    } // if
    this.contents = largerMatrix;
    this.height += 1;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    T[][] largerMatrix = (T[][]) new Object[this.height][this.width + 1];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        largerMatrix[i][j] = this.contents[i][j];
      } // for
    } // for
    for (int i = this.width(); i > col; i--) {
      for (int j = 0; j < this.height; j++) {
        largerMatrix[j][i] = largerMatrix[j][i - 1];
      } // for
    } // for
    for (int i = 0; i < this.height; i++) {
      largerMatrix[i][col] = def;
    } // if
    this.contents = largerMatrix;
    this.width += 1;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if/else if
    T[][] largerMatrix = (T[][]) new Object[this.height][this.width + 1];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        largerMatrix[i][j] = this.contents[i][j];
      } // for
    } // for
    for (int i = this.width(); i > col; i--) {
      for (int j = 0; j < this.height; j++) {
        largerMatrix[j][i] = largerMatrix[j][i - 1];
      } // for
    } // for
    for (int i = 0; i < this.height; i++) {
      largerMatrix[i][col] = vals[i];
    } // if
    this.contents = largerMatrix;
    this.width += 1;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= this.height) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = row; i < this.height - 1; i++) {
      for (int j = 0; j < this.width; j++) {
        this.contents[i][j] = this.contents[i + 1][j];
      } // for
    } // for
    --this.height;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col >= this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = col; i < this.width - 1; i++) {
      for (int j = 0; j < this.height; j++) {
        this.contents[j][i] = this.contents[j][i + 1];
      } // for
    } // for
    --this.width;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startRow < 0 || startRow > this.height || endRow < 0 || endRow > this.height
        || startCol < 0 || startCol > this.width || endCol < 0 || endCol > this.width
        || startCol > endCol || startRow > endRow) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = startCol; i < endCol; i++) {
      for (int j = startRow; j < endRow; j++) {
        this.contents[j][i] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (startRow < 0 || startRow > this.height || endRow < 0 || endRow > this.height
        || startCol < 0 || startCol > this.width || endCol < 0 || endCol > this.width
        || startCol > endCol || startRow > endRow) {
      throw new IndexOutOfBoundsException();
    } // if
    int inc = 1;
    int curRow = startRow;
    int curCol = startCol;
    while (curRow < endRow && curCol < endCol) {
      this.contents[curRow][curCol] = val;
      curRow = startRow + (deltaRow * inc);
      curCol = startCol + (deltaCol * inc);
      inc += 1;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> contentClone = new MatrixV0<T>(this.width, this.height, this.def);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        contentClone.contents[i][j] = this.contents[i][j];
      } // for
    } // for
    return contentClone;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof Matrix) {
      MatrixV0<T> matrixOther = (MatrixV0<T>) other;
      if (matrixOther.width() != this.width || matrixOther.height() != this.height) {
        return false;
      } // if
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (matrixOther.contents[i][j] != this.contents[i][j]) {
            return false;
          } // if
        } // for
      } // for
    } else {
      return false;
    } // else
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
