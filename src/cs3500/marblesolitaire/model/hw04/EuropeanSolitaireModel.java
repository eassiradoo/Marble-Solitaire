package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

/**
 * Represents an English version of MarbleSolitaire. This is a game
 * in which the goal is to move marbles accordingly so that as many
 * marbles as possible can be eliminated.
 */
public class EuropeanSolitaireModel extends AbstractMarbleSolitaireModel {


  /**
   * Represents a version of the game with an armThickness of 3,
   * and the empty slot is at position (3, 3). The row and column
   * indexes start at 0.
   * Boardsize is 7, as it is calculated by multiplying armThickness
   * by 3 and subtracting 2.
   * Inherits from the last constructor.
   */
  public EuropeanSolitaireModel() {
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
  public EuropeanSolitaireModel(int sRow, int sColumn) throws IllegalArgumentException {
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
  public EuropeanSolitaireModel(int armThickness) throws IllegalArgumentException {
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
  public EuropeanSolitaireModel(int armThickness, int sRow, int sColumn)
          throws IllegalArgumentException {
    this.boardSize = (armThickness * 3) - 2;
    if (armThickness < 1 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid armThickness.");
    }
    if (sRow < 0 || sColumn < 0) {
      throw new IllegalArgumentException("Invalid row or column position.");
    }
    for (int rowIndex = 0; rowIndex < (3 * armThickness) - 2; rowIndex++) {
      ArrayList<SlotState> rowTemp = new ArrayList<SlotState>();


      //left side of bd
      for (int colIndex = 0; colIndex < ((3 * armThickness) - 2) / 2; colIndex++) {
        if (rowIndex >= armThickness - 1 - colIndex
                && rowIndex <= 2 * armThickness - 2 + colIndex) {
          rowTemp.add(SlotState.Marble);
        } else {
          rowTemp.add(SlotState.Invalid);
        }
      }

      //right side of bd
      for (int colIndex = ((3 * armThickness) - 2) / 2;
           colIndex < (3 * armThickness) - 2; colIndex++) {
        if (rowIndex >= -2 * armThickness + 2 + colIndex
                && rowIndex <= 5 * armThickness - 5 - colIndex) {
          rowTemp.add(SlotState.Marble);
        } else {
          rowTemp.add(SlotState.Invalid);
        }
      }
      board.add(rowTemp);
    }

    //MUST start with a marble!!
    if (!board.get(sRow).get(sColumn).equals(SlotState.Marble)) {
      throw new IllegalArgumentException("Bad Starting position given");
    }
    board.get(sRow).set(sColumn, SlotState.Empty);
  }
}


