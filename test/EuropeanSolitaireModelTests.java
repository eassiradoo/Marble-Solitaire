import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Represents a testing class for European Solitaire Models.
 */
public class EuropeanSolitaireModelTests {

  private Appendable appendable;

  private MarbleSolitaireModel euroEmpty;

  private MarbleSolitaireModel euroArmThickness;

  private MarbleSolitaireModel euroThreeParameters;

  private MarbleSolitaireModel euroRowColumn;
  private MarbleSolitaireView euroView;
  private MarbleSolitaireView euroViewTwo;

  private EuropeanSolitaireModel model;


  @Before
  public void init() {
    appendable = new StringBuilder();
    euroEmpty = new EuropeanSolitaireModel();
    euroArmThickness = new EuropeanSolitaireModel(5);
    euroRowColumn = new EuropeanSolitaireModel(4, 4);
    euroThreeParameters = new EuropeanSolitaireModel(3, 3, 3);
    model = new EuropeanSolitaireModel(3);


    euroView = new EuropeanSolitaireTextView(euroThreeParameters);
    euroViewTwo = new EuropeanSolitaireTextView(euroThreeParameters, appendable);
  }

  @Test
  public void testNull() {
    try {
      euroThreeParameters = null;
      euroViewTwo.toString();
    } catch (IllegalArgumentException e) {
      assertEquals(null, e.getMessage());
    }
    try {
      appendable = null;
      euroViewTwo.toString();
    } catch (IllegalArgumentException e) {
      assertEquals(null, e.getMessage());
    }
    try {
      euroViewTwo = null;
      euroViewTwo.toString();
    } catch (NullPointerException e) {
      assertEquals(null, e.getMessage());

    }
  }

