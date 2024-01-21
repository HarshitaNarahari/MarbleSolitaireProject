package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * To represent an abstract solitaire model
 */
public abstract class ASolitaireModel implements MarbleSolitaireModel {

  protected int length;
  protected int sRow;
  protected int sCol;
  protected int score;
  protected ArrayList<ArrayList<SlotState>> board;
  private SlotState empty = SlotState.Empty;
  private SlotState marble = SlotState.Marble;
  private SlotState invalid = SlotState.Invalid;

  /**
   * Constructs a default board
   */
  public ASolitaireModel() {
    this.length = 3;
    this.sRow = (length * 3 - 2) / 2;
    this.sCol = (length * 3 - 2) / 2;

    this.board = makeBoard();


    int biggestLength = (length * 3) - 2;
    int edges = length - 1;
    this.score = (biggestLength * biggestLength) - ((edges * edges) * 4 + 1);

  }

  /**
   * Constructs a board of slot states based on the slot given
   *
   * @param sRow the row number of the slot
   * @param sCol the column number of the slot
   * @throws IllegalArgumentException if the empty slot is invalid
   */
  public ASolitaireModel(int sRow, int sCol) {
    this.length = 3;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = makeBoard();

    board.get(sRow).set(sCol, SlotState.Empty);


    int biggestLength = (length * 3) - 2;
    int edges = length - 1;
    this.score = (biggestLength * biggestLength) - ((edges * edges) * 4 + 1);
  }

  /**
   * Constructs a board of slot states based on the arm thickness given
   *
   * @param length the length of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number
   */
  public ASolitaireModel(int length) {
    this.length = length;
    this.sRow = (length * 3 - 2) / 2;
    this.sCol = (length * 3 - 2) / 2;

    this.board = makeBoard();



    int biggestLength = (length * 3) - 2;
    int edges = length - 1;
    this.score = (biggestLength * biggestLength) - ((edges * edges) * 4 + 1);
  }

  /**
   * Constructs a board of slot states based on the slot given
   *
   * @param sRow         the row number of the slot
   * @param sCol         the column number of the slot
   * @param length the length of the board
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or the empty slot is invalid
   */
  public ASolitaireModel(int length, int sRow, int sCol) {
    if (length < 0 || sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Error: Row, column, or dimensions cannot be negative.");
    }
    this.length = length;
    this.sRow = sRow;
    this.sCol = sCol;
    this.board = makeBoard();

    board.get(sRow).set(sCol, SlotState.Empty);


    int biggestLength = (length * 3) - 2;
    int edges = length - 1;
    this.score = (biggestLength * biggestLength) - ((edges * edges) * 4 + 1);
  }



  protected abstract ArrayList<ArrayList<SlotState>>makeBoard();


  /**
   * Moves a marble from a given position to another given position.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is invalid
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    SlotState fromPosition = board.get(fromRow).get(fromCol);
    SlotState toPosition = board.get(toRow).get(toCol);
    int midCol = 0;
    int midRow = 0;
    SlotState midPosition;

    if (fromCol > toCol) {
      midCol = toCol + 1;
      midRow = fromRow;
    }
    if (fromRow > toRow) {
      midRow = toRow + 1;
      midCol = fromCol;
    }
    if (toCol > fromCol) {
      midCol = fromCol + 1;
      midRow = fromRow;
    }
    if (toRow > fromRow) {
      midRow = fromRow + 1;
      midCol = fromCol;
    }

    midPosition = board.get(midRow).get(midCol);


    if (fromPosition != invalid && toPosition != invalid &&
            fromPosition == marble && toPosition == empty && midPosition == marble &&
            ((midCol + 1 == toCol || midCol - 1 == toCol)
                    || (midRow + 1 == toRow || midRow - 1 == toRow))) {
      board.get(fromRow).set(fromCol, empty);
      board.get(toRow).set(toCol, marble);
      board.get(midRow).set(midCol, empty);

      this.score = this.score - 1;
    } else {
      throw new IllegalArgumentException("Error: Move is invalid.");
    }
  }


  /**
   * Determines if the game is over
   *
   * @return boolean to determine if the game is over or not
   */
  public boolean isGameOver() {
    boolean gameOver = true;

    int armThicknessLength = (length * 3) - 2;

    for (int row = 0; row < armThicknessLength; row++) {
      for (int col = 0; col < armThicknessLength; col++) {


        if (this.getSlotAt(row, col) == SlotState.Marble) {

          // check if down move is legal
          if (row < armThicknessLength - 2 && this.getSlotAt(row + 1, col) == SlotState.Marble
                  && this.getSlotAt(row + 2, col) == SlotState.Empty) {
            gameOver = false;
          }

          // check if up move is legal
          if (row > 1 && this.getSlotAt(row - 1, col) == SlotState.Marble
                  && this.getSlotAt(row - 2, col) == SlotState.Empty) {
            gameOver = false;
          }

          // check if right move is legal
          if (col < armThicknessLength - 2 && this.getSlotAt(row, col + 1) == SlotState.Marble
                  && this.getSlotAt(row, col + 2) == SlotState.Empty) {
            gameOver = false;
          }

          // check if left move is legal
          if (col > 1 && this.getSlotAt(row, col - 1) == SlotState.Marble
                  && this.getSlotAt(row, col - 2) == SlotState.Empty) {
            gameOver = false;
          }

        }
      }
    }
    return gameOver;
  }


  /**
   * Setter for board
   *
   * @param board a 2D array of slot states
   *
   */
  public void setBoard(ArrayList<ArrayList<SlotState>> board) {
    this.board = board;
  }

  /**
   * Getter for board
   *
   * @returns 2D array representing a board
   */
  public ArrayList<ArrayList<SlotState>> getBoard() {
    return this.board;
  }


  public int getBoardSize() {
    if (this instanceof TriangleSolitaireModel) {
      return length;
    }
    else {
      return board.size();
    }
  }


  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are
   *                                  beyond the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {

    if (row >= ((length * 3) - 2) || row < 0 || col >= ((length * 3) - 2) || col < 0) {
      throw new IllegalArgumentException("The row or the column is beyond the dimensions of the board.");
    } else {
      return board.get(row).get(col);
    }
  }

  /**
   * Getter for score
   *
   * @returns an integer representing the score
   */
  public int getScore() {
    return this.score;
  }
}
