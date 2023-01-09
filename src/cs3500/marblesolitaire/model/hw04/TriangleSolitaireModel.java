package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

/**
 * Represents a Triangular version of MarbleSolitaire. This is a game
 * in which the goal is to move marbles accordingly so that as many
 * marbles as possible can be eliminated.
 */
public class TriangleSolitaireModel extends AbstractMarbleSolitaireModel {

  /**
   * Represents a version of the game that has no parameters
   * and creates a 5-row game with the empty slot at (0,0).
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Represents a version of the game with the specified dimension
   * (number of slots in the bottom-most row) and the empty slot at
   * (0,0).
   *
   * @param dimensions represents the number of slots in the bottom
   *                   (longest) row.
   * @throws IllegalArgumentException if dimension is less than 1.
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    this(dimensions, 0, 0);
    if (dimensions < 1) {
      throw new IllegalArgumentException("Dimensions must be greater than 0.");
    }
  }

  /**
   * Represents a version of the game that creates a 5-row
   * game with the empty slot at the specified position.
   *
   * @param sRow    given row where the empty slot will exist.
   * @param sColumn given column where the empty slot will exist.
   * @throws IllegalArgumentException if the specified position is invalid.
   */
  public TriangleSolitaireModel(int sRow, int sColumn) throws IllegalArgumentException {
    this(5, sRow, sColumn);
    if (sColumn < 0 || sRow < 0) {
      throw new IllegalArgumentException("Rows and columns must be non-negative.");
    }
  }

  /**
   * Represents a version of the game with three parameters
   * (dimensions,row,col) that creates a game with the specified
   * dimension and an empty slot at the specified row and column.
   *
   * @param dimensions represents the number of slots in the bottom
   *                   *                   (longest) row.
   * @param sRow       given row where the empty slot will exist.
   * @param sColumn    given column where the empty slot will exist.
   * @throws IllegalArgumentException if the specified dimension
   *                                  is invalid (non-positive) or the
   *                                  specified position is invalid.
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sColumn)
          throws IllegalArgumentException {
    this.boardSize = (dimensions);
    //not large enough
    if (dimensions < 1) {
      throw new IllegalArgumentException("Invalid thickness.");
    }
    //iterate through
    for (int i = 0; i < dimensions; i++) {
      ArrayList<SlotState> newRow = new ArrayList<SlotState>();
      //add a marble
      for (int j = 0; j < i + 1; j++) {
        newRow.add(SlotState.Marble);
      }
      //invalids
      for (int j = i + 1; j < dimensions; j++) {
        newRow.add(SlotState.Invalid);
      }
      this.board.add(newRow);
    }
    try {
      if (board.get(sRow).get(sColumn).equals(SlotState.Marble)) {
        board.get(sRow).set(sColumn, SlotState.Empty);
      } else {
        //Out of bounds
        throw new IndexOutOfBoundsException("Invalid marble.");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid starting position.");
    }
  }


  /**
   * Returns true if the provided move is allowed.
   * If the from and to rows and columns are valid,
   * there is a marble at the specified "from" position,
   * the "to" position is empty, the "to" and "from"
   * positions are exactly two positions away (horizontally
   * and vertically), and there is a marble in the slot
   * between the "to" and "from" position.
   *
   * @param fromRow    represents row from which the marble is coming from.
   * @param fromColumn represents the column from which the marble is coming from.
   * @param toRow      represents the row for which the marble is going to.
   * @param toColumn   represents the column for which the marble is going to.
   * @return true if the given move is allowed.
   */

  public boolean moveAllowed(int fromRow, int fromColumn, int toRow, int toColumn)
          throws IllegalArgumentException {
    int bdSz = this.boardSize;
    if (!(fromRow >= 0 && fromColumn >= 0) && (toRow >= 0 && toColumn >= 0) && fromRow < bdSz
            && fromColumn < bdSz && toRow < bdSz && toColumn < bdSz) {
      throw new IllegalArgumentException("Move cannot be made.");
    }

    boolean fromMarble;
    boolean skippedMarble;
    boolean toEmpty;
    // O O _ O --> allows for moves within a row
    boolean moveInRow = fromRow == toRow && Math.abs(fromColumn - toColumn) == 2;
    //allows for diagonal moves
    boolean diagonalMove = (Math.abs(fromColumn - toColumn) == 0
            || Math.abs(fromColumn - toColumn) == 2)
            && (Math.abs(fromRow - toRow) == 2);
    try {
      //rows + columns that are skipped
      int skippedRow = (fromRow + toRow) / 2;
      int skippedColumn = (fromColumn + toColumn) / 2;
      //from posn must be a marble
      fromMarble = board.get(fromRow).get(fromColumn).equals(SlotState.Marble);
      //to posn must be empty
      toEmpty = board.get(toRow).get(toColumn).equals(SlotState.Empty);
      //must jump over a marble
      skippedMarble = board.get(skippedRow).get(skippedColumn).equals(SlotState.Marble);
      //error
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
    //true if --> move permitted
    return (moveInRow || diagonalMove)
            && fromMarble && toEmpty && skippedMarble;
  }


  /**
   * Returns true if there is a valid move found
   * at the given row and column. If not, returns
   * false.
   *
   * @param row represents the given column.
   * @param col represents the given row.
   * @return true if a playable move is found.
   */
  public boolean moveFound(int row, int col) throws IllegalArgumentException {
    //must start with a marble
    if (board.get(row).get(col) != SlotState.Marble) {
      throw new IllegalArgumentException("No move allowed.");
    }
    //conditions needed for a move to be spotted
    return this.moveAllowed(row, col, row, col + 2)
            || this.moveAllowed(row, col, row, col - 2)
            || this.moveAllowed(row, col, row + 2, col + 2)
            || this.moveAllowed(row, col, row - 2, col - 2)
            || this.moveAllowed(row, col, row + 2, col)
            || this.moveAllowed(row, col, row - 2, col);
  }


}
