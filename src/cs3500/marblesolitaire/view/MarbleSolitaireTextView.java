package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a viewing class. Implements MarbleSolitaireView to
 * represent a visual model in which to view and play the game.
 * Implements toString, which will provide a visual aspect
 * and display the board and marbles.
 */
public class MarbleSolitaireTextView extends AbstractMarbleSolitaireView {

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
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
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
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable object) {
    if (model == null || object == null) {
      throw new IllegalArgumentException("Parameters need not be null.");
    }
    this.model = model;
    this.object = object;
  }
}


