import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Represents a testing class.
 */
public class EnglishSolitaireModelTests {

  //ENGLISH SOLITAIRE MODELS
  private EnglishSolitaireModel eng1;
  private EnglishSolitaireModel eng2;
  private EnglishSolitaireModel eng3;
  private EnglishSolitaireModel english333;
  private EnglishSolitaireModel english5;
  private EnglishSolitaireModel helper;
  private EnglishSolitaireModel zeroTwo;
  private EnglishSolitaireModel empty44;
  private EnglishSolitaireModel empty22;


  //MARBLE SOLITAIRE MODELS
  private MarbleSolitaireModel model;

  //MARBLE SOLITAIRE TEXT VIEWS
  private MarbleSolitaireTextView view;
  private MarbleSolitaireTextView view3;
  private MarbleSolitaireTextView view2;
  //private MarbleSolitaireTextView view02;

  private Appendable object;

  private Readable readable;

  private MarbleSolitaireControllerImpl controllerz;


  @Before
  public void init() {
    eng1 = new EnglishSolitaireModel();
    eng2 = new EnglishSolitaireModel(3, 3);
    eng3 = new EnglishSolitaireModel(3);
    english333 = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(english333);
    english5 = new EnglishSolitaireModel(5);
    empty44 = new EnglishSolitaireModel(4, 4);
    empty22 = new EnglishSolitaireModel(2, 2);
    helper = new EnglishSolitaireModel(3, 3, 3);
    view2 = new MarbleSolitaireTextView(helper);
    zeroTwo = new EnglishSolitaireModel(0, 2);
    // view02 = new MarbleSolitaireTextView(zeroTwo);
    view3 = new MarbleSolitaireTextView(empty44);
    object = new StringBuilder();
    readable = new StringReader("");
    controllerz = new MarbleSolitaireControllerImpl(english5, view, readable);

  }