  @Test
  public void testToString() {
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", euroView.toString());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(new EuropeanSolitaireModel()).toString());
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", new EuropeanSolitaireTextView(
                    new EuropeanSolitaireModel(5)).toString());
    assertEquals("            O O O O O O O\n" +
            "          O O O O O O O O O\n" +
            "        O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O\n" +
            "          O O O O O O O O O\n" +
            "            O O O O O O O",
            new EuropeanSolitaireTextView(new EuropeanSolitaireModel(7)).toString());

  }

  @Test
  public void testConstructors() {
    assertEquals(36, this.euroThreeParameters.getScore());
    assertFalse(this.euroThreeParameters.isGameOver());
    assertEquals(7, this.euroThreeParameters.getBoardSize());

    assertEquals(36, this.euroRowColumn.getScore());
    assertFalse(this.euroRowColumn.isGameOver());
    assertEquals(7, this.euroRowColumn.getBoardSize());

    assertEquals(128, this.euroArmThickness.getScore());
    assertFalse(this.euroArmThickness.isGameOver());
    assertEquals(13, this.euroArmThickness.getBoardSize());

    assertEquals(36, this.euroEmpty.getScore());
    assertFalse(this.euroEmpty.isGameOver());
    assertEquals(7, this.euroEmpty.getBoardSize());

  }

  @Test
  public void testInvalidConstructors() {
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(10));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid armThickness.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(0));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid armThickness.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(-1));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid armThickness.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(-1, 1));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column position.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(1, -1));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column position.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(-1, -1, -1));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid armThickness.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(-1, 4, 4));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid armThickness.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(3, -1, 3));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column position.", e.getMessage());
    }
    try {
      new EuropeanSolitaireTextView(new EuropeanSolitaireModel(5, 3, -1));
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column position.", e.getMessage());
    }
  }

  @Test
  public void testNullConstructors() {
    try {
      euroView = new EuropeanSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }

    try {
      euroView = new EuropeanSolitaireTextView(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }

    try {
      euroView = new EuropeanSolitaireTextView(euroEmpty, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }

    try {
      euroView = new EuropeanSolitaireTextView(null, appendable);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() throws IOException {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(euroEmpty, appendable);
    viewGame.renderBoard();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", viewGame.toString());

    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(euroArmThickness, appendable2);
    viewGame2.renderBoard();
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", viewGame2.toString());

    Appendable appendable3 = new StringBuilder();
    MarbleSolitaireTextView viewGame3 =
            new MarbleSolitaireTextView(euroThreeParameters, appendable3);
    viewGame3.renderBoard();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", viewGame3.toString());

    Appendable appendable4 = new StringBuilder();
    MarbleSolitaireTextView viewGame4 = new MarbleSolitaireTextView(euroRowColumn, appendable4);
    viewGame4.renderBoard();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "  O O O O O\n" +
            "    O O O", viewGame4.toString());
  }

  @Test
  public void testRenderMessage() throws IOException {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(euroRowColumn, appendable);
    viewGame.renderMessage("HEYYY");
    assertEquals("HEYYY", appendable.toString());

    viewGame.renderMessage(" cutie");
    assertEquals("HEYYY cutie", appendable.toString());

    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(euroEmpty, appendable2);
    viewGame2.renderMessage("Live laugh love");
    assertEquals("Live laugh love", appendable2.toString());
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", viewGame2.toString());
  }

  @Test
  public void testBoardSize() {
    assertEquals(13, this.euroArmThickness.getBoardSize());
    assertEquals(7, this.euroRowColumn.getBoardSize());
    assertEquals(7, this.euroEmpty.getBoardSize());
    assertEquals(7, this.euroThreeParameters.getBoardSize());
  }

  @Test
  public void testScore() {
    assertEquals(128, this.euroArmThickness.getScore());
    assertEquals(36, this.euroEmpty.getScore());
    assertEquals(36, this.euroThreeParameters.getScore());
    assertEquals(36, this.euroRowColumn.getScore());

  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.euroArmThickness.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            this.euroArmThickness.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            this.euroArmThickness.getSlotAt(5, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            this.euroArmThickness.getSlotAt(6, 6));
  }

  @Test
  public void testMove() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel(3);
    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "  O O O O O\n" +
                    "    O O O", new EuropeanSolitaireTextView(model).toString());
    //down
    model.move(1, 3, 3, 3);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());
    //left
    model.move(2, 5, 2, 3);
    assertEquals("    O O O\n" +
            "  O O _ O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());

    //right
    model.move(1, 1, 1, 3);
    assertEquals("    O O O\n" +
            "  _ _ O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(1, 4, 1, 2);
    assertEquals("    O O O\n" +
            "  _ O _ _ O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());

    //up
    model.move(4, 5, 2, 5);
    assertEquals("    O O O\n" +
            "  _ O _ _ O\n" +
            "O O O O _ O O\n" +
            "O O O O O _ O\n" +
            "O O O O O _ O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());

    //up
    model.move(4, 4, 2, 4);
    assertEquals("    O O O\n" +
            "  _ O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O _ _ O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());

    //up
    model.move(3, 3, 1, 3);
    assertEquals("    O O O\n" +
            "  _ O O _ O\n" +
            "O O O _ O O O\n" +
            "O O O _ _ _ O\n" +
            "O O O O _ _ O\n" +
            "  O O O O O\n" +
            "    O O O", new EuropeanSolitaireTextView(model).toString());

    //up
    model.move(6, 4, 4, 4);
    assertEquals("    O O O\n" +
            "  _ O O _ O\n" +
            "O O O _ O O O\n" +
            "O O O _ _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    O O _", new EuropeanSolitaireTextView(model).toString());
    //right
    model.move(6, 2, 6, 4);
    assertEquals("    O O O\n" +
            "  _ O O _ O\n" +
            "O O O _ O O O\n" +
            "O O O _ _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(1, 3, 1, 1);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O O O _ O O O\n" +
            "O O O _ _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //right
    model.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O O O _ O O O\n" +
            "O _ _ O _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //right
    model.move(2, 1, 2, 3);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ _ O O O O\n" +
            "O _ _ O _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(2, 4, 2, 2);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ _ O O\n" +
            "O _ _ O _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(2, 6, 2, 4);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O O O O O _ O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //right
    model.move(4, 3, 4, 5);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O O O _ _ O O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //right
    model.move(4, 1, 4, 3);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ O _ O O\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(4, 6, 4, 4);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ O O _ _\n" +
            "  O O O _ O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(5, 2, 5, 4);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ _ O O _ _\n" +
            "  O _ _ O O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    //left
    model.move(4, 4, 4, 2);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ O O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());

    assertFalse(model.isGameOver());
    model.move(5, 5, 5, 3);
    assertEquals("    O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ O _ _\n" +
            "    _ _ O", new EuropeanSolitaireTextView(model).toString());
    assertTrue(model.isGameOver());


  }

  @Test
  public void testPutMarble() {
    this.model.move(1, 3, 3, 3);
    this.model.move(2, 5, 2, 3);
    this.model.move(1, 1, 1, 3);
    this.model.move(1, 4, 1, 2);
    this.model.move(4, 5, 2, 5);
    this.model.move(4, 4, 2, 4);
    this.model.move(3, 3, 1, 3);
    this.model.move(6, 4, 4, 4);
    this.model.move(6, 2, 6, 4);
    this.model.move(1, 3, 1, 1);
    this.model.move(3, 1, 3, 3);
    this.model.move(2, 1, 2, 3);
    this.model.move(2, 4, 2, 2);
    this.model.move(2, 6, 2, 4);
    this.model.move(4, 3, 4, 5);
    this.model.move(4, 1, 4, 3);
    this.model.move(4, 6, 4, 4);
    this.model.move(5, 2, 5, 4);
    this.model.move(4, 4, 4, 2);
    assertFalse(this.model.isGameOver());
    assertEquals("    " +
            "O O O\n" +
            "  O _ _ _ O\n" +
            "O _ O _ O _ _\n" +
            "O _ _ O _ _ O\n" +
            "O _ O _ _ _ _\n" +
            "  O _ _ O O\n" +
            "    _ _ O", new EuropeanSolitaireTextView(this.model).toString());
    this.model.putMarble(1, 2);
    this.model.putMarble(2, 1);
    this.model.putMarble(5, 2);
    assertEquals(
            "    O O O\n" +
                    "  O O _ _ O\n" +
                    "O O O _ O _ _\n" +
                    "O _ _ O _ _ O\n" +
                    "O _ O _ _ _ _\n" +
                    "  O O _ O O\n" +
                    "    _ _ O", new EuropeanSolitaireTextView(this.model).toString());
    this.model.putMarble(1, 3);
    this.model.putMarble(1, 4);
    this.model.putMarble(5, 3);
    assertEquals(
            "    O O O\n" +
                    "  O O O O O\n" +
                    "O O O _ O _ _\n" +
                    "O _ _ O _ _ O\n" +
                    "O _ O _ _ _ _\n" +
                    "  O O O O O\n" +
                    "    _ _ O", new EuropeanSolitaireTextView(this.model).toString());
  }

  @Test
  public void testMoveFound() {
    this.model.move(1, 3, 3, 3);
    this.model.move(2, 5, 2, 3);
    this.model.move(1, 1, 1, 3);
    this.model.move(1, 4, 1, 2);
    this.model.move(4, 5, 2, 5);
    this.model.move(4, 4, 2, 4);
    this.model.move(3, 3, 1, 3);
    assertEquals("    " +
            "O O O\n" +
            "  _ O O _ O\n" +
            "O O O _ O O O\n" +
            "O O O _ _ _ O\n" +
            "O O O O _ _ O\n" +
            "  O O O O O\n" +
            "    O O O", new MarbleSolitaireTextView(this.model).toString());
    assertTrue(this.model.moveFound(1, 2));
    assertFalse(this.model.moveFound(0, 2));
    assertFalse(this.model.moveFound(6, 3));

    try {
      this.model.moveFound(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Rows and columns cannot be negative.", e.getMessage());
    }
    try {
      this.model.moveFound(1, -9);
    } catch (IllegalArgumentException e) {
      assertEquals("Rows and columns cannot be negative.", e.getMessage());
    }
    try {
      this.model.moveFound(-1, -2);
    } catch (IllegalArgumentException e) {
      assertEquals("Rows and columns cannot be negative.", e.getMessage());
    }
    try {
      this.model.moveFound(9, 0);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index 9 out of bounds for length 7", e.getMessage());
    }
    try {
      this.model.moveFound(7, 9);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index 7 out of bounds for length 7", e.getMessage());
    }
    try {
      this.model.moveFound(0, 11);
    } catch (IndexOutOfBoundsException e) {
      assertEquals("Index 11 out of bounds for length 7", e.getMessage());
    }
  }

  @Test
  public void testMoveAllowed() {
    this.model.move(1, 3, 3, 3);
    this.model.move(2, 5, 2, 3);
    this.model.move(1, 1, 1, 3);
    this.model.move(1, 4, 1, 2);
    this.model.move(4, 5, 2, 5);
    this.model.move(4, 4, 2, 4);
    assertEquals("    " +
            "O O O\n" +
            "  _ O _ _ O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O _ _ O\n" +
            "  O O O O O\n" +
            "    O O O", new MarbleSolitaireTextView(this.model).toString());
    assertFalse(this.model.moveAllowed(0, 0, 2, 0));
    assertFalse(this.model.moveAllowed(4, 4, 2, 4));
    assertTrue(this.model.moveAllowed(3, 2, 3, 4));
    assertTrue(this.model.moveAllowed(1, 5, 3, 5));

    try {
      this.model.moveAllowed(-1, 2, 1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.model.moveAllowed(1, -2, 1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.model.moveAllowed(1, 2, -1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.model.moveAllowed(1, 2, 1, -2);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }

  }


  @Test
  public void testGameOver() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel(3);
    assertFalse(model.isGameOver());
    assertFalse(this.euroArmThickness.isGameOver());
    model.move(1, 3, 3, 3);
    model.move(2, 5, 2, 3);
    model.move(1, 1, 1, 3);
    model.move(1, 4, 1, 2);
    model.move(4, 5, 2, 5);
    model.move(4, 4, 2, 4);
    model.move(3, 3, 1, 3);
    model.move(6, 4, 4, 4);
    model.move(6, 2, 6, 4);
    model.move(1, 3, 1, 1);
    model.move(3, 1, 3, 3);
    model.move(2, 1, 2, 3);
    model.move(2, 4, 2, 2);
    model.move(2, 6, 2, 4);
    model.move(4, 3, 4, 5);
    model.move(4, 1, 4, 3);
    model.move(4, 6, 4, 4);
    model.move(5, 2, 5, 4);
    model.move(4, 4, 4, 2);
    assertFalse(model.isGameOver());
    model.move(5, 5, 5, 3);
    assertTrue(model.isGameOver());

  }

  @Test
  public void testPlayGame() {
    Readable readable2 = new StringReader("q");
    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(euroArmThickness, appendable2);
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(euroArmThickness,
            viewGame2, readable2);
    controller2.playGame();
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score:128\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score: 128", appendable2.toString());


    //fromRow quit
    Readable readable = new StringReader("2 4 4 4 5 4 3 4 Q");
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(euroEmpty, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(euroEmpty,
            viewGame, readable);
    controller.playGame();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score:36\n" +
            "\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "\n" +
            "Score: 35\n" +
            "\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34", appendable.toString());

    //fromCol quit
    Readable readable10 = new StringReader("2 Q");
    Appendable appendable10 = new StringBuilder();
    MarbleSolitaireTextView viewGame10 =
            new MarbleSolitaireTextView(euroThreeParameters, appendable10);
    MarbleSolitaireController controller10 = new MarbleSolitaireControllerImpl(euroThreeParameters,
            viewGame10, readable10);
    controller10.playGame();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score:36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", appendable10.toString());
    //toRow quit
    Readable readable11 = new StringReader("2 2 Q");
    Appendable appendable11 = new StringBuilder();
    MarbleSolitaireTextView viewGame11 =
            new MarbleSolitaireTextView(euroThreeParameters, appendable11);
    MarbleSolitaireController controller11 = new MarbleSolitaireControllerImpl(euroThreeParameters,
            viewGame11, readable11);
    controller11.playGame();
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score:36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36", appendable11.toString());

    //toCol quit
    Readable readable12 = new StringReader("2 2 2 Q");
    Appendable appendable12 = new StringBuilder();
    MarbleSolitaireTextView viewGame12 =
            new MarbleSolitaireTextView(euroArmThickness, appendable12);
    MarbleSolitaireController controller12 = new MarbleSolitaireControllerImpl(euroArmThickness,
            viewGame12, readable12);
    controller12.playGame();
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score:128\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O\n" +
            "Score: 128", appendable12.toString());

    try {
      Readable readable3 = new StringReader("2 4 4 4 5 4 3 4 Mommy");
      Appendable appendable3 = new StringBuilder();
      MarbleSolitaireTextView viewGame3 = new MarbleSolitaireTextView(euroRowColumn, appendable3);
      MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(euroRowColumn,
              viewGame3, readable3);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Scanner has no more values.", e.getMessage());
    }

    try {
      Readable readable3 = new StringReader("2 4 4 4 5 4 3 4");
      Appendable appendable3 = new CorruptAppendable();
      MarbleSolitaireTextView viewGame3 = new MarbleSolitaireTextView(euroEmpty, appendable3);
      MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(euroEmpty,
              viewGame3, readable3);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }
  }
}
