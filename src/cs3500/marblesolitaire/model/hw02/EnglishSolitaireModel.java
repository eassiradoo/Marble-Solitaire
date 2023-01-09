package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;


/**
 * Represents an English version of MarbleSolitaire. This is a game
 * in which the goal is to move marbles accordingly so that as many
 * marbles as possible can be eliminated.
 */
public class EnglishSolitaireModel extends AbstractMarbleSolitaireModel {
  /*
  int armThickness;

  //represents the size of the board --> changes depending on armThickness
  int boardSize;

  //2D array of SlotStates
  protected List<List<SlotState>> board = new ArrayList<>();

   */

  /**
   * Represents a version of the game with an armThickness of 3,
   * and the empty slot is at position (3, 3). The row and column
   * indexes start at 0.
   * Boardsize is 7, as it is calculated by multiplying armThickness
   * by 3 and subtracting 2.
   * Inherits from the last constructor.
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);

    //boardSize is calculated by multiplying armThickness by
    //three and then subtracting two
    this.boardSize = 7;
  }


  /**
   * Represents a version of the game with an armThickness of 3.
   * The given row and column intersection will yield the
   * empty space.
   * Boardsize is 7, as it is calculated by multiplying armThickness
   * by 3 and subtracting 2.
   *
   * @param sRow    represents the row that will be empty.
   * @param sColumn represents the column that will be empty.
   *                At the point where the empty row and empty
   *                column collide, this is where the empty
   *                starting position will be.
   * @throws IllegalArgumentException if the armThickness is not an odd
   *                                  number greater than 0.
   */
  public EnglishSolitaireModel(int sRow, int sColumn) throws IllegalArgumentException {
    this(3, sRow, sColumn);
    this.boardSize = 7;
  }


  /**
   * Represents a version of the game with a given armThickness.
   * Empty row and colum calculated by multiplying armThickness
   * by 3, dividing by 2, and subtracting 1.
   *
   * @param armThickness represents the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @throws IllegalArgumentException if the armThickness is not an odd number > 0.
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, ((3 * armThickness) / 2) - 1, ((3 * armThickness) / 2) - 1);
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness.");
    }
    this.boardSize = (armThickness * 3) - 2;

  }


  /**
   * Represents a version of the game with a given armThickness, row, and column.
   *
   * @param armThickness represents the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @param sRow         represents the row where the empty marble will be.
   * @param sColumn      represents the column where the empty marble will be.
   * @throws IllegalArgumentException if armThickness is not an odd number > 0.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sColumn)
          throws IllegalArgumentException {
    this.boardSize = (armThickness * 3) - 2;
    //if armThickness is invalid
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("armThickness must be an add number > 0");
    }
    //for each row less than board size, make a new list of slotstates
    for (int row = 0; row < (3 * armThickness) - 2; row++) {
      ArrayList<SlotState> newRow = new ArrayList<>();

      //for each column
      for (int column = 0; column < (3 * armThickness) - 2; column++) {

        //if rows and columns are valid, add a marble
        if ((armThickness - 2 < row && row < 2 * armThickness - 1)
                || (armThickness - 2 < column && column < 2 * armThickness - 1)) {
          newRow.add(SlotState.Marble);

          //else, space is invalid
        } else {
          newRow.add(SlotState.Invalid);
        }
      }
      //add updated rows to the board
      board.add(newRow);
    }
    // for (0, 0)...
    try {
      if (board.get(sRow).get(sColumn).equals(SlotState.Marble)) {
        board.get(sRow).set(sColumn, SlotState.Empty);
      } else {
        throw new IndexOutOfBoundsException("Player cannot start game at given slot.");
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Given position is out of bounds.");
    }
  }
}