  @Test
  public void isGameOver() throws Exception {
    assertFalse(this.eng2.isGameOver());
    assertFalse(this.english333.isGameOver());
    assertFalse(this.eng1.isGameOver());
    this.english333.move(1, 3, 3, 3);
    this.english333.move(4, 3, 2, 3);
    this.english333.move(6, 3, 4, 3);
    this.english333.move(3, 5, 3, 3);
    this.english333.move(3, 2, 3, 4);
    this.english333.move(3, 0, 3, 2);
    assertFalse(this.english333.moveFound(3, 3));
    assertTrue(this.english333.isGameOver());
    assertEquals(false, this.zeroTwo.isGameOver());
    this.zeroTwo.move(0, 4, 0, 2);
    try {
      this.zeroTwo.move(0, 4, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testPutMarbleAndMoveFound() {
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view.toString());
    assertTrue(this.english333.moveFound(3, 1));
    assertFalse(this.english333.moveFound(3, 3));
    this.english333.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view.toString());
    assertTrue(this.english333.moveFound(3, 4));
    assertTrue(this.english333.moveFound(5, 2));
    this.english333.move(5, 2, 3, 2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    assertTrue(this.english333.moveFound(4, 0));
    this.english333.move(3, 3, 3, 1);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O _ _ O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    this.english333.putMarble(3, 2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    this.english333.putMarble(3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    this.english333.putMarble(4, 2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    assertTrue(this.english333.moveFound(3, 2));
    this.english333.putMarble(5, 2);
    assertFalse(this.english333.moveFound(3, 2));
    assertFalse(this.english333.moveFound(2, 2));
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view.toString());


    try {
      this.english5.moveFound(-1, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Rows and columns cannot be negative.", e.getMessage());
    }

    try {
      this.english333.putMarble(3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Position is not empty.", e.getMessage());
    }

    try {
      this.eng1.putMarble(3, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Position is not empty.", e.getMessage());
    }

    try {
      this.empty44.putMarble(4, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Position is not empty.", e.getMessage());
    }
  }

  @Test
  public void testInvalidSlot3Args() {
    try {
      new EnglishSolitaireModel(3, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Given position is out of bounds.", e.getMessage());
    }
  }

  @Test
  public void testMoveFoundException() {
    try {
      this.english5.moveFound(-2, -2);
    } catch (IllegalArgumentException e) {
      assertEquals("Rows and columns cannot be negative.", e.getMessage());
    }
    try {
      this.english5.moveFound(-1, -1);
    } catch (IllegalArgumentException e) {
      assertEquals("Rows and columns cannot be negative.", e.getMessage());
    }
  }


  @Test
  public void getBoardSize() {
    assertEquals(7, this.english333.getBoardSize());
    assertEquals(7, this.eng1.getBoardSize());
    assertEquals(13, this.english5.getBoardSize());
    assertEquals(7, this.eng2.getBoardSize());
    assertEquals(7, this.eng3.getBoardSize());
    assertEquals(19, new EnglishSolitaireModel(7).getBoardSize());
    assertEquals(25, new EnglishSolitaireModel(9).getBoardSize());
    assertEquals(301, new EnglishSolitaireModel(101).getBoardSize());
  }

  @Test
  public void getSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.english333.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.english333.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.english333.getSlotAt(4, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.eng1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.eng1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            new EnglishSolitaireModel(17).getSlotAt(9, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            new EnglishSolitaireModel(17).getSlotAt(6, 6));

    try {
      this.eng1.getSlotAt(0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column", e.getMessage());
    }
    try {
      this.empty44.getSlotAt(1, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column", e.getMessage());
    }
    try {
      new EnglishSolitaireModel(15).getSlotAt(9, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column", e.getMessage());
    }
  }


  @Test
  public void testMove() {
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view.toString());
    english333.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view.toString());
    english333.move(5, 2, 3, 2);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    english333.move(3, 3, 3, 1);
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O _ _ O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    //move from above
    english333.move(1, 2, 3, 2);
    assertEquals("    O O O\n" +
            "    _ O O\n" +
            "O O _ O O O O\n" +
            "O O O _ O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O", this.view.toString());
    //trying to jump over an empty space
    try {
      english333.move(3, 2, 1, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", new MarbleSolitaireTextView(english5).toString());
    english5.move(6, 4, 6, 6);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", new MarbleSolitaireTextView(english5).toString());
    english5.putMarble(6, 4);
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O _ O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", new MarbleSolitaireTextView(english5).toString());

    try {
      this.english5.move(1, 3, 3, 3);
      //DIAGONAL
      this.english5.move(3, 1, 2, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      english333.move(4, 4, 4, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      eng3.move(0, 0, 2, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
    try {
      eng3.move(4, 4, 7, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Move cannot be made.", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", this.view2.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O _ O O\n" +
            "    O O O\n" +
            "    O O O", this.view3.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", new MarbleSolitaireTextView(empty22).toString());
    assertEquals("        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O _ O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "O O O O O O O O O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O\n" +
                    "        O O O O O",
            new MarbleSolitaireTextView(new EnglishSolitaireModel(5)).toString());
    try {
      new MarbleSolitaireTextView(new EnglishSolitaireModel(0, 0));
    } catch (IllegalArgumentException e) {
      assertEquals("Given position is out of bounds.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(new EnglishSolitaireModel(9, 9));
    } catch (IllegalArgumentException e) {
      assertEquals("Given position is out of bounds.", e.getMessage());
    }
  }


  @Test(expected = IllegalArgumentException.class)
  public void testMoveIllegal() {
    this.eng1.move(3, 3, 3, 6);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveIllegal2() {
    this.eng2.move(10, 10, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveIllegal3() {
    this.eng3.move(0, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarblePlacementIllegal() {
    this.model = new EnglishSolitaireModel(-10, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarblePlacementIllegal2() {
    this.model = new EnglishSolitaireModel(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMarblePlacementIllegal3() {
    this.model = new EnglishSolitaireModel(-2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testArmThicknessIllegal() {
    this.model = new EnglishSolitaireModel(-9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testArmThicknessIllegal2() {
    this.model = new EnglishSolitaireModel(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testArmThicknessIllegal3() {
    this.eng1 = new EnglishSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllVariablesIllegal() {
    this.model = new EnglishSolitaireModel(-4, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllVariablesIllegal2() {
    this.model = new EnglishSolitaireModel(-3, 3, 3);
  }

  @Test
  public void testGetScore() {
    assertEquals(32, this.english333.getScore());
    this.english333.move(3, 1, 3, 3);
    assertEquals(31, this.english333.getScore());
    this.english333.move(5, 2, 3, 2);
    assertEquals(30, english333.getScore());

    assertEquals(32, this.empty44.getScore());
    assertEquals(104, this.english5.getScore());
    assertEquals(1376, new EnglishSolitaireModel(17).getScore());
    assertEquals(792, new EnglishSolitaireModel(13).getScore());
  }

  @Test
  public void testMoveAllowed() {
    assertTrue(this.english333.moveAllowed(3, 1, 3, 3));
    assertFalse(this.helper.moveAllowed(3, 3, 3, 3));
    assertFalse(this.english5.moveAllowed(4, 4, 4, 4));
    assertFalse(this.empty44.moveAllowed(2, 3, 4, 2));

    try {
      this.english5.moveAllowed(-1, 0, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
    try {
      this.english5.moveAllowed(4, 4, 4, 4);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid row or column.", e.getMessage());
    }
  }

  @Test
  public void testRenderBoard() throws Exception {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(english333, appendable);
    viewGame.renderBoard();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", viewGame.toString());

    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(english5, appendable);
    viewGame.renderBoard();
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", viewGame2.toString());

  }


  @Test
  public void testRenderMessage() throws Exception {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(english333, appendable);
    viewGame.renderMessage("HEYYY");
    assertEquals("HEYYY", appendable.toString());

    viewGame.renderMessage(" cutie");
    assertEquals("HEYYY cutie", appendable.toString());

    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(english333, appendable2);
    viewGame2.renderMessage("Live laugh love");
    assertEquals("Live laugh love", appendable2.toString());
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", viewGame2.toString());
  }


  @Test
  public void testGame() {
    readable = new StringReader("2 4 4 4 5 4 3 4 7 4 5 4 4 6 4 4 4 3 4 5 4 1 4 3");
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(english333, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(english333,
            viewGame, readable);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score:32\n\n" +

            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n\n" +
            "Score: 31\n\n" +

            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n\n" +
            "Score: 30\n\n" +

            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n\n" +
            "Score: 29\n\n" +

            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n\n" +
            "Score: 28\n\n" +

            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O _ _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n\n" +
            "Score: 27\n\n" +

            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n\n" +
            "Score: 26\n" +

            "Game over!\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26", appendable.toString());


    Readable readable2 = new StringReader("4 6 7 6 q");
    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(english5, appendable2);
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(english5,
            viewGame2, readable2);
    controller2.playGame();
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score:104\n" +
            "Invalid move. Play again.\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "\n" +
            "Score: 104\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104", appendable2.toString());

    Readable readable4 = new StringReader("");
    Appendable appendable4 = new StringBuilder();
    EnglishSolitaireModel one = new EnglishSolitaireModel(1);
    MarbleSolitaireTextView emma = new MarbleSolitaireTextView(one, appendable4);
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(one,
            emma, readable4);
    controller4.playGame();

    assertEquals("_\n" +
            "Score:0\n" +
            "Game over!\n" +
            "_\n" +
            "Score: 0", appendable4.toString());

    Readable readable5 = new StringReader("");
    Appendable appendable5 = new StringBuilder();
    EnglishSolitaireModel one2 = new EnglishSolitaireModel(1);
    MarbleSolitaireTextView emma2 = new MarbleSolitaireTextView(one2, appendable5);
    controllerz = new MarbleSolitaireControllerImpl(one2, emma2, readable5);
    controllerz.playGame();

    assertEquals("_\n" +
            "Score:0\n" +
            "Game over!\n" +
            "_\n" +
            "Score: 0", appendable5.toString());

    //appendable is null
    try {
      Readable readable6 = new StringReader("");
      Appendable appendable6 = null;
      EnglishSolitaireModel one6 = new EnglishSolitaireModel(1);
      MarbleSolitaireTextView emma6 = new MarbleSolitaireTextView(one6, appendable6);
      MarbleSolitaireController controller6 = new MarbleSolitaireControllerImpl(one6,
              emma6, readable6);
      controller6.playGame();
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }

    //everything is null
    try {
      Readable readable6 = null;
      Appendable appendable6 = null;
      EnglishSolitaireModel one6 = null;
      MarbleSolitaireTextView emma6 = null;
      MarbleSolitaireController controller6 = null;
      controller6.playGame();
    } catch (NullPointerException e) {
      assertEquals(null, e.getMessage());
    }

    //readable is null
    try {
      Readable readable6 = null;
      Appendable appendable6 = new StringBuilder();
      EnglishSolitaireModel one6 = new EnglishSolitaireModel(1);
      MarbleSolitaireTextView emma6 = new MarbleSolitaireTextView(one6, appendable6);
      controllerz = new MarbleSolitaireControllerImpl(one6, emma6, readable6);
      controllerz.playGame();
    } catch (IllegalArgumentException e) {
      assertEquals("No objects may be null.", e.getMessage());
    }

    //text view is null
    try {
      Readable readable6 = new StringReader("");
      Appendable appendable6 = new StringBuilder();
      EnglishSolitaireModel one6 = new EnglishSolitaireModel(1);
      MarbleSolitaireTextView emma6 = null;
      controllerz = new MarbleSolitaireControllerImpl(one6, emma6, readable6);
      controllerz.playGame();
    } catch (IllegalArgumentException e) {
      assertEquals("No objects may be null.", e.getMessage());
    }
    //model is null
    try {
      Readable readable6 = new StringReader("");
      Appendable appendable6 = new StringBuilder();
      EnglishSolitaireModel one6 = null;
      MarbleSolitaireTextView emma6 = new MarbleSolitaireTextView(one6, appendable6);
      controllerz = new MarbleSolitaireControllerImpl(one6, emma6, readable6);
      controllerz.playGame();
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }

    //controller is null
    try {
      Readable readable6 = new StringReader("");
      Appendable appendable6 = new StringBuilder();
      EnglishSolitaireModel one6 = new EnglishSolitaireModel(1);
      MarbleSolitaireTextView emma6 = new MarbleSolitaireTextView(one6, appendable6);
      controllerz = null;
      controllerz.playGame();
    } catch (NullPointerException e) {
      assertEquals(null, e.getMessage());
    }
  }


  @Test

  public void testQuit() {

    Readable readable2 = new StringReader("q");
    Appendable appendable2 = new StringBuilder();
    MarbleSolitaireTextView viewGame2 = new MarbleSolitaireTextView(english5, appendable2);
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(english5,
            viewGame2, readable2);
    controller2.playGame();
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score:104\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "Score: 104", appendable2.toString());


    //fromRow quit
    Readable readable = new StringReader("2 4 4 4 5 4 3 4 Q");
    Appendable appendable = new StringBuilder();
    MarbleSolitaireTextView viewGame = new MarbleSolitaireTextView(english333, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(english333,
            viewGame, readable);
    controller.playGame();
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score:32\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "\n" +
            "Score: 31\n" +
            "\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", appendable.toString());

    //fromCol quit
    Readable readable10 = new StringReader("2 Q");
    Appendable appendable10 = new StringBuilder();
    MarbleSolitaireTextView viewGame10 = new MarbleSolitaireTextView(english333, appendable10);
    MarbleSolitaireController controller10 = new MarbleSolitaireControllerImpl(english333,
            viewGame10, readable10);
    controller10.playGame();
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score:30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", appendable10.toString());
    //toRow quit
    Readable readable11 = new StringReader("2 2 Q");
    Appendable appendable11 = new StringBuilder();
    MarbleSolitaireTextView viewGame11 = new MarbleSolitaireTextView(english333, appendable11);
    MarbleSolitaireController controller11 = new MarbleSolitaireControllerImpl(english333,
            viewGame11, readable11);
    controller11.playGame();
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score:30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", appendable11.toString());

    //toCol quit
    Readable readable12 = new StringReader("2 2 2 Q");
    Appendable appendable12 = new StringBuilder();
    MarbleSolitaireTextView viewGame12 = new MarbleSolitaireTextView(english333, appendable12);
    MarbleSolitaireController controller12 = new MarbleSolitaireControllerImpl(english333,
            viewGame12, readable12);
    controller12.playGame();
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score:30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30", appendable12.toString());

    try {
      Readable readable3 = new StringReader("2 4 4 4 5 4 3 4 Mommy");
      Appendable appendable3 = new StringBuilder();
      MarbleSolitaireTextView viewGame3 = new MarbleSolitaireTextView(english333, appendable3);
      MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(english333,
              viewGame3, readable3);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Scanner has no more values.", e.getMessage());
    }

    try {
      Readable readable3 = new StringReader("2 4 4 4 5 4 3 4");
      Appendable appendable3 = new CorruptAppendable();
      MarbleSolitaireTextView viewGame3 = new MarbleSolitaireTextView(english333, appendable3);
      MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(english333,
              viewGame3, readable3);
      controller3.playGame();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }
  }

  @Test
  public void testIO() throws IOException {
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(english333, new CorruptAppendable());
    try {
      view.renderBoard();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }
    MarbleSolitaireTextView view02 = new MarbleSolitaireTextView(english5, new CorruptAppendable());
    try {
      view02.renderBoard();
    } catch (IllegalStateException e) {
      assertEquals("Error in board rendering.", e.getMessage());
    }

    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(english5);
    try {
      view2 = new MarbleSolitaireTextView(english5, new CorruptAppendable().append("hi"));
    } catch (IOException e) {
      assertEquals("Please no!", e.getMessage());
    }

    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(english5);
    try {
      view3 = new MarbleSolitaireTextView(english5, new CorruptAppendable().append("Q", 4, 6));
    } catch (IOException e) {
      assertEquals("Cannot append!", e.getMessage());
    }

    MarbleSolitaireTextView view4 = new MarbleSolitaireTextView(english333);
    try {
      view4 = new MarbleSolitaireTextView(english333, new CorruptAppendable().append('c'));
    } catch (IOException e) {
      assertEquals("Cannot append this character!", e.getMessage());
    }
  }

  @Test
  public void testNullModel() {
    try {
      MarbleSolitaireTextView textView = new MarbleSolitaireTextView(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      MarbleSolitaireTextView textView = new MarbleSolitaireTextView(english333, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      MarbleSolitaireTextView textView = new MarbleSolitaireTextView(null, object);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      MarbleSolitaireTextView textView = new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }

  }

  @Test
  public void testConstructors() {
    assertEquals(7, this.english333.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.english333.getSlotAt(3, 3));
    assertEquals(32, this.english333.getScore());

    assertEquals(13, this.english5.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.english5.getSlotAt(6, 6));
    assertEquals(104, this.english5.getScore());
  }

  @Test
  public void testTextViewConstructors() {
    try {
      new MarbleSolitaireTextView(english5, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(null, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(null, object);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(null);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
    try {
      new MarbleSolitaireTextView(english5);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameters need not be null.", e.getMessage());
    }
  }
}






