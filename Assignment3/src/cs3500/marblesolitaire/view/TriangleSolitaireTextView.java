package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

/**
 * Represents a triangle solitaire board game as text
 */
public class TriangleSolitaireTextView extends ASolitaireTextView {

  /**
   * The constructor with appendable
   *
   * @param modelState represents the marble solitaire model state
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);
  }

  /**
   * The constructor with appendable
   *
   * @param modelState represents the marble solitaire model state
   * @param appendObject object to be appended on
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendObject) {
    super(modelState, appendObject);
  }


  /**
   * Converts the text view of a board to a string
   *
   * @returns a String representing the board
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    int biggestRow = modelState.getBoardSize();

    for (int row = 0; row < biggestRow; row++) {

      for (int col = 0; col < biggestRow - row - 1; col++) {
        str.append(" ");
      }
      for (int col = 0; col <= row; col++) {
        TriangleSolitaireModel.SlotState slotState = modelState.getSlotAt(row, col);
        if (slotState == TriangleSolitaireModel.SlotState.Empty) {
          str.append("_ ");
        } else if (slotState == TriangleSolitaireModel.SlotState.Marble) {
          str.append("O ");
        }
      }

      if (str.length() > 0) {
        str.deleteCharAt(str.length() - 1);
      }
      if (row < biggestRow - 1) {
        str.append("\n");
      }

    }
    return str.toString();

  }



}
