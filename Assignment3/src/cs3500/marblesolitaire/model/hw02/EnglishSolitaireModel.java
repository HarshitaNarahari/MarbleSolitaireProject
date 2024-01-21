package cs3500.marblesolitaire.model.hw02;

import java.util.*;
import cs3500.marblesolitaire.model.hw04.ASolitaireModel;

/**
 * EnglishSolitaireModel class represents a solitaire board game with armThickness, sRow, sCol, and score
 */
public class EnglishSolitaireModel extends ASolitaireModel {


  /**
   * Constructs a default board
   */
  public EnglishSolitaireModel() {
    super();
    board.get(sRow).set(sCol, SlotState.Empty);
  }

  /**
   * Constructs a board of slot states based on the slot given
   *
   * @param sRow the row number of the slot
   * @param sCol the column number of the slot
   * @throws IllegalArgumentException if the empty slot is invalid
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);

    if (this.getSlotAt(sRow, sCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
  }

  /**
   * Constructs a board of slot states based on the arm thickness given
   *
   * @param armThickness the thickness of the arm of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public EnglishSolitaireModel(int armThickness) {
    super(armThickness);

    if (this.length <= 0 || this.length % 2 == 0) {
      throw new IllegalArgumentException("Error: Arm thickness is not a positive odd number.");
    }
    board.get(sRow).set(sCol, SlotState.Empty);

  }

  /**
   * Constructs a board of slot states based on the slot given
   *
   * @param sRow         the row number of the slot
   * @param sCol         the column number of the slot
   * @param armThickness the thickness of the arm of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the empty slot is invalid
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);

    if ((this.length <= 0 || this.length % 2 == 0)) {
      throw new IllegalArgumentException("Error: Arm thickness is not a positive odd number.");
    }

  }

  /**
   * Makes a board of slot states based on the slot given
   *
   * @return 2D array representing the board
   */
  public ArrayList<ArrayList<SlotState>> makeBoard() {

    int biggestLength = (super.length * 3) - 2;
    int edges = super.length - 1;

    board = new ArrayList<ArrayList<SlotState>>(length);

    for (int row = 0; row < biggestLength; row++) {
      ArrayList<SlotState> boardArray = new ArrayList<SlotState>();

      for (int col = 0; col < biggestLength; col++) {

        boolean isInvalid = (row < edges && col < edges) || (row < edges && col >= edges + length) ||
                (row >= edges + length && col < edges) ||
                (row >= edges + length && col >= edges + length);

        boolean isEmpty = (row == sRow && col == sCol);

        if (isInvalid) {
          boardArray.add(SlotState.Invalid);
        } else if (isEmpty) {
          boardArray.add(SlotState.Empty);
        } else {
          boardArray.add(SlotState.Marble);
        }
      }
      board.add(boardArray);
    }
    return board;
  }
}