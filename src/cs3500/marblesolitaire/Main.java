package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.AbstractMarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * Represents the main class that will host the
 * main method in order for users to play the game.
 */
public final class Main {
  /**
   * Represents a game that can take user inputs
   * to render a game based on said inputs. Users
   * enter one of "english", "european", or "triangular",
   * and this will determine which board shape, model, and view
   * to use. 'N' represents the size of the board, using the
   * default if no size is specified. R and C are numbers, to
   * specify the row and column of the initial hole in the same
   * manner as players of the game enter move positions (default)
   * if not specified. The user can then play the game with the
   * customized game settings.
   *
   * @param args represents what will be taken in by the main method.
   *             Will print the board, score, and play the game.
   */
  public static void main(String[] args) {
    //starting off
    String model = args[0];
    //abstract model to represent any of the three models
    AbstractMarbleSolitaireModel abstractModel;
    //readable to read inputs
    Readable readable = new InputStreamReader(System.in);

    //starting --> invalids
    int armThickness = -1;
    //row of the initial hole
    int rowEmpty = -1;
    //column of the initial hole
    int columnEmpty = -1;

    //iterate through until reach the end of args
    for (int i = 1; i < args.length; i += 2) {
      switch (args[i]) {
        //board size
        case "-size":
          armThickness = Integer.valueOf(args[i + 1]);
          break;
          //empty marb
        case "-hole":
          rowEmpty = Integer.valueOf(args[i + 1]);
          columnEmpty = Integer.valueOf(args[i + 2]);
          i += 1;
          break;
          //empty string
        case "":
          break;
          //if none of those work, uh-oh
        default:
          throw new IllegalArgumentException("Invalid entry.");
      }
    }

    //no arguments --> default
    if (armThickness < 0 && rowEmpty < 0 && columnEmpty < 0) {
      switch (model) {
        case "english":
          abstractModel = new EnglishSolitaireModel();
          //always break to end so that other conditions aren't met
          break;

        case "european":
          abstractModel = new EuropeanSolitaireModel();
          break;

        case "triangle":
          abstractModel = new TriangleSolitaireModel();
          break;

        default:
          throw new IllegalArgumentException("Invalid model provided.");
      }

      //inputted row + column for select empty slot at start
    } else if (armThickness < 0 && rowEmpty > 0 && columnEmpty > 0) {
      switch (model) {

        //ENG model
        case "english":
          abstractModel = new EnglishSolitaireModel(rowEmpty, columnEmpty);
          break;

        //EU model
        case "european":
          abstractModel = new EuropeanSolitaireModel(rowEmpty, columnEmpty);
          break;

          //TRI model
        case "triangle":
          abstractModel = new TriangleSolitaireModel(rowEmpty, columnEmpty);
          break;

        default:
          throw new IllegalArgumentException("Invalid model provided.");
      }

      //constructor w/ just armThickness
    } else if (armThickness > 0 && rowEmpty < 0 && columnEmpty < 0) {
      switch (model) {
        case "english":
          abstractModel = new EnglishSolitaireModel(armThickness);
          break;

        case "european":
          abstractModel = new EuropeanSolitaireModel(armThickness);
          break;

        case "triangle":
          abstractModel = new TriangleSolitaireModel(armThickness);
          break;

          //if none of those work,
        default:
          throw new IllegalArgumentException("Invalid model provided.");
      }
      //otherwise, call constructor w/ three arguments
    } else {
      switch (model) {
        case "english":
          abstractModel = new EnglishSolitaireModel(armThickness, rowEmpty, columnEmpty);
          break;

        case "european":
          abstractModel = new EuropeanSolitaireModel(armThickness, rowEmpty, columnEmpty);
          break;

        case "triangle":
          abstractModel = new TriangleSolitaireModel(armThickness, rowEmpty, columnEmpty);
          break;

          //catch
        default:
          throw new IllegalArgumentException("Invalid model provided.");
      }
    }
    //run 'er back --> model (abstract to represent any model), view using said model, and readable
    new MarbleSolitaireControllerImpl(abstractModel,
            new MarbleSolitaireTextView(abstractModel), readable).playGame();
  }
}

