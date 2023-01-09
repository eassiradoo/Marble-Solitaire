package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the implementation of the MarbleSolitaireController
 * interface. Contains fields including the model, the view, a readable
 * input and a scanner. Will implement the playGame method.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  //model yay
  MarbleSolitaireModel model;

  //view
  MarbleSolitaireView viewer;

  //readable input
  Readable readable;

  //scanner
  Scanner scanner;

  /**
   * Represents a version of the game (constructor) that takes in a model,
   * a view, and a readable object. Readable represents the inputs that
   * a user will make.
   *
   * @param model  represents the model in model, view, controller.
   *               Is a MarbleSolitaireModel.
   * @param view   represents the view in model, view, controller.
   *               *              Is a MarbleSolitaireView.
   * @param object represents a readable object.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable object) throws IllegalArgumentException {
    if (model == null || view == null || object == null) {
      throw new IllegalArgumentException("No objects may be null.");
    }
    this.model = model;
    this.viewer = view;
    this.readable = object;

  }

  /**
   * Renders the current state of the game using the view.
   * If the user enters 'q' or 'Q,' the controller prints a
   * quit screen with the phrase "Game quit!," followed by
   * the state of the game and a rendering of the board.
   * At the bottom of this image, the score is displayed. The
   * method then ends. If any unexpected inputs are present,
   * the user will be asked to re-enter with the phrase
   * "Invalid move. Play again." being displayed.
   *
   * @throws IllegalStateException if any transmission to
   *                               the view fails, or any attempt to read from the Readable
   *                               object fails.
   */
  @Override
  public void playGame() throws IllegalStateException {

    //new scanner that takes in inputs
    this.scanner = new Scanner(this.readable);
    int legalPlacement = 0;

    //array of moves from to, row column
    int[] moves = new int[4];

    //draws what is displayed in System.out
    try {
      
      this.viewer.renderBoard();
      this.viewer.renderMessage("Score:" + model.getScore() + "\n");

      //throw an exception
    } catch (IOException e) {
      throw new IllegalStateException("Invalid output.");
    }

    //as long as the game isn't over,
    while (!model.isGameOver()) {
      while (legalPlacement != 4) {
        String input;
        try {
          //retrieves next value of the scanner
          input = scanner.next();
        } catch (NoSuchElementException e) {
          //scanner has no next value
          throw new IllegalStateException("Scanner has no more values.");
        }

        //to quit the game
        try {
          if (input.equalsIgnoreCase("q")) {
            try {

              //message
              this.viewer.renderMessage("Game quit!\n");
              //game state message
              this.viewer.renderMessage("State of game when quit:\n");
              //draw board
              this.viewer.renderBoard();
              //display score
              this.viewer.renderMessage("Score: " + model.getScore());
              return;
            } catch (IOException e) {
              throw new IllegalStateException("Invalid output.");
            }
          } else if (Integer.parseInt(input) > 0) {

            //for the legally placed moves,
            moves[legalPlacement] = Integer.parseInt(input) - 1;

            //increment
            legalPlacement = legalPlacement + 1;
          } else {
            try {
              //invalid
              this.viewer.renderMessage("Invalid inputs \n");
            } catch (IOException e) {
              throw new IllegalStateException("Invalid output.");
            }
          }
        } catch (IllegalArgumentException e) {
          try {
            this.viewer.renderMessage("Invalid input \n");
          } catch (IOException exception) {
            throw new IllegalStateException("Invalid output.");
          }
        }
      }
      try {
        try {

          //moves (toCol, fromCol, toRow, fromRow)
          model.move(moves[0], moves[1], moves[2], moves[3]);
          this.viewer.renderMessage("\n");
        } catch (IOException e) {
          throw new IllegalStateException("Invalid output.");
        }

      } catch (IllegalArgumentException exception) {
        try {
          //changed this
          this.viewer.renderMessage("Invalid move. Play again.\n");
        } catch (IOException exception1) {
          throw new IllegalStateException("Invalid output.");
        }
      }
      try {
        //draw the board
        this.viewer.renderBoard();
        //make a new line
        this.viewer.renderMessage("\n");
        //display the score
        this.viewer.renderMessage("Score: " + this.model.getScore() + "\n");
        //this.viewer.renderMessage("\n");
      } catch (IOException e) {
        throw new IllegalStateException("Invalid output.");
      }
      //reset
      legalPlacement = 0;
    }

    //ends the scanner
    scanner.close();

    //if the game is over,
    if (model.isGameOver()) {
      try {
        //"Game over!" message
        this.viewer.renderMessage("Game over!\n");
        //draw the board
        this.viewer.renderBoard();
        //display the score
        this.viewer.renderMessage("Score: " + this.model.getScore());
        return;
        //if unable to render, throw an exception :)
      } catch (IOException e) {
        throw new IllegalStateException("Invalid output.");
      }
    }
  }
}

