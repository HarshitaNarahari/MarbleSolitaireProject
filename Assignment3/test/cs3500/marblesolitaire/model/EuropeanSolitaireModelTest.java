package cs3500.marblesolitaire.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EuropeanSolitaireModelTest {


  private MarbleSolitaireModelState europeanModel1;
  private MarbleSolitaireModelState europeanModel2;
  private MarbleSolitaireModelState europeanModel3;
  private MarbleSolitaireModelState europeanModel4;
  private MarbleSolitaireModelState model;
  private MarbleSolitaireModelState.SlotState example1;



  @Before
  public void setUp() {
    europeanModel1 = new EuropeanSolitaireModel();
    europeanModel2 = new EuropeanSolitaireModel(3, 3);
    europeanModel3 = new EuropeanSolitaireModel(3);
    europeanModel4 = new EuropeanSolitaireModel(3, 3, 3);
  }

  @Test
  public void testMakeBoard() {

    MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
    MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
    MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;

    ArrayList<MarbleSolitaireModelState.SlotState> array1 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, marble, marble, marble, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array2 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, marble, marble, marble, marble, marble, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array3 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, marble, marble, marble, marble));
    ArrayList<MarbleSolitaireModelState.SlotState> array4 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, empty, marble, marble, marble));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoard = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoard.add(array1);
    expectedBoard.add(array2);
    expectedBoard.add(array3);
    expectedBoard.add(array4);
    expectedBoard.add(array3);
    expectedBoard.add(array2);
    expectedBoard.add(array1);

    assertEquals(expectedBoard, new EuropeanSolitaireModel().makeBoard());
  }


  @Test
  public void testMove() {
    EuropeanSolitaireModel testModel = new EuropeanSolitaireModel(3, 3, 3);
    MarbleSolitaireView testView = new MarbleSolitaireTextView(testModel);
    // before calling the mutation method
    assertEquals( testModel.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals( testModel.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals( testModel.getScore(), 36);



    testModel.move(3, 5, 3, 3);


    // after calling the mutation method
    assertEquals(testModel.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(testModel.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(testModel.getScore(), 35);

  }



  @Test
  public void testGetScore() {
    MarbleSolitaireModelState model = new EnglishSolitaireModel(5, 5, 5);
    assertEquals(104, model.getScore());
    assertEquals(36, europeanModel1.getScore());
    assertEquals(36, europeanModel2.getScore());
    assertEquals(36, europeanModel3.getScore());
    assertEquals(36, europeanModel4.getScore());
  }

  @Test
  public void testExceptions() {
    setUp();
    // invalid move method
    try {
      EuropeanSolitaireModel testModel = new EuropeanSolitaireModel(3, 3, 3);
      testModel.move(3, 5, 4, 2);
      fail("Error: Move is invalid.");
    } catch (IllegalArgumentException e) {
    }

    // invalid for getSlotAt method
    try {
      EuropeanSolitaireModel testModel = new EuropeanSolitaireModel(3, 3, 3);
      testModel.getSlotAt(7, 8);
      fail("The row or the column is beyond the dimensions of the board.");
    } catch (IllegalArgumentException e) {
    }

    // invalid arm thickness
    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(-2);
      fail("Error: Length is not a positive odd number.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(0);
      fail("Error: Length is not a positive odd number.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(-3);
      fail("Error: Length is not a positive odd number.");
    } catch (IllegalArgumentException e) {
    }

    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(-3, 3, 3);
      fail("Error: Length is not a positive odd number or the empty cell position is invalid.");
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
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, empty, empty, empty, empty, empty, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array3 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, empty, empty, empty, empty));
    ArrayList<MarbleSolitaireModelState.SlotState> array4 =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, marble, empty, empty, empty));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardWin = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoardWin.add(array1);
    expectedBoardWin.add(array2);
    expectedBoardWin.add(array3);
    expectedBoardWin.add(array4);
    expectedBoardWin.add(array3);
    expectedBoardWin.add(array2);
    expectedBoardWin.add(array1);
    EuropeanSolitaireModel modelTestWin = new EuropeanSolitaireModel();
    modelTestWin.setBoard(expectedBoardWin);

    // test for when only 1 marble in board & in right location
    assertEquals(true, modelTestWin.isGameOver());



    ArrayList<MarbleSolitaireModelState.SlotState> array1Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, invalid, empty, empty, marble, invalid, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array2Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(invalid, marble, empty, empty, empty, empty, invalid));
    ArrayList<MarbleSolitaireModelState.SlotState> array3Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, empty, empty, empty, empty));
    ArrayList<MarbleSolitaireModelState.SlotState> array4Lose =
            new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty, marble, empty, empty, marble));


    ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardLose = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
    expectedBoardLose.add(array1Lose);
    expectedBoardLose.add(array2Lose);
    expectedBoardLose.add(array3Lose);
    expectedBoardLose.add(array4Lose);
    expectedBoardLose.add(array3Lose);
    expectedBoardLose.add(array2Lose);
    expectedBoardLose.add(array1Lose);
    EuropeanSolitaireModel modelTestLose = new EuropeanSolitaireModel();
    modelTestLose.setBoard(expectedBoardLose);
    // test for when no set of 2 marbles next to each other
    assertEquals(true, modelTestLose.isGameOver());

  }


  @Test
  public void testGetBoardSize() {
    assertEquals(7, europeanModel1.getBoardSize());
    assertEquals(7, europeanModel2.getBoardSize());
    assertEquals(7, europeanModel3.getBoardSize());
    assertEquals(7, europeanModel4.getBoardSize());
    assertEquals(13, new EnglishSolitaireModel(5).getBoardSize());
    assertEquals(19, new EnglishSolitaireModel(7).getBoardSize());
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, europeanModel1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, europeanModel1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, europeanModel1.getSlotAt(6, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, europeanModel1.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, europeanModel1.getSlotAt(0, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, europeanModel1.getSlotAt(4, 3));
  }

}
