package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import java.util.ArrayList;

import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import jdk.jfr.TransitionTo;

import static org.junit.Assert.*;

public class EnglishSolitaireModelTest {

  private MarbleSolitaireModelState englishModel1;
  private MarbleSolitaireModelState englishModel2;
  private MarbleSolitaireModelState englishModel3;
  private MarbleSolitaireModelState englishModel4;
  private MarbleSolitaireModelState model;
  private MarbleSolitaireModelState.SlotState example1;



  @Before
  public void setUp() {
    englishModel1 = new EnglishSolitaireModel();
    englishModel2 = new EnglishSolitaireModel(3, 3);
    englishModel3 = new EnglishSolitaireModel(3);
    englishModel4 = new EnglishSolitaireModel(3, 3, 3);


  }



  @Test
  public void testMakeBoard() {

    MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
    MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
    MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;

    ArrayList<MarbleSolitaireModelState.SlotState> array1 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, marble, marble, marble, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array2 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, marble, marble, marble, marble));
    ArrayList<MarbleSolitaireModelState.SlotState> array3 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, empty, marble, marble, marble));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoard = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoard.add(array1);
    expectedBoard.add(array1);
    expectedBoard.add(array2);
    expectedBoard.add(array3);
    expectedBoard.add(array2);
    expectedBoard.add(array1);
    expectedBoard.add(array1);

    assertEquals(expectedBoard, new EnglishSolitaireModel().makeBoard());
  }


