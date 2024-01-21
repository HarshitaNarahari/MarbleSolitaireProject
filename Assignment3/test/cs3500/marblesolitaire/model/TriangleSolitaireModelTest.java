package cs3500.marblesolitaire.model;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TriangleSolitaireModelTest {

    private MarbleSolitaireModelState triangleModel1;
    private MarbleSolitaireModelState triangleModel2;
    private MarbleSolitaireModelState triangleModel3;
    private MarbleSolitaireModelState triangleModel4;
    private MarbleSolitaireModelState model;
    private MarbleSolitaireModelState.SlotState example1;



    @Before
    public void setUp() {
        triangleModel1 = new TriangleSolitaireModel();
        triangleModel2 = new TriangleSolitaireModel(2, 1);
        triangleModel3 = new TriangleSolitaireModel(5);
        triangleModel4 = new TriangleSolitaireModel(5, 2, 2);


    }



    @Test
    public void testMakeBoard() {

        MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
        MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
        MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;

        ArrayList<MarbleSolitaireModelState.SlotState> array1 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array2 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array3 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array4 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array5 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble, marble, marble, marble));


        ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoard = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
        expectedBoard.add(array1);
        expectedBoard.add(array2);
        expectedBoard.add(array3);
        expectedBoard.add(array4);
        expectedBoard.add(array5);


        assertEquals(expectedBoard, new TriangleSolitaireModel().makeBoard());
    }


    @Test
    public void testMove() {
        TriangleSolitaireModel testModel = new TriangleSolitaireModel(5, 2, 2);
        MarbleSolitaireView testView = new TriangleSolitaireTextView(testModel);
        // before calling the mutation method
        assertEquals( testModel.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
        assertEquals( testModel.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Empty);
        assertEquals( testModel.getScore(), 14);



        testModel.move(2, 0, 2, 2);


        // after calling the mutation method
        assertEquals(testModel.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Empty);
        assertEquals(testModel.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Marble);
        assertEquals(testModel.getScore(), 13);

    }



    @Test
    public void testGetScore() {
        MarbleSolitaireModelState model = new TriangleSolitaireModel(3, 1, 0);

        assertEquals(14, triangleModel1.getScore());
        assertEquals(14, triangleModel2.getScore());
        assertEquals(14, triangleModel3.getScore());
        assertEquals(14, triangleModel4.getScore());
        assertEquals(5, model.getScore());
    }

    @Test
    public void testExceptions() {
        setUp();
        // invalid move method
        try {
            TriangleSolitaireModel testModel = new TriangleSolitaireModel(5, 2, 2);
            testModel.move(0, 0, 2, 1);
            fail("Error: Move is invalid.");
        } catch (IllegalArgumentException e) {
        }

        // invalid for getSlotAt method
        try {
            TriangleSolitaireModel testModel = new TriangleSolitaireModel(5, 2, 2);
            testModel.getSlotAt(5, 6);
            fail("The row or the column is beyond the dimensions of the board.");
        } catch (IllegalArgumentException e) {
        }


        // invalid dimensions
        try {
            TriangleSolitaireModel model = new TriangleSolitaireModel(-1);
            fail("Error: The dimensions cannot be negative.");
        } catch (IllegalArgumentException e) {
        }

        // invalid row or column
        try {
            TriangleSolitaireModel model = new TriangleSolitaireModel(4, -1);
            fail("Error: Row or column cannot be negative.");
        } catch (IllegalArgumentException e) {
        }

        // invalid dimension, row, or column
        try {
            TriangleSolitaireModel model = new TriangleSolitaireModel(-2, 4, -1);
            fail("Error: Row, column, or dimensions cannot be negative.");
        } catch (IllegalArgumentException e) {
        }
    }


    @Test
    public void testIsGameOver() {
        assertEquals(false, new TriangleSolitaireModel(3).isGameOver());

        MarbleSolitaireModelState.SlotState empty = MarbleSolitaireModelState.SlotState.Empty;
        MarbleSolitaireModelState.SlotState marble = MarbleSolitaireModelState.SlotState.Marble;
        MarbleSolitaireModelState.SlotState invalid = MarbleSolitaireModelState.SlotState.Invalid;

        ArrayList<MarbleSolitaireModelState.SlotState> array1 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array2 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array3 =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble, invalid, marble));


        ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardWin = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
        expectedBoardWin.add(array1);
        expectedBoardWin.add(array2);
        expectedBoardWin.add(array3);
        TriangleSolitaireModel modelTestWin = new TriangleSolitaireModel(3);
        modelTestWin.setBoard(expectedBoardWin);

        // test for when only 1 marble in board & in right location
        assertEquals(true, modelTestWin.isGameOver());



        ArrayList<MarbleSolitaireModelState.SlotState> array1Lose =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(marble));
        ArrayList<MarbleSolitaireModelState.SlotState> array2Lose =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty));
        ArrayList<MarbleSolitaireModelState.SlotState> array3Lose =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, empty, empty));
        ArrayList<MarbleSolitaireModelState.SlotState> array4Lose =
                new ArrayList<MarbleSolitaireModelState.SlotState>(Arrays.asList(empty, marble, empty, empty));


        ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> expectedBoardLose = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();
        expectedBoardLose.add(array1Lose);
        expectedBoardLose.add(array2Lose);
        expectedBoardLose.add(array3Lose);
        expectedBoardLose.add(array4Lose);
        TriangleSolitaireModel modelTestLose = new TriangleSolitaireModel(4);
        modelTestLose.setBoard(expectedBoardLose);

        // test for when no set of 2 marbles next to each other
        assertEquals(true, modelTestLose.isGameOver());

    }


    @Test
    public void testGetBoardSize() {
        assertEquals(5, triangleModel1.getBoardSize());
        assertEquals(5, triangleModel2.getBoardSize());
        assertEquals(5, triangleModel3.getBoardSize());
        assertEquals(5, triangleModel4.getBoardSize());
        assertEquals(3, new TriangleSolitaireModel(3).getBoardSize());
        assertEquals(7, new TriangleSolitaireModel(7).getBoardSize());
        assertEquals(5, new TriangleSolitaireModel(2, 2).getBoardSize());

    }

    @Test
    public void testGetSlotAt() {
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, triangleModel1.getSlotAt(3, 3));
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, triangleModel1.getSlotAt(1, 0));
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, triangleModel1.getSlotAt(0, 0));
        assertEquals(MarbleSolitaireModelState.SlotState.Invalid, triangleModel1.getSlotAt(0, 2));

        MarbleSolitaireModelState modelState = new TriangleSolitaireModel(3, 2, 1);
        assertEquals(MarbleSolitaireModelState.SlotState.Marble, modelState.getSlotAt(0, 0));
        assertEquals(MarbleSolitaireModelState.SlotState.Empty, modelState.getSlotAt(2,1));

    }

}
