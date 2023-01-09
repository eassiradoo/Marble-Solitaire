package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents an abstract class that will allow for less
 * code duplication. Is implemented by all Solitaire Models.
 */
public class AbstractMarbleSolitaireModel implements MarbleSolitaireModel {

  //2D Array of Slotstates
  protected List<List<MarbleSolitaireModelState.SlotState>> board = new ArrayList<>();

  //size of the board
  protected int boardSize;

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    if (!(this.moveAllowed(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("Move cannot be made.");
    }

    int skippedRow = (fromRow + toRow) / 2;
    int skippedColumn = (fromCol + toCol) / 2;

    board.get(fromRow).set(fromCol, MarbleSolitaireModelState.SlotState.Empty);
    board.get(skippedRow).set(skippedColumn, MarbleSolitaireModelState.SlotState.Empty);
    this.putMarble(toRow, toCol);
  }

  @Override
  public boolean isGameOver() {
    //board size
    int size = this.boardSize;

    //for every row and column
    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        //if the retrieved space has a marble and there is a move that can be made,
        //the game is not over
        if (board.get(row).get(column).equals(SlotState.Marble) && this.moveFound(row, column)) {
          return false;
        }
      }
    }
    //else, the game is over
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if ((row < 0 || col < 0)) {
      throw new IllegalArgumentException("Invalid row or column.");
    }
    //gets posn of (row, column)
    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    //size of the board is the boardSize
    int size = this.boardSize;
    //score starts off at 0 and increases by one each
    //time a valid move is made
    int scoreCount = 0;
    //for each row and column less than the boardSize
    for (int row = 0; row < size; row++) {
      for (int column = 0; column < size; column++) {
        //get the posn (row, column)
        SlotState posn = board.get(row).get(column);
        //if there is a marble at that spot,
        if (posn == SlotState.Marble) {
          //increase the score
          scoreCount++;
        }
      }
    }
    //else, score cannot increase this turn and the same score
    //will be returned
    return scoreCount;
  }

  /**
   * Returns true if a move with the given row and column
   * is possible. If a move is not possible (due to invalid
   * rows or columns, or the slot not being a marble), returns false.
   *
   * @param sRow    represents the given column.
   * @param sColumn represents the given row.
   * @return true if the move is possible!
   */

  public boolean moveFound(int sRow, int sColumn) throws IllegalArgumentException {
    if (sRow < 0 || sColumn < 0) {
      throw new IllegalArgumentException("Rows and columns cannot be negative.");
    }
    //board size
    int bdSz = boardSize;

    //conditions in which a move would not be possible
    if (board.get(sRow).get(sColumn) != SlotState.Marble) {
      return false;
    }

    //if the row and column is valid and the move is allowed
    return sRow < (bdSz - 1) && this.moveAllowed(sRow, sColumn, (sRow + 2), sColumn)
            || sColumn < (bdSz - 1) && this.moveAllowed(sRow, sColumn, sRow, (sColumn + 2))
            || sColumn > 1 && this.moveAllowed(sRow, sColumn, sRow, (sColumn - 2))
            || sRow > 1 && this.moveAllowed(sRow, sColumn, (sRow - 2), sColumn);
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
   * @param toCol      represents the column for which the marble is going to.
   * @return true if the given move is allowed.
   */

  public boolean moveAllowed(int fromRow, int fromColumn, int toRow, int toCol)
          throws IllegalArgumentException {
    if (fromRow < 0 || toRow < 0 || fromColumn < 0 || toCol < 0) {
      throw new IllegalArgumentException("Invalid row or column.");
    }
    //row that will be skipped over jumpy jump
    int skippedRow = (fromRow + toRow) / 2;
    //column that will be skipped over
    int skippedCol = (fromColumn + toCol) / 2;
    //board size once again
    int bdSz = this.getBoardSize();

    return // can't exceed board perimeter
            ((fromRow < bdSz && fromColumn < bdSz && toRow < bdSz && toCol < bdSz))
                    //must be a marb!
                    && (board.get(fromRow).get(fromColumn) == SlotState.Marble)
                    //distance must be two
                    && ((Math.abs(fromRow - toRow) == 2 && (fromColumn - toCol) == 0)
                    //distance must be two
                    || (Math.abs(fromColumn - toCol) == 2 && (fromRow - toRow) == 0))
                    //the skipped space MUST have a marble
                    && (board.get(skippedRow).get(skippedCol) == SlotState.Marble)
                    //the space the player is going to MUST be empty
                    && (board.get(toRow).get(toCol) == SlotState.Empty);
  }

  /**
   * Puts a marble in needed position based on given row and column.
   *
   * @param sRow represents the row with the empty position.
   * @param sCol represents the column with the empty position.
   * @throws IllegalArgumentException if the position is not empty.
   */
  public void putMarble(int sRow, int sCol) throws IllegalArgumentException {
    if (board.get(sRow).get(sCol) != SlotState.Empty) {
      //trying to put a marble where it shant be
      throw new IllegalArgumentException("Position is not empty.");
    }
    board.get(sRow).set(sCol, SlotState.Marble);
  }
}