@Test
public void testMove() {
  EnglishSolitaireModel testModel = new EnglishSolitaireModel(3, 3, 3);
  // before calling the mutation method
  assertEquals( testModel.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
  assertEquals( testModel.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
  assertEquals( testModel.getScore(), 32);

  testModel.move(3, 5, 3, 3);

  // after calling the mutation method
  assertEquals(testModel.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
  assertEquals(testModel.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
  assertEquals(testModel.getScore(), 31);

}



  @Test
  public void testGetScore() {
    MarbleSolitaireModelState model = new EnglishSolitaireModel(5, 5, 5);

    assertEquals(32, englishModel1.getScore());
    assertEquals(32, englishModel2.getScore());
    assertEquals(32, englishModel3.getScore());
    assertEquals(32, englishModel4.getScore());
    assertEquals(104, model.getScore());
  }

  @Test
  public void testExceptions() {
    setUp();
    // invalid move method
    try {
      EnglishSolitaireModel testModel = new EnglishSolitaireModel(3, 3, 3);
      testModel.move(3, 5, 4, 2);
      fail("Error: Move is invalid.");
    } catch (IllegalArgumentException e) {
    }

    // invalid for getSlotAt method
    try {
      EnglishSolitaireModel testModel = new EnglishSolitaireModel(3, 3, 3);
      testModel.getSlotAt(7, 8);
      fail("The row or the column is beyond the dimensions of the board.");
    } catch (IllegalArgumentException e) {
    }

    // invalid slot state
    try {
      model = new EnglishSolitaireModel(8, 7);
      fail("Invalid empty cell position (" + 8 + "," + 7 + ")");
    } catch (IllegalArgumentException e) {
    }

    // invalid arm thickness
    try {
      EnglishSolitaireModel model = new EnglishSolitaireModel(2);
      fail("Error: Arm thickness is not a positive odd number.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EnglishSolitaireModel model = new EnglishSolitaireModel(0);
      fail("Error: Arm thickness is not a positive odd number.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EnglishSolitaireModel model = new EnglishSolitaireModel(-3);
      fail("Error: Arm thickness is not a positive odd number.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EnglishSolitaireModel model = new EnglishSolitaireModel(-3, 3, 3);
      fail("Error: Arm thickness is not a positive odd number or the empty cell position is invalid.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EnglishSolitaireModel model = new EnglishSolitaireModel(4, 3, 3);
      fail("Error: Arm thickness is not a positive odd number or the empty cell position is invalid.");
    } catch (IllegalArgumentException e) {
    }
  }


  @Test
  public void testIsGameOver() {
    assertEquals(false, new EnglishSolitaireModel().isGameOver());

    MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
    MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
    MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;

    ArrayList<MarbleSolitaireModelState.SlotState> array1 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, empty, empty, empty, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array2 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, empty, empty, empty, empty));
    ArrayList<MarbleSolitaireModelState.SlotState> array3 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, marble, empty, empty, empty));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardWin = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoardWin.add(array1);
    expectedBoardWin.add(array1);
    expectedBoardWin.add(array2);
    expectedBoardWin.add(array3);
    expectedBoardWin.add(array2);
    expectedBoardWin.add(array1);
    expectedBoardWin.add(array1);
    EnglishSolitaireModel modelTestWin = new EnglishSolitaireModel();
    modelTestWin.setBoard(expectedBoardWin);

    // test for when only 1 marble in board & in right location
    assertEquals(true, modelTestWin.isGameOver());



    ArrayList<MarbleSolitaireModelState.SlotState> array1Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, empty, empty, empty, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array2Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, marble, empty, empty, empty, empty, marble));
    ArrayList<MarbleSolitaireModelState.SlotState> array3Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, marble, empty, empty, empty));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardLose = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoardLose.add(array1Lose);
    expectedBoardLose.add(array1Lose);
    expectedBoardLose.add(array2Lose);
    expectedBoardLose.add(array3Lose);
    expectedBoardLose.add(array2Lose);
    expectedBoardLose.add(array1Lose);
    expectedBoardLose.add(array1Lose);
    EnglishSolitaireModel modelTestLose = new EnglishSolitaireModel();
    modelTestLose.setBoard(expectedBoardLose);

    // test for when no set of 2 marbles next to each other
    assertEquals(true, modelTestLose.isGameOver());

  }


  @Test
  public void testGetBoardSize() {
    assertEquals(7, englishModel1.getBoardSize());
    assertEquals(7, englishModel2.getBoardSize());
    assertEquals(7, englishModel3.getBoardSize());
    assertEquals(7, englishModel4.getBoardSize());
    assertEquals(13, new EnglishSolitaireModel(5).getBoardSize());
    assertEquals(19, new EnglishSolitaireModel(7).getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, englishModel1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, englishModel1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, englishModel1.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, englishModel1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, englishModel1.getSlotAt(4, 3));
  }

  @Test // for setBoard and getBoard
 public void testSetBoardAndGetBoard() {

    MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
    MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
    MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;

    ArrayList<MarbleSolitaireModelState.SlotState> array1 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, marble, marble, marble, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array2 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, marble, marble, marble, marble));
    ArrayList<MarbleSolitaireModelState.SlotState> array3 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, empty, marble, marble, marble));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoard = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoard.add(array1);
    expectedBoard.add(array1);
    expectedBoard.add(array2);
    expectedBoard.add(array3);
    expectedBoard.add(array2);
    expectedBoard.add(array1);
    expectedBoard.add(array1);

EnglishSolitaireModel testModel = new EnglishSolitaireModel();
    assertEquals(expectedBoard, testModel.getBoard());


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardWin = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();


    ArrayList<MarbleSolitaireModelState.SlotState> arrayWin1 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, empty, empty, empty, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> arrayWin2 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, empty, empty, empty, empty));
    ArrayList<MarbleSolitaireModelState.SlotState> arrayWin3 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, marble, empty, empty, empty));

    expectedBoardWin.add(arrayWin1);
    expectedBoardWin.add(arrayWin1);
    expectedBoardWin.add(arrayWin2);
    expectedBoardWin.add(arrayWin3);
    expectedBoardWin.add(arrayWin2);
    expectedBoardWin.add(arrayWin1);
    expectedBoardWin.add(arrayWin1);
    EnglishSolitaireModel modelTest = new EnglishSolitaireModel();


    testModel.setBoard(expectedBoardWin);

    assertEquals(expectedBoardWin, testModel.getBoard());
  }
}