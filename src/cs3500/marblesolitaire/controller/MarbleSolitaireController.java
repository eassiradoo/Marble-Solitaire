package cs3500.marblesolitaire.controller;

/**
 * Represents the controller for a game of marble solitaire.
 * This controller has one method, playGame, which allows
 * players to interactively play this game.
 */
public interface MarbleSolitaireController {

  /**
   * "Runs" the game until the game ends. This method renders
   * the current state of the game, transmits the score,
   * obtains user input using the Readable object (fromRow, fromCol,
   * toRow, toCol). These inputs are passed onto the model in order
   * for it to make the move. As long as the game is not over,
   * repeat these steps. Once the game is over, display a "Game over!"
   * screen with the score and final game state. The method will then end.
   *
   * @throws IllegalStateException if the controller is
   *                               unable to successfully read input or transmit output.
   */
  void playGame() throws IllegalStateException;

}


