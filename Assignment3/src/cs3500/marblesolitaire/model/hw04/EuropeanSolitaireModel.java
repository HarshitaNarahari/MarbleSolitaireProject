package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

/**
 * This class represents a European Solitaire Model
 */
public class EuropeanSolitaireModel extends ASolitaireModel {
  private SlotState empty = SlotState.Empty;
  private SlotState marble = SlotState.Marble;
  private SlotState invalid = SlotState.Invalid;

  /**
   * Default constructor for EuropeanSolitaireModel
   * With an armThickness of 3
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructs a board with arm thickness given
   *
   * @param armThickness the thickness of the octagon sides
   */
  public EuropeanSolitaireModel(int armThickness) {
    this(armThickness, (((armThickness * 3) - 2) / 2), (((armThickness * 3) - 2) / 2));
    if (this.length <= 0 || this.length % 2 == 0) {
      throw new IllegalArgumentException("Error: Length is not a positive odd number.");
    }
  }

  /**
   * Constructs an octagon board based on the row and col
   * of the empty slot
   *
   * @param sRow the row of the empty slot in the octagon board
   * @param sCol the column of the empty slot in the octagon board
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }


  /**
   * Constructs an octagon board based on the row and col
   * of the empty slot and arm thickness
   *
   * @param armThickness the length of the octagon sides
   * @param sRow the row of the empty slot in the octagon board
   * @param sCol the column of the empty slot in the octagon board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd
   */
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol) throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
    if (this.length <= 0) {
      throw new IllegalArgumentException("Error: Length should be a positive number.");
    }
    this.score = 4 * (armThickness * armThickness) + (armThickness - 2) * (armThickness - 2) - 5;
    for (int i = 1; i <= armThickness - 2; i++) {
      this.score += i * 4;
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

        boolean isInvalid = (row < edges && col < edges-row) ||
                (row < edges && col >= edges + length + row) ||
                (row >= edges + length && col < edges - (biggestLength-1-row)) ||
                (row >= edges + length && col >= edges + length + (biggestLength-1-row));

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










