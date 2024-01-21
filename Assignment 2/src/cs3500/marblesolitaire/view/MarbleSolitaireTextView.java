package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * MarbleSolitaireTextView class represents a solitaire board game as text
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState modelState;
  private Appendable appendObject;


  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);
  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendObject) {
    this.modelState = modelState;
    this.appendObject = appendObject;

    if (modelState == null || appendObject == null) {
      throw new IllegalArgumentException("Error: The provided model state or appendable object is null");
    }
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
      str.deleteCharAt(str.length()-1);

      if (row < modelState.getBoardSize() - 1) {
        str.append("\n");
      }
    }
    return str.toString();
  }

  /**
   * to transmit the state of the marble solitaire board
   * @throws IOException if the board is null
   */
  @Override
  public void renderBoard() throws IOException {
    this.appendObject.append(this.toString());

    if (this.toString() == null) {
      throw new IOException("Error: the transmission failed.");
    }
  }

  /**
   * to show an arbitrary message
   * @param message the message to be transmitted
   * @throws IOException if the message is null
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.appendObject.append(message);

    if (message == null) {
      throw new IOException("Error: the transmission failed.");
    }
  }
}








