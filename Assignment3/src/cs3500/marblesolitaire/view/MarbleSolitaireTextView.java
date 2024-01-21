

package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * MarbleSolitaireTextView class represents a solitaire board game as text
 */
public class MarbleSolitaireTextView extends ASolitaireTextView {

  /**
   * the constructor
   * @param modelState represents the marble solitaire model state
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);
  }

  /**
   * the constructor with appendable
   * @param modelState represents the marble solitaire model state
   * @param appendObject object to be appended on
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendObject) {
    super(modelState, appendObject);
  }


  /**
   * Converts the text view of a board to a string
   *
   * @returns a String representing the board
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int row = 0; row < modelState.getBoardSize(); row++) {
      for (int col = 0; col < modelState.getBoardSize(); col++) {

        if (this.modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          str.append("_ ");
        }
        if (this.modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          str.append("0 ");
        }
        if (this.modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Invalid
                && col < modelState.getBoardSize() / 2 - 1) {
          str.append("  ");
        }
      }
      str.deleteCharAt(str.length() - 1);

      if (row < modelState.getBoardSize() - 1) {
        str.append("\n");
      }
    }
    return str.toString();
  }


}




