package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the 'view' of Triangle Solitaire, a version
 * of Marble Solitaire. This board has marbles arranged in an
 * equilateral triangle. A family of such games can be created
 * by modifying the dimension of this board, in the form of the
 * number of marbles in its bottom-most row. The marbles in row i
 * (starting with row 0) can be indexed as (i,0) to (i,i). This class
 * allows for the visualization of the board.
 */
public class TriangleSolitaireTextView extends AbstractMarbleSolitaireView {

  /**
   * Represents a text view with a model. If the model
   * is null, an exception is thrown. Else,
   * the model is initialized. There are rows and columns
   * and slots within these rows and columns that are represented
   * by one of three states: marble, empty, or invalid.
   * Defaults to the console as its default destination.
   *
   * @param model represents a MarbleSolitaireModelState.
   * @throws IllegalArgumentException if the provided model is null.
   */

  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Parameters need not be null.");
    }
    this.model = model;
    this.object = System.out;
  }


  /**
   * Represents a view that takes in a model and an appendable object.
   *
   * @param model  represents a MarbleSolitaireModelState.
   * @param object represents an appendable object.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable object) {
    if (model == null || object == null) {
      throw new IllegalArgumentException("Parameters need not be null.");
    }
    this.model = model;
    this.object = object;
  }


  @Override
  public String toString() {

    //makes a new Stringbuilder
    StringBuilder builder = new StringBuilder();


    //sets board size
    int bdSz = this.model.getBoardSize();


    for (int rowIndex = 0; rowIndex < bdSz; rowIndex++) {
      for (int i = 0; i < bdSz - rowIndex - 1; i++) {
        builder.append(" ");

      }
      for (int colIndex = 0; colIndex < bdSz; colIndex++) {
        //MarbleSolitaireModelState.SlotState item = model.getSlotAt(rowIndex, colIndex);


        //gets the row and column of the given position in the model
        MarbleSolitaireModelState.SlotState state = model.getSlotAt(rowIndex, colIndex);

        //if invalid, add a space
        if (state.equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          builder.append(" ");
        }

        //if empty, add an underscore
        if (state.equals(MarbleSolitaireModelState.SlotState.Empty)) {
          builder.append("_");
        }

        //if marble, add a "O"
        if (state.equals(MarbleSolitaireModelState.SlotState.Marble)) {
          builder.append("O");
        }


        if (colIndex < rowIndex) {
          builder.append(" ");
        }
      }
      if (rowIndex < bdSz - 1) {
        builder.append("\n");
      }
    }
    //makes stringbuidler into a string
    String str = builder.toString();

    //splits the new string from the stringbuilder by row
    String[] parts = str.split("\n");

    //start off empty cool cucumber
    String ans = "";

    //iterate thru until you reach boardsize
    for (int i = 0; i < bdSz; i++) {

      //each row + the old rows, striptrail for whitespace
      ans = ans + parts[i].stripTrailing();

      //don't add a new line for the last row!
      if (i + 1 < bdSz) {
        ans = ans + "\n";
      }
    }
    //return :)
    return ans;
  }
}

