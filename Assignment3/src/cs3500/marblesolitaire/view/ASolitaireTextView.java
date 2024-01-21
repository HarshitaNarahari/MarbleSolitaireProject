package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * To represent an abstract class for solitaire text view
 */
public abstract class ASolitaireTextView implements MarbleSolitaireView {

  protected MarbleSolitaireModelState modelState;
  protected Appendable appendObject;

  /**
   * the constructor
   * @param modelState represents the marble solitaire model state
   */
  public ASolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);
  }

  /**
   * the constructor with appendable
   * @param modelState represents the marble solitaire model state
   * @param appendObject object to be appended on
   */
  public ASolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendObject) {
    this.modelState = modelState;
    this.appendObject = appendObject;

    if (modelState == null || appendObject == null) {
      throw new IllegalArgumentException("Error: The provided model state or appendable object is null");
    }
  }


  /**
   * Transforming the board into a String
   * @return a string view of the solitaire board
   */
  public abstract String toString();

  /**
   * to transmit the state of the marble solitaire board
   *
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
   *
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
