package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

/**
 * To represent a Triangle Solitaire Model
 */
public class TriangleSolitaireModel extends ASolitaireModel {


  /**
   * Default constructor
   */
  public TriangleSolitaireModel() {
    this(5);
    score = (length * (length + 1)) / 2 - 1;

  }

  /**
   * Constructor with given dimensions value
   * @param dimensions represents the bottom-most row
   * @throws IllegalArgumentException if the dimensions is a negative value
   */
  public TriangleSolitaireModel(int dimensions) throws IllegalArgumentException {
    this(dimensions, 0, 0);
    if (dimensions < 0) {
      throw new IllegalArgumentException("Error: The dimensions cannot be negative.");
    }
    score = (length * (length + 1)) / 2 - 1;

  }

  /**
   * Constructor with given row and col value to specify the initial position of the empty slot
   * @param sRow represents the row value of the empty slot
   * @param sCol represents the column value of the empty slot
   * @throws IllegalArgumentException if the given row or column value is negative
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(5, sRow, sCol);

    if (sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Error: Row or column cannot be negative.");
    }
    this.score = (this.length * (this.length + 1)) / 2 - 1;

  }

  /**
   * Constructor with given dimensions, row, and col value to specify the initial position of the empty slot
   * @param dimensions represents the bottom-most row
   * @param sRow represents the row value of the empty slot
   * @param sCol represents the column value of the empty slot
   * @throws IllegalArgumentException if the given dimensions, row, or column value is negative
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) throws IllegalArgumentException {
    super(dimensions, sRow, sCol);
    if (dimensions < 0 || sRow < 0 || sCol < 0) {
      throw new IllegalArgumentException("Error: Row, column, or dimensions cannot be negative.");
    }
    this.score = (this.length * (this.length + 1)) / 2 - 1;

  }


  /**
   * Makes a board of slot states based on the slot given
   *
   * @return 2D array representing the board
   */
  @Override
  public ArrayList<ArrayList<SlotState>> makeBoard() {
    board = new ArrayList<ArrayList<SlotState>>();


    for (int row = 0; row < length; row++) {
      ArrayList<SlotState> boardArray = new ArrayList<SlotState>();
      for (int col = 0; col <= row; col++) {

        boardArray.add(SlotState.Marble);
      }
      board.add(boardArray);
    }

    int totalMarbles = (length * (length + 1)) / 2;
    score = totalMarbles - 1;

    return board;
  }

  /**
   * Moves a marble from a given position to another given position.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is invalid
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int modFromRow = fromRow;
    int modFromCol = length - 1 - fromCol;
    int modToRow = toRow;
    int modToCol = length - 1 - toCol;

    SlotState fromPosition = board.get(modFromRow).get(length - 1 - modFromCol);
    SlotState toPosition = board.get(modToRow).get(length - 1 - modToCol);

    if (fromPosition == SlotState.Marble && toPosition == SlotState.Empty) {
      int midCol, midRow;
      SlotState midPosition;

      if (fromCol > toCol) {
        midCol = toCol + 1;
        midRow = fromRow;
      } else if (fromRow > toRow) {
        midRow = toRow + 1;
        midCol = fromCol;
      } else if (toCol > fromCol) {
        midCol = fromCol + 1;
        midRow = fromRow;
      } else {
        midRow = fromRow + 1;
        midCol = fromCol;
      }

      midPosition = board.get(midRow).get(midCol);

      if (midPosition == SlotState.Marble &&
              ((midCol + 1 == toCol || midCol - 1 == toCol) || (midRow + 1 == toRow || midRow - 1 == toRow))) {
        board.get(modFromRow).set(length - 1 - modFromCol, SlotState.Empty);
        board.get(modToRow).set(length - 1 - modToCol, SlotState.Marble);
        board.get(midRow).set(midCol, SlotState.Empty);

        score = score - 1;
      }
    }

   else {
     throw new IllegalArgumentException("Error: Move is invalid.");
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
    if (row >= length || row < 0 || col >= length || col < 0) {
      throw new IllegalArgumentException("The row or the column is beyond the dimensions of the board.");
    }

    if(col <= row) {
      return board.get(row).get(col);
    }
    return SlotState.Invalid;
  }


  /**
   * Determines if the game is over
   *
   * @return boolean to determine if the game is over or not
   */
  @Override
  public boolean isGameOver() {
    int biggestRow = length;
    boolean gameOver = true;

    for (int row = 0; row < biggestRow; row++) {
      for (int col = 0; col <= row; col++) {
        if (board.get(row).get(col) == SlotState.Marble) {
          if (
                  isMoveLegal(row, col, row, col - 2) ||
                  isMoveLegal(row, col, row, col + 2) ||
                  isMoveLegal(row, col, row - 2, col) ||
                  isMoveLegal(row, col, row + 2, col) ||
                  isMoveLegal(row, col, row - 2, col - 2) ||
                  isMoveLegal(row, col, row + 2, col + 2)) {
            gameOver = false;
          }
        }
      }
    }
    return gameOver;
  }

  /**
   * Helper method to determine if a move is valid
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @return
   */
  private boolean isMoveLegal(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow < 0 || toCol < 0 || toRow >= length || toCol > toRow) {
      return false;
    }

    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;

    SlotState fromPosition = board.get(fromRow).get(fromCol);
    SlotState toPosition = board.get(toRow).get(toCol);
    SlotState midPosition = board.get(midRow).get(midCol);

    return fromPosition == SlotState.Marble &&
            toPosition == SlotState.Empty &&
            midPosition == SlotState.Marble;
  }

}

