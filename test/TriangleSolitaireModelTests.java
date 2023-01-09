import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Represents a testing class for Triangle Solitaire Models.
 */
public class TriangleSolitaireModelTests {

  private TriangleSolitaireModel triangleEmpty;
  private MarbleSolitaireModel triangleDimensions;
  private MarbleSolitaireModel triangleRowColumn;
  private MarbleSolitaireModel triangleThreeParameters;

  private MarbleSolitaireModel tinyThree;


  @Before
  public void init() {
    triangleEmpty = new TriangleSolitaireModel();
    triangleDimensions = new TriangleSolitaireModel(6);
    triangleRowColumn = new TriangleSolitaireModel(4, 3);
    triangleThreeParameters = new TriangleSolitaireModel(5, 2, 2);
    tinyThree = new TriangleSolitaireModel(3);

  }

  @Test
  public void testToString() {
    assertEquals("     _\n" +
            "    O O\n" +
            "   O O O\n" +
            "  O O O O\n" +
            " O O O O O\n" +
            "O O O O O O",
            new TriangleSolitaireTextView(new TriangleSolitaireModel(6)).toString());
    assertEquals("        O\n" +
            "       O _\n" +
            "      O O O\n" +
            "     O O O O\n" +
            "    O O O O O\n" +
            "   O O O O O O\n" +
            "  O O O O O O O\n" +
            " O O O O O O O O\n" +
            "O O O O O O O O O",
            new TriangleSolitaireTextView(
                    new TriangleSolitaireModel(9, 1, 1)).toString());
    assertEquals(" _\n" +
            "O O", new TriangleSolitaireTextView(new TriangleSolitaireModel(2)).toString());
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(new TriangleSolitaireModel()).toString());
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O",
            new TriangleSolitaireTextView(new TriangleSolitaireModel(5)).toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O O",
            new TriangleSolitaireTextView(new TriangleSolitaireModel(3, 3)).toString());
    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O _",
            new TriangleSolitaireTextView(new TriangleSolitaireModel(4, 4)).toString());

    try {
      new TriangleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());

    }

    try {
      TriangleSolitaireTextView view =
              new TriangleSolitaireTextView(new TriangleSolitaireModel(4));
      view = null;
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
  }

  @Test
  public void testIllegalConstructors() {
    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(new TriangleSolitaireModel(2, 3)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid starting position.", e.getMessage());
    }

    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(new TriangleSolitaireModel(-1, 0)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid starting position.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" + "O O",
              new TriangleSolitaireTextView(new TriangleSolitaireModel(-1, -1)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid starting position.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(
                      new TriangleSolitaireModel(1, -9)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid starting position.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(
                      new TriangleSolitaireModel(1, 9, -9)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid starting position.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(
                      new TriangleSolitaireModel(-9, -9, -9)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid thickness.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" + "O O",
              new TriangleSolitaireTextView(
                      new TriangleSolitaireModel(-9, 9, 7)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid thickness.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(new TriangleSolitaireModel(-8)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid thickness.", e.getMessage());
    }
    try {
      assertEquals(" _ \n" +
              "O O", new TriangleSolitaireTextView(new TriangleSolitaireModel(0)));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid thickness.", e.getMessage());
    }
  }

  @Test
  public void testScore() {
    assertEquals(14, this.triangleEmpty.getScore());
    assertEquals(20, this.triangleDimensions.getScore());
    assertEquals(14, this.triangleRowColumn.getScore());
    assertEquals(14, this.triangleRowColumn.getScore());
    assertEquals(44, new TriangleSolitaireModel(9).getScore());
    assertEquals(65, new TriangleSolitaireModel(11).getScore());
    assertEquals(35, new TriangleSolitaireModel(8, 4, 4).getScore());
    assertEquals(14, new TriangleSolitaireModel(4, 3).getScore());
    assertEquals(14, new TriangleSolitaireModel().getScore());
  }

  @Test
  public void testGameOver() {
    assertFalse(this.triangleDimensions.isGameOver());
    assertFalse(this.triangleEmpty.isGameOver());
    assertFalse(this.triangleRowColumn.isGameOver());
    assertFalse(this.triangleThreeParameters.isGameOver());
    TriangleSolitaireModel model = new TriangleSolitaireModel(3);
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model);
    assertEquals("  _\n" +
            " O O\n" +
            "O O O", view.toString());
    model.move(2, 2, 0, 0);
    assertEquals("  O\n" +
            " O _\n" +
            "O O _", view.toString());
    assertFalse(model.isGameOver());
    model.move(2, 0, 2, 2);
    assertEquals("  O\n" +
            " O _\n" +
            "_ _ O", view.toString());
    assertFalse(model.isGameOver());
    model.move(0, 0, 2, 0);
    assertEquals("  _\n" +
            " _ _\n" +
            "O _ O", view.toString());
    assertTrue(model.isGameOver());
  }

  @Test
  public void testIllegalMoves() {
    try {
      this.triangleDimensions.move(-1, 0, 1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.triangleDimensions.move(1, -1, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.triangleDimensions.move(1, 2, -1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.triangleDimensions.move(1, 0, 3, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      this.triangleDimensions.move(-1, -1, -1, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }

    try {
      //jumping from an empty marble to a slot WITH a marble
      this.triangleEmpty.move(2, 0, 4, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }

    try {
      //jump from an empty marble, over an empty marble, to an empty marble
      this.triangleEmpty.move(3, 0, 3, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }

    try {
      //jump from a marble, over an empty slot, to a marble
      this.triangleEmpty.move(0, 0, 2, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }

    try {
      //jump from a marble, over a marble, to a marble
      this.triangleEmpty.move(4, 4, 2, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testMove() {

    //moving up + to the left
    this.triangleEmpty.move(2, 2, 0, 0);

    //moving up + to the left
    this.triangleEmpty.move(4, 4, 2, 2);
    assertEquals("    O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O O O _\n" +
            "O O O O _", new TriangleSolitaireTextView(triangleEmpty).toString());
    //left
    this.triangleEmpty.move(3, 1, 3, 3);
    assertEquals("    " +
            "O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "O O O O _", new TriangleSolitaireTextView(triangleEmpty).toString());

    //right
    this.triangleEmpty.move(4, 2, 4, 4);
    assertEquals("    " +
            "O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "O O _ _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    //right
    this.triangleEmpty.move(4, 0, 4, 2);
    assertEquals("    " +
            "O\n" +
            "   O _\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "_ _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());

    //down and to the left
    this.triangleEmpty.move(2, 0, 4, 0);
    assertEquals("    " +
            "O\n" +
            "   O _\n" +
            "  _ O O\n" +
            " _ _ _ O\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    //up and to the left
    this.triangleEmpty.move(3, 3, 1, 1);
    assertEquals("    " +
            "O\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    //down and to the right
    this.triangleEmpty.move(0, 0, 2, 2);
    assertEquals("    " +
            "_\n" +
            "   O _\n" +
            "  _ O O\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    //left
    this.triangleEmpty.move(2, 2, 2, 0);
    assertEquals("    " +
            "_\n" +
            "   O _\n" +
            "  O _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    //up and to the right
    this.triangleEmpty.move(2, 0, 0, 0);
    assertEquals("    " +
            "O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    assertTrue(this.triangleEmpty.isGameOver());

  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.triangleDimensions.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            new TriangleSolitaireModel(3, 3).getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.triangleDimensions.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.triangleDimensions.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.triangleDimensions.getSlotAt(5, 4));
  }


  @Test
  public void testIllegalGetSlotAt() {
    try {
      this.triangleDimensions.getSlotAt(9, 0);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index 9 out of bounds for length 6", e.getMessage());
    }
    try {
      this.triangleDimensions.getSlotAt(4, 9);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index 9 out of bounds for length 6", e.getMessage());
    }
    try {
      this.triangleDimensions.getSlotAt(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.triangleDimensions.getSlotAt(9, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.triangleDimensions.getSlotAt(-1, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.triangleDimensions.getSlotAt(9, 7);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index 9 out of bounds for length 6", e.getMessage());
    }
  }

  @Test
  public void testNullTextViewConstructors() {
    try {
      TriangleSolitaireModel nullTri = new TriangleSolitaireModel();
      nullTri = null;
      new TriangleSolitaireTextView((nullTri));
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      TriangleSolitaireModel nullTri = new TriangleSolitaireModel();
      nullTri = null;
      new TriangleSolitaireTextView(nullTri, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() throws IOException {
    MarbleSolitaireTextView view =
            new MarbleSolitaireTextView(triangleEmpty, new CorruptAppendable());
    try {
      view.renderBoard();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }
    MarbleSolitaireTextView view02 =
            new MarbleSolitaireTextView(triangleRowColumn, new CorruptAppendable());
    try {
      view02.renderBoard();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }

    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame =
            new MarbleSolitaireTextView(triangleThreeParameters, appendable);
    viewGame.renderBoard();
    assertEquals("O\n" +
            "O O\n" +
            "O O _\n" +
            "O O O O\n" +
            "O O O O O", viewGame.toString());

    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(triangleDimensions, appendable);
    viewGame.renderBoard();
    assertEquals("_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "O O O O O O", viewGame2.toString());

  }

  @Test
  public void testRenderMessage() throws IOException {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(triangleEmpty, appendable);
    viewGame.renderMessage("HEYYY ");
    assertEquals("HEYYY ", appendable.toString());
    viewGame.renderMessage("lovely OOD TA ");
    viewGame.renderMessage("so happy you're here :))");
    assertEquals("HEYYY lovely OOD TA so happy you're here :))", appendable.toString());
  }

  @Test
  public void testQuit() {

    Readable readable2 = new StringReader("q");
    Appendable appendable2 = new StringBuilder();
    TriangleSolitaireTextView viewGame2 =
            new TriangleSolitaireTextView(triangleDimensions, appendable2);
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(triangleDimensions,
            viewGame2, readable2);
    controller2.playGame();
    assertEquals("_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "O O O O O O\n" +
            "Score:20\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "O O O O O O\n" +
            "Score: 20", appendable2.toString());
    //fromRow quit
    Readable readable = new StringReader("2 4 4 4 5 4 3 4 Q");
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(triangleRowColumn, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(triangleRowColumn,
            viewGame, readable);
    controller.playGame();
    assertEquals("O\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O _ O\n" +
            "Score:14\n" +
            "Invalid move. Play again.\n" +
            "O\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O _ O\n" +
            "\n" +
            "Score: 14\n" +
            "Invalid move. Play again.\n" +
            "O\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O _ O\n" +
            "\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "O\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O _ O\n" +
            "Score: 14", appendable.toString());

    //fromCol quit
    Readable readable10 = new StringReader("2 Q");
    Appendable appendable10 = new StringBuilder();
    MarbleSolitaireTextView viewGame10 = new MarbleSolitaireTextView(triangleEmpty, appendable10);
    MarbleSolitaireController controller10 = new MarbleSolitaireControllerImpl(triangleEmpty,
            viewGame10, readable10);
    controller10.playGame();
    assertEquals("_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "Score:14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "_\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "Score: 14", appendable10.toString());
    //toRow quit
    Readable readable11 = new StringReader("2 2 Q");
    Appendable appendable11 = new StringBuilder();
    MarbleSolitaireTextView viewGame11 =
            new MarbleSolitaireTextView(triangleRowColumn, appendable11);
    MarbleSolitaireController controller11 = new MarbleSolitaireControllerImpl(triangleRowColumn,
            viewGame11, readable11);
    controller11.playGame();
    assertEquals("O\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O _ O\n" +
            "Score:14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "O\n" +
            "O O\n" +
            "O O O\n" +
            "O O O O\n" +
            "O O O _ O\n" +
            "Score: 14", appendable11.toString());

    //toCol quit
    Readable readable12 = new StringReader("2 2 2 Q");
    Appendable appendable12 = new StringBuilder();
    MarbleSolitaireTextView viewGame12 =
            new MarbleSolitaireTextView(triangleThreeParameters, appendable12);
    MarbleSolitaireController controller12 =
            new MarbleSolitaireControllerImpl(triangleThreeParameters,
            viewGame12, readable12);
    controller12.playGame();
    assertEquals("O\n" +
            "O O\n" +
            "O O _\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "Score:14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "O\n" +
            "O O\n" +
            "O O _\n" +
            "O O O O\n" +
            "O O O O O\n" +
            "Score: 14", appendable12.toString());

    try {
      Readable readable3 = new StringReader("2 4 4 4 5 4 3 4 Mommy");
      Appendable appendable3 = new StringBuilder();
      MarbleSolitaireTextView viewGame3 =
              new MarbleSolitaireTextView(triangleThreeParameters, appendable3);
      MarbleSolitaireController controller3 =
              new MarbleSolitaireControllerImpl(triangleThreeParameters,
              viewGame3, readable3);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Scanner has no more values.", e.getMessage());
    }

    try {
      Readable readable3 = new StringReader("2 4 4 4 5 4 3 4");
      Appendable appendable3 = new CorruptAppendable();
      MarbleSolitaireTextView viewGame3 = new MarbleSolitaireTextView(triangleEmpty, appendable3);
      MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(triangleEmpty,
              viewGame3, readable3);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }
  }


  @Test
  public void testPutMarble() {
    this.triangleEmpty.move(2, 2, 0, 0);
    this.triangleEmpty.move(4, 4, 2, 2);
    this.triangleEmpty.move(3, 1, 3, 3);
    this.triangleEmpty.move(4, 2, 4, 4);
    this.triangleEmpty.move(4, 0, 4, 2);
    this.triangleEmpty.move(2, 0, 4, 0);
    this.triangleEmpty.move(3, 3, 1, 1);
    this.triangleEmpty.move(0, 0, 2, 2);
    this.triangleEmpty.move(2, 2, 2, 0);
    this.triangleEmpty.move(2, 0, 0, 0);
    assertEquals("    " +
            "O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    this.triangleEmpty.putMarble(1, 0);
    this.triangleEmpty.putMarble(1, 1);
    this.triangleEmpty.putMarble(2, 2);
    assertEquals("    " +
            "O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    this.triangleEmpty.putMarble(2, 0);
    this.triangleEmpty.putMarble(2, 1);
    this.triangleEmpty.putMarble(3, 2);
    assertEquals("    " +
            "O\n" +
            "   O O\n" +
            "  O O O\n" +
            " _ _ O _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
  }

  @Test
  public void putIllegalMarble() {
    this.triangleEmpty.move(2, 2, 0, 0);
    this.triangleEmpty.move(4, 4, 2, 2);
    this.triangleEmpty.move(3, 1, 3, 3);
    this.triangleEmpty.move(4, 2, 4, 4);
    this.triangleEmpty.move(4, 0, 4, 2);
    this.triangleEmpty.move(2, 0, 4, 0);
    this.triangleEmpty.move(3, 3, 1, 1);
    this.triangleEmpty.move(0, 0, 2, 2);
    this.triangleEmpty.move(2, 2, 2, 0);
    this.triangleEmpty.move(2, 0, 0, 0);
    assertEquals("    " +
            "O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "O _ O _ O", new TriangleSolitaireTextView(triangleEmpty).toString());
    try {
      this.triangleEmpty.putMarble(0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Position is not empty.", e.getMessage());
    }
    try {
      this.triangleEmpty.putMarble(-1, 0);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index -1 out of bounds for length 5", e.getMessage());
    }
    try {
      this.triangleEmpty.putMarble(0, -1);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index -1 out of bounds for length 5", e.getMessage());
    }
  }

  @Test
  public void testMoveFound() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", new TriangleSolitaireTextView(triangleEmpty).toString());
    assertFalse(triangleEmpty.moveFound(1, 1));
    assertTrue(triangleEmpty.moveFound(2, 2));

    try {
      this.triangleEmpty.moveFound(0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("No move allowed.", e.getMessage());
    }
    try {
      this.triangleEmpty.moveFound(4, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("No move allowed.", e.getMessage());
    }
  }

  @Test
  public void testMoveAllowed() {
    TriangleSolitaireModel model = new TriangleSolitaireModel(3);
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model);
    assertEquals(
            "  _\n" +
                    " O O\n" +
                    "O O O", view.toString());

    assertTrue(model.moveAllowed(2, 2, 0, 0));
    assertFalse(model.moveAllowed(2, 0, 0, 2));

    model.move(2, 2, 0, 0);
    assertEquals("  O\n" +
            " O _\n" +
            "O O _", view.toString());

    assertFalse(model.moveAllowed(1, 0, 1, 1));
    assertTrue(model.moveAllowed(2, 0, 2, 2));
    model.move(2, 0, 2, 2);
    assertEquals("  O\n" +
            " O _\n" +
            "_ _ O", view.toString());
    model.move(0, 0, 2, 0);
    assertEquals("  _\n" +
            " _ _\n" +
            "O _ O", view.toString());

  }

  @Test
  public void testBoardSize() {
    assertEquals(6, this.triangleDimensions.getBoardSize());
    assertEquals(5, this.triangleEmpty.getBoardSize());
    assertEquals(5, this.triangleRowColumn.getBoardSize());
    assertEquals(3, this.tinyThree.getBoardSize());
  }
}


