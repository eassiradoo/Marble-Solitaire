package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a viewing class. Implements MarbleSolitaireView to
 * represent a visual model in which to view and play the game.
 * Implements toString, renderBoard, and
 * renderMessage, which will provide a visual aspect
 * and display the board, marbles, and a message.
 */
public class EuropeanSolitaireTextView extends AbstractMarbleSolitaireView {

  /**
   * Represents a view that takes in a model. The object
   * defaults to the console for effective rendering and
   * gameplay.
   *
   * @param model represents a MarbleSolitaireModelState.
   * @throws IllegalArgumentException if the given model is null.
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModelState model)
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
  public EuropeanSolitaireTextView(MarbleSolitaireModelState model, Appendable object) {
    if (model == null || object == null) {
      throw new IllegalArgumentException("Parameters need not be null.");
    }
    this.model = model;
    this.object = object;
  }
}

