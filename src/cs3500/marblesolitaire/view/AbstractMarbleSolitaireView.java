package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents an abstract class for MarbleSolitaireView that
 * can be extended by all view implementations.
 */
public abstract class AbstractMarbleSolitaireView implements MarbleSolitaireView {

  //represents the given model
  public MarbleSolitaireModelState model;

  //appendable to concatenate
  public Appendable object = new StringBuilder();

  @Override
  public String toString() {
    //makes a new Stringbuilder
    StringBuilder builder = new StringBuilder();

    //sets board size
    int bdSz = this.model.getBoardSize();
    //for each row and column, incrememt
    for (int row = 0; row < bdSz; row++) {
      for (int column = 0; column < bdSz; column++) {

        //gets the row and column of the given position in the model
        MarbleSolitaireModelState.SlotState state = model.getSlotAt(row, column);

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

        //adds spaces in between each slot, including at the end
        if (!(state == MarbleSolitaireModelState.SlotState.Invalid && column
                > bdSz / 2)) {
          builder.append(" ");
        }
      }

      //make a new line ALA row is appropriate for board size
      if (row < bdSz - 1) {
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

  @Override
  public void renderBoard() throws IOException {
    try {
      //draws the board
      this.object.append(new MarbleSolitaireTextView(model) + "\n");
    } catch (IOException exception) {
      throw new IllegalStateException("Error in board rendering.");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      //writes a message
      this.object.append(message);
    } catch (IOException exception) {
      throw new IllegalStateException("Error found in message.");
    }
  }
}
